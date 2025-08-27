package sdai.com.sis.rednodal.datosdsistema;

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
import sdai.com.sis.rednodal.atributosdtupla.AtributoDTupla;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KDatosDSistema.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KDatosDSistema.NamedQueries.SDASIS0000, query = "SELECT D FROM DatoDSistema D WHERE D.codigoDDato=:CODIGODATO"),
    @NamedQuery(name = KDatosDSistema.NamedQueries.SDASIS0001, query = "SELECT D FROM DatoDSistema D"),
    @NamedQuery(name = KDatosDSistema.NamedQueries.SDASIS0002, query = "SELECT D FROM DatoDSistema D WHERE D.entornoAplicacion=:ENTORNOAPP")
})
public class DatoDSistema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KDatosDSistema.AtributosDEntidad.IDDATSISTE)
    private Long identificador;

    @Column(name = KDatosDSistema.AtributosDEntidad.CODIGODATO, length = 10, nullable = false, unique = true)
    private String codigoDDato;

    @Column(name = KDatosDSistema.AtributosDEntidad.DESCRDDATO, length = 450, nullable = false)
    private String descripcionDDato;

    @Column(name = KDatosDSistema.AtributosDEntidad.ENTORNOAPP, length = 10, nullable = false)
    private String entornoAplicacion;

    @OneToMany(mappedBy = "datoDSistema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AtributoDNodo> atributosDNodo;

    @OneToMany(mappedBy = "datoDSistema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AtributoDTupla> atributosDTupla;

    DatoDSistema() {
        this.atributosDNodo = new ArrayList<>();
        this.atributosDTupla = new ArrayList<>();
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getCodigoDDato() {
        return codigoDDato;
    }

    public void setCodigoDDato(String codigoDDato) {
        this.codigoDDato = codigoDDato;
    }

    public String getDescripcionDDato() {
        return descripcionDDato;
    }

    public void setDescripcionDDato(String descripcionDDato) {
        this.descripcionDDato = descripcionDDato;
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

    public List<AtributoDTupla> getAtributosDTupla() {
        return atributosDTupla;
    }

    public void setAtributosDTupla(List<AtributoDTupla> atributosDTupla) {
        this.atributosDTupla = atributosDTupla;
    }

}
