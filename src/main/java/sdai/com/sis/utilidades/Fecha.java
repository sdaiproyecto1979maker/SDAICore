package sdai.com.sis.utilidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
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
