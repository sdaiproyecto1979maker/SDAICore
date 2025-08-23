package sdai.com.sis.rednodal.datosdsistema;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import sdai.com.sis.conexiones.PoolDConexionesLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADDatosDSistema {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;

}
