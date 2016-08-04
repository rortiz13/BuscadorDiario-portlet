package la.netco.generico.uilayer.beans;

import la.netco.generico.beans.datamodels.GenericDataModel;
import la.netco.generico.persistencia.dto.DiariosOficiales;

import org.hibernate.criterion.Order;


public final class DiarioOficialDataModel extends GenericDataModel<DiariosOficiales, String> {
	private static final long serialVersionUID = 1L;

	public DiarioOficialDataModel() {
		super(DiariosOficiales.class);
		setOrderBy(Order.desc("numero"));
	}

	@Override
	protected Object getId(DiariosOficiales t) {
		return t.getNumero();
	}
}
