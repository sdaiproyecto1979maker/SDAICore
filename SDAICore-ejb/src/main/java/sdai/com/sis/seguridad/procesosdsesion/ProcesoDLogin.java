package sdai.com.sis.seguridad.procesosdsesion;

import jakarta.enterprise.context.SessionScoped;
import sdai.com.sis.procesosdsesion.AbstractProcesoDSesion;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.procesosdsesion.ProcesosDSesion;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@ProcesosDSesion(KProcesosDSesion.ProcesosDSesion.PROCELOGIN)
public class ProcesoDLogin extends AbstractProcesoDSesion {

    @Override
    public String getPaginaDProceso() {
        return KProcesosDSesion.PaginasDProceso.PROCELOGIN;
    }

}
