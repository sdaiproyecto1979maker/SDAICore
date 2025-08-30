package sdai.com.sis.sistemasdreglas;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DSResponsesLiteral extends AnnotationLiteral<DSResponses> implements DSResponses {

    private final String value;

    private DSResponsesLiteral(String value) {
        this.value = value;
    }

    public static DSResponsesLiteral of(String value) {
        return new DSResponsesLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
