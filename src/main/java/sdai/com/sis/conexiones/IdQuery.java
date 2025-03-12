package sdai.com.sis.conexiones;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class IdQuery {

	private final String namedQuery;
	private final Map<String, Object> parametrosDQuery;

	public IdQuery(String namedQuery) {
		this.namedQuery = namedQuery;
		this.parametrosDQuery = new HashMap<>();
	}

	public String getNamedQuery() {
		return namedQuery;
	}

	public Map<String, Object> getParametrosDQuery() {
		return parametrosDQuery;
	}

	public void addParametroDQuery(String key, Object value) {
		this.parametrosDQuery.put(key, value);
	}

}
