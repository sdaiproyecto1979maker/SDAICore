package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Named
@ApplicationScoped
public class BeanSistema {

    @Inject
    private AtributosDSistemaLocal atributosDSistemaLocal;
    private String nombreDSistema;
    private String pathEstilosCSS;

    @PostConstruct
    public void init() {
        this.nombreDSistema = this.atributosDSistemaLocal.getValorString(KSistema.AtributosDSistema.NOMBRSISTE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FacesUtil.getContextPath());
        stringBuilder.append("/estilos/estilos.css");
        this.pathEstilosCSS = stringBuilder.toString();
    }

    public String getNombreDSistema() {
        return nombreDSistema;
    }

    public String getPathEstilosCSS() {
        return pathEstilosCSS;
    }

}
