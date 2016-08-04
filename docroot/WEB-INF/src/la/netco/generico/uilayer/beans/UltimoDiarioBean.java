package la.netco.generico.uilayer.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import la.netco.generico.persistencia.dto.DiariosHtml;
import la.netco.generico.persistencia.dto.DiariosOficiales;
import la.netco.generico.persistencia.dto.DiariosPdf;
import la.netco.generico.persistencia.dto.Entidades;
import la.netco.generico.persistencia.dto.Indices;
import la.netco.generico.persistencia.dto.TiposEdiciones;
import la.netco.generico.persistencia.dto.TiposEntidades;
import la.netco.generico.persistencia.dto.TiposNormas;
import la.netco.generico.utils.ConstantesQuemadas;
import la.netco.generico.utils.EntidadUtil;
import la.netco.generico.utils.IndiceUtil;
import la.netco.generico.utils.TipoEntidadUtil;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.liferay.portal.kernel.exception.SystemException;
import com.sun.faces.context.flash.ELFlash;

@ManagedBean
@ViewScoped
public class UltimoDiarioBean extends BaseDiarioOficialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroint;

	private Integer actionIndex;

	private Short pagDuc;

	private String publica;
	private String contieneDuc;
	private String numerodiario;
	private String ultimodiario;

	private List<DiariosPdf> listaDummyPDF;
	private List<TipoEntidadUtil> listaTipoEntidadUtils;

	private DiariosPdf diariosPdf;
	private DiariosHtml diariosHtml;

	private Entidades entidades;

	private DiariosOficiales diarioSeleccionado;
	private DiariosOficiales objDiarioOficialCargado;
	private DiariosOficiales ultimoDiarioOficial;

	private TiposEdiciones tedicionUltimoDiario;
	private TiposEdiciones tiposEdiciones;

	private Indices indice;

	public UltimoDiarioBean() throws SystemException {
		System.out.println("###Construyendo UltimoDiarioBean");
		objDiarioOficialCargado = new DiariosOficiales();
		tedicionUltimoDiario = new TiposEdiciones();
		ultimoDiarioOficial = new DiariosOficiales();
		indice = new Indices();
	}

	public void cargarUltimoDiario() {
		List<Criterion> criteriosBusqeda = new ArrayList<Criterion>();
		criteriosBusqeda.add(Restrictions.eq("publica", ConstantesQuemadas.SIPUBLICA));
		List<?> listaObjeto = getDaoServicio().getGenericCommonDao().executeCriteriaFindPaged(DiariosOficiales.class, criteriosBusqeda, 0, 1, Order.desc("fecha"));
		ultimoDiarioOficial = (DiariosOficiales) listaObjeto.get(0);
		System.out.println("### Ultimo Diario Oficial: " + ultimoDiarioOficial.getNumero());
		tedicionUltimoDiario = (TiposEdiciones) getDaoServicio().getGenericCommonDao().read(TiposEdiciones.class, ultimoDiarioOficial.getTiposEdiciones().getCodigo());
	}

	@SuppressWarnings({ "unchecked" })
	public void cargarlistaIndices() {

		Entidades entidadUso = new Entidades();
		TiposNormas tiposNormasUso = new TiposNormas();
		TiposEntidades tiposEntidadesUso = new TiposEntidades();
		IndiceUtil indiceUtilUso = new IndiceUtil();
		EntidadUtil entidadUtilUso = new EntidadUtil();
		TipoEntidadUtil tipoEntidadUtilUso = new TipoEntidadUtil();

		List<Indices> listaIndices = new ArrayList<Indices>();
		List<IndiceUtil> listaIndiceUtil = new ArrayList<IndiceUtil>();
		List<EntidadUtil> listaEntidadUtil = new ArrayList<EntidadUtil>();
		listaTipoEntidadUtils = new ArrayList<TipoEntidadUtil>();

		HashMap<String, String> mapaTipoEntidades = new HashMap<String, String>();
		HashMap<String, Entidades> mapaEntidades = new HashMap<String, Entidades>();
		HashMap<Integer, String> params = new HashMap<Integer, String>();

		params.put(0, objDiarioOficialCargado.getNumero());
		listaIndices = (List<Indices>) getDaoServicio().getGenericCommonDao().executeFind(Indices.class, params, Indices.NAMED_QUERY_INDICES);
		params.clear();

		for (Indices indice : listaIndices) {
			entidadUso = (Entidades) getDaoServicio().getGenericCommonDao().read(Entidades.class, indice.getEntidades().getCodigo());
			if (!mapaEntidades.containsKey(entidadUso.getCodigo())) {
				mapaEntidades.put(entidadUso.getCodigo(), entidadUso);
			}

			tiposEntidadesUso = (TiposEntidades) getDaoServicio().getGenericCommonDao().read(TiposEntidades.class, entidadUso.getTiposEntidades().getCodigo());
			if (!mapaTipoEntidades.containsKey(tiposEntidadesUso.getCodigo())) {
				mapaTipoEntidades.put(tiposEntidadesUso.getCodigo(), tiposEntidadesUso.getNombre());
			}
		}

		for (Map.Entry<String, String> varMapa : mapaTipoEntidades.entrySet()) {
			tipoEntidadUtilUso.setNombreTipoEntidad(varMapa.getValue());
			for (Map.Entry<String, Entidades> varMapaE : mapaEntidades.entrySet()) {
				if (varMapa.getKey().equals(varMapaE.getValue().getTiposEntidades().getCodigo())) {
					entidadUtilUso.setNombreEntidad(varMapaE.getValue().getNombre());
					for (Indices indice : listaIndices) {
						if (varMapaE.getValue().getCodigo() == indice.getEntidades().getCodigo()) {
							tiposNormasUso = (TiposNormas) getDaoServicio().getGenericCommonDao().read(TiposNormas.class, indice.getTiposNormas().getCodigo());
							if (indice.getTitulo() == null || indice.getTitulo().equals("")) {
								indice.setTitulo("");
							}
							if (indice.getResumen() == null || indice.getResumen().equals("")) {
								indice.setResumen("");
							}
							indiceUtilUso.setTituloResumen(tiposNormasUso.getNombre() + " " + indice.getTitulo());
							indiceUtilUso.setResumen(indice.getResumen());
							listaIndiceUtil.add(indiceUtilUso);
							indiceUtilUso = new IndiceUtil();
						}
					}
					entidadUtilUso.setListIndiceUtils(listaIndiceUtil);
					listaEntidadUtil.add(entidadUtilUso);
					entidadUtilUso = new EntidadUtil();
					listaIndiceUtil = new ArrayList<IndiceUtil>();
				}
			}
			tipoEntidadUtilUso.setListaEntidadUtils(listaEntidadUtil);
			listaTipoEntidadUtils.add(tipoEntidadUtilUso);
			tipoEntidadUtilUso = new TipoEntidadUtil();
			listaEntidadUtil = new ArrayList<EntidadUtil>();
		}

	}

	public void cargaParametrosDetalle() {
		System.out.println("Cargando parametros  detalle del diario.****");
		try {

			if (FacesContext.getCurrentInstance().getExternalContext().getFlash().containsKey(ConstantesQuemadas.OBJDIARIO)) {
				objDiarioOficialCargado = (DiariosOficiales) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(ConstantesQuemadas.OBJDIARIO);
				tedicionUltimoDiario = (TiposEdiciones) getDaoServicio().getGenericCommonDao().read(TiposEdiciones.class, objDiarioOficialCargado.getTiposEdiciones().getCodigo());
			} else if (FacesContext.getCurrentInstance().getExternalContext().getFlash().containsKey(ConstantesQuemadas.NUMDIARIO)) {
				String numerDiarioOficialEnv = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(ConstantesQuemadas.NUMDIARIO);
				objDiarioOficialCargado = (DiariosOficiales) getDaoServicio().getGenericCommonDao().read(DiariosOficiales.class, numerDiarioOficialEnv);
			}

			System.out.println("### Numero Diario Cargado: " + objDiarioOficialCargado.getNumero());
			cargarlistaIndices();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public StreamedContent descargar(String idDiario) {
		try {
			DiariosPdf diario = (DiariosPdf) getDaoServicio().getGenericCommonDao().read(DiariosPdf.class, idDiario);
			InputStream stream = diario.getTexto().getBinaryStream();

			StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", diario.getDrofNumero());

			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String detalleUltimoDiario() {
		ELFlash.getFlash().put(ConstantesQuemadas.OBJDIARIO, ultimoDiarioOficial);
		return ConstantesQuemadas.NAV_VISORDETALLE;
	}

	public String detalleEPUB() {
		// ELFlash.getFlash().put(OBJDIARIO, ultimoDiarioOficial.get(0));
		return "diarioepub";
	}

	public TiposEdiciones getTiposEdiciones() {
		return tiposEdiciones;
	}

	public void setTiposEdiciones(TiposEdiciones tiposEdiciones) {
		this.tiposEdiciones = tiposEdiciones;
	}

	public Short getPagDuc() {
		return pagDuc;
	}

	public void setPagDuc(Short pagDuc) {
		this.pagDuc = pagDuc;
	}

	public String getPublica() {
		return publica;
	}

	public void setPublica(String publica) {
		this.publica = publica;
	}

	public String getContieneDuc() {
		return contieneDuc;
	}

	public void setContieneDuc(String contieneDuc) {
		this.contieneDuc = contieneDuc;
	}

	public DiariosPdf getDiariosPdf() {
		return diariosPdf;
	}

	public void setDiariosPdf(DiariosPdf diariosPdf) {
		this.diariosPdf = diariosPdf;
	}

	public DiariosHtml getDiariosHtml() {
		return diariosHtml;
	}

	public void setDiariosHtml(DiariosHtml diariosHtml) {
		this.diariosHtml = diariosHtml;
	}

	public DiariosOficiales getObjDiarioOficialCargado() {
		return objDiarioOficialCargado;
	}

	public void setObjDiarioOficialCargado(DiariosOficiales objDiarioOficialCargado) {
		this.objDiarioOficialCargado = objDiarioOficialCargado;
	}

	public Entidades getEntidades() {
		return entidades;
	}

	public void setEntidades(Entidades entidades) {
		this.entidades = entidades;
	}

	public Integer getActionIndex() {
		return actionIndex;
	}

	public void setActionIndex(Integer actionIndex) {
		this.actionIndex = actionIndex;
	}

	public DiariosOficiales getDiarioSeleccionado() {
		return diarioSeleccionado;
	}

	public void setDiarioSeleccionado(DiariosOficiales diarioSeleccionado) {
		this.diarioSeleccionado = diarioSeleccionado;
	}

	public Indices getIndice() {
		return indice;
	}

	public void setIndice(Indices indice) {
		this.indice = indice;
	}

	public String getNumerodiario() {
		return numerodiario;
	}

	public void setNumerodiario(String numerodiario) {
		this.numerodiario = numerodiario;
	}

	public String getUltimodiario() {
		return ultimodiario;
	}

	public void setUltimodiario(String ultimodiario) {
		this.ultimodiario = ultimodiario;
	}

	public List<DiariosPdf> getListaDummyPDF() {
		return listaDummyPDF;
	}

	public void setListaDummyPDF(List<DiariosPdf> listaDummyPDF) {
		this.listaDummyPDF = listaDummyPDF;
	}

	public Integer getNumeroint() {
		return numeroint;
	}

	public void setNumeroint(Integer numeroint) {
		this.numeroint = numeroint;
	}

	public DiariosOficiales getUltimoDiarioOficial() {
		return ultimoDiarioOficial;
	}

	public void setUltimoDiarioOficial(DiariosOficiales ultimoDiarioOficial) {
		this.ultimoDiarioOficial = ultimoDiarioOficial;
	}

	public TiposEdiciones getTedicionUltimoDiario() {
		return tedicionUltimoDiario;
	}

	public void setTedicionUltimoDiario(TiposEdiciones tedicionUltimoDiario) {
		this.tedicionUltimoDiario = tedicionUltimoDiario;
	}

	public List<TipoEntidadUtil> getListaTipoEntidadUtils() {
		return listaTipoEntidadUtils;
	}

	public void setListaTipoEntidadUtils(List<TipoEntidadUtil> listaTipoEntidadUtils) {
		this.listaTipoEntidadUtils = listaTipoEntidadUtils;
	}

}
