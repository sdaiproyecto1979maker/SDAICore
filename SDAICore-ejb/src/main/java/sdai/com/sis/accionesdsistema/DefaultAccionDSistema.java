package sdai.com.sis.accionesdsistema;

import jakarta.enterprise.context.RequestScoped;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
@AccionesDSistema(KAccionesDSistema.AccionesDSistema.DEFAACCION)
public class DefaultAccionDSistema extends AbstractAccionDSistema {

    @Override
    protected void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral {

    }

}
