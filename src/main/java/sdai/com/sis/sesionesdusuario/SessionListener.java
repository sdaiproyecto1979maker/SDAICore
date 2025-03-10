package sdai.com.sis.sesionesdusuario;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionListener;

import sdai.com.sis.versionado.VersionesDAplicacion;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class SessionListener implements ServletContextListener, HttpSessionListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ServletContext servletContext = servletContextEvent.getServletContext();
			VersionesDAplicacion.createInstancia(servletContext);
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
	}
}
