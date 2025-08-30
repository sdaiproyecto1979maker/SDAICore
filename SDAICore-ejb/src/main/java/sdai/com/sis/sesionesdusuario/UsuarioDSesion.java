package sdai.com.sis.sesionesdusuario;

import sdai.com.sis.seguridad.usuarios.Usuario;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class UsuarioDSesion implements UsuarioDSesionLocal {

    private Usuario usuario;

    UsuarioDSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
