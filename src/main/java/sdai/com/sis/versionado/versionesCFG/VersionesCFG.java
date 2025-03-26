package sdai.com.sis.versionado.versionesCFG;

import java.util.ArrayList;
import java.util.List;

import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class VersionesCFG {

	private static VersionesCFG instancia;
	private final List<IVersionCFG> versiones;

	private VersionesCFG() {
		this.versiones = new ArrayList<IVersionCFG>();
	}

	public static VersionesCFG getInstancia() throws Exception {
		if (VersionesCFG.instancia == null) {
			synchronized (VersionesCFG.class) {
				if (VersionesCFG.instancia == null) {
					VersionesCFG.instancia = new VersionesCFG();
					VersionesCFG.instancia.load();
				}
			}
		}
		return VersionesCFG.instancia;
	}

	private void load() throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		List<IProyecto> proyectos = proyectosDAplicacion.getProyectos();
		for (IProyecto proyecto : proyectos) {
			IVersionCFG versionCFG = new VersionCFG(proyecto);
			versionCFG.createNumerosDVersion();
			versionCFG.establecerVersionesInstalables();
			versionCFG.versionar();
			versionCFG.loadVersionEnCurso();
			this.versiones.add(versionCFG);
		}
	}

	public List<IVersionCFG> getVersiones() {
		return versiones;
	}

}
