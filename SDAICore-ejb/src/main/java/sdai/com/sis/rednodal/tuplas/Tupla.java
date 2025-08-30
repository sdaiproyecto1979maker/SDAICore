package sdai.com.sis.rednodal.tuplas;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.rednodal.atributosdtupla.AtributoDTupla;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KTuplas.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KTuplas.NamedQueries.STUPLA0000, query = "SELECT T FROM Tupla T WHERE T.codigoDTupla=:CODIGTUPLA"),
    @NamedQuery(name = KTuplas.NamedQueries.STUPLA0001, query = "SELECT T FROM Tupla T"),
    @NamedQuery(name = KTuplas.NamedQueries.STUPLA0002, query = "SELECT T FROM Tupla T WHERE T.entornoAplicacion=:ENTORNOAPP")
})
public class Tupla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KTuplas.AtributosDEntidad.IDENTTUPLA)
    private Long identificador;

    @Column(name = KTuplas.AtributosDEntidad.CODIGTUPLA, length = 450, nullable = false, unique = true)
    private String codigoDTupla;

    @Column(name = KTuplas.AtributosDEntidad.DESCRTUPLA, length = 450, nullable = false)
    private String descripcionDTupla;

    @Column(name = KTuplas.AtributosDEntidad.ENTORNOAPP, length = 10, nullable = false)
    private String entornoAplicacion;

    @ManyToOne
    @JoinColumn(name = KNodos.AtributosDEntidad.IDENTINODO)
    private Nodo nodo;

    @OneToMany(mappedBy = "tupla", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AtributoDTupla> atributosDTupla;

    Tupla() {
        this.atributosDTupla = new ArrayList<>();
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getCodigoDTupla() {
        return codigoDTupla;
    }

    public void setCodigoDTupla(String codigoDTupla) {
        this.codigoDTupla = codigoDTupla;
    }

    public String getDescripcionDTupla() {
        return descripcionDTupla;
    }

    public void setDescripcionDTupla(String descripcionDTupla) {
        this.descripcionDTupla = descripcionDTupla;
    }

    public String getEntornoAplicacion() {
        return entornoAplicacion;
    }

    public void setEntornoAplicacion(String entornoAplicacion) {
        this.entornoAplicacion = entornoAplicacion;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public List<AtributoDTupla> getAtributosDTupla() {
        return atributosDTupla;
    }

    public void setAtributosDTupla(List<AtributoDTupla> atributosDTupla) {
        this.atributosDTupla = atributosDTupla;
    }

}
