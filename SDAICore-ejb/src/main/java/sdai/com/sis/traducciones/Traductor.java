package sdai.com.sis.traducciones;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sdai.com.sis.sesionesdusuario.SesionDUsuarioLocal;
import sdai.com.sis.utilidades.Util;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class Traductor implements TraductorLocal {

    private static final String PATHDCORE = "traducciones/IDIOMACORE_LANGUAJE.properties";
    private static final String PATHDFMWK = "traducciones/IDIOMAFMWK_LANGUAJE.properties";
    private static final String PATHDCSTM = "traducciones/IDIOMACSTM_LANGUAJE.properties";

    private static final String LANGUAJE = "LANGUAJE";

    private final List<String> listaDLocales;
    private final ConcurrentMap<String, Map<String, String>> traducciones;
    @Inject
    private SesionDUsuarioLocal sesionDUsuarioLocal;

    public Traductor() {
        this.listaDLocales = new ArrayList<>();
        this.traducciones = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void init() {
        try {
            loadListaDLocales();
            loadCore();
            loadFramework();
            loadCustom();
        } catch (IOException ex) {

        }
    }

    private void loadListaDLocales() {
        this.listaDLocales.add("es_ES");
        this.listaDLocales.add("en_EN");
    }

    private void loadCore() throws IOException {
        for (String languaje : this.listaDLocales) {
            String path = PATHDCORE.replace(LANGUAJE, languaje);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                loadLanguaje(inputStream, languaje);
            }
        }
    }

    private void loadFramework() throws IOException {
        for (String languaje : this.listaDLocales) {
            String path = PATHDFMWK.replace(LANGUAJE, languaje);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                loadLanguaje(inputStream, languaje);
            }
        }
    }

    private void loadCustom() throws IOException {
        for (String languaje : this.listaDLocales) {
            String path = PATHDCSTM.replace(LANGUAJE, languaje);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                loadLanguaje(inputStream, languaje);
            }
        }
    }

    private void loadLanguaje(InputStream inputStream, String languaje) throws IOException {
        Map<String, String> almacen = this.traducciones.get(languaje);
        if (almacen == null) {
            almacen = new HashMap<>();
            this.traducciones.put(languaje, almacen);
        }
        Properties properties = new Properties();
        properties.load(inputStream);
        for (String key : properties.stringPropertyNames()) {
            almacen.put(key, properties.getProperty(key));
        }
    }

    @Override
    public String traducir(String key) {
        String keyLocale = getKeyLocale();
        Map<String, String> almacen = this.traducciones.get(keyLocale);
        if (almacen.containsKey(key)) {
            return almacen.get(key);
        }
        return "";
    }

    private String getKeyLocale() {
        Locale locale = this.sesionDUsuarioLocal.getLocale();
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
    public List<String> getListaDLocales() {
        return listaDLocales;
    }

}
