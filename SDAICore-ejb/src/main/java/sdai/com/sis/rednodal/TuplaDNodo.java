package sdai.com.sis.rednodal;

import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.rednodal.atributosdtupla.AtributoDTupla;
import sdai.com.sis.rednodal.tuplas.Tupla;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class TuplaDNodo implements TuplaDNodoLocal {

    private final String codigoDTupla;
    private final String descripcionDTupla;
    private final DatoDTuplaLocal[] datosDTupla;

    public TuplaDNodo(Tupla tupla) {
        this.codigoDTupla = tupla.getCodigoDTupla();
        this.descripcionDTupla = tupla.getDescripcionDTupla();
        this.datosDTupla = loadDatosDNodo(tupla);
    }

    private DatoDTuplaLocal[] loadDatosDNodo(Tupla tupla) {
        List<DatoDTuplaLocal> lista = new ArrayList<>();
        List<AtributoDTupla> atributosDTupla = tupla.getAtributosDTupla();
        for (AtributoDTupla atributoDTupla : atributosDTupla) {
            DatoDTuplaLocal datoDTupla = new DatoDTupla(atributoDTupla);
            lista.add(datoDTupla);
        }
        return lista.toArray(DatoDTuplaLocal[]::new);
    }

    Boolean isTuplaBuscada(String... argumentos) {
        for (int i = 0; i < argumentos.length; i = i + 2) {
            String codigoDDato = argumentos[i];
            DatoDTuplaLocal datoDTuplaLocal = getDatoDTupla(codigoDDato);
            if (datoDTuplaLocal == null) {
                return Boolean.FALSE;
            }
            String valorDAtributo = argumentos[i + 1];
            if (!valorDAtributo.equals(datoDTuplaLocal.getValorDAtributo())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public DatoDTuplaLocal getDatoDTupla(String codigoDDato) {
        if (this.datosDTupla == null || this.datosDTupla.length == 0) {
            return null;
        }
        for (DatoDTuplaLocal datoDTupla : this.datosDTupla) {
            if (codigoDDato.equals(datoDTupla.getCodigoDDato())) {
                return datoDTupla;
            }
        }
        return null;
    }

    @Override
    public String getCodigoDTupla() {
        return codigoDTupla;
    }

    @Override
    public String getDescripcionDTupla() {
        return descripcionDTupla;
    }

    @Override
    public DatoDTuplaLocal[] getDatosDTupla() {
        return datosDTupla;
    }

}
