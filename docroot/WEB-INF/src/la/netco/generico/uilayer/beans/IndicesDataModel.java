package la.netco.generico.uilayer.beans;

import la.netco.generico.beans.datamodels.GenericDataModel;
import la.netco.generico.persistencia.dto.Indices;
import la.netco.generico.persistencia.dto.IndicesId;

import org.hibernate.criterion.Order;

public final class IndicesDataModel extends GenericDataModel<Indices, IndicesId> {
	private static final long serialVersionUID = 1L;

	public IndicesDataModel() {
		super(Indices.class);
		setOrderBy(Order.desc("anno"));
	}

	@Override
	protected Object getId(Indices t) {
		return t.getId();
	}
}
