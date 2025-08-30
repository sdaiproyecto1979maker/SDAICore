package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImpl;
import sdai.com.sis.utilidades.EstructuraDatos;
import sdai.com.sis.utilidades.Util;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
public class GestorDProcesos implements GestorDProcesosLocal, Serializable {

    @Inject
    @Any
    private Instance<ProcesoDSesionLocal> instancias;
    private ProcesoDSesionLocal procesoDSesionLocal;
    private final Map<String, EstructuraDatos> almacenDEstructuras;

    public GestorDProcesos() {
        this.almacenDEstructuras = new HashMap<>();
    }

    @Override
    public void iniciar(String codigoDProceso) throws ErrorGeneral {
        if (this.procesoDSesionLocal != null) {
            generarEstructurasTemporales();
            destroyProcesoDSesion();
        }
        ProcesoDSesionImpl procesoDSesionImpl = ProcesoDSesionImpl.getInstancia(codigoDProceso);
        String codigoDQualifer = procesoDSesionImpl.getCodigoDQualifer();
        if (Util.isCadenaVacia(codigoDQualifer)) {
            codigoDQualifer = KProcesosDSesion.ProcesosDSesion.DFPROCSESI;
        }
        ProcesosDSesionLiteral procesosDSesionLiteral = ProcesosDSesionLiteral.of(codigoDQualifer);
        Instance<ProcesoDSesionLocal> instancia = this.instancias.select(procesosDSesionLiteral);
        this.procesoDSesionLocal = instancia.get();
        this.procesoDSesionLocal.setProcesoDSesionImplLocal(procesoDSesionImpl);
        this.procesoDSesionLocal.iniciar();
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

    public void destroyProcesoDSesion() {
        String codigoDQualifer = this.procesoDSesionLocal.getCodigoDQualifer();
        if (Util.isCadenaVacia(codigoDQualifer)) {
            codigoDQualifer = KProcesosDSesion.ProcesosDSesion.DFPROCSESI;
        }
        ProcesosDSesionLiteral literal = ProcesosDSesionLiteral.of(codigoDQualifer);
        Instance<ProcesoDSesionLocal> instancia = this.instancias.select(literal);
        instancia.destroy(this.procesoDSesionLocal);
    }

}
