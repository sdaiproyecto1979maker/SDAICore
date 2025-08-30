package sdai.com.sis.rednodal.nodos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.rednodal.atributosdnodo.AtributoDNodo;
import sdai.com.sis.rednodal.tuplas.Tupla;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KNodos.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KNodos.NamedQueries.SNODOS0000, query = "SELECT N FROM Nodo N WHERE N.codigoDNodo=:CODIGONODO"),
    @NamedQuery(name = KNodos.NamedQueries.SNODOS0001, query = "SELECT N FROM Nodo N"),
    @NamedQuery(name = KNodos.NamedQueries.SNODOS0002, query = "SELECT N FROM Nodo N WHERE N.entornoAplicacion=:ENTORNOAPP")
})
public class Nodo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KNodos.AtributosDEntidad.IDENTINODO)
    private Long identificador;

    @Column(name = KNodos.AtributosDEntidad.CODIGONODO, length = 10, nullable = false, unique = true)
    private String codigoDNodo;

    @Column(name = KNodos.AtributosDEntidad.DESCRDNODO, length = 450, nullable = false)
    private String descripcionDNodo;

    @Column(name = KNodos.AtributosDEntidad.ENTORNOAPP, length = 10, nullable = false)
    private String entornoAplicacion;

    @OneToMany(mappedBy = "nodo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AtributoDNodo> atributosDNodo;

    @OneToMany(mappedBy = "nodo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tupla> tuplas;

    Nodo() {
        this.atributosDNodo = new ArrayList<>();
        this.tuplas = new ArrayList<>();
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getCodigoDNodo() {
        return codigoDNodo;
    }

    public void setCodigoDNodo(String codigoDNodo) {
        this.codigoDNodo = codigoDNodo;
    }

    public String getDescripcionDNodo() {
        return descripcionDNodo;
    }

    public void setDescripcionDNodo(String descripcionDNodo) {
        this.descripcionDNodo = descripcionDNodo;
    }

    public String getEntornoAplicacion() {
        return entornoAplicacion;
    }

    public void setEntornoAplicacion(String entornoAplicacion) {
        this.entornoAplicacion = entornoAplicacion;
    }

    public List<AtributoDNodo> getAtributosDNodo() {
        return atributosDNodo;
    }

    public void setAtributosDNodo(List<AtributoDNodo> atributosDNodo) {
        this.atributosDNodo = atributosDNodo;
    }

    public List<Tupla> getTuplas() {
        return tuplas;
    }

    public void setTuplas(List<Tupla> tuplas) {
        this.tuplas = tuplas;
    }

}
