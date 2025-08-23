package sdai.com.sis.dataswaps;

import jakarta.enterprise.context.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@DataSwaps(KDataSwaps.DataSwaps.DFDATASWAP)
public class DefaultDataSwap extends AbstractDataSwap {

    @Override
    public void generateDataSwap() {

    }

    @Override
    public List<EstructuraDatos> getEstructurasTemporales() {
        return new ArrayList<>();
    }

}
