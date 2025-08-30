package sdai.com.sis.dataswaps;

import java.util.HashMap;
import java.util.Map;
import sdai.com.sis.sistemasdreglas.DSResponseLocal;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author sergio
 */
public final class ContextoDataSwap implements ContextoDataSwapLocal {

    private final Map<String, DSResponseLocal> responses;

    ContextoDataSwap() {
        this.responses = new HashMap<>();
    }

    @Override
    public void addDSResponseLocal(DSResponseLocal dSResponseLocal) {
        String codigoDResponse = dSResponseLocal.getCodigoDResponse();
        this.responses.put(codigoDResponse, dSResponseLocal);
    }

    @Override
    public DSResponseLocal getDSResponseLocal(String codigoDResponse) {
        DSResponseLocal dSResponseLocal = this.responses.get(codigoDResponse);
        return dSResponseLocal;
    }

}
