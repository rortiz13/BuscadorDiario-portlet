package la.netco.generico.uilayer.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import la.netco.generico.utils.ConstantesQuemadas;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;

@ManagedBean
@ViewScoped
public class NumeroDiarioBean extends BaseDiarioOficialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public NumeroDiarioBean() {
		System.out.println("###Constructor NumeroDiario");
	}

	public void cargaFiltros() {
		List<Criterion> filtros = new ArrayList<Criterion>();
		setNumeroresultados(0);
		getDiarioOficialDataModel().setRowCount(null);

		if (Validator.isNull(getNumero())) {
			setActivo(false);
			FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.INFODIGITENUMERO);
		}

		if (Validator.isNotNull(getNumero())) {
			DecimalFormat formatoDecimal = new DecimalFormat("###,###.###");
			Double numeroDDiario = Double.parseDouble(getNumero());
			String numeroSDiario = formatoDecimal.format(numeroDDiario);
			filtros.add(Restrictions.like("numero", "%" + numeroSDiario + "%"));
			setActivo(true);
		}
		getDiarioOficialDataModel().setFiltros(filtros);
		setNumeroresultados(getDiarioOficialDataModel().getRowCount());

	}
}
