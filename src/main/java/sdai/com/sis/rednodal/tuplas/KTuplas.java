package sdai.com.sis.rednodal.tuplas;

/**
 * @date 14/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class KTuplas {

	public abstract class KTupla {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBENTTUPLA";

		public abstract class AtributosDEntidad {

			/** Identificador de tupla */
			public static final String IDENTTUPLA = "IDENTTUPLA";
			/** Código de tupla */
			public static final String CODIGTUPLA = "CODIGTUPLA";
		}

		public abstract class NamedQueries {

			/** Obtiene una tupla por su código de tupla */
			public static final String STUPLA0000 = "STUPLA0000";
		}
	}

	public abstract class KSituacionDTupla {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBSITTUPLA";

		public abstract class AtributosDEntidad {

			/** Identificador de situación de tupla */
			public static final String IDSITTUPLA = "IDSITTUPLA";
			/** Descripción de tupla */
			public static final String DESCRTUPLA = "DESCRTUPLA";
		}
	}

}
