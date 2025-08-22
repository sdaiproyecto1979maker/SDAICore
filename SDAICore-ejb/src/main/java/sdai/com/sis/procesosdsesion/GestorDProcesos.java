package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
public class GestorDProcesos implements GestorDProcesosLocal, Serializable {

    @Inject
    @Any
    private Instance<ProcesoDSesionLocal> instances;
    private ProcesoDSesionLocal procesoDSesionLocal;

    @Override
    public void iniciar(String codigoDProceso) {
        ProcesosDSesionLiteral procesosDSesionLiteral = ProcesosDSesionLiteral.of(codigoDProceso);
        Instance<ProcesoDSesionLocal> instance = this.instances.select(procesosDSesionLiteral);
        this.procesoDSesionLocal = instance.get();
    }

    @Override
    public ProcesoDSesionLocal getProcesoDSesionLocal() {
        return procesoDSesionLocal;
    }

}
