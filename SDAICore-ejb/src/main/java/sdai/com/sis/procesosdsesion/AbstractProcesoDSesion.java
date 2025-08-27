package sdai.com.sis.procesosdsesion;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import java.io.Serializable;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.rednodal.DataSwapImpl;
import sdai.com.sis.dataswaps.rednodal.DataSwapsUtil;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.rednodal.DataSwapDProcesoImpl;
import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImplLocal;
import sdai.com.sis.rednodal.ElementoDRedImplLocal;
import sdai.com.sis.rednodal.GeneradorDElementos;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractProcesoDSesion implements ProcesoDSesionLocal, Serializable {

    private ProcesoDSesionImplLocal procesoDSesionImplLocal;
    private DataSwapLocal dataSwapLocal;
    @Inject
    private GeneradorDElementos generadorDElementos;
    @Inject
    private DataSwapsUtil dataSwapsUtil;

    @Override
    public void setElementoDRedImplLocal(ElementoDRedImplLocal elementoDRedImplLocal) {
        this.procesoDSesionImplLocal = (ProcesoDSesionImplLocal) elementoDRedImplLocal;
    }

    @Override
    public void iniciar() throws ErrorGeneral {
        String codigoDProceso = getCodigoDProceso();
        DataSwapDProcesoImpl dataSwapDProcesoImpl = (DataSwapDProcesoImpl) this.generadorDElementos.getElementoDRedImplLocal(DataSwapDProcesoImpl.CODIGONODO, DataSwapDProcesoImpl.class, DataSwapDProcesoImpl.CODIGPROCE, codigoDProceso);
        String codigoDDataSwap = dataSwapDProcesoImpl.getCodigoDDataSwap();
        this.dataSwapLocal = (DataSwapLocal) this.generadorDElementos.getElementoDRedLocal(DataSwapImpl.CODIGONODO, DataSwapImpl.class, DataSwapImpl.CDDATASWAP, codigoDDataSwap);
        this.dataSwapLocal.setProcesoDSesionLocal(this);
    }

    @Override
    public void realizarValidaciones() throws ErrorGeneral {
        DSEntidadLocal[] entidades = this.dataSwapLocal.getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            entidad.validarIntegridad();
        }
    }

    @Override
    public void procesarAccion(String codigoDAccion) throws ErrorGeneral {
        /*AccionDSistemaLocal accionDSistemaLocal = this.generadorAccionesDProceso.getAccionDSistemaLocal(getCodigoDProceso(), codigoDAccion);
        accionDSistemaLocal.procesarAccion(this);*/
    }

    @Override
    public DataSwapLocal getDataSwapLocal() {
        return this.dataSwapLocal;
    }

    @Override
    public String getCodigoDProceso() {
        return this.procesoDSesionImplLocal.getCodigoDProceso();
    }

    @Override
    public String getPaginaDProceso() {
        return this.procesoDSesionImplLocal.getPaginaDProceso();
    }

    @Override
    public ProcesoDSesionImplLocal getProcesoDSesionImplLocal() {
        return procesoDSesionImplLocal;
    }

    @PreDestroy
    public void destroy() {
        this.dataSwapsUtil.destroyDataSwapLocal(this.dataSwapLocal);
    }

}
