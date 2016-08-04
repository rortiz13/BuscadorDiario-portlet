package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import la.netco.generico.utils.Esquemas;

/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "ENTIDADES_BANCARIAS", schema = Esquemas.DIARIOFICIAL)
public class EntidadesBancarias implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String entidad;
	private String nit;
	private Set<TiposPagos> tiposPagoses = new HashSet<TiposPagos>(0);

	public EntidadesBancarias() {
	}

	public EntidadesBancarias(String codigo, String entidad) {
		this.codigo = codigo;
		this.entidad = entidad;
	}

	public EntidadesBancarias(String codigo, String entidad, String nit, Set<TiposPagos> tiposPagoses) {
		this.codigo = codigo;
		this.entidad = entidad;
		this.nit = nit;
		this.tiposPagoses = tiposPagoses;
	}

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false, length = 2)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "ENTIDAD", nullable = false, length = 50)
	public String getEntidad() {
		return this.entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	@Column(name = "NIT", length = 15)
	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidadesBancarias")
	public Set<TiposPagos> getTiposPagoses() {
		return this.tiposPagoses;
	}

	public void setTiposPagoses(Set<TiposPagos> tiposPagoses) {
		this.tiposPagoses = tiposPagoses;
	}

}
