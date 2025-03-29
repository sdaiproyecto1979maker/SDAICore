package sdai.com.sis.cacchesdsistema;

import sdai.com.sis.utilidades.Hora;

/**
 * @date 26/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class InstanciaDContenedor {

	private final KeyCache keyCache;
	private final Object instancia;
	private final Hora horaDCreacion;

	InstanciaDContenedor(KeyCache keyCache, Object instancia) {
		this.keyCache = keyCache;
		this.instancia = instancia;
		this.horaDCreacion = Hora.getHoraDSistema();
	}

	Boolean isInstanciaCaducada(Integer minutosEnContenedor) {
		Hora horaDSistema = Hora.getHoraDSistema();
		Integer minutosTranscurridos = this.horaDCreacion.getMinutosTranscurridos(horaDSistema);
		return minutosTranscurridos > minutosEnContenedor;
	}

	public KeyCache getKeyCache() {
		return keyCache;
	}

	public Object getInstancia() {
		return instancia;
	}

	public Hora getHoraDCreacion() {
		return horaDCreacion;
	}

}
