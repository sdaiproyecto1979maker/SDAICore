package sdai.com.sis.procesosdsesion;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.Serializable;
import sdai.com.sis.accionesdsistema.AccionDSistemaLocal;
import sdai.com.sis.accionesdsistema.AccionesDSistemaLiteral;
import sdai.com.sis.accionesdsistema.KAccionesDSistema;
import sdai.com.sis.accionesdsistema.rednodal.AccionDSistemaImpl;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.dataswaps.DataSwapsLiteral;
import sdai.com.sis.dataswaps.KDataSwaps;
import sdai.com.sis.dataswaps.rednodal.DataSwapImpl;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.rednodal.AccionDProcesoImpl;
import sdai.com.sis.procesosdsesion.rednodal.DataSwapDProcesoImpl;
import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImplLocal;
import sdai.com.sis.utilidades.Util;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractProcesoDSesion implements ProcesoDSesionLocal, Serializable {

    private ProcesoDSesionImplLocal procesoDSesionImplLocal;
    @Inject
    @Any
    private Instance<DataSwapLocal> instancias;
    private DataSwapLocal dataSwapLocal;
    @Inject
    private GestorDProcesos gestorDProcesos;
    @Inject
    @Any
    private Instance<AccionDSistemaLocal> acciones;

    @Override
    public void setProcesoDSesionImplLocal(ProcesoDSesionImplLocal procesoDSesionImplLocal) {
        this.procesoDSesionImplLocal = procesoDSesionImplLocal;
    }

    @Override
    public void iniciar() throws ErrorGeneral {
        String codigoDProceso = getCodigoDProceso();
        String codigoDDataSwap;
        try {
            DataSwapDProcesoImpl dataSwapDProcesoImpl = DataSwapDProcesoImpl.getInstancia(codigoDProceso);
            codigoDDataSwap = dataSwapDProcesoImpl.getCodigoDDataSwap();
        } catch (ErrorGeneral ex) {
            codigoDDataSwap = KDataSwaps.DataSwaps.DFDATASWAP;
        }
        DataSwapImpl dataSwapImpl = DataSwapImpl.getInstancia(codigoDDataSwap);
        String codigoDQualifer = dataSwapImpl.getCodigoDQualifer();
        if (Util.isCadenaVacia(codigoDQualifer)) {
            codigoDQualifer = KDataSwaps.DataSwaps.DFDATASWAP;
        }
        DataSwapsLiteral dataSwapsLiteral = DataSwapsLiteral.of(codigoDQualifer);
        Instance<DataSwapLocal> instancia = this.instancias.select(dataSwapsLiteral);
        this.dataSwapLocal = instancia.get();
        this.dataSwapLocal.setDataSwapImplLocal(dataSwapImpl);
        this.dataSwapLocal.setProcesoDSesionLocal(this);
        this.dataSwapLocal.iniciar();
    }

    @Override
    public void realizarValidaciones() throws ErrorGeneral {
        DSEntidadLocal[] entidades = this.dataSwapLocal.getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            entidad.validarIntegridad();
        }
    }

    @Override
    public void procesarAccion(String codigoDAccion) throws ErrorGeneral {
        String codigoDQualifer;
        String codigoDProceso = getCodigoDProceso();
        AccionDSistemaImpl accionDSistemaImpl = null;
        AccionDProcesoImpl accionDProcesoImpl = AccionDProcesoImpl.getInstancia(codigoDProceso, codigoDAccion);
        if (accionDProcesoImpl != null) {
            codigoDQualifer = accionDProcesoImpl.getCodigoDQualifer();
        } else {
            accionDSistemaImpl = AccionDSistemaImpl.getInstancia(codigoDAccion);
            codigoDQualifer = accionDSistemaImpl.getCodigoDQualifer();
        }
        if (Util.isCadenaVacia(codigoDQualifer)) {
            codigoDQualifer = KAccionesDSistema.AccionesDSistema.DEFAACCION;
        }
        AccionesDSistemaLiteral literal = AccionesDSistemaLiteral.of(codigoDQualifer);
        Instance<AccionDSistemaLocal> instancia = this.acciones.select(literal);
        AccionDSistemaLocal accionDSistemaLocal = instancia.get();
        if (accionDProcesoImpl != null) {
            accionDSistemaLocal.setAccionDProcesoImplLocal(accionDProcesoImpl);
        } else {
            accionDSistemaLocal.setAccionDSistemaImplLocal(accionDSistemaImpl);
        }
        accionDSistemaLocal.procesarAccion(this);
    }

    @Override
    public DataSwapLocal getDataSwapLocal() {
        return this.dataSwapLocal;
    }

    @Override
    public String getCodigoDProceso() {
        return this.procesoDSesionImplLocal.getCodigoDProceso();
    }

    @Override
    public String getPaginaDProceso() {
        return this.procesoDSesionImplLocal.getPaginaDProceso();
    }

    @Override
    public ProcesoDSesionImplLocal getProcesoDSesionImplLocal() {
        return procesoDSesionImplLocal;
    }

    @Override
    public String getCodigoDQualifer() {
        return this.procesoDSesionImplLocal.getCodigoDQualifer();
    }

    @PreDestroy
    public void destroy() {
        String codigoDQualifer = this.dataSwapLocal.getCodigoDQualifer();
        if (Util.isCadenaVacia(codigoDQualifer)) {
            codigoDQualifer = KDataSwaps.DataSwaps.DFDATASWAP;
        }
        DataSwapsLiteral literal = DataSwapsLiteral.of(codigoDQualifer);
        Instance<DataSwapLocal> instancia = this.instancias.select(literal);
        instancia.destroy(this.dataSwapLocal);
    }

}
