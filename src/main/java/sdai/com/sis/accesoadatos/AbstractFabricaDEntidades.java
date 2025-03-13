package sdai.com.sis.accesoadatos;

import sdai.com.sis.conexiones.IdConexion;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractFabricaDEntidades implements IFabricaDEntidades {

	private final IdConexion idConexion;

	protected AbstractFabricaDEntidades(Integer entornoDConexion) throws Exception {
		this.idConexion = IdConexion.getInstancia(entornoDConexion);
	}

	public IdConexion getIdConexion() {
		return idConexion;
	}

}
