package sdai.com.sis.servidor.rednodal;

import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.AbstractElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodo;
import sdai.com.sis.servidor.KParametrosDProyecto;
import sdai.com.sis.servidor.ParametrosDProyecto;

/**
 * @date 27/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class InstanciaSDAI extends AbstractElementoDRed {

	private static final String CODIGONODO = "INSTANSDAI";

	private static final String CODIINSTAN = "CODIINSTAN";
	private static final String HORAGESCHS = "HORAGESCHS";

	private InstanciaSDAI(String codigoDInstancia) throws Exception {
		super(CODIGONODO);
		TuplaDNodo tuplaDNodo = getTuplaDNodo(CODIINSTAN, codigoDInstancia);
		setTuplaDNodo(tuplaDNodo);
	}

	public static InstanciaSDAI getInstancia() throws Exception {
		ParametrosDProyecto parametrosDProyecto = ParametrosDProyecto.getInstancia();
		String codigoDInstancia = parametrosDProyecto.getValorDParametroString(KParametrosDProyecto.CODIINSTAN);
		return InstanciaSDAI.getInstancia(codigoDInstancia);
	}

	public static InstanciaSDAI getInstancia(String codigoDInstancia) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(InstanciaSDAI.class, codigoDInstancia);
		InstanciaSDAI instancia = (InstanciaSDAI) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			instancia = new InstanciaSDAI(codigoDInstancia);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	public String getCodigoDInstancia() {
		return getValorString(CODIINSTAN);
	}

	public Integer getHoraGestionDCaches() {
		return getValorInteger(HORAGESCHS);
	}

}
