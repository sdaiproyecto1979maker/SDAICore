package sdai.com.sis.dataswaps;

import sdai.com.sis.sistemasdreglas.DSResponseLocal;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author sergio
 */
public interface ContextoDataSwapLocal {

    void addDSResponseLocal(DSResponseLocal dSResponseLocal);

    DSResponseLocal getDSResponseLocal(String codigoDResponse);

}
