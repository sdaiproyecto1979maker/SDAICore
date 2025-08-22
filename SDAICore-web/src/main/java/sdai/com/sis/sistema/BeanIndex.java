package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Named
@RequestScoped
public class BeanIndex {

    @Inject
    private AtributosDSistemaLocal atributosDSistemaLocal;
    @Inject
    private GestorDProcesosLocal gestorDProcesosLocal;

    @PostConstruct
    public void init() {
        try {
            String codigoDProceso = this.atributosDSistemaLocal.getValorString(KSistema.AtributosDSistema.PROCINICIO);
            this.gestorDProcesosLocal.iniciar(codigoDProceso);
            FacesUtil.redirect(KProcesosDSesion.PaginasDProceso.PLANTILLAS);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getIndex() {
        return "";
    }

}
