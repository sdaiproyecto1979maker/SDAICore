package sdai.com.sis.utilidades;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Hora {

	private final LocalDateTime localDateTime;

	private Hora() {
		this.localDateTime = LocalDateTime.now();
	}

	public static Hora getHoraDSistema() {
		return new Hora();
	}

	public Integer getMinutosDDiferencia(Hora hora) {
		Duration duration = Duration.between(getLocalDateTime(), hora.getLocalDateTime());
		Long segundos = duration.getSeconds();
		Integer minutosTranscurridos = Transform.toInteger(segundos / Long.valueOf(60));
		return minutosTranscurridos;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

}