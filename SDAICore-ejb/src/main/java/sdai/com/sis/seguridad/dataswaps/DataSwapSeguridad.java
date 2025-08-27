package sdai.com.sis.seguridad.dataswaps;

import jakarta.enterprise.context.SessionScoped;
import sdai.com.sis.dataswaps.AbstractDataSwap;
import sdai.com.sis.dataswaps.DataSwaps;
import sdai.com.sis.dataswaps.KDataSwaps;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@DataSwaps(KDataSwaps.DataSwaps.DASWASEGUR)
public class DataSwapSeguridad extends AbstractDataSwap {

    public DSUsuario getDSUsuario() {
        DSUsuario dSUsuario = (DSUsuario) getDSEntidad("DSDUSUARIO");
        return dSUsuario;
    }

}
