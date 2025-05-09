package sdai.com.sis.sesionesdusuario;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import sdai.com.sis.sistema.DirectoriosDSistema;

/**
 * @date 07/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
public class SessionListener implements ServletContextListener {

    @Inject
    private SdaiCFG sdaiCFG;
    @Inject
    private DirectoriosDSistema directoriosDSistema;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ServletContext servletContext = servletContextEvent.getServletContext();
            sdaiCFG.init();
            directoriosDSistema.init();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
