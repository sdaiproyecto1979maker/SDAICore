package sdai.com.sis.procesosdsesion;

import jakarta.enterprise.context.SessionScoped;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@ProcesosDSesion(KProcesosDSesion.ProcesosDSesion.DFPROCSESI)
public class DefaultProcesoDSesion extends AbstractProcesoDSesion {

    @Override
    public DataSwapLocal getDataSwapLocal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void realizarValidaciones() throws ErrorGeneral {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
