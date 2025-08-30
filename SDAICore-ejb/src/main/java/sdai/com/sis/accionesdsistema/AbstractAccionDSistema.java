package sdai.com.sis.accionesdsistema;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.Map;
import sdai.com.sis.accionesdsistema.rednodal.AccionDSistemaImplLocal;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.GestorDProcesosLocal;
import sdai.com.sis.procesosdsesion.KProcesosDSesion;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.procesosdsesion.rednodal.AccionDProcesoImplLocal;
import sdai.com.sis.sistemasdreglas.KSistemasDReglas;
import sdai.com.sis.sistemasdreglas.SistemaDReglasLocal;
import sdai.com.sis.sistemasdreglas.SistemasDReglasLiteral;
import sdai.com.sis.sistemasdreglas.rednodal.SistemaDReglasImpl;
import sdai.com.sis.utilidades.FacesUtil;
import sdai.com.sis.utilidades.Util;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractAccionDSistema implements AccionDSistemaLocal {

    private AccionDSistemaImplLocal accionDSistemaImplLocal;
    private AccionDProcesoImplLocal accionDProcesoImplLocal;
    @Inject
    private GestorDProcesosLocal gestorDProcesosLocal;
    @Inject
    @Any
    private Instance<SistemaDReglasLocal> instancias;

    @Override
    public void setAccionDSistemaImplLocal(AccionDSistemaImplLocal accionDSistemaImplLocal) {
        this.accionDSistemaImplLocal = accionDSistemaImplLocal;
    }

    @Override
    public void setAccionDProcesoImplLocal(AccionDProcesoImplLocal accionDProcesoImplLocal) {
        this.accionDProcesoImplLocal = accionDProcesoImplLocal;
    }

    @Override
    public void procesarAccion(ProcesoDSesionLocal procesoDSesionLocal) throws ErrorGeneral {
        DataSwapLocal dataSwapLocal = procesoDSesionLocal.getDataSwapLocal();
        procesar(dataSwapLocal);
        procesarSistemaDReglas(dataSwapLocal);
        crearNewProceso();
    }

    protected abstract void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral;

    private void procesarSistemaDReglas(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        String codigoDSistema;
        if (this.accionDSistemaImplLocal != null) {
            codigoDSistema = this.accionDSistemaImplLocal.getSistemaDReglas();
        } else {
            codigoDSistema = this.accionDProcesoImplLocal.getSistemaDReglas();
        }
        if (Util.isCadenaNoVacia(codigoDSistema)) {
            SistemaDReglasImpl sistemaDReglasImpl = SistemaDReglasImpl.getInstancia(codigoDSistema);
            String codigoDQualifer = sistemaDReglasImpl.getCodigoDQualifer();
            if (Util.isCadenaVacia(codigoDQualifer)) {
                codigoDQualifer = KSistemasDReglas.SistemasDReglas.SIRULDEFLT;
            }
            SistemasDReglasLiteral literal = SistemasDReglasLiteral.of(codigoDQualifer);
            Instance<SistemaDReglasLocal> instancia = this.instancias.select(literal);
            SistemaDReglasLocal sistemaDReglasLocal = instancia.get();
            sistemaDReglasLocal.setSistemaDReglasImplLocal(sistemaDReglasImpl);
            sistemaDReglasLocal.setDataSwapLocal(dataSwapLocal);
            sistemaDReglasLocal.iniciar();
            sistemaDReglasLocal.procesarSistema();
        }
    }

    private void crearNewProceso() throws ErrorGeneral {
        Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
        String codigoDProceso = (String) almacenDSesion.get(KProcesosDSesion.NEWPROCESO);
        if (Util.isCadenaVacia(codigoDProceso)) {
            codigoDProceso = this.accionDSistemaImplLocal.getProcesoDestino();
        }
        if (Util.isCadenaNoVacia(codigoDProceso)) {
            this.gestorDProcesosLocal.iniciar(codigoDProceso);
            almacenDSesion.remove(KProcesosDSesion.NEWPROCESO);
        }
    }

}
