package sdai.com.sis.sistema;

import jakarta.ejb.Local;
import java.nio.file.Path;

/**
 * @date 26/05/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface DirectoriosDSistemaLocal {

    Path getDirectorioDConfiguracion();

    Path getDirectorioDVersiones();

    Path getDirectorioDLogs();

    Path getDirectorioTemporal();

}
