package sdai.com.sis.rednodal.atributosdnodo;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.conexiones.PoolDConexionesLocal;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.rednodal.datosdsistema.ADDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.nodos.ADNodos;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADAtributosDNodo {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private ADDatosDSistema adDatosDSistema;
    @Inject
    private ADNodos adNodos;

    public AtributoDNodo getAtributoDNodo(String codigoDNodo, String codigoDDato) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<AtributoDNodo> query = entityManager.createNamedQuery(KAtributosDNodo.NamedQueries.SATRNO0000, AtributoDNodo.class);
            query.setParameter(KNodos.AtributosDEntidad.CODIGONODO, codigoDNodo);
            query.setParameter(KDatosDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public AtributoDNodo[] getAtributosDNodo() {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<AtributoDNodo> query = entityManager.createNamedQuery(KAtributosDNodo.NamedQueries.SATRNO0001, AtributoDNodo.class);
        return query.getResultList().toArray(AtributoDNodo[]::new);
    }

    public void persistAtributoDNodoVersion(XMLCFGLocal xmlCFGLocal) {
        XMLAtributoDNodo xmlAtributoDNodo = (XMLAtributoDNodo) xmlCFGLocal;
        String codigoDNodo = xmlAtributoDNodo.getCodigoDNodo();
        String codigoDDato = xmlAtributoDNodo.getCodigoDDato();
        AtributoDNodo atributoDNodo = getAtributoDNodo(codigoDNodo, codigoDDato);
        if (atributoDNodo == null) {
            atributoDNodo = persistAtributoDNodo(xmlAtributoDNodo);
            Nodo nodo = this.adNodos.getNodo(codigoDNodo);
            nodo.getAtributosDNodo().add(atributoDNodo);
            this.adNodos.mergeNodo(nodo);
            DatoDSistema datoDSistema = this.adDatosDSistema.getDatoDSistema(codigoDDato);
            datoDSistema.getAtributosDNodo().add(atributoDNodo);
            this.adDatosDSistema.mergeDatoDSistema(datoDSistema);
        }
    }

    public AtributoDNodo persistAtributoDNodo(XMLAtributoDNodo xmlAtributoDNodo) {
        AtributoDNodo atributoDNodo = new AtributoDNodo();
        String codigoDNodo = xmlAtributoDNodo.getCodigoDNodo();
        Nodo nodo = this.adNodos.getNodo(codigoDNodo);
        atributoDNodo.setNodo(nodo);
        String codigoDDato = xmlAtributoDNodo.getCodigoDDato();
        DatoDSistema datoDSistema = this.adDatosDSistema.getDatoDSistema(codigoDDato);
        atributoDNodo.setDatoDSistema(datoDSistema);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(atributoDNodo);
        return atributoDNodo;
    }

}
