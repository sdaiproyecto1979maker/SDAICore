package sdai.com.sis.rednodal.atributosdnodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import sdai.com.sis.rednodal.datosdsistema.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDNodo.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KAtributosDNodo.NamedQueries.SATRNO0000, query = "SELECT A FROM AtributoDNodo A WHERE A.nodo.codigoDNodo=:CODIGONODO AND A.datoDSistema.codigoDDato=:CODIGODATO"),
    @NamedQuery(name = KAtributosDNodo.NamedQueries.SATRNO0001, query = "SELECT A FROM AtributoDNodo A"),
    @NamedQuery(name = KAtributosDNodo.NamedQueries.SATRNO0002, query = "SELECT A FROM AtributoDNodo A WHERE A.entornoAplicacion=:ENTORNOAPP")
})
public class AtributoDNodo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KAtributosDNodo.AtributosDEntidad.IDATRSNODO)
    private Long identificador;

    @ManyToOne
    @JoinColumn(name = KNodos.AtributosDEntidad.IDENTINODO)
    private Nodo nodo;

    @ManyToOne
    @JoinColumn(name = KDatosDSistema.AtributosDEntidad.IDDATSISTE)
    private DatoDSistema datoDSistema;

    @Column(name = KNodos.AtributosDEntidad.ENTORNOAPP, length = 10, nullable = false)
    private String entornoAplicacion;

    AtributoDNodo() {
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public DatoDSistema getDatoDSistema() {
        return datoDSistema;
    }

    public void setDatoDSistema(DatoDSistema datoDSistema) {
        this.datoDSistema = datoDSistema;
    }

    public String getEntornoAplicacion() {
        return entornoAplicacion;
    }

    public void setEntornoAplicacion(String entornoAplicacion) {
        this.entornoAplicacion = entornoAplicacion;
    }

}
