package sdai.com.sis.dataswaps.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.DataSwapsLiteral;
import sdai.com.sis.dataswaps.KDataSwaps;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.dsentidades.rednodal.DSEntidadesUtil;
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
public class DataSwapsUtil {

    @Inject
    private NodosDRedLocal nodosDRedLocal;
    @Inject
    @Any
    private Instance<DataSwapLocal> instancias;
    @Inject
    private DSEntidadesUtil dSEntidadesUtil;

    public DataSwapLocal getDataSwapLocal(String codigoDDataSwap) {
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(DataSwapImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(DataSwapImpl.CDDATASWAP, codigoDDataSwap);
        DataSwapImpl dataSwapImpl = new DataSwapImpl(tuplaDNodoLocal);
        return createDataSwapLocal(dataSwapImpl);
    }

    private DataSwapLocal createDataSwapLocal(DataSwapImpl dataSwapImpl) {
        String qualifer = dataSwapImpl.getCodigoDQualifer();
        if (Util.isCadenaVacia(qualifer)) {
            qualifer = KDataSwaps.DataSwaps.DFDATASWAP;
        }
        DataSwapsLiteral dataSwapsLiteral = DataSwapsLiteral.of(qualifer);
        Instance<DataSwapLocal> instancia = this.instancias.select(dataSwapsLiteral);
        DataSwapLocal dataSwapLocal = instancia.get();
        dataSwapLocal.setDataSwapImplLocal(dataSwapImpl);
        dataSwapLocal.iniciar();
        return dataSwapLocal;
    }

    public DSEntidadLocal[] getEntidadesDDataSwap(String codigoDDataSwap) {
        List<DSEntidadLocal> lista = new ArrayList<>();
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal("ENTIDASWAP");
        TuplaDNodoLocal[] tuplasDNodo = nodoDRedLocal.getTuplasDNodo("CDDATASWAP", codigoDDataSwap);
        for (TuplaDNodoLocal tuplaDNodoLocal : tuplasDNodo) {
            String codigoDEntidad = tuplaDNodoLocal.getDatoDTupla("CODIENTITY").getValorDAtributo();
            DSEntidadLocal dSEntidadLocal = this.dSEntidadesUtil.getDSEntidadLocal(codigoDEntidad);
            lista.add(dSEntidadLocal);
        }
        return lista.toArray(DSEntidadLocal[]::new);
    }

}
