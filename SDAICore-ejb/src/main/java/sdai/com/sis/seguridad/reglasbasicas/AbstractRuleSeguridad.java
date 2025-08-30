package sdai.com.sis.seguridad.reglasbasicas;

import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.AbstractReglaBasica;
import sdai.com.sis.seguridad.dataswaps.DataSwapSeguridad;
import sdai.com.sis.seguridad.usuarios.Usuario;
import sdai.com.sis.sistemasdreglas.DSResponseLocal;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractRuleSeguridad extends AbstractReglaBasica {

    @Override
    public void evaluar(DSResponseLocal dSResponseLocal) throws ErrorGeneral {
        try {
            super.evaluar(dSResponseLocal);
            DataSwapLocal dataSwapLocal = dSResponseLocal.getDataSwapLocal();
            DataSwapSeguridad dataSwapSeguridad = (DataSwapSeguridad) dataSwapLocal;
            evaluar(dataSwapSeguridad);
        } catch (ErrorGeneral ex) {
            dSResponseLocal = getDSResponseLocal();
            ErrorGeneral errorGeneral = dSResponseLocal.tratarErrorResponse(this);
            if (errorGeneral != null) {
                throw errorGeneral;
            }
            throw ex;
        }
    }

    @Override
    public void evaluar(DataSwapLocal dataSwapLocal) throws ErrorGeneral {
        DataSwapSeguridad dataSwapSeguridad = (DataSwapSeguridad) dataSwapLocal;
        evaluar(dataSwapSeguridad);
    }

    protected abstract void evaluar(DataSwapSeguridad dataSwapSeguridad) throws ErrorGeneral;

    protected void setUsuario(Usuario usuario) {
        DSResponseLocal dSResponseLocal = getDSResponseLocal();
        if (dSResponseLocal != null) {
            dSResponseLocal.setObjectResponse(usuario);
        }
    }

}
