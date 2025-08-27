package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class DirectoriosDSistema implements DirectoriosDSistemaLocal {

    private static final String DIREPRINCI = "DIREPRINCI";
    private static final String DIREAPLICA = "DIREAPLICA";
    private static final String DIRECTDCFG = "DIRECTDCFG";
    private static final String DIRECTVERS = "DIRECTVERS";
    private static final String DIRECTLOGS = "DIRECTLOGS";
    private static final String DIRECTTEMP = "DIRECTTEMP";

    @Inject
    private AtributosDSistemaLocal atributosDSistemaLocal;
    private final ConcurrentMap<String, Path> almacenDDirectorios;

    public DirectoriosDSistema() {
        this.almacenDDirectorios = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void init() {
        try {
            loadDirectorioPrincipal();
            loadDirectorioDAplicacion();
            loadDirectorioDConfiguracion();
            loadDirectorioDVersiones();
            loadDirectorioLogs();
            loadDirectorioTemporal();
        } catch (ErrorGeneral ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadDirectorioPrincipal() throws ErrorGeneral {
        try {
            Path home = getHome();
            ServletContext servletContext = FacesUtil.getServletContext();
            String directorioPrincipal = servletContext.getInitParameter(DIREPRINCI);
            Path path = Paths.get(home.toAbsolutePath().toString(), directorioPrincipal);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIREPRINCI, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private void loadDirectorioDAplicacion() throws ErrorGeneral {
        try {
            Path home = getDirectorioPrincipal();
            String directorioPrincipal = this.atributosDSistemaLocal.getValorString(KSistema.AtributosDSistema.NOMBRSISTE);
            Path path = Paths.get(home.toAbsolutePath().toString(), directorioPrincipal);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIREAPLICA, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private void loadDirectorioDConfiguracion() throws ErrorGeneral {
        try {
            Path home = getDirectorioDAplicacion();
            Path path = Paths.get(home.toAbsolutePath().toString(), "Configuracion");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIRECTDCFG, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private void loadDirectorioDVersiones() throws ErrorGeneral {
        try {
            Path home = getDirectorioDAplicacion();
            Path path = Paths.get(home.toAbsolutePath().toString(), "Versiones");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIRECTVERS, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private void loadDirectorioLogs() throws ErrorGeneral {
        try {
            Path home = getDirectorioDAplicacion();
            Path path = Paths.get(home.toAbsolutePath().toString(), "Logs");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIRECTLOGS, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private void loadDirectorioTemporal() throws ErrorGeneral {
        try {
            Path home = getDirectorioDAplicacion();
            Path path = Paths.get(home.toAbsolutePath().toString(), "Temporal");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            this.almacenDDirectorios.put(DIRECTTEMP, path);
        } catch (IOException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Directorios de sistema", ex.getMessage());
            throw errorGeneral;
        }
    }

    private Path getHome() {
        String stringPath = System.getProperty("user.home");
        Path path = Paths.get(stringPath);
        return path;
    }

    private Path getDirectorioPrincipal() {
        Path path = this.almacenDDirectorios.get(DIREPRINCI);
        return path;
    }

    private Path getDirectorioDAplicacion() {
        Path path = this.almacenDDirectorios.get(DIREAPLICA);
        return path;
    }

    @Override
    public Path getDirectorioDConfiguracion() {
        Path path = this.almacenDDirectorios.get(DIRECTDCFG);
        return path;
    }

    @Override
    public Path getDirectorioDVersiones() {
        Path path = this.almacenDDirectorios.get(DIRECTVERS);
        return path;
    }

    @Override
    public Path getDirectorioDLogs() {
        Path path = this.almacenDDirectorios.get(DIRECTLOGS);
        return path;
    }

    @Override
    public Path getDirectorioTemporal() {
        Path path = this.almacenDDirectorios.get(DIRECTTEMP);
        return path;
    }

}
