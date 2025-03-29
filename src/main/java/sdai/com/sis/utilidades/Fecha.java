package sdai.com.sis.utilidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class Fecha {

	private final LocalDate localDate;

	private Fecha() {
		this.localDate = LocalDate.now();
	}

	public static Fecha getFechaDSistema() {
		return new Fecha();
	}

	public Date toDate() {
		Date date = Date.from(this.localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}

}
