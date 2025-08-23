package sdai.com.sis.procesosdsesion;

import sdai.com.sis.procesosdsesion.rednodal.ProcesosDSesionUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
public class GestorDProcesos implements GestorDProcesosLocal, Serializable {

    @Inject
    private ProcesosDSesionUtil procesosDSesionUtil;
    private ProcesoDSesionLocal procesoDSesionLocal;
    private final Map<String, EstructuraDatos> almacenDEstructuras;

    public GestorDProcesos() {
        this.almacenDEstructuras = new HashMap<>();
    }

    @Override
    public void iniciar(String codigoDProceso) {
        if (this.procesoDSesionLocal != null) {
            //generarEstructurasTemporales();
            //eliminarProcesoDSesion();
        }
        this.procesoDSesionLocal = this.procesosDSesionUtil.getProcesoDSesionLocal(codigoDProceso);
    }

    /*
    @Override
    public void procesarAccion(String codigoDAccion) throws ErrorGeneral {
        this.procesoDSesionLocal.realizarValidaciones();
        DataSwapLocal dataSwapLocal = this.procesoDSesionLocal.getDataSwapLocal();
        dataSwapLocal.generateDataSwap();
        AccionDSistemaLocal accionDSistemaLocal = getAccionDSistema(codigoDAccion);
        accionDSistemaLocal.procesarAccion(dataSwapLocal);
    }
     */

 /*
    private AccionDSistemaLocal getAccionDSistema(String codigoDAccion) {
        AccionesDSistemaLiteral accionesDSistemaLiteral = AccionesDSistemaLiteral.of(codigoDAccion);
        Instance<AccionDSistemaLocal> accion = this.acciones.select(accionesDSistemaLiteral);
        AccionDSistemaLocal accionDSistemaLocal = accion.get();
        return accionDSistemaLocal;
    }

    private void generarEstructurasTemporales() {
        DataSwapLocal dataSwapLocal = this.procesoDSesionLocal.getDataSwapLocal();
        List<EstructuraDatos> lista = dataSwapLocal.getEstructurasTemporales();
        for (EstructuraDatos eDatos : lista) {
            String nombreEstructura = eDatos.getNombreDEstructura();
            this.almacenDEstructuras.put(nombreEstructura, eDatos);
        }
    }

    private void eliminarProcesoDSesion() {
        String codigoDProceso = this.procesoDSesionLocal.getCodigoDProceso();
        ProcesosDSesionLiteral procesosDSesionLiteral = ProcesosDSesionLiteral.of(codigoDProceso);
        Instance<ProcesoDSesionLocal> instance = this.instances.select(procesosDSesionLiteral);
        instance.destroy(this.procesoDSesionLocal);
    }
     */
    @Override
    public ProcesoDSesionLocal getProcesoDSesionLocal() {
        return procesoDSesionLocal;
    }

}
