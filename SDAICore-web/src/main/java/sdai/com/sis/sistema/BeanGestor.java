package sdai.com.sis.sistema;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.component.outputlabel.OutputLabel;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
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

    private static final String PARMACCION = "PARMACCION";

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

    public String getTraduccion() {
        UIComponent component = FacesUtil.getCurrentComponent();
        String id = component.getId();
        return this.traductorLocal.traducir(id);
    }

    public String procesarAccion() {
        try {
            Map<String, String> parametrosDRequest = FacesUtil.getParametrosDRequest();
            String codigoDAccion = parametrosDRequest.get(PARMACCION);
            this.gestorDProcesosLocal.procesarAccion(codigoDAccion);
            Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
            String newPagina = (String) almacenDSesion.get(KProcesosDSesion.DNEWPAGINA);
            if (Util.isCadenaNoVacia(newPagina)) {
                almacenDSesion.remove(KProcesosDSesion.DNEWPAGINA);
                return newPagina;
            }
            return KProcesosDSesion.PaginasDProceso.PLANTILLAM;
        } catch (ErrorGeneral ex) {
            FacesMessage facesMessage = new FacesMessage(ex.getSeverity(), ex.getSummary(), ex.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(facesMessage, true);
            return "";
        }
    }

}
