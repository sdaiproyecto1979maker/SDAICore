package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.versionado.KVersionado;

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

	NumeroDVersion getNumeroDVersion(String numeroDVersion, String codigoDProyectoDAplicacion) throws Exception {
		String numeroVersion = "";
		StringTokenizer stringTokenizer = new StringTokenizer(numeroDVersion, "-");
		Integer contador = Integer.valueOf(0);
		while (stringTokenizer.hasMoreElements()) {
			if (contador.equals(Integer.valueOf(0)))
				numeroVersion = stringTokenizer.nextToken();
		}
		Map<Integer, Integer> almacen = new HashMap<Integer, Integer>();
		stringTokenizer = new StringTokenizer(numeroVersion, ".");
		contador = Integer.valueOf(1);
		while (stringTokenizer.hasMoreElements()) {
			almacen.put(contador, Transform.toInteger(stringTokenizer.nextToken()));
			contador++;
		}
		IdQuery idQuery = new IdQuery(KVersionado.KNumerosDVersion.NamedQueries.SNUVER0000);
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERRELEASE, almacen.get(Integer.valueOf(1)));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERFEATURE, almacen.get(Integer.valueOf(2)));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSIONFIX, almacen.get(Integer.valueOf(3)));
		idQuery.addParametroDQuery(KVersionado.KNumerosDVersion.AtributosDEntidad.VERSHOTFIX, almacen.get(Integer.valueOf(4)));
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
