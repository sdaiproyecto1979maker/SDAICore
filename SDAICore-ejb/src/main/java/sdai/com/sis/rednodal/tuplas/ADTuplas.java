package sdai.com.sis.rednodal.tuplas;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import sdai.com.sis.conexiones.PoolDConexionesLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADTuplas {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;

}
