package sdai.com.sis.reglasbasicas;

import java.util.Map;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.rednodal.ReglaBasicaImplLocal;
import sdai.com.sis.sistemasdreglas.DSResponseLocal;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractReglaBasica implements ReglaBasicaLocal {

    private ReglaBasicaImplLocal reglaBasicaImplLocal;

    @Override
    public void setReglaBasicaImplLocal(ReglaBasicaImplLocal reglaBasicaImplLocal) {
        this.reglaBasicaImplLocal = reglaBasicaImplLocal;
    }

    @Override
    public void evaluar(DSResponseLocal dSResponseLocal) throws ErrorGeneral {
        Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
        almacenDSesion.put(DSResponseLocal.class.getName(), dSResponseLocal);
    }

    protected DSResponseLocal getDSResponseLocal() {
        Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
        if (!almacenDSesion.containsKey(DSResponseLocal.class.getName())) {
            return null;
        }
        DSResponseLocal dSResponseLocal = (DSResponseLocal) almacenDSesion.get(DSResponseLocal.class.getName());
        return dSResponseLocal;
    }

}
