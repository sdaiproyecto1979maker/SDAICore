package sdai.com.sis.seguridad.reglasbasicas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.KReglasBasicas;
import sdai.com.sis.reglasbasicas.ReglasBasicas;
import sdai.com.sis.seguridad.dataswaps.DSUsuario;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;
import sdai.com.sis.seguridad.usuarios.ADUsuarios;
import sdai.com.sis.seguridad.usuarios.SecretoDUsuario;
import sdai.com.sis.seguridad.usuarios.Usuario;
import sdai.com.sis.traducciones.Traductor;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
@ReglasBasicas(KReglasBasicas.Seguridad.USUAR00001)
public class RuleValidacionDPassword extends AbstractRuleSeguridad {

    @Inject
    private ADUsuarios adUsuarios;
    @Inject
    private Traductor traductor;

    @Override
    protected void evaluar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral {
        DSUsuario dsUsuario = dataSwapSeguridad.getDSUsuario();
        String codigoDUsuario = dsUsuario.getCodigoDUsuario();
        Usuario usuario = this.adUsuarios.getUsuario(codigoDUsuario);
        String passwordDUsuario = dsUsuario.getPasswordDUsuario();
        SecretoDUsuario secretoDUsuario = usuario.getSecretoDUsuario();
        if (!secretoDUsuario.getPasswordDUsuario().equals(passwordDUsuario)) {
            String mensaje = this.traductor.traducir("USUARINVAL");
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Login", mensaje);
            throw errorGeneral;
        }
    }

}
