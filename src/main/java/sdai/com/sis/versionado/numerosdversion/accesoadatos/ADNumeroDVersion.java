package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
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

	NumeroDVersion getNumeroDVersion(String numeroDVersion, String codigoDProyectoDAplicacion) throws Exception {
		IdQuery idQuery = new IdQuery(KVersionado.KNumerosDVersion.NamedQueries.SNUVER0000);
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERRELEASE, NumerosDVersionUtil.getVersionRelease(numeroDVersion));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERFEATURE, NumerosDVersionUtil.getVersionFeature(numeroDVersion));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSIONFIX, NumerosDVersionUtil.getVersionFix(numeroDVersion));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSHOTFIX, NumerosDVersionUtil.getVersionHotfix(numeroDVersion));
		idQuery.addParametroDQuery(KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.CODPROYECT, codigoDProyectoDAplicacion);
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
