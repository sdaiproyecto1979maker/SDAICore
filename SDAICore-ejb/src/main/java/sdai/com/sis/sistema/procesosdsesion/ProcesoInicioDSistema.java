package sdai.com.sis.sistema.procesosdsesion;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.Serializable;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.DataSwapsLiteral;
import sdai.com.sis.dataswaps.DefaultDataSwap;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.procesosdsesion.ProcesoDSesionImplLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.procesosdsesion.ProcesosDSesion;

/**
 * @23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@ProcesosDSesion(KProcesosDSesion.ProcesosDSesion.PROCINISIS)
public class ProcesoInicioDSistema implements ProcesoDSesionLocal, Serializable {

    @Inject
    @Any
    private DefaultDataSwap dataSwapLocal;
    @Inject
    @Any
    private Instance<DataSwapLocal> instancias;

    @Override
    public void iniciar() {

    }

    @Override
    public String getCodigoDProceso() {
        return KProcesosDSesion.ProcesosDSesion.PROCINISIS;
    }

    @Override
    public String getPaginaDProceso() {
        return KProcesosDSesion.PaginasDProceso.PAGINAVOID;
    }

    @Override
    public DataSwapLocal getDataSwapLocal() {
        return this.dataSwapLocal;
    }

    @Override
    public void realizarValidaciones() throws ErrorGeneral {

    }

    @PreDestroy
    public void destroy() {
        String codigoDDataSwap = this.dataSwapLocal.getCodigoDDataSwap();
        DataSwapsLiteral dataSwapsLiteral = DataSwapsLiteral.of(codigoDDataSwap);
        Instance<DataSwapLocal> instancia = this.instancias.select(dataSwapsLiteral);
        instancia.destroy(this.dataSwapLocal);
    }

    @Override
    public void setProcesoDSesion(ProcesoDSesionImplLocal procesoDSesion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
