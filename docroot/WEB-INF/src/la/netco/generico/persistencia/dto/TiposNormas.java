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
@Table(name = "TIPOS_NORMAS", schema = Esquemas.DIARIOFICIAL)
public class TiposNormas implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String codGrupo;
	private Set<Indices> indiceses = new HashSet<Indices>(0);

	public TiposNormas() {
	}

	public TiposNormas(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TiposNormas(String codigo, String nombre, String codGrupo, Set<Indices> indiceses) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.codGrupo = codGrupo;
		this.indiceses = indiceses;
	}

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false, length = 8)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "NOMBRE", nullable = false, length = 150)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "COD_GRUPO", length = 8)
	public String getCodGrupo() {
		return this.codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposNormas")
	public Set<Indices> getIndiceses() {
		return this.indiceses;
	}

	public void setIndiceses(Set<Indices> indiceses) {
		this.indiceses = indiceses;
	}

}
