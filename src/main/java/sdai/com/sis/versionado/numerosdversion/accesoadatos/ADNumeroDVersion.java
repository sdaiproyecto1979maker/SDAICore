package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADNumeroDVersion extends AbstractAccesoADatos {

	ADNumeroDVersion() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADNumeroDVersion(String idDConexion) throws Exception {
		super(idDConexion);
	}

	NumeroDVersion getNumeroDVersion(IProyecto proyecto) throws Exception {
		IdQuery idQuery = new IdQuery(KVersionado.KNumerosDVersion.NamedQueries.SNUVER0000);
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERRELEASE, NumerosDVersionUtil.getVersionRelease(proyecto));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERFEATURE, NumerosDVersionUtil.getVersionFeature(proyecto));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSIONFIX, NumerosDVersionUtil.getVersionFix(proyecto));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSHOTFIX, NumerosDVersionUtil.getVersionHotfix(proyecto));
		idQuery.addParametroDQuery(KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.CODPROYECT, proyecto.getCodigoDProyecto());
		NumeroDVersion entidad = (NumeroDVersion) ejecutarConsultaSimple(idQuery);
		return entidad;
	}

	NumeroDVersion getNumeroDVersion(Long identificador) throws Exception {
		IdQuery idQuery = new IdQuery(KVersionado.KNumerosDVersion.NamedQueries.SNUVER0001);
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.IDNUMEVERS, identificador);
		NumeroDVersion numeroDVersion = (NumeroDVersion) ejecutarConsultaSimple(idQuery);
		return numeroDVersion;
	}

	void createNumeroDVersion(NumeroDVersion numeroDVersion) throws Exception {
		ejecutarPersist(numeroDVersion);
	}

	void updateNumeroDVersion(NumeroDVersion numeroDVersion) throws Exception {
		Long identificador = numeroDVersion.getIdentificador();
		Integer numeroDSituacion = numeroDVersion.getNumeroDSituacion();
		numeroDVersion = getNumeroDVersion(identificador);
		numeroDVersion.setNumeroDSituacion(numeroDSituacion);
		ejecutarMerge(numeroDVersion);
	}

}
