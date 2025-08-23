package sdai.com.sis.dataswaps;

import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sdai.com.sis.dataswaps.rednodal.DataSwapsUtil;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;

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
    public void setProcesoDSesionLocal(ProcesoDSesionLocal procesoDSesionLocal) {
        this.procesoDSesionLocal = procesoDSesionLocal;
    }

    @Override
    public ProcesoDSesionLocal getProcesoDSesionLocal() {
        return procesoDSesionLocal;
    }

    @Override
    public String getCodigoDDataSwap() {
        return this.dataSwapImplLocal.getCodigoDDataSwap();
    }

}
