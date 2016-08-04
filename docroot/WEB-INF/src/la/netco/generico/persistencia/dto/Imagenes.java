package la.netco.generico.persistencia.dto;


import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import la.netco.generico.utils.Esquemas;


@Entity
@Table(name = "IMAGENES", schema = Esquemas.DIARIOFICIAL)
public class Imagenes implements Serializable {

	private static final long serialVersionUID = 1L;
	private ImagenesId id;
	private DiariosOficiales diariosOficiales;
	private String idimagen;
	private String volumen;
	private Blob imagen;

	public Imagenes() {
	}

	public Imagenes(ImagenesId id, DiariosOficiales diariosOficiales,
			String idimagen, String volumen) {
		this.id = id;
		this.diariosOficiales = diariosOficiales;
		this.idimagen = idimagen;
		this.volumen = volumen;
	}

	public Imagenes(ImagenesId id, DiariosOficiales diariosOficiales,
			String idimagen, String volumen, Blob imagen) {
		this.id = id;
		this.diariosOficiales = diariosOficiales;
		this.idimagen = idimagen;
		this.volumen = volumen;
		this.imagen = imagen;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "drofNumero", column = @Column(name = "DROF_NUMERO", nullable = false, length = 12)),
			@AttributeOverride(name = "consimg", column = @Column(name = "CONSIMG", nullable = false, precision = 22, scale = 0)) })
	public ImagenesId getId() {
		return this.id;
	}

	public void setId(ImagenesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DROF_NUMERO", nullable = false, insertable = false, updatable = false)
	public DiariosOficiales getDiariosOficiales() {
		return this.diariosOficiales;
	}

	public void setDiariosOficiales(DiariosOficiales diariosOficiales) {
		this.diariosOficiales = diariosOficiales;
	}

	@Column(name = "IDIMAGEN", nullable = false, length = 20)
	public String getIdimagen() {
		return this.idimagen;
	}

	public void setIdimagen(String idimagen) {
		this.idimagen = idimagen;
	}

	@Column(name = "VOLUMEN", nullable = false, length = 5)
	public String getVolumen() {
		return this.volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

	@Column(name = "IMAGEN")
	public Blob getImagen() {
		return this.imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

}
