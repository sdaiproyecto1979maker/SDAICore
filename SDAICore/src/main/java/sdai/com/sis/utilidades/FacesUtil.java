package sdai.com.sis.utilidades;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import sdai.com.sis.beans.BeanDVistaLocal;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class FacesUtil {

    public static String getContextPath() {
        ExternalContext externalContext = FacesUtil.getExternalContext();
        String contextPath = externalContext.getApplicationContextPath();
        return contextPath;
    }

    public static ServletContext getServletContext() {
        HttpServletRequest httpServletRequest = FacesUtil.getRequest();
        ServletContext servletContext = httpServletRequest.getServletContext();
        return servletContext;
    }

    public static HttpServletRequest getRequest() {
        ExternalContext externalContext = FacesUtil.getExternalContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) externalContext.getRequest();
        return httpServletRequest;
    }

    public static ExternalContext getExternalContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return externalContext;
    }

    public static void redirect(String pagina) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FacesUtil.getContextPath());
        stringBuilder.append("/faces");
        stringBuilder.append(pagina);
        ExternalContext externalContext = FacesUtil.getExternalContext();
        externalContext.redirect(stringBuilder.toString());
    }

    public static UIComponent getCurrentComponent() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(facesContext);
        return component;
    }

    public static Map<String, String> getParametrosDRequest() {
        ExternalContext externalContext = FacesUtil.getExternalContext();
        Map<String, String> parametrosDRequest = externalContext.getRequestParameterMap();
        return parametrosDRequest;
    }

    public static Map<String, Object> getAlmacenDSesion() {
        ExternalContext externalContext = FacesUtil.getExternalContext();
        Map<String, Object> almacenDSesion = externalContext.getSessionMap();
        return almacenDSesion;
    }

    public static Map<String, Object> getAlmacenDVista() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = facesContext.getViewRoot();
        Map<String, Object> almacenDVista = viewRoot.getViewMap();
        return almacenDVista;
    }

    public static BeanDVistaLocal getBeanDVistaLocal(String codigoDNamed) {
        Map<String, Object> almacenDVista = FacesUtil.getAlmacenDVista();
        if (almacenDVista.containsKey(codigoDNamed)) {
            return (BeanDVistaLocal) almacenDVista.get(codigoDNamed);
        }
        return null;
    }

}
