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
public class BeanClaveCaducada implements BeanDVistaLocal, Serializable {

    @NotBlank(groups = NotBlank.class, message = "PASSWNOBLK")
    @Size(min = 3, groups = Size.class, message = "PASSWDSIZE")
    private String passwordDUsuario;
    @NotBlank(groups = NotBlank.class, message = "PASSWNOBLK")
    @Size(min = 3, groups = Size.class, message = "PASSWDSIZE")
    private String passwordRUsuario;

    @Override
    public void addDatosDEntidad(DSEntidadLocal dSEntidad) {
        dSEntidad.addValorDDato("PASSWUSUAR", getPasswordDUsuario());
        dSEntidad.addValorDDato("PASSRUSUAR", getPasswordRUsuario());
    }

    public String getPasswordDUsuario() {
        return passwordDUsuario;
    }

    public void setPasswordDUsuario(String passwordDUsuario) {
        this.passwordDUsuario = passwordDUsuario;
    }

    public String getPasswordRUsuario() {
        return passwordRUsuario;
    }

    public void setPasswordRUsuario(String passwordRUsuario) {
        this.passwordRUsuario = passwordRUsuario;
    }

}
