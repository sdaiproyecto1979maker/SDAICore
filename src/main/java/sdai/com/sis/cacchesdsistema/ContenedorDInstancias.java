package sdai.com.sis.cacchesdsistema;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ContenedorDInstancias extends ConcurrentHashMap<String, InstanciaDContenedor> {

	private static final long serialVersionUID = 1L;

	private final ICacheDSistema cacheDSistema;

	ContenedorDInstancias(ICacheDSistema cacheDSistema) {
		this.cacheDSistema = cacheDSistema;
	}

	InstanciaDContenedor recuperarInstancia(KeyCache keyCache) {
		String key = keyCache.getKeyCache();
		if (!super.containsKey(key))
			return null;
		InstanciaDContenedor instanciaDContenedor = super.get(key);
		Boolean swDeleteable = keyCache.getSwDeleteable();
		if (swDeleteable.equals(Boolean.valueOf(true))) {
			Integer minutosEnContenedor = this.cacheDSistema.getMinutosEnContenedor();
			if (instanciaDContenedor.isInstanciaCaducada(minutosEnContenedor).equals(Boolean.valueOf(true))) {
				super.remove(key);
				return null;
			}
		}
		return instanciaDContenedor;
	}

	void almacenarInstancia(KeyCache keyCache, Object instancia) {
		Integer elementosMaximos = this.cacheDSistema.getElementosMaximos();
		Integer numeroDElementos = super.size();
		if (numeroDElementos > elementosMaximos) {
			Integer minutosEnContenedor = this.cacheDSistema.getMinutosEnContenedor();
			InstanciaDContenedor[] instanciasDContenedor = super.values().toArray(new InstanciaDContenedor[0]);
			for (InstanciaDContenedor instanciaDContenedor : instanciasDContenedor) {
				KeyCache _keyCache = instanciaDContenedor.getKeyCache();
				Boolean swDeleteable = _keyCache.getSwDeleteable();
				if (swDeleteable.equals(Boolean.valueOf(true))) {
					if (instanciaDContenedor.isInstanciaCaducada(minutosEnContenedor).equals(Boolean.valueOf(true))) {
						String key = _keyCache.getKeyCache();
						super.remove(key);
					}
				}
			}
		}
		InstanciaDContenedor instanciaDContenedor = new InstanciaDContenedor(keyCache, instancia);
		String key = keyCache.getKeyCache();
		super.put(key, instanciaDContenedor);
	}

	void eliminarInstancia(KeyCache keyCache) {
		Boolean swDeleteable = keyCache.getSwDeleteable();
		if (swDeleteable.equals(Boolean.valueOf(true))) {
			String key = keyCache.getKeyCache();
			super.remove(key);
		}
	}

	void eliminarInstancias() {
		InstanciaDContenedor[] instanciasDContenedor = super.values().toArray(new InstanciaDContenedor[0]);
		for (InstanciaDContenedor instanciaDContenedor : instanciasDContenedor) {
			KeyCache keyCache = instanciaDContenedor.getKeyCache();
			Boolean swDeleteable = keyCache.getSwDeleteable();
			if (swDeleteable.equals(Boolean.valueOf(true))) {
				String key = keyCache.getKeyCache();
				super.remove(key);
			}
		}
	}

}
