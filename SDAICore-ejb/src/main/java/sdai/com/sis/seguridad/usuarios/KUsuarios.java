package sdai.com.sis.seguridad.usuarios;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class KUsuarios {

    public static final String NOMBRTABLA = "TBDUSUARIO";
    public static final String CODIENTITY = "DSDUSUARIO";

    public final class AtributosDEntidad {

        public static final String IDDUSUARIO = "IDDUSUARIO";
        public static final String CODIGUSUAR = "CODIGUSUAR";
        public static final String SWUSACTIVO = "SWUSACTIVO";
        public static final String SWUSUBLOCK = "SWUSUBLOCK";
        public static final String FECHADALTA = "FECHADALTA";
        public static final String FECHADBAJA = "FECHADBAJA";
    }

    public final class NamedQueries {

        public static final String SUSUAR0000 = "SUSUAR0000";
    }

    public final class SecretosDUsuario {

        public static final String NOMBRTABLA = "TBSECUSUAR";

        public final class AtributosDEntidad {

            public static final String IDSECUSUAR = "IDSECUSUAR";
            public static final String PASSWUSUAR = "PASSWUSUAR";
            public static final String FECHACADUC = "FECHACADUC";
        }

    }

    public final class AtributosDUsuario {

        public static final String NOMBRTABLA = "TBATRUSUAR";

        public final class AtributosDEntidad {

            public static final String IDATRUSUAR = "IDATRUSUAR";
            public static final String NOMBRATRIB = "NOMBRATRIB";
            public static final String VALORATRIB = "VALORATRIB";
        }

        public final class AtributosUsuario {

            public static final String NMINTENTOS = "NMINTENTOS";
        }

    }

}
