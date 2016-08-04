package la.netco.generico.utils;

import java.io.Serializable;

public class IndiceUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tituloResumen;
	private String resumen;

	public String getTituloResumen() {
		return tituloResumen;
	}

	public void setTituloResumen(String tituloResumen) {
		this.tituloResumen = tituloResumen;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

}
