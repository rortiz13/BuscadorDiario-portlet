package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import la.netco.generico.utils.Esquemas;

@Entity
@Table(name = "ENTIDADES", schema = Esquemas.DIARIOFICIAL)
public class Entidades implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private TiposEntidades tiposEntidades;
	private String nombre;
	private String nit;
	private Set<Indices> indiceses = new HashSet<Indices>(0);

	public Entidades() {
	}

	public Entidades(String codigo, TiposEntidades tiposEntidades, String nombre) {
		this.codigo = codigo;
		this.tiposEntidades = tiposEntidades;
		this.nombre = nombre;
	}

	public Entidades(String codigo, TiposEntidades tiposEntidades, String nombre, String nit, Set<Indices> indiceses) {
		this.codigo = codigo;
		this.tiposEntidades = tiposEntidades;
		this.nombre = nombre;
		this.nit = nit;
		this.indiceses = indiceses;
	}

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false, length = 10)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIEN_CODIGO", nullable = false)
	public TiposEntidades getTiposEntidades() {
		return this.tiposEntidades;
	}

	public void setTiposEntidades(TiposEntidades tiposEntidades) {
		this.tiposEntidades = tiposEntidades;
	}

	@Column(name = "NOMBRE", nullable = false, length = 250)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "NIT", length = 15)
	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidades")
	public Set<Indices> getIndiceses() {
		return this.indiceses;
	}

	public void setIndiceses(Set<Indices> indiceses) {
		this.indiceses = indiceses;
	}

	/*
	 * public static final String NAMED_QUERY_ENTIDADES_POR_TIPENT =
	 * "consulPorTiposEntidades"; public static final String
	 * QUERY_ENTIDADES_POR_TIPENT =
	 * "FROM Entidades enti where enti.tiposEntidades.";
	 */
	
}
