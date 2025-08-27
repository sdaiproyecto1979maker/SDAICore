package sdai.com.sis.seguridad;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import sdai.com.sis.beans.BeanDVistaLocal;
import sdai.com.sis.dsentidades.DSEntidadLocal;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Named
@ViewScoped
public class BeanUsuarioLogin implements BeanDVistaLocal, Serializable {

    @NotBlank(groups = NotBlank.class, message = "USUARNOBLK")
    @Size(min = 3, max = 45, groups = Size.class, message = "USUARDSIZE")
    private String codigoDUsuario;
    @NotBlank(groups = NotBlank.class, message = "PASSWNOBLK")
    @Size(min = 3, groups = Size.class, message = "PASSWDSIZE")
    private String passwordDUsuario;

    @Override
    public void addDatosDEntidad(DSEntidadLocal dSEntidad) {
        dSEntidad.addValorDDato("CODIGUSUAR", getCodigoDUsuario());
        dSEntidad.addValorDDato("PASSWUSUAR", getPasswordDUsuario());
    }

    public String getCodigoDUsuario() {
        return codigoDUsuario;
    }

    public void setCodigoDUsuario(String codigoDUsuario) {
        this.codigoDUsuario = codigoDUsuario;
    }

    public String getPasswordDUsuario() {
        return passwordDUsuario;
    }

    public void setPasswordDUsuario(String passwordDUsuario) {
        this.passwordDUsuario = passwordDUsuario;
    }

}
