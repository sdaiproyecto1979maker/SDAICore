package sdai.com.sis.seguridad.reglasbasicas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.KReglasBasicas;
import sdai.com.sis.reglasbasicas.ReglasBasicas;
import sdai.com.sis.seguridad.dataswaps.DSUsuario;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;
import sdai.com.sis.traducciones.Traductor;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
@ReglasBasicas(KReglasBasicas.Seguridad.USUAR00002)
public class RuleValidacionMismasPasswords extends AbstractRuleSeguridad {

    @Inject
    private Traductor traductor;

    @Override
    protected void evaluar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral {
        DSUsuario dsUsuario = dataSwapSeguridad.getDSUsuario();
        String passwordDUsuario = dsUsuario.getPasswordDUsuario();
        String passwordRUsuario = dsUsuario.getPasswordRUsuario();
        if (!passwordDUsuario.equals(passwordRUsuario)) {
            String mensaje = this.traductor.traducir("DISTIPASSW");
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Usuarios", mensaje);
            throw errorGeneral;
        }
    }

}
