package sdai.com.sis.procesosdsesion.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.rednodal.DataSwapsUtil;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.procesosdsesion.ProcesosDSesionLiteral;
import sdai.com.sis.rednodal.NodoDRedLocal;
import sdai.com.sis.rednodal.NodosDRedLocal;
import sdai.com.sis.rednodal.TuplaDNodoLocal;
import sdai.com.sis.utilidades.Util;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class ProcesosDSesionUtil {

    @Inject
    private NodosDRedLocal nodosDRedLocal;
    @Inject
    @Any
    private Instance<ProcesoDSesionLocal> instancias;
    @Inject
    private DataSwapsUtil dataSwapsUtil;

    public ProcesoDSesionLocal getProcesoDSesionLocal(String codigoDProceso) {
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(ProcesoDSesionImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(ProcesoDSesionImpl.CODIGPROCE, codigoDProceso);
        ProcesoDSesionImpl procesoDSesion = new ProcesoDSesionImpl(tuplaDNodoLocal);
        return createProcesoDSesionLocal(procesoDSesion);
    }

    private ProcesoDSesionLocal createProcesoDSesionLocal(ProcesoDSesionImpl procesoDSesion) {
        String qualifer = procesoDSesion.getCodigoDQualifer();
        if (Util.isCadenaVacia(qualifer)) {
            qualifer = KProcesosDSesion.ProcesosDSesion.DFPROCSESI;
        }
        ProcesosDSesionLiteral procesosDSesionLiteral = ProcesosDSesionLiteral.of(qualifer);
        Instance<ProcesoDSesionLocal> instancia = this.instancias.select(procesosDSesionLiteral);
        ProcesoDSesionLocal procesoDSesionLocal = instancia.get();
        procesoDSesionLocal.setProcesoDSesion(procesoDSesion);
        procesoDSesionLocal.iniciar();
        return procesoDSesionLocal;
    }

    public DataSwapLocal getDataSwapLocal(String codigoDProceso) {
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal("DASWAPROCE");
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(ProcesoDSesionImpl.CODIGPROCE, codigoDProceso);
        String codigoDDataSwap = tuplaDNodoLocal.getDatoDTupla("CDDATASWAP").getValorDAtributo();
        return this.dataSwapsUtil.getDataSwapLocal(codigoDDataSwap);
    }

}
