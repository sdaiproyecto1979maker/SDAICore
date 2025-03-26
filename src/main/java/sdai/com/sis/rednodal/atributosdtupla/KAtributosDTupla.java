package sdai.com.sis.rednodal.atributosdtupla;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class KAtributosDTupla {

	public abstract class KAtributoDTupla {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBATRTUPLA";

		public abstract class AtributosDEntidad {

			/** Identificador de atributo de tupla */
			public static final String IDATRTUPLA = "IDATRTUPLA";
		}
	}

	public abstract class KSituacionDAtributoDTupla {

		/** Nombre de la tabla */
		public static final String NOMBRTABLA = "TBSITATRTU";

		public abstract class AtributosDEntidad {

			/** Identificador de situación de atributo de tupla */
			public static final String IDSITATRTU = "IDSITATRTU";
			/** Identificador de situación de atributo de tupla */
			public static final String VALORATRIB = "VALORATRIB";
		}
	}

}
