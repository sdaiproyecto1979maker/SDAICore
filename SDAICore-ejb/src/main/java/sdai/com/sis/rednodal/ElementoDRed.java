package sdai.com.sis.rednodal;

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

}
