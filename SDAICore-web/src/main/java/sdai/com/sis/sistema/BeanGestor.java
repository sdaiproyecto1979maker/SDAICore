package sdai.com.sis.sistema;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.component.outputlabel.OutputLabel;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.traducciones.TraductorLocal;
import sdai.com.sis.utilidades.FacesUtil;
import sdai.com.sis.utilidades.Util;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Named
@RequestScoped
public class BeanGestor {

    @Inject
    private GestorDProcesosLocal gestorDProcesosLocal;
    @Inject
    private TraductorLocal traductorLocal;

    public String getPaginaDProceso() {
        ProcesoDSesionLocal procesoDSesionLocal = this.gestorDProcesosLocal.getProcesoDSesionLocal();
        String paginaDProceso = procesoDSesionLocal.getPaginaDProceso();
        return paginaDProceso;
    }

    public String getTraduccionLabel() {
        UIComponent component = FacesUtil.getCurrentComponent();
        if (component instanceof OutputLabel outputLabel) {
            String id = outputLabel.getFor();
            if (Util.isCadenaVacia(id)) {
                id = outputLabel.getId();
            }
            return this.traductorLocal.traducir(id);
        }
        return "";
    }

}
