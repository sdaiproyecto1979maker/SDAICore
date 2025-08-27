package sdai.com.sis.seguridad.procesosdsesion;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.beans.BeansUtil;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.DataSwapsLiteral;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.gruposdvalidacion.ValidadorDIntegridadLocal;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImplLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.procesosdsesion.ProcesosDSesion;
import sdai.com.sis.rednodal.ElementoDRedImplLocal;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@ProcesosDSesion(KProcesosDSesion.ProcesosDSesion.PROCELOGIN)
public class ProcesoDLogin implements ProcesoDSesionLocal, Serializable {

    @Inject
    @Any
    private DataSwapSeguridad dataSwap;
    @Inject
    private ValidadorDIntegridadLocal validadorDIntegridadLocal;
    @Inject
    @Any
    private Instance<DataSwapLocal> instancias;

    @Override
    public void iniciar() {

    }

    @Override
    public String getCodigoDProceso() {
        return KProcesosDSesion.ProcesosDSesion.PROCELOGIN;
    }

    @Override
    public String getPaginaDProceso() {
        return KProcesosDSesion.PaginasDProceso.PROCELOGIN;
    }

    @Override
    public void realizarValidaciones() throws ErrorGeneral {
        try {
            List<Object> beans = new ArrayList<>();
            beans.add(BeansUtil.getBeanDVista("beanUsuarioLogin"));
            this.validadorDIntegridadLocal.validar(beans, NotBlank.class, Size.class);
        } catch (ErrorGeneral ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Proceso de login", ex.getMessage());
            throw errorGeneral;
        }
    }

    @Override
    public DataSwapLocal getDataSwapLocal() {
        return this.dataSwap;
    }

    @PreDestroy
    public void destroy() {
        String codigoDDataSwap = this.dataSwap.getCodigoDDataSwap();
        DataSwapsLiteral dataSwapsLiteral = DataSwapsLiteral.of(codigoDDataSwap);
        Instance<DataSwapLocal> instancia = this.instancias.select(dataSwapsLiteral);
        instancia.destroy(this.dataSwap);
    }

    @Override
    public void procesarAccion(String codigoDAccion) throws ErrorGeneral {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProcesoDSesionImplLocal getProcesoDSesionImplLocal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setElementoDRedImplLocal(ElementoDRedImplLocal elementoDRedImplLocal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
