package sdai.com.sis.cacchesdsistema;

import java.util.concurrent.ConcurrentHashMap;

import sdai.com.sis.accesoadatos.IEntidadCFG;
import sdai.com.sis.utilidades.Reflexion;

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

	void eliminarInstancia(KeyCache keyCache) throws Exception {
		Boolean swDeleteable = keyCache.getSwDeleteable();
		if (swDeleteable.equals(Boolean.valueOf(true))) {
			if (isEntidadCFG(keyCache)) {
				String key = keyCache.getKeyCache();
				InstanciaDContenedor instanciaDContenedor = super.get(key);
				Class<?> clase = keyCache.getClase();
				Object instance = Reflexion.createInstancia(clase.getName());
				if (instance instanceof IEntidadCFG)
					Reflexion.invokeMetodo(instance, "deleteCacheInstanciaArray", instanciaDContenedor);
			} else {
				String key = keyCache.getKeyCache();
				super.remove(key);
			}
		}
	}

	void eliminarInstancias() throws Exception {
		InstanciaDContenedor[] instanciasDContenedor = super.values().toArray(new InstanciaDContenedor[0]);
		for (InstanciaDContenedor instanciaDContenedor : instanciasDContenedor) {
			KeyCache keyCache = instanciaDContenedor.getKeyCache();
			Boolean swDeleteable = keyCache.getSwDeleteable();
			if (swDeleteable.equals(Boolean.valueOf(false)))
				continue;
			if (isEntidadCFG(keyCache)) {
				Class<?> clase = keyCache.getClase();
				Object instance = Reflexion.createInstancia(clase.getName());
				if (instance instanceof IEntidadCFG)
					Reflexion.invokeMetodo(instance, "deleteCacheInstanciaArray", instanciaDContenedor);
			}
			String key = keyCache.getKeyCache();
			super.remove(key);
		}
	}

	private Boolean isEntidadCFG(KeyCache keyCache) {
		Class<?> clase = keyCache.getClase();
		Class<?> superClass = clase.getSuperclass();
		Class<?>[] interfaces = superClass.getInterfaces();
		for (Class<?> interfaz : interfaces) {
			String className = interfaz.getName();
			if (className.equals(IEntidadCFG.class.getName()))
				return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

}
