package sdai.com.sis.dataswaps;

import java.util.List;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface DataSwapLocal {

    void setDataSwapImplLocal(DataSwapImplLocal dataSwapImplLocal);

    void setProcesoDSesionLocal(ProcesoDSesionLocal procesoDSesionLocal);

    void iniciar();

    void generateDataSwap();

    DSEntidadLocal getDSEntidad(String codigoDEntidad);

    DSEntidadLocal[] getDSEntidades();

    ProcesoDSesionLocal getProcesoDSesionLocal();

    String getCodigoDDataSwap();

    List<EstructuraDatos> getEstructurasTemporales();

    DataSwapImplLocal getDataSwapImplLocal();

}
