package sdai.com.sis.sistemasdreglas;

import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.sistemasdreglas.rednodal.SistemaDReglasImplLocal;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface SistemaDReglasLocal {

    void setSistemaDReglasImplLocal(SistemaDReglasImplLocal sistemaDReglasImplLocal);

    void setDataSwapLocal(DataSwapLocal dataSwapLocal);

    void iniciar() throws ErrorGeneral;

    void procesarSistema() throws ErrorGeneral;

}
