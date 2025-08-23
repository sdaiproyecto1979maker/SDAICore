package sdai.com.sis.utilidades;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class EstructuraDatos {

    private final String nombreDEstructura;
    private final Map<String, Object> almacenDDatos;

    public EstructuraDatos(String nombreDEstructura) {
        this.nombreDEstructura = nombreDEstructura;
        this.almacenDDatos = new HashMap<>();
    }

    public void addDato(String key, Object value) {
        this.almacenDDatos.put(key, value);
    }

    public String getNombreDEstructura() {
        return nombreDEstructura;
    }

}
