package sdai.com.sis.seguridad.dataswaps;

import jakarta.enterprise.context.SessionScoped;
import java.util.List;
import sdai.com.sis.dataswaps.AbstractDataSwap;
import sdai.com.sis.dataswaps.DataSwaps;
import sdai.com.sis.dataswaps.KDataSwaps;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@DataSwaps(KDataSwaps.DataSwaps.DASWASEGUR)
public class DataSwapSeguridad extends AbstractDataSwap {

    @Override
    public void generateDataSwap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EstructuraDatos> getEstructurasTemporales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
