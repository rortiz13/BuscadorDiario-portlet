package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import la.netco.generico.utils.Esquemas;

/**
 * @author jahumada
 * 
 */
/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "DIARIOS_OFICIALES", schema = Esquemas.DIARIOFICIAL)
public class DiariosOficiales implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numero;
	private TiposEdiciones tiposEdiciones;
	private Short pagDuc;
	private String publica;
	private String contieneDuc;
	private Date fecha;

	/*
	 * private Set<Indices> indiceses = new HashSet<Indices>(0); private
	 * Set<Imagenes> imageneses = new HashSet<Imagenes>(0); private DiariosPdf
	 * diariosPdf; private DiariosHtml diariosHtml;
	 */

	public DiariosOficiales() {
	}

	public DiariosOficiales(String numero, TiposEdiciones tiposEdiciones, Date fecha) {
		this.numero = numero;
		this.tiposEdiciones = tiposEdiciones;
		this.fecha = fecha;
	}

	/*
	 * public DiariosOficiales(String numero, TiposEdiciones tiposEdiciones,
	 * Short pagDuc, String publica, String contieneDuc, Date fecha,
	 * Set<Indices> indiceses, Set<Imagenes> imageneses, DiariosPdf diariosPdf,
	 * DiariosHtml diariosHtml) { this.numero = numero; this.tiposEdiciones =
	 * tiposEdiciones; this.pagDuc = pagDuc; this.publica = publica;
	 * this.contieneDuc = contieneDuc; this.fecha = fecha; this.indiceses =
	 * indiceses; this.imageneses = imageneses; this.diariosPdf = diariosPdf;
	 * this.diariosHtml = diariosHtml; }
	 */

	@Id
	@Column(name = "NUMERO", unique = true, nullable = false, length = 12)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIED_CODIGO", nullable = false)
	public TiposEdiciones getTiposEdiciones() {
		return this.tiposEdiciones;
	}

	public void setTiposEdiciones(TiposEdiciones tiposEdiciones) {
		this.tiposEdiciones = tiposEdiciones;
	}

	@Column(name = "PAG_DUC", precision = 4, scale = 0)
	public Short getPagDuc() {
		return this.pagDuc;
	}

	public void setPagDuc(Short pagDuc) {
		this.pagDuc = pagDuc;
	}

	@Column(name = "PUBLICA", length = 2)
	public String getPublica() {
		return this.publica;
	}

	public void setPublica(String publica) {
		this.publica = publica;
	}

	@Column(name = "CONTIENE_DUC", length = 2)
	public String getContieneDuc() {
		return this.contieneDuc;
	}

	public void setContieneDuc(String contieneDuc) {
		this.contieneDuc = contieneDuc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "diariosOficiales") public
	 * Set<Indices> getIndiceses() { return this.indiceses; }
	 * 
	 * public void setIndiceses(Set<Indices> indiceses) { this.indiceses =
	 * indiceses; }
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "diariosOficiales") public
	 * Set<Imagenes> getImageneses() { return this.imageneses; }
	 * 
	 * public void setImageneses(Set<Imagenes> imageneses) { this.imageneses =
	 * imageneses; }
	 * 
	 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "diariosOficiales" ) public
	 * DiariosPdf getDiariosPdf() { return this.diariosPdf; }
	 * 
	 * public void setDiariosPdf(DiariosPdf diariosPdf) { this.diariosPdf =
	 * diariosPdf; }
	 * 
	 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "diariosOficiales") public
	 * DiariosHtml getDiariosHtml() { return this.diariosHtml; }
	 * 
	 * public void setDiariosHtml(DiariosHtml diariosHtml) { this.diariosHtml =
	 * diariosHtml; }
	 */

}
