package sdai.com.sis.rednodal.atributosdnodo;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KAtributosDNodo {

	public abstract class KAtributoDNodo {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBATRDNODO";

		public abstract class AtributosDEntidad {

			/** Identificador de atributo de nodo */
			public static final String IDATRDNODO = "IDATRDNODO";
		}

		public abstract class NamedQueries {

			/** Obtiene todos los atributos de nodo */
			public static final String SATRNO0000 = "SATRNO0000";
			/** Obtiene todos los atributos de nodo de un nodo */
			public static final String SATRNO0001 = "SATRNO0001";
			/**
			 * Obtiene todos los atributos de nodo por su código de nodo y su código de dato
			 * de sistema
			 */
			public static final String SATRNO0002 = "SATRNO0002";
		}
	}

	public abstract class KSituacionDAtributoDNodo {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBSITATRNO";

		public abstract class AtributosDEntidad {

			/** Identificador de situación de atributo de nodo */
			public static final String IDSITATRNO = "IDSITATRNO";
		}
	}

}
