package sdai.com.sis.seguridad.sistemasdreglas;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.reglasbasicas.ReglaBasicaLocal;
import sdai.com.sis.seguridad.reglasbasicas.RuleValidacionDPassword;
import sdai.com.sis.seguridad.usuarios.ADUsuarios;
import sdai.com.sis.seguridad.usuarios.AtributoDUsuario;
import sdai.com.sis.seguridad.usuarios.KUsuarios;
import sdai.com.sis.seguridad.usuarios.SecretoDUsuario;
import sdai.com.sis.seguridad.usuarios.Usuario;
import sdai.com.sis.sesionesdusuario.SesionDUsuario;
import sdai.com.sis.sistema.AtributosDSistema;
import sdai.com.sis.sistema.KSistema;
import sdai.com.sis.sistemasdreglas.AbstractDSResponse;
import sdai.com.sis.sistemasdreglas.DSResponses;
import sdai.com.sis.sistemasdreglas.KSistemasDReglas;
import sdai.com.sis.traducciones.Traductor;
import sdai.com.sis.utilidades.FacesUtil;
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.utilidades.Transform;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
@DSResponses(KSistemasDReglas.Responses.RESPOLOGIN)
public class DSResponseLogin extends AbstractDSResponse {

    @Inject
    private ADUsuarios adUsuarios;
    @Inject
    private GlobalCaches globalCaches;
    @Inject
    private AtributosDSistema atributosDSistema;
    @Inject
    private Traductor traductor;
    @Inject
    private SesionDUsuario sesionDUsuario;

    public Usuario getUsuario() {
        Usuario usuario = (Usuario) getObjectResponse(Usuario.class.getName());
        return usuario;
    }

    @Override
    public void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        Usuario usuario = getUsuario();
        deleteUsuarioCache(usuario);
        AtributoDUsuario atributoDUsuario = getAtributoDUsuario(usuario, KUsuarios.AtributosDUsuario.AtributosUsuario.NMINTENTOS);
        if (atributoDUsuario == null) {
            atributoDUsuario = this.adUsuarios.persistAtributoDUsuario(usuario, KUsuarios.AtributosDUsuario.AtributosUsuario.NMINTENTOS, "0");
            usuario.getAtributosDUsuario().add(atributoDUsuario);
            this.adUsuarios.mergeUsuario(usuario);
        } else {
            atributoDUsuario.setValorDAtributo("0");
            this.adUsuarios.mergeAtributoDUsuario(atributoDUsuario);
        }
        SecretoDUsuario secretoDUsuario = usuario.getSecretoDUsuario();
        LocalDate fechaDCaducidad = secretoDUsuario.getFechaDCaducidad();
        if (fechaDCaducidad == null) {
            Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
            almacenDSesion.put(KProcesosDSesion.NEWPROCESO, KProcesosDSesion.ProcesosDSesion.PRSWTCHPAS);
            almacenDSesion.put(KProcesosDSesion.DNEWPAGINA, KProcesosDSesion.PaginasDProceso.PLANTILLAS);
        } else {
            Fecha fecha = Fecha.getInstancia(fechaDCaducidad);
            Fecha fechaDSistema = Fecha.getFechaDSistema();
            if (fecha.isMenor(fechaDSistema)) {
                Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
                almacenDSesion.put(KProcesosDSesion.NEWPROCESO, KProcesosDSesion.ProcesosDSesion.PRSWTCHPAS);
                almacenDSesion.put(KProcesosDSesion.DNEWPAGINA, KProcesosDSesion.PaginasDProceso.PLANTILLAS);
            }
        }
        this.sesionDUsuario.setUsuario(usuario);
    }

    @Override
    public ErrorGeneral tratarErrorResponse(ReglaBasicaLocal reglaBasicaLocal) {
        Usuario usuario = getUsuario();
        if (usuario != null) {
            deleteUsuarioCache(usuario);
            if (reglaBasicaLocal instanceof RuleValidacionDPassword) {
                AtributoDUsuario atributoDUsuario = getAtributoDUsuario(usuario, KUsuarios.AtributosDUsuario.AtributosUsuario.NMINTENTOS);
                if (atributoDUsuario == null) {
                    atributoDUsuario = this.adUsuarios.persistAtributoDUsuario(usuario, KUsuarios.AtributosDUsuario.AtributosUsuario.NMINTENTOS, "1");
                    usuario.getAtributosDUsuario().add(atributoDUsuario);
                    this.adUsuarios.mergeUsuario(usuario);
                } else {
                    Integer numeroDIntentos = Transform.toInteger(atributoDUsuario.getValorDAtributo());
                    Integer numeroDIntentosApp = this.atributosDSistema.getValorInteger(KSistema.AtributosDSistema.NMINTLOGIN);
                    if (numeroDIntentos >= numeroDIntentosApp) {
                        usuario.setIsBloqueado(Boolean.TRUE);
                        this.adUsuarios.mergeUsuario(usuario);
                        String mensaje = this.traductor.traducir("NMINTENTOS");
                        ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Login", mensaje);
                        return errorGeneral;
                    } else {
                        numeroDIntentos++;
                        atributoDUsuario.setValorDAtributo(Transform.toString(numeroDIntentos));
                        this.adUsuarios.mergeAtributoDUsuario(atributoDUsuario);
                    }
                }
            }
        }
        return null;
    }

    private AtributoDUsuario getAtributoDUsuario(Usuario usuario, String nombreDAtributo) {
        List<AtributoDUsuario> atributosDUsuario = usuario.getAtributosDUsuario();
        if (atributosDUsuario.isEmpty()) {
            return null;
        }
        for (AtributoDUsuario atributoDUsuario : atributosDUsuario) {
            if (atributoDUsuario.getNombreDAtributo().equals(nombreDAtributo)) {
                return atributoDUsuario;
            }
        }
        return null;
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
