package sdai.com.sis.accionesdsistema;

import jakarta.inject.Inject;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.utilidades.Util;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractAccionDSistema implements AccionDSistemaLocal {

    private AccionDSistemaImplLocal accionDSistemaImplLocal;
    @Inject
    private GestorDProcesosLocal gestorDProcesosLocal;

    @Override
    public void setAccionDSistemaImplLocal(AccionDSistemaImplLocal accionDSistemaImplLocal) {
        this.accionDSistemaImplLocal = accionDSistemaImplLocal;
    }

    @Override
    public void procesarAccion(ProcesoDSesionLocal procesoDSesionLocal) throws ErrorGeneral {
        DataSwapLocal dataSwapLocal = procesoDSesionLocal.getDataSwapLocal();
        procesar(dataSwapLocal);
        crearNewProceso();
    }

    protected abstract void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral;

    private void crearNewProceso() throws ErrorGeneral {
        String codigoDProceso = this.accionDSistemaImplLocal.getProcesoDestino();
        if (Util.isCadenaNoVacia(codigoDProceso)) {
            this.gestorDProcesosLocal.iniciar(codigoDProceso);
        }
    }

}
