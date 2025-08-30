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

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KUsuarios.AtributosDUsuario.NOMBRTABLA)
public class AtributoDUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KUsuarios.AtributosDUsuario.AtributosDEntidad.IDATRUSUAR)
    private Long identificador;

    @Column(name = KUsuarios.AtributosDUsuario.AtributosDEntidad.NOMBRATRIB, length = 10, nullable = false)
    private String nombreDAtributo;

    @Column(name = KUsuarios.AtributosDUsuario.AtributosDEntidad.VALORATRIB, length = 450, nullable = false)
    private String valorDAtributo;

    @ManyToOne
    @JoinColumn(name = KUsuarios.AtributosDEntidad.IDDUSUARIO)
    private Usuario usuario;

    AtributoDUsuario() {
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getNombreDAtributo() {
        return nombreDAtributo;
    }

    public void setNombreDAtributo(String nombreDAtributo) {
        this.nombreDAtributo = nombreDAtributo;
    }

    public String getValorDAtributo() {
        return valorDAtributo;
    }

    public void setValorDAtributo(String valorDAtributo) {
        this.valorDAtributo = valorDAtributo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
