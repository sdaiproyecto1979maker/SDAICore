package sdai.com.sis.dataswaps;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdai.com.sis.dataswaps.rednodal.DataSwapImplLocal;
import sdai.com.sis.dataswaps.rednodal.EntidadDataSwapImpl;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.dsentidades.DSEntidadesLiteral;
import sdai.com.sis.dsentidades.rednodal.DSEntidadImpl;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractDataSwap implements DataSwapLocal, Serializable {

    private DataSwapImplLocal dataSwapImplLocal;
    private ProcesoDSesionLocal procesoDSesionLocal;
    @Inject
    @Any
    private Instance<DSEntidadLocal> instancias;
    private final Map<String, DSEntidadLocal> almacenDEntidades;
    private ContextoDataSwap contextoDataSwap;

    public AbstractDataSwap() {
        this.almacenDEntidades = new HashMap<>();
    }

    @Override
    public void setDataSwapImplLocal(DataSwapImplLocal dataSwapImplLocal) {
        this.dataSwapImplLocal = dataSwapImplLocal;
    }

    @Override
    public void setProcesoDSesionLocal(ProcesoDSesionLocal procesoDSesionLocal) {
        this.procesoDSesionLocal = procesoDSesionLocal;
    }

    @Override
    public void iniciar() throws ErrorGeneral {
        String codigoDDataSwap = getCodigoDDataSwap();
        EntidadDataSwapImpl[] entidades = EntidadDataSwapImpl.getInstancias(codigoDDataSwap);
        for (EntidadDataSwapImpl entidad : entidades) {
            String codigoDEntidad = entidad.getCodigoDEntidad();
            DSEntidadImpl dSEntidadImpl = DSEntidadImpl.getInstancia(codigoDEntidad);
            String codigoDQualifer = dSEntidadImpl.getCodigoDQualifer();
            DSEntidadesLiteral literal = DSEntidadesLiteral.of(codigoDQualifer);
            Instance<DSEntidadLocal> instancia = this.instancias.select(literal);
            DSEntidadLocal entidadLocal = instancia.get();
            entidadLocal.setDSEntidadImplLocal(dSEntidadImpl);
            this.almacenDEntidades.put(codigoDEntidad, entidadLocal);
        }
        this.contextoDataSwap = new ContextoDataSwap();
    }

    @Override
    public void generateDataSwap() throws ErrorGeneral {
        DSEntidadLocal[] entidades = getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            entidad.generateDSEntidad();
        }
    }

    @Override
    public ContextoDataSwapLocal getContextoDataSwapLocal() {
        return this.contextoDataSwap;
    }

    @Override
    public String getCodigoDDataSwap() {
        return this.dataSwapImplLocal.getCodigoDDataSwap();
    }

    @Override
    public String getCodigoDQualifer() {
        return this.dataSwapImplLocal.getCodigoDQualifer();
    }

    @Override
    public DSEntidadLocal getDSEntidad(String codigoDEntidad) {
        DSEntidadLocal dSEntidadLocal = this.almacenDEntidades.get(codigoDEntidad);
        return dSEntidadLocal;
    }

    @Override
    public DSEntidadLocal[] getDSEntidades() {
        DSEntidadLocal[] entidades = this.almacenDEntidades.values().toArray(DSEntidadLocal[]::new);
        return entidades;
    }

    @Override
    public ProcesoDSesionLocal getProcesoDSesionLocal() {
        return procesoDSesionLocal;
    }

    @Override
    public DataSwapImplLocal getDataSwapImplLocal() {
        return this.dataSwapImplLocal;
    }

    @Override
    public List<EstructuraDatos> getEstructurasTemporales() {
        List<EstructuraDatos> lista = new ArrayList<>();
        DSEntidadLocal[] entidades = getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            EstructuraDatos eDatos = entidad.generateEstructuraDDatos();
            lista.add(eDatos);
        }
        return lista;
    }

    @PreDestroy
    public void destroy() {
        DSEntidadLocal[] entidades = getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            String codigoDQualifer = entidad.getCodigoDQualifer();
            DSEntidadesLiteral literal = DSEntidadesLiteral.of(codigoDQualifer);
            Instance<DSEntidadLocal> instancia = this.instancias.select(literal);
            instancia.destroy(entidad);
        }
    }

}
