package sdai.com.sis.rednodal.atributosdtupla;

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
import sdai.com.sis.rednodal.tuplas.ADTuplas;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.rednodal.tuplas.Tupla;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADAtributosDTupla {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private ADDatosDSistema adDatosDSistema;
    @Inject
    private ADTuplas adTuplas;

    public AtributoDTupla getAtributoDTupla(String codigoDTupla, String codigoDDato) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<AtributoDTupla> query = entityManager.createNamedQuery(KAtributosDTupla.NamedQueries.SATRTU0000, AtributoDTupla.class);
            query.setParameter(KTuplas.AtributosDEntidad.CODIGTUPLA, codigoDTupla);
            query.setParameter(KDatosDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public AtributoDTupla[] getAtributosDTupla() {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<AtributoDTupla> query = entityManager.createNamedQuery(KAtributosDTupla.NamedQueries.SATRTU0001, AtributoDTupla.class);
        return query.getResultList().toArray(AtributoDTupla[]::new);
    }

    public AtributoDTupla[] getAtributosDTupla(String entornoAplicacion) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<AtributoDTupla> query = entityManager.createNamedQuery(KAtributosDTupla.NamedQueries.SATRTU0002, AtributoDTupla.class);
        query.setParameter(KAtributosDTupla.AtributosDEntidad.ENTORNOAPP, entornoAplicacion);
        return query.getResultList().toArray(AtributoDTupla[]::new);
    }

    public void persistAtributoDTuplaVersion(XMLCFGLocal xmlCFGLocal) {
        XMLAtributoDTupla xmlAtributoDTupla = (XMLAtributoDTupla) xmlCFGLocal;
        String codigoDTupla = xmlAtributoDTupla.getCodigoDTupla();
        String codigoDDato = xmlAtributoDTupla.getCodigoDDato();
        AtributoDTupla atributoDTupla = getAtributoDTupla(codigoDTupla, codigoDDato);
        if (atributoDTupla == null) {
            atributoDTupla = persistAtributoDTupla(xmlAtributoDTupla);
            Tupla tupla = this.adTuplas.getTupla(codigoDTupla);
            tupla.getAtributosDTupla().add(atributoDTupla);
            this.adTuplas.mergeTupla(tupla);
            DatoDSistema datoDSistema = this.adDatosDSistema.getDatoDSistema(codigoDDato);
            datoDSistema.getAtributosDTupla().add(atributoDTupla);
            this.adDatosDSistema.mergeDatoDSistema(datoDSistema);
        }
    }

    public AtributoDTupla persistAtributoDTupla(XMLAtributoDTupla xmlAtributoDTupla) {
        AtributoDTupla atributoDTupla = new AtributoDTupla();
        atributoDTupla.setValorDAtributo(xmlAtributoDTupla.getValorDAtributo());
        String codigoDTupla = xmlAtributoDTupla.getCodigoDTupla();
        Tupla tupla = this.adTuplas.getTupla(codigoDTupla);
        atributoDTupla.setTupla(tupla);
        String codigoDDato = xmlAtributoDTupla.getCodigoDDato();
        DatoDSistema datoDSistema = this.adDatosDSistema.getDatoDSistema(codigoDDato);
        atributoDTupla.setDatoDSistema(datoDSistema);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(atributoDTupla);
        return atributoDTupla;
    }

}
