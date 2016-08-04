package la.netco.generico.uilayer.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import la.netco.generico.utils.ConstantesQuemadas;

import com.liferay.portal.kernel.exception.SystemException;
import com.sun.faces.context.flash.ELFlash;

@ManagedBean
@ViewScoped
public class CapasVistasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String indexMenuTab;
	public String valor;
	public String textoAyuda;
	private String textoSuscripcion;
	private String correoContacto;
	private String telContacto;

	public CapasVistasBean() throws SystemException {
		System.out.println("###Construyendo CapasVistasBean");
		if (FacesContext.getCurrentInstance().getExternalContext().getFlash().containsKey(ConstantesQuemadas.LLAVEMENU)) {
			this.indexMenuTab = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(ConstantesQuemadas.LLAVEMENU);
		} else {
			this.indexMenuTab = ConstantesQuemadas.TABPORDEFECTOMENU;
		}

		ConfiguracionBean objConfiguracionBean = new ConfiguracionBean();
		setTextoAyuda(objConfiguracionBean.getTextoAyuda());
		setTextoSuscripcion(objConfiguracionBean.getTextoSuscripcion());
		setCorreoContacto(objConfiguracionBean.getCorreoContacto());
		setTelContacto(objConfiguracionBean.getTelContacto());
	}

	public String escuchadorMenu(String valorIndex) {
		System.out.println("Valor Index Es: " + valorIndex);
		ELFlash.getFlash().put(ConstantesQuemadas.LLAVEMENU, valorIndex);
		if (valorIndex.equals(ConstantesQuemadas.TABPORDEFECTOMENU)) {
			return ConstantesQuemadas.NAV_ULTIMODIARIO;
		} else if (valorIndex.equals(ConstantesQuemadas.TABPORNUMERODIARIO)) {
			return ConstantesQuemadas.NAV_NUMERODIARIO;
		} else if (valorIndex.equals(ConstantesQuemadas.TABPORFECHASDIARIO)) {
			return ConstantesQuemadas.NAV_RANGOFECHAS;
		} else if (valorIndex.equals(ConstantesQuemadas.TABPORNORMASDIARIO)) {
			return ConstantesQuemadas.NAV_NORMADIARIO;
		}
		return ConstantesQuemadas.NAV_ULTIMODIARIO;
	}

	public String getIndexMenuTab() {
		return indexMenuTab;
	}

	public void setIndexMenuTab(String indexMenuTab) {
		this.indexMenuTab = indexMenuTab;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTextoAyuda() {
		return textoAyuda;
	}

	public void setTextoAyuda(String textoAyuda) {
		this.textoAyuda = textoAyuda;
	}

	public String getTextoSuscripcion() {
		return textoSuscripcion;
	}

	public void setTextoSuscripcion(String textoSuscripcion) {
		this.textoSuscripcion = textoSuscripcion;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getTelContacto() {
		return telContacto;
	}

	public void setTelContacto(String telContacto) {
		this.telContacto = telContacto;
	}

}
