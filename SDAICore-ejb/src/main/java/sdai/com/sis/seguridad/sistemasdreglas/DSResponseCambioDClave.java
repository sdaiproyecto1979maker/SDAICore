package sdai.com.sis.seguridad.sistemasdreglas;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.Map;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.seguridad.dataswaps.DSUsuario;
import sdai.com.sis.seguridad.usuarios.ADUsuarios;
import sdai.com.sis.seguridad.usuarios.KUsuarios;
import sdai.com.sis.seguridad.usuarios.SecretoDUsuario;
import sdai.com.sis.seguridad.usuarios.Usuario;
import sdai.com.sis.sesionesdusuario.SesionDUsuario;
import sdai.com.sis.sesionesdusuario.UsuarioDSesion;
import sdai.com.sis.sistemasdreglas.AbstractDSResponse;
import sdai.com.sis.sistemasdreglas.DSResponses;
import sdai.com.sis.sistemasdreglas.KSistemasDReglas;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
@DSResponses(KSistemasDReglas.Responses.RSSWTCHPAS)
public class DSResponseCambioDClave extends AbstractDSResponse {

    @Inject
    private SesionDUsuario sesionDUsuario;
    @Inject
    private ADUsuarios adUsuarios;
    @Inject
    private GlobalCaches globalCaches;

    @Override
    public void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        UsuarioDSesion usuarioDSesion = (UsuarioDSesion) this.sesionDUsuario.getUsuarioDSesionLocal();
        Usuario usuario = usuarioDSesion.getUsuario();
        deleteUsuarioCache(usuario);
        DSUsuario dsUsuario = (DSUsuario) dataSwapLocal.getDSEntidad(KUsuarios.CODIENTITY);
        String passwordDUsuario = dsUsuario.getPasswordDUsuario();
        SecretoDUsuario secretoDUsuario = this.adUsuarios.persistSecretoDUsuario(usuario, passwordDUsuario);
        usuario.setSecretoDUsuario(secretoDUsuario);
        usuario.getSecretosDUsuario().add(secretoDUsuario);
        this.adUsuarios.mergeUsuario(usuario);
        this.sesionDUsuario.setUsuario(usuario);
    }

    private void deleteUsuarioCache(Usuario usuario) {
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHESEGUR);
        KeyCache keyCache = KeyCache.getInstancia(Usuario.class, usuario.getCodigoDUsuario());
        Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
        String key = keyCache.getKeyCache();
        almacenDSesion.remove(key);
        cacheDSistemaLocal.eliminarInstancia(keyCache);
    }

}
