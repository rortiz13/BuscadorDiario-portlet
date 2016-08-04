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
public class RecibosId implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal consecutivo;
	private String indDrofNumero;
	private BigDecimal indConsecutivo;

	public RecibosId() {
	}

	public RecibosId(BigDecimal consecutivo, String indDrofNumero, BigDecimal indConsecutivo) {
		this.consecutivo = consecutivo;
		this.indDrofNumero = indDrofNumero;
		this.indConsecutivo = indConsecutivo;
	}

	@Column(name = "CONSECUTIVO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(BigDecimal consecutivo) {
		this.consecutivo = consecutivo;
	}

	@Column(name = "IND_DROF_NUMERO", nullable = false, length = 12)
	public String getIndDrofNumero() {
		return this.indDrofNumero;
	}

	public void setIndDrofNumero(String indDrofNumero) {
		this.indDrofNumero = indDrofNumero;
	}

	@Column(name = "IND_CONSECUTIVO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getIndConsecutivo() {
		return this.indConsecutivo;
	}

	public void setIndConsecutivo(BigDecimal indConsecutivo) {
		this.indConsecutivo = indConsecutivo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RecibosId))
			return false;
		RecibosId castOther = (RecibosId) other;

		return ((this.getConsecutivo() == castOther.getConsecutivo()) || (this.getConsecutivo() != null && castOther.getConsecutivo() != null && this.getConsecutivo().equals(
				castOther.getConsecutivo())))
				&& ((this.getIndDrofNumero() == castOther.getIndDrofNumero()) || (this.getIndDrofNumero() != null && castOther.getIndDrofNumero() != null && this
						.getIndDrofNumero().equals(castOther.getIndDrofNumero())))
				&& ((this.getIndConsecutivo() == castOther.getIndConsecutivo()) || (this.getIndConsecutivo() != null && castOther.getIndConsecutivo() != null && this
						.getIndConsecutivo().equals(castOther.getIndConsecutivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getConsecutivo() == null ? 0 : this.getConsecutivo().hashCode());
		result = 37 * result + (getIndDrofNumero() == null ? 0 : this.getIndDrofNumero().hashCode());
		result = 37 * result + (getIndConsecutivo() == null ? 0 : this.getIndConsecutivo().hashCode());
		return result;
	}

}
