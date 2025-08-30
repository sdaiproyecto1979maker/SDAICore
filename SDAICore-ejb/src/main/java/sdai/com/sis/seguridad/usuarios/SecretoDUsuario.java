package sdai.com.sis.seguridad.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KUsuarios.SecretosDUsuario.NOMBRTABLA)
public class SecretoDUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KUsuarios.SecretosDUsuario.AtributosDEntidad.IDSECUSUAR)
    private Long identificador;

    @Column(name = KUsuarios.SecretosDUsuario.AtributosDEntidad.PASSWUSUAR, nullable = false)
    private String passwordDUsuario;

    @Column(name = KUsuarios.SecretosDUsuario.AtributosDEntidad.FECHACADUC, nullable = true)
    private LocalDate fechaDCaducidad;

    @ManyToOne
    @JoinColumn(name = KUsuarios.AtributosDEntidad.IDDUSUARIO)
    private Usuario usuario;

    SecretoDUsuario() {
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getPasswordDUsuario() {
        return passwordDUsuario;
    }

    public void setPasswordDUsuario(String passwordDUsuario) {
        this.passwordDUsuario = passwordDUsuario;
    }

    public LocalDate getFechaDCaducidad() {
        return fechaDCaducidad;
    }

    public void setFechaDCaducidad(LocalDate fechaDCaducidad) {
        this.fechaDCaducidad = fechaDCaducidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
