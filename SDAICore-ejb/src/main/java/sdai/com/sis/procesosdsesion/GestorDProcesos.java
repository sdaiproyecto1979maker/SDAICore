package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImpl;
import sdai.com.sis.rednodal.GeneradorDElementos;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
public class GestorDProcesos implements GestorDProcesosLocal, Serializable {

    @Inject
    private GeneradorDElementos generadorDElementos;
    private ProcesoDSesionLocal procesoDSesionLocal;
    private final Map<String, EstructuraDatos> almacenDEstructuras;

    public GestorDProcesos() {
        this.almacenDEstructuras = new HashMap<>();
    }

    @Override
    public void iniciar(String codigoDProceso) throws ErrorGeneral {
        if (this.procesoDSesionLocal != null) {
            generarEstructurasTemporales();
            //this.generadorDProcesosDSesion.deleteProcesoDSesion(this.procesoDSesionLocal);
        }
        this.procesoDSesionLocal = (ProcesoDSesionLocal) this.generadorDElementos.getElementoDRedLocal("PROCSESION", ProcesoDSesionImpl.class, "CODIGPROCE", codigoDProceso);
    }

    @Override
    public void procesarAccion(String codigoDAccion) throws ErrorGeneral {
        this.procesoDSesionLocal.realizarValidaciones();
        DataSwapLocal dataSwapLocal = this.procesoDSesionLocal.getDataSwapLocal();
        dataSwapLocal.generateDataSwap();
        this.procesoDSesionLocal.procesarAccion(codigoDAccion);
    }

    private void generarEstructurasTemporales() {
        DataSwapLocal dataSwapLocal = this.procesoDSesionLocal.getDataSwapLocal();
        List<EstructuraDatos> lista = dataSwapLocal.getEstructurasTemporales();
        for (EstructuraDatos eDatos : lista) {
            String nombreEstructura = eDatos.getNombreDEstructura();
            this.almacenDEstructuras.put(nombreEstructura, eDatos);
        }
    }

    @Override
    public ProcesoDSesionLocal getProcesoDSesionLocal() {
        return procesoDSesionLocal;
    }

}
