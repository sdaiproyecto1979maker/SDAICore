package sdai.com.sis.rednodal;

import sdai.com.sis.utilidades.Transform;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class ElementoDRed {

    private final TuplaDNodoLocal tuplaDNodoLocal;

    public ElementoDRed(TuplaDNodoLocal tuplaDNodoLocal) {
        this.tuplaDNodoLocal = tuplaDNodoLocal;
    }

    public String getValorString(String key) {
        DatoDTuplaLocal atributoDTuplaLocal = this.tuplaDNodoLocal.getDatoDTupla(key);
        return atributoDTuplaLocal == null ? "" : atributoDTuplaLocal.getValorDAtributo();
    }

    public Integer getValorInteger(String key) {
        DatoDTuplaLocal atributoDTuplaLocal = this.tuplaDNodoLocal.getDatoDTupla(key);
        return atributoDTuplaLocal == null ? 0 : Transform.toInteger(atributoDTuplaLocal.getValorDAtributo());
    }

}
