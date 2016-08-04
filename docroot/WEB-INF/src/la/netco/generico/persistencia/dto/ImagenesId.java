package la.netco.generico.persistencia.dto;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author cpineros
 * 
 */
@Embeddable
public class ImagenesId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String drofNumero;
	private BigDecimal consimg;

	public ImagenesId() {
	}

	public ImagenesId(String drofNumero, BigDecimal consimg) {
		this.drofNumero = drofNumero;
		this.consimg = consimg;
	}

	@Column(name = "DROF_NUMERO", nullable = false, length = 12)
	public String getDrofNumero() {
		return this.drofNumero;
	}

	public void setDrofNumero(String drofNumero) {
		this.drofNumero = drofNumero;
	}

	@Column(name = "CONSIMG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getConsimg() {
		return this.consimg;
	}

	public void setConsimg(BigDecimal consimg) {
		this.consimg = consimg;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ImagenesId))
			return false;
		ImagenesId castOther = (ImagenesId) other;

		return ((this.getDrofNumero() == castOther.getDrofNumero()) || (this.getDrofNumero() != null && castOther.getDrofNumero() != null && this.getDrofNumero().equals(
				castOther.getDrofNumero())))
				&& ((this.getConsimg() == castOther.getConsimg()) || (this.getConsimg() != null && castOther.getConsimg() != null && this.getConsimg().equals(
						castOther.getConsimg())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDrofNumero() == null ? 0 : this.getDrofNumero().hashCode());
		result = 37 * result + (getConsimg() == null ? 0 : this.getConsimg().hashCode());
		return result;
	}

}
