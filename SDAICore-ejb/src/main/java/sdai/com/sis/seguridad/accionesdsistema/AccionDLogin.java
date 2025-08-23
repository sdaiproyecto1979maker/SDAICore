package sdai.com.sis.seguridad.accionesdsistema;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import sdai.com.sis.accionesdsistema.AccionDSistemaLocal;
import sdai.com.sis.accionesdsistema.AccionesDSistema;
import sdai.com.sis.accionesdsistema.KAccionesDSistema;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;

/**
 * @23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
@AccionesDSistema(KAccionesDSistema.AccionesDSistema.ACCIOLOGIN)
public class AccionDLogin implements AccionDSistemaLocal {

    @Inject
    private GestorDProcesosLocal gestorDProcesosLocal;

    @Override
    public void procesarAccion(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        /*DataSwapSeguridad dataSwapSeguridad = (DataSwapSeguridad) dataSwapLocal;
        DSUsuario dSUsuario = dataSwapSeguridad.getDsUsuario();
        String codigoDUsuario = dSUsuario.getCodigoDUsuario();
        String passwordDUsuario = dSUsuario.getPasswordDUsuario();*/
        crearProcesoInicioDSistema();
    }

    private void crearProcesoInicioDSistema() {
        this.gestorDProcesosLocal.iniciar(KProcesosDSesion.ProcesosDSesion.PROCINISIS);
    }

}
