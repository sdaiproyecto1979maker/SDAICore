package sdai.com.sis.rednodal;

import jakarta.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.atributosdnodo.AtributoDNodo;
import sdai.com.sis.rednodal.nodos.Nodo;
import sdai.com.sis.rednodal.tuplas.Tupla;
import sdai.com.sis.utilidades.Transform;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class NodoDRed implements NodoDRedLocal {

    private final String codigoDNodo;
    private final String descripcionDNodo;
    private final DatoDNodoLocal[] datosDNodo;
    private final TuplaDNodoLocal[] tuplasDNodo;

    NodoDRed(Nodo nodo) {
        this.codigoDNodo = nodo.getCodigoDNodo();
        this.descripcionDNodo = nodo.getDescripcionDNodo();
        this.datosDNodo = loadDatosDNodo(nodo);
        this.tuplasDNodo = loadTuplasDNodo(nodo);
    }

    private DatoDNodoLocal[] loadDatosDNodo(Nodo nodo) {
        List<DatoDNodoLocal> lista = new ArrayList<>();
        List<AtributoDNodo> atributosDNodo = nodo.getAtributosDNodo();
        for (AtributoDNodo atributoDNodo : atributosDNodo) {
            DatoDNodoLocal datoDNodo = new DatoDNodo(atributoDNodo);
            lista.add(datoDNodo);
        }
        return lista.toArray(DatoDNodoLocal[]::new);
    }

    private TuplaDNodoLocal[] loadTuplasDNodo(Nodo nodo) {
        List<TuplaDNodoLocal> lista = new ArrayList<>();
        List<Tupla> tuplas = nodo.getTuplas();
        for (Tupla tupla : tuplas) {
            TuplaDNodoLocal tuplaDNodo = new TuplaDNodo(tupla);
            lista.add(tuplaDNodo);
        }
        return lista.toArray(TuplaDNodoLocal[]::new);
    }

    @Override
    public TuplaDNodoLocal getTuplaDNodo(Object... argumentos) {
        List<String> lista = new ArrayList<>();
        for (Object argumento : argumentos) {
            lista.add(Transform.toString(argumento));
        }
        TuplaDNodoLocal[] tuplaDNodoLocals = getTuplasDNodo(lista.toArray(String[]::new));
        return tuplaDNodoLocals[0];
    }

    @Override
    public TuplaDNodoLocal getTuplaDNodo(String... argumentos) throws ErrorGeneral {
        TuplaDNodoLocal[] tuplaDNodoLocals = getTuplasDNodo(argumentos);
        if (tuplaDNodoLocals == null || tuplaDNodoLocals.length == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("No se han encontrado tuplas para el nodo ");
            stringBuilder.append(getCodigoDNodo());
            stringBuilder.append(" con los siguientes argumentos (");
            for (int i = 0; i < argumentos.length; i = i + 2) {
                if (i % 2 == 0) {
                    stringBuilder.append(argumentos[i]);
                } else {
                    stringBuilder.append(": ");
                    stringBuilder.append(argumentos[i + 1]);
                }
                if (i + 2 < argumentos.length) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(")");
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Nodo de red", stringBuilder.toString());
            throw errorGeneral;
        }
        return tuplaDNodoLocals[0];
    }

    @Override
    public TuplaDNodoLocal[] getTuplasDNodo(String... argumentos) {
        if (this.tuplasDNodo == null || this.tuplasDNodo.length == 0) {
            return new TuplaDNodoLocal[0];
        }
        List<TuplaDNodoLocal> lista = new ArrayList<>();
        for (TuplaDNodoLocal tuplaDNodoLocal : this.tuplasDNodo) {
            TuplaDNodo tuplaDNodo = (TuplaDNodo) tuplaDNodoLocal;
            if (tuplaDNodo.isTuplaBuscada(argumentos)) {
                lista.add(tuplaDNodoLocal);
            }
        }
        return lista.toArray(TuplaDNodoLocal[]::new);
    }

    @Override
    public String getCodigoDNodo() {
        return codigoDNodo;
    }

    @Override
    public String getDescripcionDNodo() {
        return descripcionDNodo;
    }

    @Override
    public DatoDNodoLocal[] getDatosDNodo() {
        return datosDNodo;
    }

    @Override
    public TuplaDNodoLocal[] getTuplasDNodo() {
        return tuplasDNodo;
    }

}
