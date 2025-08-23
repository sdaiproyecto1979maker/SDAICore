package sdai.com.sis.dataswaps;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DataSwapsLiteral extends AnnotationLiteral<DataSwaps> implements DataSwaps {

    private final String value;

    private DataSwapsLiteral(String value) {
        this.value = value;
    }

    public static DataSwapsLiteral of(String value) {
        return new DataSwapsLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
