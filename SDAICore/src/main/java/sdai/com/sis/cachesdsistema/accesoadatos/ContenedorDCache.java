package sdai.com.sis.cachesdsistema.accesoadatos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@Entity
@Table(name = "TBCONCACHE")
@NamedQueries({
    @NamedQuery(name = "SCONCA0000", query = "SELECT C FROM ContenedorDCache C WHERE C.codigoDContenedor=:CODIGCONTE")
})
public class ContenedorDCache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CODIGCONTE", length = 10, nullable = false)
    private String codigoDContenedor;

    @Column(name = "DESCRCONTE", length = 450, nullable = false)
    private String descripcionDContenedor;

    @Column(name = "CLASECONTE", length = 450, nullable = false)
    private String claseDContenedor;

    ContenedorDCache() {
    }

    public String getCodigoDContenedor() {
        return codigoDContenedor;
    }

    public void setCodigoDContenedor(String codigoDContenedor) {
        this.codigoDContenedor = codigoDContenedor;
    }

    public String getDescripcionDContenedor() {
        return descripcionDContenedor;
    }

    public void setDescripcionDContenedor(String descripcionDContenedor) {
        this.descripcionDContenedor = descripcionDContenedor;
    }

    public String getClaseDContenedor() {
        return claseDContenedor;
    }

    public void setClaseDContenedor(String claseDContenedor) {
        this.claseDContenedor = claseDContenedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDContenedor != null ? codigoDContenedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContenedorDCache)) {
            return false;
        }
        ContenedorDCache other = (ContenedorDCache) object;
        if ((this.codigoDContenedor == null && other.codigoDContenedor != null) || (this.codigoDContenedor != null && !this.codigoDContenedor.equals(other.codigoDContenedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdai.com.sis.cachesdsistema.accesoadatos.ContenedorDCache[ id=" + codigoDContenedor + " ]";
    }

}
