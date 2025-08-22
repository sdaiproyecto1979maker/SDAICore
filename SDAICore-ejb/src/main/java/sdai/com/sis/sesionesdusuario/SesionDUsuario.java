package sdai.com.sis.sesionesdusuario;

import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.StringTokenizer;
import sdai.com.sis.utilidades.Util;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
public class SesionDUsuario implements SesionDUsuarioLocal, Serializable {

    private Locale locale;

    @Override
    public Locale getLocale() {
        if (this.locale == null) {
            this.locale = Locale.getDefault();
        }
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        StringTokenizer stringTokenizer = new StringTokenizer(locale, "_");
        int numeroDTokens = stringTokenizer.countTokens();
        int contador = 0;
        String[] tokens = new String[numeroDTokens];
        while (stringTokenizer.hasMoreElements()) {
            tokens[contador] = stringTokenizer.nextToken();
            contador++;
        }
        switch (tokens.length) {
            case 3:
                this.locale = new Locale(tokens[0], tokens[1], tokens[2]);
                break;
            case 2:
                this.locale = new Locale(tokens[0], tokens[1]);
                break;
            default:
                this.locale = new Locale(tokens[0]);
                break;
        }
    }

    private String getKeyLocale() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage());
        if (Util.isCadenaNoVacia(locale.getCountry())) {
            stringBuilder.append("_");
            stringBuilder.append(locale.getCountry());
        }
        if (Util.isCadenaNoVacia(locale.getVariant())) {
            stringBuilder.append("_");
            stringBuilder.append(locale.getVariant());
        }
        return stringBuilder.toString();
    }

    @Override
    public Boolean isLocaleSeleccionado(String key) {
        return key.equals(getKeyLocale());
    }

}
