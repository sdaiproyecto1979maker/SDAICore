package sdai.com.sis.sistemasdreglas;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.dataswaps.ContextoDataSwapLocal;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.ReglaBasicaLocal;
import sdai.com.sis.reglasbasicas.ReglasBasicasLiteral;
import sdai.com.sis.reglasbasicas.rednodal.ReglaBasicaImpl;
import sdai.com.sis.sistemasdreglas.rednodal.DSResponseImpl;
import sdai.com.sis.sistemasdreglas.rednodal.ReglaDSistemaImpl;
import sdai.com.sis.sistemasdreglas.rednodal.SistemaDReglasImplLocal;
import sdai.com.sis.utilidades.Util;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractSistemaDReglas implements SistemaDReglasLocal {

    private SistemaDReglasImplLocal sistemaDReglasImplLocal;
    private DataSwapLocal dataSwapLocal;
    @Inject
    @Any
    private Instance<DSResponseLocal> instancias;
    @Inject
    @Any
    private Instance<ReglaBasicaLocal> reglasBasicas;
    private final List<ReglaBasicaLocal> reglasDSistema;

    public AbstractSistemaDReglas() {
        this.reglasDSistema = new ArrayList<>();
    }

    @Override
    public void setSistemaDReglasImplLocal(SistemaDReglasImplLocal sistemaDReglasImplLocal) {
        this.sistemaDReglasImplLocal = sistemaDReglasImplLocal;
    }

    @Override
    public void setDataSwapLocal(DataSwapLocal dataSwapLocal) {
        this.dataSwapLocal = dataSwapLocal;
    }

    @Override
    public void iniciar() throws ErrorGeneral {
        String codigoDResponse = this.sistemaDReglasImplLocal.getCodigoDResponse();
        if (Util.isCadenaNoVacia(codigoDResponse)) {
            loadResponse(codigoDResponse);
        }
        String codigoDSistema = this.sistemaDReglasImplLocal.getCodigoDSistema();
        ReglaDSistemaImpl[] _reglasDSistema = ReglaDSistemaImpl.getInstancias(codigoDSistema);
        for (ReglaDSistemaImpl reglaDSistemaImpl : _reglasDSistema) {
            String codigoDRegla = reglaDSistemaImpl.getCodigoDRegla();
            ReglaBasicaImpl reglaBasicaImpl = ReglaBasicaImpl.getInstancia(codigoDRegla);
            String codigoDQualifer = reglaBasicaImpl.getCodigoDQualifer();
            ReglasBasicasLiteral literal = ReglasBasicasLiteral.of(codigoDQualifer);
            Instance<ReglaBasicaLocal> reglaBasica = this.reglasBasicas.select(literal);
            ReglaBasicaLocal reglaBasicaLocal = reglaBasica.get();
            reglaBasicaLocal.setReglaBasicaImplLocal(reglaBasicaImpl);
            this.reglasDSistema.add(reglaBasicaLocal);
        }
    }

    private void loadResponse(String codigoDResponse) throws ErrorGeneral {
        DSResponseImpl dSResponseImpl = DSResponseImpl.getInstancia(codigoDResponse);
        String codigoDQualifer = dSResponseImpl.getCodigoDQualifer();
        DSResponsesLiteral literal = DSResponsesLiteral.of(codigoDQualifer);
        Instance<DSResponseLocal> instancia = this.instancias.select(literal);
        DSResponseLocal dSResponseLocal = instancia.get();
        dSResponseLocal.setDSResponseImplLocal(dSResponseImpl);
        dSResponseLocal.setDataSwapLocal(this.dataSwapLocal);
        ContextoDataSwapLocal contextoDataSwapLocal = this.dataSwapLocal.getContextoDataSwapLocal();
        contextoDataSwapLocal.addDSResponseLocal(dSResponseLocal);
    }

    @Override
    public void procesarSistema() throws ErrorGeneral {
        String codigoDResponse = this.sistemaDReglasImplLocal.getCodigoDResponse();
        if (Util.isCadenaNoVacia(codigoDResponse)) {
            ContextoDataSwapLocal contextoDataSwapLocal = this.dataSwapLocal.getContextoDataSwapLocal();
            DSResponseLocal dSResponseLocal = contextoDataSwapLocal.getDSResponseLocal(codigoDResponse);
            for (ReglaBasicaLocal reglaBasicaLocal : this.reglasDSistema) {
                reglaBasicaLocal.evaluar(dSResponseLocal);
            }
            dSResponseLocal.procesar(this.dataSwapLocal);
        } else {
            for (ReglaBasicaLocal reglaBasicaLocal : this.reglasDSistema) {
                reglaBasicaLocal.evaluar(this.dataSwapLocal);
            }
        }
    }

}
