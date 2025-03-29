package sdai.com.sis.sesionesdusuario;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionListener;

import sdai.com.sis.cacchesdsistema.GlobalCaches;
import sdai.com.sis.versionado.elementosCFG.ElementosCFG;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.versionado.versionesCFG.VersionesCFG;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public class SessionListener implements ServletContextListener, HttpSessionListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ElementosCFG.getInstancia();
			ProyectosDAplicacion.getInstancia();
			VersionesCFG.getInstancia();
			GlobalCaches.getInstancia();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
