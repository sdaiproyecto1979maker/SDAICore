package sdai.com.sis.reglasbasicas;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ReglasBasicasLiteral extends AnnotationLiteral<ReglasBasicas> implements ReglasBasicas {

    private final String value;

    private ReglasBasicasLiteral(String value) {
        this.value = value;
    }

    public static ReglasBasicasLiteral of(String value) {
        return new ReglasBasicasLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
