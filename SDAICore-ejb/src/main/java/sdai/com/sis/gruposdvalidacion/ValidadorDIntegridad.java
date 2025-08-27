package sdai.com.sis.gruposdvalidacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Map;
import java.util.Set;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.traducciones.TraductorLocal;
import sdai.com.sis.utilidades.Transform;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class ValidadorDIntegridad implements ValidadorDIntegridadLocal {

    @Inject
    private Validator Validator;
    @Inject
    private TraductorLocal traductorLocal;

    @Override
    public void validar(Object bean, Class<?>... grupos) throws ErrorGeneral {
        for (Class<?> grupo : grupos) {
            Set<ConstraintViolation<Object>> violaciones = validarGrupo(bean, grupo);
            if (!violaciones.isEmpty()) {
                lanzarErrorGeneral(violaciones);
            }
        }
    }

    private Set<ConstraintViolation<Object>> validarGrupo(Object bean, Class<?> grupo) throws ErrorGeneral {
        Set<ConstraintViolation<Object>> violaciones = this.Validator.validate(bean, grupo);
        return violaciones;
    }

    private void lanzarErrorGeneral(Set<ConstraintViolation<Object>> violaciones) throws ErrorGeneral {
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<Object> v : violaciones) {
            rellenarMensaje(stringBuilder, v);
        }
        throw new ErrorGeneral(stringBuilder.toString());
    }

    private void rellenarMensaje(StringBuilder stringBuilder, ConstraintViolation<Object> v) {
        String mensaje = this.traductorLocal.traducir(v.getMessage());
        Map<String, Object> parametros = v.getConstraintDescriptor().getAttributes();
        for (Map.Entry<String, Object> mapa : parametros.entrySet()) {
            String key = "{" + mapa.getKey() + "}";
            String value = Transform.toString(mapa.getValue());
            if (mensaje.contains(key)) {
                mensaje = mensaje.replace(key, value);
            }
        }
        stringBuilder.append(mensaje);
        stringBuilder.append("\n");
    }

}
