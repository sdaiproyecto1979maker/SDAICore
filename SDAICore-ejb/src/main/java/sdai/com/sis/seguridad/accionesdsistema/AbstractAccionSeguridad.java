package sdai.com.sis.seguridad.accionesdsistema;

import sdai.com.sis.accionesdsistema.AbstractAccionDSistema;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractAccionSeguridad extends AbstractAccionDSistema {

    @Override
    protected void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        DataSwapSeguridad dataSwapSeguridad = (DataSwapSeguridad) dataSwapLocal;
        procesar(dataSwapSeguridad);
    }

    protected abstract void procesar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral;

}
