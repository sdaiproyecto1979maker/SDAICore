package sdai.com.sis.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Fecha {

    private LocalDate localDate;

    private Fecha() {
        this.localDate = LocalDate.now();
    }

    private Fecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.localDate = LocalDate.parse(fecha, formatter);
    }

    private Fecha(LocalDate localDate) {
        this.localDate = localDate;
    }

    public static Fecha getFechaDSistema() {
        return new Fecha();
    }

    public static Fecha getInstancia(String fecha) {
        return new Fecha(fecha);
    }

    public static Fecha getInstancia(LocalDate localDate) {
        return new Fecha(localDate);
    }

    public String toChar() {
        return toChar("dd/MM/yyyy");
    }

    public String toChar(String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        String cadena = this.localDate.format(formatter);
        return cadena;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

}
