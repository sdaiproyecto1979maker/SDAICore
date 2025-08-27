package sdai.com.sis.seguridad.accionesdsistema;

import jakarta.enterprise.context.RequestScoped;
import sdai.com.sis.accionesdsistema.AccionesDSistema;
import sdai.com.sis.accionesdsistema.KAccionesDSistema;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.seguridad.dataswaps.DSUsuario;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;

/**
 * @23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
@AccionesDSistema(KAccionesDSistema.AccionesDSistema.ACCIOLOGIN)
public class AccionDLogin extends AbstractAccionSeguridad {

    @Override
    protected void procesar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral {
        DSUsuario dSUsuario = dataSwapSeguridad.getDSUsuario();
        String codigoDUsuario = dSUsuario.getCodigoDUsuario();
        String passwordDUsuario = dSUsuario.getPasswordDUsuario();
    }

}
