package sdai.com.sis.versionado.versionesdproyecto;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KVersionesDProyecto {

	/** Código de versión release */
	public static final String RELEASE = "RELEASE";
	/** Código de versión de core */
	public static final String VERSIDCORE = "VERSIDCORE";
	/** Código de versión de framework */
	public static final String VERSFRMWRK = "VERSFRMWRK";
	/** Código de versión custom */
	public static final String VERSCUSTOM = "VERSCUSTOM";

	public abstract class KVersionDProyecto {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBVERPROYT";

		public abstract class AtributosDEntidad {

			/** Identificador de versión de proyecto */
			public static final String IDVERPROYT = "IDVERPROYT";
			/** Tipo de versión de proyecto */
			public static final String TIPOVRSPRY = "TIPOVRSPRY";
		}

		public abstract class NamedQueries {

			/** Obtiene una versión de proyecto por su tipo de versión de proyecto */
			public static final String SVRPRY0000 = "SVRPRY0000";
		}
	}

}
