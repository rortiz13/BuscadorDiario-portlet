package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import la.netco.generico.utils.Esquemas;

/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "INDICES", schema = Esquemas.DIARIOFICIAL)
@NamedQuery(name = Indices.NAMED_QUERY_INDICES, query = Indices.QUERY_INDICES)
public class Indices implements Serializable {

	private static final long serialVersionUID = 1L;
	private IndicesId id;
	private DiariosOficiales diariosOficiales;
	private Entidades entidades;
	private TiposNormas tiposNormas;
	private String titulo;
	private Date fechaRecibido;
	private Date fecha;
	private Clob norma;
	private String resumen;
	private String feDeErratas;
	private String fecano;
	private String anno;
	private String dia;
	private String mes;
	private String usCrea;

	// private Set<Recibos> reciboses = new HashSet<Recibos>(0);

	public Indices() {
	}

	public Indices(IndicesId id, DiariosOficiales diariosOficiales, Entidades entidades, TiposNormas tiposNormas) {
		this.id = id;
		this.diariosOficiales = diariosOficiales;
		this.entidades = entidades;
		this.tiposNormas = tiposNormas;
	}

	/*
	 * public Indices(IndicesId id, DiariosOficiales diariosOficiales, Entidades
	 * entidades, TiposNormas tiposNormas, String titulo, Date fechaRecibido,
	 * Date fecha, Clob norma, String resumen, String feDeErratas, String
	 * fecano, String anno, String dia, String mes, String usCrea, Set<Recibos>
	 * reciboses) { this.id = id; this.diariosOficiales = diariosOficiales;
	 * this.entidades = entidades; this.tiposNormas = tiposNormas; this.titulo =
	 * titulo; this.fechaRecibido = fechaRecibido; this.fecha = fecha;
	 * this.norma = norma; this.resumen = resumen; this.feDeErratas =
	 * feDeErratas; this.fecano = fecano; this.anno = anno; this.dia = dia;
	 * this.mes = mes; this.usCrea = usCrea; this.reciboses = reciboses; }
	 */

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "consecutivo", column = @Column(name = "CONSECUTIVO", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "drofNumero", column = @Column(name = "DROF_NUMERO", nullable = false, length = 12)) })
	public IndicesId getId() {
		return this.id;
	}

	public void setId(IndicesId id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENT_CODIGO", nullable = false)
	public Entidades getEntidades() {
		return this.entidades;
	}

	public void setEntidades(Entidades entidades) {
		this.entidades = entidades;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TINO_CODIGO", nullable = false)
	public TiposNormas getTiposNormas() {
		return this.tiposNormas;
	}

	public void setTiposNormas(TiposNormas tiposNormas) {
		this.tiposNormas = tiposNormas;
	}

	@Column(name = "TITULO", length = 50)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_RECIBIDO", length = 7)
	public Date getFechaRecibido() {
		return this.fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "NORMA")
	public Clob getNorma() {
		return this.norma;
	}

	public void setNorma(Clob norma) {
		this.norma = norma;
	}

	@Column(name = "RESUMEN", length = 2000)
	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	@Column(name = "FE_DE_ERRATAS", length = 2000)
	public String getFeDeErratas() {
		return this.feDeErratas;
	}

	public void setFeDeErratas(String feDeErratas) {
		this.feDeErratas = feDeErratas;
	}

	@Column(name = "FECANO", length = 4)
	public String getFecano() {
		return this.fecano;
	}

	public void setFecano(String fecano) {
		this.fecano = fecano;
	}

	@Column(name = "ANNO", length = 4)
	public String getAnno() {
		return this.anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	@Column(name = "DIA", length = 2)
	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@Column(name = "MES", length = 2)
	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Column(name = "US_CREA", length = 15)
	public String getUsCrea() {
		return this.usCrea;
	}

	public void setUsCrea(String usCrea) {
		this.usCrea = usCrea;
	}

	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "indices") public
	 * Set<Recibos> getReciboses() { return this.reciboses; }
	 * 
	 * public void setReciboses(Set<Recibos> reciboses) { this.reciboses =
	 * reciboses; }
	 */

	public static final String NAMED_QUERY_INDICES = "consultaindeces";
	public static final String QUERY_INDICES = "FROM Indices indi where indi.diariosOficiales.numero = ? ORDER BY indi.entidades.tiposEntidades.codigo ASC  AND ORDER BY indi.entidades.codigo ASC";

}
