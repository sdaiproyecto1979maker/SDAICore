package sdai.com.sis.dataswaps;

import java.util.List;
import sdai.com.sis.dataswaps.rednodal.DataSwapImplLocal;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
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

    void iniciar() throws ErrorGeneral;

    void generateDataSwap() throws ErrorGeneral;

    ContextoDataSwapLocal getContextoDataSwapLocal();

    DSEntidadLocal getDSEntidad(String codigoDEntidad);

    DSEntidadLocal[] getDSEntidades();

    ProcesoDSesionLocal getProcesoDSesionLocal();

    List<EstructuraDatos> getEstructurasTemporales();

    DataSwapImplLocal getDataSwapImplLocal();

    String getCodigoDDataSwap();

    String getCodigoDQualifer();

}
