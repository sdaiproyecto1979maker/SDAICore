package sdai.com.sis.seguridad.usuarios;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KUsuarios.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KUsuarios.NamedQueries.SUSUAR0000, query = "SELECT U FROM Usuario U WHERE U.codigoDUsuario=:CODIGUSUAR")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KUsuarios.AtributosDEntidad.IDDUSUARIO)
    private Long identificador;

    @Column(name = KUsuarios.AtributosDEntidad.CODIGUSUAR, length = 45, nullable = false, unique = true)
    private String codigoDUsuario;

    @Column(name = KUsuarios.AtributosDEntidad.SWUSACTIVO, nullable = false)
    private Boolean isActivo;

    @Column(name = KUsuarios.AtributosDEntidad.SWUSUBLOCK, nullable = false)
    private Boolean isBloqueado;

    @Column(name = KUsuarios.AtributosDEntidad.FECHADALTA, nullable = false)
    private LocalDate fechaDAlta;

    @Column(name = KUsuarios.AtributosDEntidad.FECHADBAJA, nullable = true)
    private LocalDate fechaDBaja;

    @OneToOne
    @JoinColumn(name = KUsuarios.SecretosDUsuario.AtributosDEntidad.IDSECUSUAR)
    private SecretoDUsuario SecretoDUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SecretoDUsuario> secretosDUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AtributoDUsuario> atributosDUsuario;

    Usuario() {
        this.secretosDUsuario = new ArrayList<>();
        this.atributosDUsuario = new ArrayList<>();
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getCodigoDUsuario() {
        return codigoDUsuario;
    }

    public void setCodigoDUsuario(String codigoDUsuario) {
        this.codigoDUsuario = codigoDUsuario;
    }

    public Boolean getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(Boolean isActivo) {
        this.isActivo = isActivo;
    }

    public Boolean getIsBloqueado() {
        return isBloqueado;
    }

    public void setIsBloqueado(Boolean isBloqueado) {
        this.isBloqueado = isBloqueado;
    }

    public LocalDate getFechaDAlta() {
        return fechaDAlta;
    }

    public void setFechaDAlta(LocalDate fechaDAlta) {
        this.fechaDAlta = fechaDAlta;
    }

    public LocalDate getFechaDBaja() {
        return fechaDBaja;
    }

    public void setFechaDBaja(LocalDate fechaDBaja) {
        this.fechaDBaja = fechaDBaja;
    }

    public SecretoDUsuario getSecretoDUsuario() {
        return SecretoDUsuario;
    }

    public void setSecretoDUsuario(SecretoDUsuario SecretoDUsuario) {
        this.SecretoDUsuario = SecretoDUsuario;
    }

    public List<SecretoDUsuario> getSecretosDUsuario() {
        return secretosDUsuario;
    }

    public void setSecretosDUsuario(List<SecretoDUsuario> secretosDUsuario) {
        this.secretosDUsuario = secretosDUsuario;
    }

    public List<AtributoDUsuario> getAtributosDUsuario() {
        return atributosDUsuario;
    }

    public void setAtributosDUsuario(List<AtributoDUsuario> atributosDUsuario) {
        this.atributosDUsuario = atributosDUsuario;
    }

}
