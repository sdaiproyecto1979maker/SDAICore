package sdai.com.sis.dsentidades;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DSEntidadesLiteral extends AnnotationLiteral<DSEntidades> implements DSEntidades {

    private final String value;

    private DSEntidadesLiteral(String value) {
        this.value = value;
    }

    public static DSEntidadesLiteral of(String value) {
        return new DSEntidadesLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
