package sdai.com.sis.versionado;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;

import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.versionesdproyecto.KVersionesDProyecto;
import sdai.com.sis.versionado.versionesdproyecto.VersionesDProyectoUtil;
import sdai.com.sis.versionado.versionesdproyecto.accesoadatos.VersionDProyecto;

/**
 * date 09/03/2025
 * 
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class VersionesDAplicacion {

	private static VersionesDAplicacion instancia;
	private final ServletContext servletContext;
	private final ConcurrentMap<String, NumeroDVersion> numerosDVersion;

	private VersionesDAplicacion(ServletContext servletContext) {
		this.servletContext = servletContext;
		this.numerosDVersion = new ConcurrentHashMap<String, NumeroDVersion>();
	}

	public static void createInstancia(ServletContext servletContext) throws Throwable {
		if (VersionesDAplicacion.instancia == null) {
			synchronized (VersionesDAplicacion.class) {
				if (VersionesDAplicacion.instancia == null) {
					VersionesDAplicacion.instancia = new VersionesDAplicacion(servletContext);
					VersionesDAplicacion.instancia.load();
				}
			}
		}
	}

	public static VersionesDAplicacion getInstancia() {
		return VersionesDAplicacion.instancia;
	}

	private void load() throws Throwable {
		VersionDProyecto versionDProyectoDCore = VersionDProyecto.getCreateInstancia(KVersionesDProyecto.VERSIDCORE);
		String versionDCore = this.servletContext.getInitParameter(KVersionesDProyecto.VERSIDCORE);
		NumeroDVersion numeroDVersion = VersionesDProyectoUtil.getNumeroDVersion(versionDCore, KVersionesDProyecto.VERSIDCORE);
		VersionesDProyectoUtil.ordenarNumerosDVersion(versionDProyectoDCore);
		this.numerosDVersion.put(KVersionesDProyecto.VERSIDCORE, numeroDVersion);
		VersionDProyecto versionDProyectoDFramework = VersionDProyecto.getCreateInstancia(KVersionesDProyecto.VERSFRMWRK);
		String versionDFramework = this.servletContext.getInitParameter(KVersionesDProyecto.VERSFRMWRK);
		if (Util.isCadenaNoVacia(versionDFramework)) {
			numeroDVersion = VersionesDProyectoUtil.getNumeroDVersion(versionDFramework, KVersionesDProyecto.VERSFRMWRK);
			VersionesDProyectoUtil.ordenarNumerosDVersion(versionDProyectoDFramework);
			this.numerosDVersion.put(KVersionesDProyecto.VERSFRMWRK, numeroDVersion);
		}
		VersionDProyecto versionDProyectoCustom = VersionDProyecto.getCreateInstancia(KVersionesDProyecto.VERSCUSTOM);
		String versionCustom = this.servletContext.getInitParameter(KVersionesDProyecto.VERSCUSTOM);
		numeroDVersion = VersionesDProyectoUtil.getNumeroDVersion(versionCustom, KVersionesDProyecto.VERSCUSTOM);
		VersionesDProyectoUtil.ordenarNumerosDVersion(versionDProyectoCustom);
		this.numerosDVersion.put(KVersionesDProyecto.VERSCUSTOM, numeroDVersion);
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public NumeroDVersion getNumeroDVersionDCore() {
		NumeroDVersion numeroDVersion = this.numerosDVersion.get(KVersionesDProyecto.VERSIDCORE);
		return numeroDVersion;
	}

	public NumeroDVersion getNumeroDVersionDFramework() {
		NumeroDVersion numeroDVersion = this.numerosDVersion.get(KVersionesDProyecto.VERSFRMWRK);
		return numeroDVersion;
	}

	public NumeroDVersion getNumeroDVersionCustom() {
		NumeroDVersion numeroDVersion = this.numerosDVersion.get(KVersionesDProyecto.VERSCUSTOM);
		return numeroDVersion;
	}

}
