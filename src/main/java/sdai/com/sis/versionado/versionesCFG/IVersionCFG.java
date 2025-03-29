package sdai.com.sis.versionado.versionesCFG;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public interface IVersionCFG {

	void createNumerosDVersion() throws Exception;

	void establecerVersionesInstalables();

	void versionar() throws Exception;

	void loadVersionEnCurso() throws Exception;
}
