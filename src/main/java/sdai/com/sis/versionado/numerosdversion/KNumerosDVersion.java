package sdai.com.sis.versionado.numerosdversion;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KNumerosDVersion {

	/** Nombre de la tabla */
	public static final String NOMBRTABLA = "TBNUMEVERS";

	public abstract class AtributosDEntidad {

		/** Identificador de número de versión */
		public static final String IDNUMEVERS = "IDNUMEVERS";
		/** Situación de número de versión */
		public static final String NUMERSITUA = "NUMERSITUA";
		/** Versión de release */
		public static final String VERRELEASE = "VERRELEASE";
		/** Versión de feature */
		public static final String VERFEATURE = "VERFEATURE";
		/** Versión de fix */
		public static final String VERSIONFIX = "VERSIONFIX";
		/** Versión de hotfix */
		public static final String VERDHOTFIX = "VERDHOTFIX";
		/** Indicador de release */
		public static final String SWVRELEASE = "SWVRELEASE";
		/** Indicador de deprecated */
		public static final String SWDEPRECAT = "SWDEPRECAT";
	}

	public abstract class NamedQueries {

		/**
		 * Obtiene un número de versión por el número de versión y el tipo de versión de
		 * proyecto
		 */
		public static final String SNUVRS0000 = "SNUVRS0000";
	}

}
