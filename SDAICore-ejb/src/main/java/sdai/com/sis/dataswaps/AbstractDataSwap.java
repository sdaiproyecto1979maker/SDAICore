package sdai.com.sis.dataswaps;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdai.com.sis.dataswaps.rednodal.DataSwapsUtil;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.dsentidades.rednodal.DSEntidadesUtil;
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
    private DataSwapsUtil dataSwapsUtil;
    private final Map<String, DSEntidadLocal> almacenDEntidades;
    @Inject
    private DSEntidadesUtil dSEntidadesUtil;

    public AbstractDataSwap() {
        this.almacenDEntidades = new HashMap<>();
    }

    @Override
    public void setDataSwapImplLocal(DataSwapImplLocal dataSwapImplLocal) {
        this.dataSwapImplLocal = dataSwapImplLocal;
    }

    @Override
    public void iniciar() {
        String codigoDDataSwap = getCodigoDDataSwap();
        DSEntidadLocal[] dsEntidades = this.dataSwapsUtil.getEntidadesDDataSwap(codigoDDataSwap);
        for (DSEntidadLocal dsEntidad : dsEntidades) {
            String codigoDEntidad = dsEntidad.getCodigoDEntidad();
            this.almacenDEntidades.put(codigoDEntidad, dsEntidad);
        }
    }

    @Override
    public void generateDataSwap() {
        DSEntidadLocal[] entidades = getDSEntidades();
        for (DSEntidadLocal entidad : entidades) {
            entidad.generateDSEntidad();
        }
    }

    @Override
    public void setProcesoDSesionLocal(ProcesoDSesionLocal procesoDSesionLocal) {
        this.procesoDSesionLocal = procesoDSesionLocal;
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
    public String getCodigoDDataSwap() {
        return this.dataSwapImplLocal.getCodigoDDataSwap();
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
            this.dSEntidadesUtil.destroyDSEntidadLocal(entidad);
        }
    }

}
