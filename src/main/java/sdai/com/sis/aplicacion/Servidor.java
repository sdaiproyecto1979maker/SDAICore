package sdai.com.sis.aplicacion;

import java.net.InetAddress;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public final class Servidor {

	private static Servidor instancia;
	private final InetAddress inetAddress;

	private Servidor() throws Throwable {
		this.inetAddress = InetAddress.getLocalHost();
	}

	public static Servidor getInstancia() throws Throwable {
		if (Servidor.instancia == null) {
			synchronized (Servidor.class) {
				if (Servidor.instancia == null) {
					Servidor.instancia = new Servidor();
				}
			}
		}
		return Servidor.instancia;
	}

	public String getHostAddress() {
		return this.inetAddress.getHostAddress();
	}

}
