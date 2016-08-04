package la.netco.generico.uilayer.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class FechaDiarioBean extends BaseDiarioOficialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public FechaDiarioBean() {
		System.out.println("###Construyendo FechaDiario");
	}

	public void cargaFiltros() {
		try {
			List<Criterion> filtros = new ArrayList<Criterion>();
			boolean busqInicial = true;
			boolean busqFinal = true;
			boolean continuar = true;
			setNumeroresultados(0);
			getDiarioOficialDataModel().setRowCount(null);
			Date fechaUtil = null;
			String fechaSimuladaS = "01/01/1700";
			DateFormat formateador = new SimpleDateFormat("dd/mm/yyyy");
			Date fechasimulada = formateador.parse(fechaSimuladaS);
			if (getFechaEscrita() != "") {
				fechaUtil = formateador.parse(getFechaEscrita());
			}

			// Valida si las fechas son nulas y dar continuidad a la consulta

			if (Validator.isNull(fechaUtil) && (Validator.isNull(getFechaInicial()) && Validator.isNull(getFechaFinal()))) {
				FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.INFOFALTACRITERIOFECHA);
				setActivo(false);
			}

			if (Validator.isNull(getFechaInicial())) {
				busqInicial = false;
			}
			if (Validator.isNull(getFechaFinal())) {
				busqFinal = false;
			}

			if (busqInicial && busqFinal && continuar) {

				if (Validator.isNotNull(fechaUtil) && Validator.isNotNull(getFechaInicial()) && Validator.isNotNull(getFechaFinal())) {
					FacesMessageUtil.error(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORSOLOUNAFECHA);
					continuar = false;
					setActivo(false);
				}

				if ((Validator.isNotNull(getFechaInicial()) && Validator.isNotNull(getFechaFinal())) && Validator.isNull(fechaUtil)) {					
					if (getFechaFinal().before(getFechaInicial())) {
						FacesMessageUtil.info(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORMENORFECHAFINAL);
						continuar = false;
						setActivo(false);
					} else {
						filtros.add(Restrictions.between("fecha", getFechaInicial(), getFechaFinal()));
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
				filtros.add(Restrictions.between("fecha", getFechaInicial(), new Date(System.currentTimeMillis())));
				setActivo(true);
			}

			if (!busqInicial && busqFinal && continuar) {
				filtros.add(Restrictions.between("fecha", fechasimulada, getFechaFinal()));
				setActivo(true);
			}

			if (Validator.isNotNull(fechaUtil) && !busqInicial && !busqFinal) {
				filtros.add(Restrictions.eq("fecha", fechaUtil));
				setActivo(true);
			}

			if (filtros.isEmpty()) {
				setActRangoFechas(false);
			}

			getDiarioOficialDataModel().setFiltros(filtros);
			setNumeroresultados(getDiarioOficialDataModel().getRowCount());

		} catch (ParseException e) {
			FacesMessageUtil.error(FacesContext.getCurrentInstance(), ConstantesQuemadas.ERRORTIPOFECHA);
			setActivo(false);
		} catch (Exception e) {
			setActivo(false);
			e.printStackTrace();
		}
	}
}
