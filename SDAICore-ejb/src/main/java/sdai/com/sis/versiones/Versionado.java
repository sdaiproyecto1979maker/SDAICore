package sdai.com.sis.versiones;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.sistema.DirectoriosDSistema;
import sdai.com.sis.sistema.SdaiCFG;
import sdai.com.sis.versionado.VersionadoLocal;
import sdai.com.sis.versiones.accesoadatos.ADVersionado;
import sdai.com.sis.versiones.accesoadatos.Version;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class Versionado implements VersionadoLocal {

    private static final String ENTORNOCOR = "ENTORNOCOR";
    private static final String ENTORNOFMW = "ENTORNOFMW";
    private static final String ENTORNOCTM = "ENTORNOCTM";

    @Inject
    private SdaiCFG sdaiCFG;
    @Inject
    private ADVersionado adVersionado;
    @Inject
    private GeneradorCFG generadorCFG;
    @Inject
    private DirectoriosDSistema directoriosDSistema;
    @Inject
    private ComparadorCFG comparadorCFG;

    @Override
    public void generarCFG() throws ErrorGeneral {
        String numeroDVersion = this.sdaiCFG.getVersionDCore();
        Version version = this.adVersionado.getVersion(ENTORNOCOR);
        if (version == null) {
            String fileVoid = this.generadorCFG.getFileVoid();
            byte[] bytes = fileVoid.getBytes();
            this.adVersionado.persistVersion(numeroDVersion, ENTORNOCOR, bytes);
        } else {
            if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                String fileVersion = this.generadorCFG.getFileVersion(ENTORNOCOR);
                byte[] bytes = fileVersion.getBytes();
                version.setNumeroDVersion(numeroDVersion);
                version.setFicheroDVersion(bytes);
                this.adVersionado.mergeVersion(version);
            }
        }
        if (this.sdaiCFG.isAppFramework()) {
            numeroDVersion = this.sdaiCFG.getVersionDFramework();
            version = this.adVersionado.getVersion(ENTORNOFMW);
            if (version == null) {
                String fileVoid = this.generadorCFG.getFileVoid();
                byte[] bytes = fileVoid.getBytes();
                this.adVersionado.persistVersion(numeroDVersion, ENTORNOFMW, bytes);
            } else {
                if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                    String fileVersion = this.generadorCFG.getFileVersion(ENTORNOFMW);
                    byte[] bytes = fileVersion.getBytes();
                    version.setNumeroDVersion(numeroDVersion);
                    version.setFicheroDVersion(bytes);
                    this.adVersionado.mergeVersion(version);
                }
            }
        }
        numeroDVersion = this.sdaiCFG.getVersionDCustom();
        version = this.adVersionado.getVersion(ENTORNOCTM);
        if (version == null) {
            String fileVoid = this.generadorCFG.getFileVoid();
            byte[] bytes = fileVoid.getBytes();
            this.adVersionado.persistVersion(numeroDVersion, ENTORNOCTM, bytes);
        } else {
            if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                String fileVersion = this.generadorCFG.getFileVersion(ENTORNOCTM);
                byte[] bytes = fileVersion.getBytes();
                version.setNumeroDVersion(numeroDVersion);
                version.setFicheroDVersion(bytes);
                this.adVersionado.mergeVersion(version);
            }
        }
    }

    @Override
    public void generarComparacion() throws ErrorGeneral {
        try {
            Version version = this.adVersionado.getVersion(ENTORNOCOR);
            DocumentoXML documentoXML = getFicheroOrigen(version);
            this.comparadorCFG.compararCFG(documentoXML, ENTORNOCOR);
            if (this.sdaiCFG.isAppFramework()) {
                version = this.adVersionado.getVersion(ENTORNOFMW);
                documentoXML = getFicheroOrigen(version);
                this.comparadorCFG.compararCFG(documentoXML, ENTORNOFMW);
            }
            version = this.adVersionado.getVersion(ENTORNOCTM);
            documentoXML = getFicheroOrigen(version);
            this.comparadorCFG.compararCFG(documentoXML, ENTORNOCTM);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Versionado", ex.getMessage());
            throw errorGeneral;
        }
    }

    private DocumentoXML getFicheroOrigen(Version version) throws IOException, ErrorGeneral {
        byte[] ficheroDVersion = version.getFicheroDVersion();
        Path directorio = this.directoriosDSistema.getDirectorioDVersiones();
        Path fileOrigen = Paths.get(directorio.toAbsolutePath().toString(), version.getNumeroDVersion().concat(".xml"));
        Files.write(fileOrigen, ficheroDVersion);
        InputStream inputStream = new FileInputStream(fileOrigen.toFile());
        return new DocumentoXML(inputStream);
    }

}
