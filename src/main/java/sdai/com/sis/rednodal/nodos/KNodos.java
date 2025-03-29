package sdai.com.sis.rednodal.nodos;

/**
 * @date 14/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class KNodos {

	public abstract class KNodo {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBENTINODO";

		public abstract class AtributosDEntidad {

			/** Identificador de nodo */
			public static final String IDENTINODO = "IDENTINODO";
			/** Código de nodo */
			public static final String CODIGONODO = "CODIGONODO";
		}

		public abstract class NamedQueries {

			/** Obtiene un nodo por su código de nodo */
			public static final String SNODOS0000 = "SNODOS0000";
			/** Obtiene todos los nodos */
			public static final String SNODOS0001 = "SNODOS0001";
		}
	}

	public abstract class KSituacionDNodo {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBSITDNODO";

		public abstract class AtributosDEntidad {

			/** Identificador de situación de nodo */
			public static final String IDSITDNODO = "IDSITDNODO";
			/** Descripción de nodo */
			public static final String DESCRDNODO = "DESCRDNODO";
		}
	}

}
