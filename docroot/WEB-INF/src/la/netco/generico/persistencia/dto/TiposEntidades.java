package la.netco.generico.persistencia.dto;

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
@Table(name = "TIPOS_ENTIDADES", schema = Esquemas.DIARIOFICIAL)
public class TiposEntidades implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private Set<Entidades> entidadeses = new HashSet<Entidades>(0);

	public TiposEntidades() {
	}

	public TiposEntidades(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TiposEntidades(String codigo, String nombre, Set<Entidades> entidadeses) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.entidadeses = entidadeses;
	}

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false, length = 5)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "NOMBRE", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposEntidades")
	public Set<Entidades> getEntidadeses() {
		return this.entidadeses;
	}

	public void setEntidadeses(Set<Entidades> entidadeses) {
		this.entidadeses = entidadeses;
	}

}
