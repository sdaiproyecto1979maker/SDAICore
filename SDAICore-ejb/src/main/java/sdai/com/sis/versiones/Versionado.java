package sdai.com.sis.versiones;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
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
    private ThreadVersionado threadVersionado;
    private Boolean isThreadFinalizado;

    @PostConstruct
    public void init() {
        this.threadVersionado = new ThreadVersionado();
        this.isThreadFinalizado = Boolean.FALSE;
    }

    @Override
    public void generarCFG() throws ErrorGeneral {
        this.threadVersionado.start();
    }

    private void createVersionVoid(String numeroDVersion, String entornoAplicacion) {
        String fileVoid = this.generadorCFG.getFileVoid();
        byte[] bytes = fileVoid.getBytes();
        this.adVersionado.persistVersion(numeroDVersion, ENTORNOCOR, bytes);
    }

    private void createVersion(Version version, String numeroDVersion, String entornoAplicacion) throws ErrorGeneral {
        String fileVersion = this.generadorCFG.getFileVersion(entornoAplicacion);
        byte[] bytes = fileVersion.getBytes();
        version.setNumeroDVersion(numeroDVersion);
        version.setFicheroDVersion(bytes);
        this.adVersionado.mergeVersion(version);
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
        Files.deleteIfExists(fileOrigen);
        Files.write(fileOrigen, ficheroDVersion);
        InputStream inputStream = new FileInputStream(fileOrigen.toFile());
        return new DocumentoXML(inputStream);
    }

    @Override
    public Boolean getIsThreadFinalizado() {
        return isThreadFinalizado;
    }

    private class ThreadVersionado extends Thread {

        @Override
        public void run() {
            try {
                String numeroDVersion = sdaiCFG.getVersionDCore();
                Version version = adVersionado.getVersion(ENTORNOCOR);
                if (version == null) {
                    createVersionVoid(numeroDVersion, ENTORNOCOR);
                } else {
                    if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                        createVersion(version, numeroDVersion, ENTORNOCOR);
                    }
                }
                if (sdaiCFG.isAppFramework()) {
                    numeroDVersion = sdaiCFG.getVersionDFramework();
                    version = adVersionado.getVersion(ENTORNOFMW);
                    if (version == null) {
                        createVersionVoid(numeroDVersion, ENTORNOFMW);
                    } else {
                        if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                            createVersion(version, numeroDVersion, ENTORNOFMW);
                        }
                    }
                }
                numeroDVersion = sdaiCFG.getVersionDCustom();
                version = adVersionado.getVersion(ENTORNOCTM);
                if (version == null) {
                    createVersionVoid(numeroDVersion, ENTORNOCTM);
                } else {
                    if (!version.getNumeroDVersion().equals(numeroDVersion)) {
                        createVersion(version, numeroDVersion, ENTORNOCTM);
                    }
                }
                isThreadFinalizado = Boolean.TRUE;
            } catch (ErrorGeneral ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
