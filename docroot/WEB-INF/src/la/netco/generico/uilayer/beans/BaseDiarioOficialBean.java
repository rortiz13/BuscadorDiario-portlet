package la.netco.generico.uilayer.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;

import la.netco.generico.persistencia.dto.DiariosPdf;
import la.netco.generico.persistencia.dto.Entidades;
import la.netco.generico.persistencia.dto.TiposNormas;
import la.netco.generico.servicios.ServiceDao;
import la.netco.generico.utils.ConstantesQuemadas;

import org.apache.commons.io.IOUtils;

import com.liferay.portal.theme.ThemeDisplay;
import com.sun.faces.context.flash.ELFlash;

public class BaseDiarioOficialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{serviceDao}")
	private transient ServiceDao daoServicio;

	private DiarioOficialDataModel diarioOficialDataModel;
	private IndicesDataModel indicesDataModel;

	private List<SelectItem> itemsEntidad;
	private List<SelectItem> itemsTiposNormas;

	private String numero;
	private String codigoEntidadSel;
	private String codigoTipoNormaSel;
	private String fechaEscrita;

	private boolean activo = false;
	private boolean actRangoFechas = false;

	private Integer numeroresultados;
	private Integer idEntidadSeleccionada;

	private Date fecha;
	private Date fechaInicial;
	private Date fechaFinal;

	public BaseDiarioOficialBean() {
		System.out.println("###Construyendo Base");
		diarioOficialDataModel = new DiarioOficialDataModel();
		indicesDataModel = new IndicesDataModel();
	}

	public String detalleUtilDtl(String numeroRecibido) {
		ELFlash.getFlash().put(ConstantesQuemadas.NUMDIARIO, numeroRecibido);
		return ConstantesQuemadas.NAV_VISORDETALLE;
	}

	@SuppressWarnings("unchecked")
	public void descargarPDF(String numeroObjeto) throws IOException, SQLException, NoSuchAlgorithmException {
		HashMap<Integer, String> paramsBusqueda = new HashMap<Integer, String>();
		paramsBusqueda.put(0, numeroObjeto);

		List<DiariosPdf> listaDiarioPDF = (List<DiariosPdf>) getDaoServicio().getGenericCommonDao().executeFind(DiariosPdf.class, paramsBusqueda,
				DiariosPdf.NOM_CONSULPORDIARIOFICIAL);
		DiariosPdf diarioPdfCargado = listaDiarioPDF.get(0);

		InputStream streamCargado = diarioPdfCargado.getTexto().getBinaryStream();

		File fileCarga = new File(ConstantesQuemadas.RUTA_ABSOLUTA_TEMPDOWNLOADS);

		if (!fileCarga.exists()) {
			fileCarga.mkdirs();
		}

		String auxiNum = diarioPdfCargado.getDrofNumero().replace('.', 'D');
		long tiempoAuxi = System.currentTimeMillis();
		String nombreGenerado = auxiNum + tiempoAuxi + ".pdf";

		String rutaFile = fileCarga.getPath() + File.separator + nombreGenerado;
		fileCarga = new File(rutaFile);
		if (!fileCarga.exists()) {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			OutputStream salidaFile = new DigestOutputStream(new FileOutputStream(rutaFile), digest);
			try {
				IOUtils.copyLarge(streamCargado, salidaFile);
			} finally {
				salidaFile.close();
			}
		}

		ExternalContext contextoExt = FacesContext.getCurrentInstance().getExternalContext();
		PortletRequest portletRequest = (PortletRequest) contextoExt.getRequest();
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute("THEME_DISPLAY");
		String portalURL = themeDisplay.getServerName();
		String redirect = obtenerProtocolHttp(themeDisplay) + portalURL + ConstantesQuemadas.RUTA_PUBLICA_TEMPDOWNLOADS + nombreGenerado;
		contextoExt.redirect(redirect);

	}

	public static String obtenerProtocolHttp(ThemeDisplay request) {
		String protocol = "http://";
		if (request.isSecure())
			protocol = "https://";
		return protocol;
	}

	@SuppressWarnings("unchecked")
	public void cargaItemsEntidad() {
		try {

			List<Entidades> listaAuxientidades = (List<Entidades>) getDaoServicio().getGenericCommonDao().loadAll(Entidades.class);

			itemsEntidad = new ArrayList<SelectItem>();

			if (!listaAuxientidades.isEmpty()) {
				for (Entidades entidades : listaAuxientidades) {
					itemsEntidad.add(new SelectItem(entidades.getCodigo(), entidades.getNombre()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargaItemsTiposNormas() {
		try {
			String tinoCargados = ConstantesQuemadas.getValorPropiedad(ConstantesQuemadas.PROPIEDADTINO);
			itemsTiposNormas = new ArrayList<SelectItem>();

			for (String tinoCargado : tinoCargados.split(",")) {
				TiposNormas objTipoNorma = (TiposNormas) getDaoServicio().getGenericCommonDao().read(TiposNormas.class, tinoCargado);
				if (objTipoNorma != null) {
					itemsTiposNormas.add(new SelectItem(objTipoNorma.getCodigo(), objTipoNorma.getNombre()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ServiceDao getDaoServicio() {
		return daoServicio;
	}

	public void setDaoServicio(ServiceDao daoServicio) {
		this.daoServicio = daoServicio;
	}

	public DiarioOficialDataModel getDiarioOficialDataModel() {
		return diarioOficialDataModel;
	}

	public void setDiarioOficialDataModel(DiarioOficialDataModel diarioOficialDataModel) {
		this.diarioOficialDataModel = diarioOficialDataModel;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getFechaEscrita() {
		return fechaEscrita;
	}

	public void setFechaEscrita(String fechaEscrita) {
		this.fechaEscrita = fechaEscrita;
	}

	public boolean isActRangoFechas() {
		return actRangoFechas;
	}

	public void setActRangoFechas(boolean actRangoFechas) {
		this.actRangoFechas = actRangoFechas;
	}

	public Integer getNumeroresultados() {
		return numeroresultados;
	}

	public void setNumeroresultados(Integer numeroresultados) {
		this.numeroresultados = numeroresultados;
	}

	public IndicesDataModel getIndicesDataModel() {
		return indicesDataModel;
	}

	public void setIndicesDataModel(IndicesDataModel indicesDataModel) {
		this.indicesDataModel = indicesDataModel;
	}

	public String getCodigoEntidadSel() {
		return codigoEntidadSel;
	}

	public void setCodigoEntidadSel(String codigoEntidadSel) {
		this.codigoEntidadSel = codigoEntidadSel;
	}

	public String getCodigoTipoNormaSel() {
		return codigoTipoNormaSel;
	}

	public void setCodigoTipoNormaSel(String codigoTipoNormaSel) {
		this.codigoTipoNormaSel = codigoTipoNormaSel;
	}

	public List<SelectItem> getItemsEntidad() {
		return itemsEntidad;
	}

	public void setItemsEntidad(List<SelectItem> itemsEntidad) {
		this.itemsEntidad = itemsEntidad;
	}

	public List<SelectItem> getItemsTiposNormas() {
		return itemsTiposNormas;
	}

	public void setItemsTiposNormas(List<SelectItem> itemsTiposNormas) {
		this.itemsTiposNormas = itemsTiposNormas;
	}

	public Integer getIdEntidadSeleccionada() {
		return idEntidadSeleccionada;
	}

	public void setIdEntidadSeleccionada(Integer idEntidadSeleccionada) {
		this.idEntidadSeleccionada = idEntidadSeleccionada;
	}

}
