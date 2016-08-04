package la.netco.generico.persistencia.dto;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * @author cpineros
 *
 */
@Embeddable
public class TiposPagosId implements Serializable {


	private static final long serialVersionUID = 1L;
	private String codigo;
	private String enbaCodigo;

	public TiposPagosId() {
	}

	public TiposPagosId(String codigo, String enbaCodigo) {
		this.codigo = codigo;
		this.enbaCodigo = enbaCodigo;
	}

	@Column(name = "CODIGO", nullable = false, length = 2)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "ENBA_CODIGO", nullable = false, length = 2)
	public String getEnbaCodigo() {
		return this.enbaCodigo;
	}

	public void setEnbaCodigo(String enbaCodigo) {
		this.enbaCodigo = enbaCodigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TiposPagosId))
			return false;
		TiposPagosId castOther = (TiposPagosId) other;

		return ((this.getCodigo() == castOther.getCodigo()) || (this
				.getCodigo() != null && castOther.getCodigo() != null && this
				.getCodigo().equals(castOther.getCodigo())))
				&& ((this.getEnbaCodigo() == castOther.getEnbaCodigo()) || (this
						.getEnbaCodigo() != null
						&& castOther.getEnbaCodigo() != null && this
						.getEnbaCodigo().equals(castOther.getEnbaCodigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodigo() == null ? 0 : this.getCodigo().hashCode());
		result = 37
				* result
				+ (getEnbaCodigo() == null ? 0 : this.getEnbaCodigo()
						.hashCode());
		return result;
	}

}
