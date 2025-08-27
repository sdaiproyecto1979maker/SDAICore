package sdai.com.sis.rednodal.atributosdtupla;

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
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.rednodal.tuplas.Tupla;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDTupla.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KAtributosDTupla.NamedQueries.SATRTU0000, query = "SELECT A FROM AtributoDTupla A WHERE A.tupla.codigoDTupla=:CODIGTUPLA AND A.datoDSistema.codigoDDato=:CODIGODATO"),
    @NamedQuery(name = KAtributosDTupla.NamedQueries.SATRTU0001, query = "SELECT A FROM AtributoDTupla A")
})
public class AtributoDTupla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KAtributosDTupla.AtributosDEntidad.IDATRTUPLA)
    private Long identificador;

    @ManyToOne
    @JoinColumn(name = KTuplas.AtributosDEntidad.IDENTTUPLA)
    private Tupla tupla;

    @ManyToOne
    @JoinColumn(name = KDatosDSistema.AtributosDEntidad.IDDATSISTE)
    private DatoDSistema datoDSistema;

    @Column(name = KAtributosDTupla.AtributosDEntidad.VALORATRIB, length = 450, nullable = false)
    private String valorDAtributo;

    AtributoDTupla() {
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Tupla getTupla() {
        return tupla;
    }

    public void setTupla(Tupla tupla) {
        this.tupla = tupla;
    }

    public DatoDSistema getDatoDSistema() {
        return datoDSistema;
    }

    public void setDatoDSistema(DatoDSistema datoDSistema) {
        this.datoDSistema = datoDSistema;
    }

    public String getValorDAtributo() {
        return valorDAtributo;
    }

    public void setValorDAtributo(String valorDAtributo) {
        this.valorDAtributo = valorDAtributo;
    }

}
