package sdai.com.sis.excepciones;

import jakarta.faces.application.FacesMessage;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ErrorGeneral extends Throwable {

    private FacesMessage.Severity severity;
    private String summary;

    public ErrorGeneral(String mensaje) {
        super(mensaje);
    }

    public ErrorGeneral(FacesMessage.Severity severity, String summary, String mensaje) {
        super(mensaje);
        this.severity = severity;
        this.summary = summary;
    }

    public FacesMessage.Severity getSeverity() {
        return severity;
    }

    public String getSummary() {
        return summary;
    }

}
