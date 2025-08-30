package sdai.com.sis.seguridad.reglasbasicas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import java.util.List;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.KReglasBasicas;
import sdai.com.sis.reglasbasicas.ReglasBasicas;
import sdai.com.sis.seguridad.dataswaps.DSUsuario;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;
import sdai.com.sis.seguridad.usuarios.ADUsuarios;
import sdai.com.sis.seguridad.usuarios.SecretoDUsuario;
import sdai.com.sis.seguridad.usuarios.Usuario;
import sdai.com.sis.sesionesdusuario.SesionDUsuario;
import sdai.com.sis.sesionesdusuario.UsuarioDSesion;
import sdai.com.sis.traducciones.Traductor;
import sdai.com.sis.utilidades.Util;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
@ReglasBasicas(KReglasBasicas.Seguridad.USUAR00003)
public class RuleValidacionPasswordDUsuario extends AbstractRuleSeguridad {

    @Inject
    private ADUsuarios adUsuarios;
    @Inject
    private SesionDUsuario sesionDUsuario;
    @Inject
    private Traductor traductor;

    @Override
    protected void evaluar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral {
        Usuario usuario;
        DSUsuario dsUsuario = dataSwapSeguridad.getDSUsuario();
        String codigoDUsuario = dsUsuario.getCodigoDUsuario();
        if (Util.isCadenaNoVacia(codigoDUsuario)) {
            usuario = this.adUsuarios.getUsuario(codigoDUsuario);
        } else {
            UsuarioDSesion usuarioDSesion = (UsuarioDSesion) this.sesionDUsuario.getUsuarioDSesionLocal();
            usuario = usuarioDSesion.getUsuario();
        }
        String passwordDUsuario = dsUsuario.getPasswordDUsuario();
        List<SecretoDUsuario> lista = usuario.getSecretosDUsuario();
        if (isSecretoDUsuario(lista, passwordDUsuario)) {
            String mensaje = this.traductor.traducir("PASSWINVAL");
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Login", mensaje);
            throw errorGeneral;
        }
    }

    private Boolean isSecretoDUsuario(List<SecretoDUsuario> lista, String passwordDUsuario) {
        if (lista.isEmpty()) {
            return Boolean.FALSE;
        }
        for (SecretoDUsuario secretoDUsuario : lista) {
            if (secretoDUsuario.getPasswordDUsuario().equals(passwordDUsuario)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
