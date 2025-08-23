package sdai.com.sis.procesosdsesion;

import jakarta.inject.Inject;
import java.io.Serializable;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.procesosdsesion.rednodal.ProcesosDSesionUtil;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractProcesoDSesion implements ProcesoDSesionLocal, Serializable {

    private ProcesoDSesionImplLocal procesoDSesionImplLocal;
    @Inject
    private ProcesosDSesionUtil procesosDSesionUtil;
    private DataSwapLocal dataSwapLocal;

    @Override
    public void setProcesoDSesion(ProcesoDSesionImplLocal procesoDSesion) {
        this.procesoDSesionImplLocal = procesoDSesion;
    }

    @Override
    public void iniciar() {
        String codigoDProceso = getCodigoDProceso();
        this.dataSwapLocal = this.procesosDSesionUtil.getDataSwapLocal(codigoDProceso);
        this.dataSwapLocal.setProcesoDSesionLocal(this);
    }

    @Override
    public String getCodigoDProceso() {
        return this.procesoDSesionImplLocal.getCodigoDProceso();
    }

    @Override
    public String getPaginaDProceso() {
        return this.procesoDSesionImplLocal.getPaginaDProceso();
    }

}
