package sdai.com.sis.rednodal.atributosdtupla.accesoadatos;

import org.w3c.dom.Node;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.cacchesdsistema.InstanciaDContenedor;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdtupla.KAtributosDTupla;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDTupla.KSituacionDAtributoDTupla.NOMBRTABLA)
public final class SituacionDAtributoDTupla extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KAtributosDTupla.KSituacionDAtributoDTupla.AtributosDEntidad.IDSITATRTU)
	private Long identificador;

	@Column(name = KAtributosDTupla.KSituacionDAtributoDTupla.AtributosDEntidad.VALORATRIB, length = 450, nullable = false)
	private String valorDAtributo;

	@ManyToOne
	@JoinColumn(name = KAtributosDTupla.KAtributoDTupla.AtributosDEntidad.IDATRTUPLA)
	private AtributoDTupla atributoDTupla;

	SituacionDAtributoDTupla() {

	}

	public static SituacionDAtributoDTupla getInstancia(String codigoDTupla, String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDTupla.class, codigoDTupla, codigoDDato);
		SituacionDAtributoDTupla instancia = (SituacionDAtributoDTupla) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADAtributosDTupla adatos = new ADAtributosDTupla();
			instancia = adatos.getSituacionDAtributoDTupla(codigoDTupla, codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	public static SituacionDAtributoDTupla[] getInstancias(String codigoDTupla) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDTupla.class, codigoDTupla);
		SituacionDAtributoDTupla[] instancias = (SituacionDAtributoDTupla[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADAtributosDTupla adatos = new ADAtributosDTupla();
			instancias = adatos.getSituacionesDAtributoDTupla(codigoDTupla);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.valorDAtributo = DocumentoXML.getStringValueNodeDescendencia(root, KAtributosDTupla.KSituacionDAtributoDTupla.AtributosDEntidad.VALORATRIB);
	}

	@Override
	public void deleteCacheInstanciaArray(InstanciaDContenedor instanciaDContenedor) throws Exception {
		Boolean isInstanciasNoDeleteables = Boolean.valueOf(true);
		SituacionDAtributoDTupla[] instancias = (SituacionDAtributoDTupla[]) instanciaDContenedor.getInstancia();
		for (SituacionDAtributoDTupla instancia : instancias) {
			AtributoDTupla atributoDTupla = instancia.getAtributoDTupla();
			Tupla tupla = atributoDTupla.getTupla();
			String codigoDTupla = tupla.getCodigoDTupla();
			DatoDSistema datoDSistema = atributoDTupla.getDatoDSistema();
			String codigoDDato = datoDSistema.getCodigoDDato();
			KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDTupla.class, Boolean.valueOf(false), codigoDTupla, codigoDDato);
			if (CacheDRednodal.existeInstanciaNoDeleteable(keyCache)) {
				isInstanciasNoDeleteables = Boolean.valueOf(false);
				break;
			}
		}
		if (isInstanciasNoDeleteables.equals(Boolean.valueOf(true))) {
			KeyCache keyCache = instanciaDContenedor.getKeyCache();
			CacheDRednodal.eliminarInstancia(keyCache);
		}
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getValorDAtributo() {
		return valorDAtributo;
	}

	public void setValorDAtributo(String valorDAtributo) {
		this.valorDAtributo = valorDAtributo;
	}

	public AtributoDTupla getAtributoDTupla() {
		return atributoDTupla;
	}

	public void setAtributoDTupla(AtributoDTupla atributoDTupla) {
		this.atributoDTupla = atributoDTupla;
	}

}
