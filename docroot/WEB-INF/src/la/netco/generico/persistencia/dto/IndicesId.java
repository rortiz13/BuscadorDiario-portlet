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
public class IndicesId implements  Serializable {


	private static final long serialVersionUID = 1L;
	private BigDecimal consecutivo;
	private String drofNumero;

	public IndicesId() {
	}

	public IndicesId(BigDecimal consecutivo, String drofNumero) {
		this.consecutivo = consecutivo;
		this.drofNumero = drofNumero;
	}

	@Column(name = "CONSECUTIVO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(BigDecimal consecutivo) {
		this.consecutivo = consecutivo;
	}

	@Column(name = "DROF_NUMERO", nullable = false, length = 12)
	public String getDrofNumero() {
		return this.drofNumero;
	}

	public void setDrofNumero(String drofNumero) {
		this.drofNumero = drofNumero;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IndicesId))
			return false;
		IndicesId castOther = (IndicesId) other;

		return ((this.getConsecutivo() == castOther.getConsecutivo()) || (this
				.getConsecutivo() != null && castOther.getConsecutivo() != null && this
				.getConsecutivo().equals(castOther.getConsecutivo())))
				&& ((this.getDrofNumero() == castOther.getDrofNumero()) || (this
						.getDrofNumero() != null
						&& castOther.getDrofNumero() != null && this
						.getDrofNumero().equals(castOther.getDrofNumero())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsecutivo() == null ? 0 : this.getConsecutivo()
						.hashCode());
		result = 37
				* result
				+ (getDrofNumero() == null ? 0 : this.getDrofNumero()
						.hashCode());
		return result;
	}

}
