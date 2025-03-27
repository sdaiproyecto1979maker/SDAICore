package sdai.com.sis.utilidades;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @date 12/03/2025
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

	public Integer getHora() {
		Integer hora = this.localDateTime.getHour();
		return hora;
	}

	public Integer getMinutosTranscurridos(Hora hora) {
		Duration duration = Duration.between(this.localDateTime, hora.getLocalDateTime());
		Long segundosTranscurridos = duration.getSeconds();
		Integer minutosTranscurridos = Transform.toInteger(segundosTranscurridos / Long.valueOf(60));
		return minutosTranscurridos;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

}
