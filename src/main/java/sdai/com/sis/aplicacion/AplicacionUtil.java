package sdai.com.sis.aplicacion;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public abstract class AplicacionUtil {

	public static FacesContext getFacesContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext;
	}

	public static ExternalContext getExternalContext() {
		FacesContext facesContext = AplicacionUtil.getFacesContext();
		if (facesContext == null)
			return null;
		return facesContext.getExternalContext();
	}

	public static Map<String, Object> getAlmacenDSesion() {
		ExternalContext externalContext = AplicacionUtil.getExternalContext();
		if (externalContext == null)
			return new HashMap<String, Object>();
		return externalContext.getSessionMap();
	}

}
