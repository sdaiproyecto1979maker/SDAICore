package sdai.com.sis.dsentidades;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sdai.com.sis.beans.BeanDVistaImplLocal;
import sdai.com.sis.beans.BeanDVistaLocal;
import sdai.com.sis.beans.BeansUtil;
import sdai.com.sis.dsentidades.rednodal.DSEntidadesUtil;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.gruposdvalidacion.ValidadorDIntegridadLocal;
import sdai.com.sis.utilidades.Transform;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractDSEntidad implements DSEntidadLocal, Serializable {

    private DSEntidadImplLocal dSEntidadImplLocal;
    @Inject
    private DSEntidadesUtil dSEntidadesUtil;
    @Inject
    private ValidadorDIntegridadLocal validadorDIntegridadLocal;
    private final Map<String, Object> almacenDDatos;

    public AbstractDSEntidad() {
        this.almacenDDatos = new HashMap<>();
    }

    @Override
    public void setDSEntidadImplLocal(DSEntidadImplLocal dSEntidadImplLocal) {
        this.dSEntidadImplLocal = dSEntidadImplLocal;
    }

    @Override
    public void validarIntegridad() throws ErrorGeneral {
        String codigoDEntidad = getCodigoDEntidad();
        BeanDVistaImplLocal[] beansDVista = this.dSEntidadesUtil.getBeansDVista(codigoDEntidad);
        for (BeanDVistaImplLocal beanDVista : beansDVista) {
            String named = beanDVista.getCodigoDNamed();
            Object bean = BeansUtil.getBeanDVista(named);
            if (bean != null) {
                this.validadorDIntegridadLocal.validar(bean, NotBlank.class, Size.class);
            }
        }
    }

    @Override
    public void generateDSEntidad() {
        String codigoDEntidad = getCodigoDEntidad();
        BeanDVistaImplLocal[] beansDVista = this.dSEntidadesUtil.getBeansDVista(codigoDEntidad);
        for (BeanDVistaImplLocal beanDVista : beansDVista) {
            String named = beanDVista.getCodigoDNamed();
            BeanDVistaLocal bean = (BeanDVistaLocal) BeansUtil.getBeanDVista(named);
            if (bean != null) {
                bean.addDatosDEntidad(this);
            }
        }
    }

    @Override
    public String getValorStringOriginal(String key) {
        Object value = this.almacenDDatos.get(key);
        return Transform.toString(value);
    }

    @Override
    public String getValorString(String key) {
        String value = getValorStringOriginal(key);
        return value.toUpperCase();
    }

    @Override
    public void addValorDDato(String key, Object value) {
        this.almacenDDatos.put(key, value);
    }

    @Override
    public String getCodigoDEntidad() {
        return this.dSEntidadImplLocal.getCodigoDEntidad();
    }

    @Override
    public DSEntidadImplLocal getDSEntidadImplLocal() {
        return this.dSEntidadImplLocal;
    }

}
