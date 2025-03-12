package sdai.com.sis.versionado;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KVersionado {

	public abstract class KElementosCFG {

		/** Código de elemento de CFG */
		public static final String CODELEMENT = "CODELEMENT";
		/** Fabrica de elemento de CFG */
		public static final String FBKELEMENT = "FBKELEMENT";
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
		}
	}

}
