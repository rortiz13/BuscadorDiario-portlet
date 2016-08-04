package la.netco.generico.uilayer.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import la.netco.generico.utils.ConstantesQuemadas;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean
@ViewScoped
public class ConfiguracionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String textoAyuda;
	private String textoSuscripcion;
	private String correoContacto;
	private String telContacto;
	private AssetCategory categoriaCargada;
	private AssetCategoryProperty propiedadSus;
	private AssetCategoryProperty propiedadCorreoContacto;
	private AssetCategoryProperty propiedadTelContacto;

	public ConfiguracionBean() throws SystemException {
		cargaParametros();
	}

	@SuppressWarnings("unchecked")
	public void cargaParametros() throws SystemException {
		DynamicQuery consultaDinamica;
		consultaDinamica = DynamicQueryFactoryUtil.forClass(AssetCategory.class).add(
				PropertyFactoryUtil.forName(ConstantesQuemadas.CAMPONAME).eq(ConstantesQuemadas.getValorPropiedad(ConstantesQuemadas.PROPIEDADCAMPOAYUDA)));
		List<AssetCategory> categoriaAyuda = AssetCategoryLocalServiceUtil.dynamicQuery(consultaDinamica);
		categoriaCargada = categoriaAyuda.get(0);
		textoAyuda = categoriaCargada.getDescription();

		consultaDinamica = DynamicQueryFactoryUtil.forClass(AssetCategory.class).add(
				PropertyFactoryUtil.forName(ConstantesQuemadas.CAMPONAME).eq(ConstantesQuemadas.getValorPropiedad(ConstantesQuemadas.PROPIEDADPIE)));
		List<AssetCategory> categoriaPie = AssetCategoryLocalServiceUtil.dynamicQuery(consultaDinamica);
		List<AssetCategoryProperty> categoriasPie = AssetCategoryPropertyLocalServiceUtil.getCategoryProperties(categoriaPie.get(0).getCategoryId());
		propiedadSus = categoriasPie.get(0);
		textoSuscripcion = propiedadSus.getValue();
		propiedadCorreoContacto = categoriasPie.get(1);
		correoContacto = propiedadCorreoContacto.getValue();
		propiedadTelContacto = categoriasPie.get(2);
		telContacto = propiedadTelContacto.getValue();

	}

	public void guardarCambios() throws SystemException {
		System.out.println("*** Actualizando parametros de pie");
		categoriaCargada.setDescription(textoAyuda);
		propiedadSus.setValue(textoSuscripcion);
		propiedadCorreoContacto.setValue(correoContacto);
		propiedadTelContacto.setValue(telContacto);
		AssetCategoryLocalServiceUtil.updateAssetCategory(categoriaCargada);
		AssetCategoryPropertyLocalServiceUtil.updateAssetCategoryProperty(propiedadSus);
		AssetCategoryPropertyLocalServiceUtil.updateAssetCategoryProperty(propiedadCorreoContacto);
		AssetCategoryPropertyLocalServiceUtil.updateAssetCategoryProperty(propiedadTelContacto);
		FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.INFOACTUALIZADA);
		System.out.println("!!! Parametos actualizados");
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

	public void setCorreoContacto(String CorreoContacto) {
		this.correoContacto = CorreoContacto;
	}

	public String getTelContacto() {
		return telContacto;
	}

	public void setTelContacto(String telContacto) {
		this.telContacto = telContacto;
	}

}
