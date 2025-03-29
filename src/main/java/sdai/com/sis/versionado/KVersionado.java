package sdai.com.sis.versionado;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class KVersionado {

	public abstract class KElementosCFG {

		/** Código de elemento de CFG */
		public static final String CODELEMENT = "CODELEMENT";
		/** Fabrica de elemento de CFG */
		public static final String FBKELEMENT = "FBKELEMENT";
		/** Clase de acceso a datos */
		public static final String CLSACDATOS = "CLSACDATOS";
	}

	public abstract class KProyectosDAplicacion {

		/** Código de proyecto de aplicación */
		public static final String CODPROYECT = "CODPROYECT";
		/** Package de proyecto de aplicación */
		public static final String PKGPROYECT = "PKGPROYECT";
		/** Número de versión */
		public static final String NUMVERSION = "NUMVERSION";

		public abstract class KProyectoDAplicacion {

			/** Nombre de la tabla */
			public static final String NOMBRTABLA = "TBPYTAPLIC";

			public abstract class AtributosDEntidad {

				/** Identificador de proyecto de aplicación */
				public static final String IDPYTAPLIC = "IDPYTAPLIC";
				/** Código de proyecto de aplicación */
				public static final String CODPROYECT = KProyectosDAplicacion.CODPROYECT;
			}

			public abstract class NamedQueries {

				/** Obtiene un proyecto de aplicación por su código de proyecto */
				public static final String SPRAPL0000 = "SPRAPL0000";
			}
		}
	}

	public abstract class KNumerosDVersion {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBNUMEVERS";

		public abstract class AtributosDEntidad {

			/** Identificador de número de versión */
			public static final String IDNUMEVERS = "IDNUMEVERS";
			/** Número de situación */
			public static final String NUMERSITUA = "NUMERSITUA";
			/** Versión de release */
			public static final String VERRELEASE = "VERRELEASE";
			/** Versión de feature */
			public static final String VERFEATURE = "VERFEATURE";
			/** Versión de fix */
			public static final String VERSIONFIX = "VERSIONFIX";
			/** Versión de hotfix */
			public static final String VERSHOTFIX = "VERSHOTFIX";
			/** Indicador de release */
			public static final String SWVRELEASE = "SWVRELEASE";
			/** Indicador de release */
			public static final String SWVINSTALL = "SWVINSTALL";
		}

		public abstract class NamedQueries {

			/**
			 * Obtiene un número de versión por sus números y el código del proyecto de
			 * aplicación
			 */
			public static final String SNUVER0000 = "SNUVER0000";
			/**
			 * Obtiene un número de versión por su identificador
			 */
			public static final String SNUVER0001 = "SNUVER0001";
		}
	}

}
