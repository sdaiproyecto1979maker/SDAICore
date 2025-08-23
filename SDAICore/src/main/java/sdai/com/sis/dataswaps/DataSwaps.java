package sdai.com.sis.dataswaps;

import jakarta.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE
})
public @interface DataSwaps {

    String value();

}
