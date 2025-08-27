package sdai.com.sis.versiones.accesoadatos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import sdai.com.sis.versiones.KVersionado;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KVersionado.NOMBRTABLA)
@NamedQueries({
    @NamedQuery(name = KVersionado.NamedQueries.SVERSI0000, query = "SELECT V FROM Version V WHERE V.entornoAplicacion=:ENTORNOAPP")
})
public class Version implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = KVersionado.AtributosDEntidad.IDDVERSION)
    private Long identificador;

    @Column(name = KVersionado.AtributosDEntidad.NUMVERSION, length = 20, nullable = false)
    private String numeroDVersion;

    @Column(name = KVersionado.AtributosDEntidad.ENTORNOAPP, length = 10, nullable = false)
    private String entornoAplicacion;

    @Lob
    @Column(name = KVersionado.AtributosDEntidad.FILVERSION, columnDefinition = "LONGBLOB", nullable = false)
    private byte[] ficheroDVersion;

    Version() {

    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getNumeroDVersion() {
        return numeroDVersion;
    }

    public void setNumeroDVersion(String numeroDVersion) {
        this.numeroDVersion = numeroDVersion;
    }

    public String getEntornoAplicacion() {
        return entornoAplicacion;
    }

    public void setEntornoAplicacion(String entornoAplicacion) {
        this.entornoAplicacion = entornoAplicacion;
    }

    public byte[] getFicheroDVersion() {
        return ficheroDVersion;
    }

    public void setFicheroDVersion(byte[] ficheroDVersion) {
        this.ficheroDVersion = ficheroDVersion;
    }

}
