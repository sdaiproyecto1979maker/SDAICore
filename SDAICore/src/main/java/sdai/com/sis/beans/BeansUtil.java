package sdai.com.sis.beans;

import java.util.Map;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class BeansUtil {

    public static Object getBeanDVista(String named) {
        Map<String, Object> almacenDVista = FacesUtil.getAlmacenDVista();
        if (almacenDVista.containsKey(named)) {
            return almacenDVista.get(named);
        }
        return null;
    }

}
