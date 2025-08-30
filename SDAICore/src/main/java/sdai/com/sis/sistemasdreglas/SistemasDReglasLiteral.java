package sdai.com.sis.sistemasdreglas;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class SistemasDReglasLiteral extends AnnotationLiteral<SistemasDReglas> implements SistemasDReglas {

    private final String value;

    private SistemasDReglasLiteral(String value) {
        this.value = value;
    }

    public static SistemasDReglasLiteral of(String value) {
        return new SistemasDReglasLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
