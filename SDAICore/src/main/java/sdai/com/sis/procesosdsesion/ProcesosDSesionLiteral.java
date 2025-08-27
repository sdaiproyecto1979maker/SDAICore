package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.util.AnnotationLiteral;
import sdai.com.sis.rednodal.ElementoDRedLocal;

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

    public static Instance<ElementoDRedLocal> getInstancia(Instance<ElementoDRedLocal> instancias, String codigoDQualifer) {
        ProcesosDSesionLiteral procesosDSesionLiteral = of(codigoDQualifer);
        Instance<ElementoDRedLocal> instancia = instancias.select(procesosDSesionLiteral);
        return instancia;
    }

    @Override
    public String value() {
        return this.value;
    }

}
