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
@Table(name = "TIPOS_EDICIONES", schema = Esquemas.DIARIOFICIAL)
public class TiposEdiciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private Set<DiariosOficiales> diariosOficialeses = new HashSet<DiariosOficiales>(0);

	public TiposEdiciones() {
	}

	public TiposEdiciones(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public TiposEdiciones(String codigo, String nombre, Set<DiariosOficiales> diariosOficialeses) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.diariosOficialeses = diariosOficialeses;
	}

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false, length = 3)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "NOMBRE", nullable = false, length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposEdiciones")
	public Set<DiariosOficiales> getDiariosOficialeses() {
		return this.diariosOficialeses;
	}

	public void setDiariosOficialeses(Set<DiariosOficiales> diariosOficialeses) {
		this.diariosOficialeses = diariosOficialeses;
	}

}
