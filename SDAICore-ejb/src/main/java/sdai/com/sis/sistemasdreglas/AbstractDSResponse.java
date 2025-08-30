package sdai.com.sis.sistemasdreglas;

import java.util.HashMap;
import java.util.Map;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.ReglaBasicaLocal;
import sdai.com.sis.sistemasdreglas.rednodal.DSResponseImplLocal;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractDSResponse implements DSResponseLocal {

    private DSResponseImplLocal dSResponseImplLocal;
    private DataSwapLocal dataSwapLocal;
    private final Map<String, Object> objectsResponse;

    public AbstractDSResponse() {
        this.objectsResponse = new HashMap<>();
    }

    @Override
    public void setDSResponseImplLocal(DSResponseImplLocal dSResponseImplLocal) {
        this.dSResponseImplLocal = dSResponseImplLocal;
    }

    @Override
    public void setDataSwapLocal(DataSwapLocal dataSwapLocal) {
        this.dataSwapLocal = dataSwapLocal;
    }

    @Override
    public DataSwapLocal getDataSwapLocal() {
        return this.dataSwapLocal;
    }

    public Object getObjectResponse(String key) {
        return this.objectsResponse.get(key);
    }

    @Override
    public void setObjectResponse(Object value) {
        String key = value.getClass().getName();
        this.objectsResponse.put(key, value);
    }

    public ErrorGeneral tratarErrorResponse(ReglaBasicaLocal reglaBasicaLocal) {
        return null;
    }

    @Override
    public String getCodigoDResponse() {
        return this.dSResponseImplLocal.getCodigoDResponse();
    }

}
