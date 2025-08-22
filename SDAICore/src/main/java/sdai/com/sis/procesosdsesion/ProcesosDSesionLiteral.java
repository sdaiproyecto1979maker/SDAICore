package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ProcesosDSesionLiteral extends AnnotationLiteral<ProcesosDSesion> implements ProcesosDSesion {

    private final String value;

    private ProcesosDSesionLiteral(String value) {
        this.value = value;
    }

    public static ProcesosDSesionLiteral of(String value) {
        return new ProcesosDSesionLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
