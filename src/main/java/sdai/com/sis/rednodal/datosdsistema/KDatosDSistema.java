package sdai.com.sis.rednodal.datosdsistema;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KDatosDSistema {

	public abstract class KDatoDSistema {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBDATSISTE";

		public abstract class AtributosDEntidad {

			/** Identificador de dato de sistema */
			public static final String IDDATSISTE = "IDDATSISTE";
			/** Código de dato de sistema */
			public static final String CODIGODATO = "CODIGODATO";
		}

		public abstract class NamedQueries {

			/** Obtiene un dato de sistema por su código de dato */
			public static final String SDASIS0000 = "SDASIS0000";
			/** Obtiene todos los datos de sistema */
			public static final String SDASIS0001 = "SDASIS0001";
		}
	}

	public abstract class KSituacionDDatoDSistema {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBSITDASIS";

		public abstract class AtributosDEntidad {

			/** Identificador de situación de dato de sistema */
			public static final String IDSITDASIS = "IDSITDASIS";
			/** Descripción de dato de sistema */
			public static final String DESCRDDATO = "DESCRDDATO";
		}
	}

}
