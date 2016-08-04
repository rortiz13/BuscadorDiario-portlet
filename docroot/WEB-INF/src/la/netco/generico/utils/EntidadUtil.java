package la.netco.generico.utils;

import java.io.Serializable;
import java.util.List;


public class EntidadUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreEntidad;
	private List<IndiceUtil>  listIndiceUtils;

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public List<IndiceUtil> getListIndiceUtils() {
		return listIndiceUtils;
	}

	public void setListIndiceUtils(List<IndiceUtil> listIndiceUtils) {
		this.listIndiceUtils = listIndiceUtils;
	}

}
