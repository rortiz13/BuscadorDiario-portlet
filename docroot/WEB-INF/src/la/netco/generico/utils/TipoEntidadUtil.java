package la.netco.generico.utils;

import java.io.Serializable;
import java.util.List;

public class TipoEntidadUtil implements Serializable {

	private static final long serialVersionUID = 2168675206942717814L;
	private String nombreTipoEntidad;
	private List<EntidadUtil> listaEntidadUtils;

	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}

	public void setNombreTipoEntidad(String nombreTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
	}

	public List<EntidadUtil> getListaEntidadUtils() {
		return listaEntidadUtils;
	}

	public void setListaEntidadUtils(List<EntidadUtil> listaEntidadUtils) {
		this.listaEntidadUtils = listaEntidadUtils;
	}

}
