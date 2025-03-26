package sdai.com.sis.rednodal;

import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class NodoDRed {

	private NodoDRed(SituacionDNodo situacionDNodo) {

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

}
