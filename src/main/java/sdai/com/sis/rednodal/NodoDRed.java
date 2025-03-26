package sdai.com.sis.rednodal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.SituacionDAtributoDNodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.rednodal.tuplas.accesoadatos.SituacionDTupla;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class NodoDRed {

	private final String codigoDNodo;
	private final String descripcionDNodo;
	private final DatoDNodo[] datosDNodo;
	private final TuplaDNodo[] tuplasDNodo;
	private final ConcurrentMap<String, TuplaDNodo[]> tuplasIndexadas;

	private NodoDRed(SituacionDNodo situacionDNodo) throws Exception {
		Nodo nodo = situacionDNodo.getNodo();
		this.codigoDNodo = nodo.getCodigoDNodo();
		this.descripcionDNodo = situacionDNodo.getDescripcionDNodo();
		this.datosDNodo = loadDatosDNodo(this.codigoDNodo);
		this.tuplasDNodo = loadTuplasDNodo(codigoDNodo);
		this.tuplasIndexadas = new ConcurrentHashMap<String, TuplaDNodo[]>();
	}

	private DatoDNodo[] loadDatosDNodo(String codigoDNodo) throws Exception {
		List<DatoDNodo> lista = new ArrayList<DatoDNodo>();
		SituacionDAtributoDNodo[] situacionesDAtributoDNodo = SituacionDAtributoDNodo.getInstancias(codigoDNodo);
		for (SituacionDAtributoDNodo situacionDAtributoDNodo : situacionesDAtributoDNodo) {
			DatoDNodo datoDNodo = new DatoDNodo(situacionDAtributoDNodo);
			lista.add(datoDNodo);
		}
		return lista.toArray(new DatoDNodo[0]);
	}

	private TuplaDNodo[] loadTuplasDNodo(String codigoDNodo) throws Exception {
		List<TuplaDNodo> lista = new ArrayList<TuplaDNodo>();
		SituacionDTupla[] situacionesDTupla = SituacionDTupla.getInstancias(codigoDNodo);
		for (SituacionDTupla situacionDTupla : situacionesDTupla) {
			TuplaDNodo tuplaDNodo = new TuplaDNodo(situacionDTupla);
			lista.add(tuplaDNodo);
		}
		return lista.toArray(new TuplaDNodo[0]);
	}

	static NodoDRed getInstancia(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(NodoDRed.class, codigoDNodo);
		NodoDRed instancia = (NodoDRed) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
			if (situacionDNodo != null) {
				instancia = new NodoDRed(situacionDNodo);
				CacheDRednodal.almacenarInstancia(keyCache, instancia);
			}
		}
		return instancia;
	}

	static NodoDRed[] getInstancias() throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(NodoDRed.class);
		NodoDRed[] instancias = (NodoDRed[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			List<NodoDRed> lista = new ArrayList<NodoDRed>();
			Nodo[] nodos = Nodo.getInstancias();
			for (Nodo nodo : nodos) {
				String codigoDNodo = nodo.getCodigoDNodo();
				SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
				if (situacionDNodo != null) {
					KeyCache _keyCache = KeyCache.getInstancia(NodoDRed.class, codigoDNodo);
					NodoDRed instancia = new NodoDRed(situacionDNodo);
					CacheDRednodal.almacenarInstancia(_keyCache, instancia);
					lista.add(instancia);
				}
			}
			instancias = lista.toArray(new NodoDRed[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	TuplaDNodo[] getTuplasDNodo(String... argumentos) {
		String keyCache = generateKeyCache(argumentos);
		if (!this.tuplasIndexadas.containsKey(keyCache))
			indexarTuplas(keyCache, argumentos);
		TuplaDNodo[] tuplasDNodo = this.tuplasIndexadas.get(keyCache);
		return tuplasDNodo;
	}

	private String generateKeyCache(String... argumentos) {
		List<Object> lista = new ArrayList<Object>();
		lista.add(getCodigoDNodo());
		for (String argumento : argumentos)
			lista.add(argumento);
		KeyCache keyCache = KeyCache.getInstancia(TuplaDNodo.class, lista.toArray(new Object[0]));
		return keyCache.getKeyCache();
	}

	private void indexarTuplas(String keyCache, String... argumentos) {

	}

	public String getCodigoDNodo() {
		return codigoDNodo;
	}

	public String getDescripcionDNodo() {
		return descripcionDNodo;
	}

	public DatoDNodo[] getDatosDNodo() {
		return datosDNodo;
	}

	public TuplaDNodo[] getTuplasDNodo() {
		return tuplasDNodo;
	}

}
