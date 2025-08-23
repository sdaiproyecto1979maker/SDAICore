package sdai.com.sis.accionesdsistema;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class AccionesDSistemaLiteral extends AnnotationLiteral<AccionesDSistema> implements AccionesDSistema {

    private final String value;

    private AccionesDSistemaLiteral(String value) {
        this.value = value;
    }

    public static AccionesDSistemaLiteral of(String value) {
        return new AccionesDSistemaLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
