package la.netco.generico.uilayer.beans;

import java.io.Serializable;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import la.netco.generico.persistencia.dto.DiariosOficiales;
import la.netco.generico.persistencia.dto.Entidades;
import la.netco.generico.persistencia.dto.IndicesId;
import la.netco.generico.persistencia.dto.TiposNormas;
import la.netco.generico.utils.ConstantesQuemadas;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean
@ViewScoped
public class IndicesPorletBean extends BaseDiarioOficialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IndicesId id;
	private DiariosOficiales diariosOficiales;
	private Entidades entidades;

	private String titulo;
	private Date fechaRecibido;

	private Clob norma;

	private String resumen;
	private String feDeErratas;
	private String fecano;
	private String anno;
	private String dia;
	private String mes;
	private String usCrea;

	public IndicesPorletBean() {
		System.out.println("###Construyendo IndicesPorletBean");
		entidades = new Entidades();
	}

	@PostConstruct
	public void initIndicesPorletBean() {
		cargaItemsEntidad();
		cargaItemsTiposNormas();
	}

	public String obtenerFecha(String numeroDO) {
		String fechaDevolver = "02/05/2003";
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MMM-yyyy");
			DiariosOficiales diarioOficialAuxi = (DiariosOficiales) getDaoServicio().getGenericCommonDao().read(DiariosOficiales.class, numeroDO);
			if (diarioOficialAuxi != null) {
				fechaDevolver = formateador.format(diarioOficialAuxi.getFecha());
			}
			return fechaDevolver;
		} catch (Exception e) {
			e.printStackTrace();
			return fechaDevolver;
		}
	}

	public String obtenerNorma(String numeroTN) {
		String normaDevolver = "DEFECTO";
		try {
			TiposNormas tiposNormasAuxi = (TiposNormas) getDaoServicio().getGenericCommonDao().read(TiposNormas.class, numeroTN);
			if (tiposNormasAuxi != null) {
				normaDevolver = tiposNormasAuxi.getNombre();
			}
			return normaDevolver;
		} catch (Exception e) {
			e.printStackTrace();
			return normaDevolver;
		}
	}

	public void cargaFiltros() {
		try {
			System.out.println("### Buscando Diarios");
			List<Criterion> filtros = new ArrayList<Criterion>();
			boolean busqInicial = true;
			boolean busqFinal = true;
			boolean continuar = true;
			String fechaSimuladaS = "01/01/1700";
			DateFormat formateador = new SimpleDateFormat("dd/mm/yyyy");
			Date fechasimulada = formateador.parse(fechaSimuladaS);
			setNumeroresultados(0);
			getIndicesDataModel().setRowCount(null);

			if (Validator.isNull(getCodigoEntidadSel()) && Validator.isNull(getNumero()) && Validator.isNull(getCodigoTipoNormaSel()) && Validator.isNull(getFechaInicial())
					&& Validator.isNull(getFechaFinal())) {
				setActivo(false);
				FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.INFOSELECCIONECRITERIOS);
			}

			if (Validator.isNotNull(getCodigoTipoNormaSel()) && (Validator.isNotNull(getNumero()) || getNumero() != "")) {
				setActivo(true);
				filtros.add(Restrictions.eq("tiposNormas.codigo", getCodigoTipoNormaSel()));
				filtros.add(Restrictions.like("titulo", "%" + getNumero() + "%"));
			}

			if (Validator.isNotNull(getCodigoEntidadSel())) {
				setActivo(true);
				filtros.add(Restrictions.eq("entidades.codigo", getCodigoEntidadSel()));
			}

			// Valida si las fechas son nulas y dar continuidad a la consulta

			if (Validator.isNull(getFechaInicial())) {
				busqInicial = false;
			}
			if (Validator.isNull(getFechaFinal())) {
				busqFinal = false;
			}

			if (busqInicial && busqFinal && continuar) {

				if ((Validator.isNotNull(getFechaInicial()) && Validator.isNotNull(getFechaFinal()))) {
					if (getFechaFinal().before(getFechaInicial())) {
						FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORMENORFECHAFINAL);
						continuar = false;
						setActivo(false);
					} else {
						filtros.add(Restrictions.between("diariosOficiales.fecha", getFechaInicial(), getFechaFinal()));
						continuar = true;
						setActivo(true);
						setActRangoFechas(true);
					}
				}
			}

			if (busqFinal && continuar) {

				if (getFechaFinal().after(new Date(System.currentTimeMillis()))) {
					FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORMAYORFINALACTUAL);
					busqFinal = false;
					continuar = false;
					setActivo(false);
					setActRangoFechas(false);
				}

			}

			if (busqInicial && continuar) {
				if (getFechaInicial().after(new Date(System.currentTimeMillis()))) {
					FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORMAYORINICIALACTUAL);
					busqInicial = false;
					continuar = false;
					setActivo(false);
					setActRangoFechas(false);
				}
			}

			if (busqInicial && !busqFinal && continuar) {
				filtros.add(Restrictions.between("diariosOficiales.fecha", getFechaInicial(), new Date(System.currentTimeMillis())));
				setActivo(true);
			}

			if (!busqInicial && busqFinal && continuar) {
				filtros.add(Restrictions.between("diariosOficiales.fecha", fechasimulada, getFechaFinal()));
				setActivo(true);
			}

			if (filtros.isEmpty()) {
				setActivo(false);
			}

			getIndicesDataModel().setFiltros(filtros);
			setNumeroresultados(getIndicesDataModel().getRowCount());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public IndicesId getId() {
		return id;
	}

	public void setId(IndicesId id) {
		this.id = id;
	}

	public DiariosOficiales getDiariosOficiales() {
		return diariosOficiales;
	}

	public void setDiariosOficiales(DiariosOficiales diariosOficiales) {
		this.diariosOficiales = diariosOficiales;
	}

	public Entidades getEntidades() {
		return entidades;
	}

	public void setEntidades(Entidades entidades) {
		this.entidades = entidades;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public Clob getNorma() {
		return norma;
	}

	public void setNorma(Clob norma) {
		this.norma = norma;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getFeDeErratas() {
		return feDeErratas;
	}

	public void setFeDeErratas(String feDeErratas) {
		this.feDeErratas = feDeErratas;
	}

	public String getFecano() {
		return fecano;
	}

	public void setFecano(String fecano) {
		this.fecano = fecano;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getUsCrea() {
		return usCrea;
	}

	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}

}
