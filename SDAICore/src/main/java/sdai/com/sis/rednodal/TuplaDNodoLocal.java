package sdai.com.sis.rednodal;

import jakarta.ejb.Local;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface TuplaDNodoLocal {

    String getCodigoDTupla();

    String getDescripcionDTupla();

    DatoDTuplaLocal[] getDatosDTupla();

    DatoDTuplaLocal getDatoDTupla(String codigoDDato);

}
