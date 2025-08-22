package sdai.com.sis.sesionesdusuario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.traducciones.TraductorLocal;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Named
@SessionScoped
public class BeanSesionDUsuario implements Serializable {
    
    private String codigoDIdioma;
    @Inject
    private TraductorLocal traductorLocal;
    @Inject
    private SesionDUsuarioLocal sesionDUsuarioLocal;
    
    public boolean isMostrarIdiomas() {
        List<String> lista = this.traductorLocal.getListaDLocales();
        return lista.size() > 1;
    }
    
    public List<SelectItem> getListaDIdiomas() {
        List<SelectItem> lista = new ArrayList<>();
        List<String> listaDLocales = this.traductorLocal.getListaDLocales();
        for (String locale : listaDLocales) {
            if (!this.sesionDUsuarioLocal.isLocaleSeleccionado(locale)) {
                String label = this.traductorLocal.traducir(locale);
                SelectItem selectItem = new SelectItem(locale, label);
                lista.add(selectItem);
            }
        }
        return lista;
    }
    
    public void cambiarIdioma() {
        this.sesionDUsuarioLocal.setLocale(this.codigoDIdioma);
    }
    
    public String getCodigoDIdioma() {
        return codigoDIdioma;
    }
    
    public void setCodigoDIdioma(String codigoDIdioma) {
        this.codigoDIdioma = codigoDIdioma;
    }
    
}
