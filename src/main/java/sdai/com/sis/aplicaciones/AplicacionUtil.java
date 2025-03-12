package sdai.com.sis.aplicaciones;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AplicacionUtil {

	public static Map<String, Object> getAlmacenDRequest() {
		ExternalContext externalContext = AplicacionUtil.getExternalContext();
		if (externalContext == null)
			return new HashMap<String, Object>();
		Map<String, Object> almacen = externalContext.getRequestMap();
		return almacen;
	}

	public static Map<String, Object> getAlmacenDVista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null)
			return new HashMap<String, Object>();
		UIViewRoot viewRoot = facesContext.getViewRoot();
		Map<String, Object> almacen = viewRoot.getViewMap();
		return almacen;
	}

	public static Map<String, Object> getAlmacenDSesion() {
		ExternalContext externalContext = AplicacionUtil.getExternalContext();
		if (externalContext == null)
			return new HashMap<String, Object>();
		Map<String, Object> almacen = externalContext.getSessionMap();
		return almacen;
	}

	public static Map<String, Object> getAlmacenDAplicacion() {
		ExternalContext externalContext = AplicacionUtil.getExternalContext();
		if (externalContext == null)
			return new HashMap<String, Object>();
		Map<String, Object> almacen = externalContext.getApplicationMap();
		return almacen;
	}

	public static ExternalContext getExternalContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null)
			return null;
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext;
	}

}
