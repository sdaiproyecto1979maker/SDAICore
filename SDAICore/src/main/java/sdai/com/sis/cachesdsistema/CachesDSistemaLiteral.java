package sdai.com.sis.cachesdsistema;

import jakarta.enterprise.util.AnnotationLiteral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class CachesDSistemaLiteral extends AnnotationLiteral<CachesDSistema> implements CachesDSistema {

    private final String value;

    private CachesDSistemaLiteral(String value) {
        this.value = value;
    }

    public static CachesDSistemaLiteral of(String value) {
        return new CachesDSistemaLiteral(value);
    }

    @Override
    public String value() {
        return this.value;
    }

}
