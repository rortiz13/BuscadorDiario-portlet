package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import la.netco.generico.utils.Esquemas;

/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "RECIBOS", schema = Esquemas.DIARIOFICIAL)
public class Recibos implements Serializable {

	private static final long serialVersionUID = 1L;
	private RecibosId id;
	private Indices indices;
	private Date fecrea;
	private long valor;
	private Date fecha;
	private Date femod;
	private String numero;
	private Set<TiposPagos> tiposPagoses = new HashSet<TiposPagos>(0);

	public Recibos() {
	}

	public Recibos(RecibosId id, Indices indices, Date fecrea, long valor) {
		this.id = id;
		this.indices = indices;
		this.fecrea = fecrea;
		this.valor = valor;
	}

	public Recibos(RecibosId id, Indices indices, Date fecrea, long valor, Date fecha, Date femod, String numero, Set<TiposPagos> tiposPagoses) {
		this.id = id;
		this.indices = indices;
		this.fecrea = fecrea;
		this.valor = valor;
		this.fecha = fecha;
		this.femod = femod;
		this.numero = numero;
		this.tiposPagoses = tiposPagoses;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "consecutivo", column = @Column(name = "CONSECUTIVO", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "indDrofNumero", column = @Column(name = "IND_DROF_NUMERO", nullable = false, length = 12)),
			@AttributeOverride(name = "indConsecutivo", column = @Column(name = "IND_CONSECUTIVO", nullable = false, precision = 22, scale = 0)) })
	public RecibosId getId() {
		return this.id;
	}

	public void setId(RecibosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "IND_CONSECUTIVO", referencedColumnName = "CONSECUTIVO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "IND_DROF_NUMERO", referencedColumnName = "DROF_NUMERO", nullable = false, insertable = false, updatable = false) })
	public Indices getIndices() {
		return this.indices;
	}

	public void setIndices(Indices indices) {
		this.indices = indices;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECREA", nullable = false, length = 7)
	public Date getFecrea() {
		return this.fecrea;
	}

	public void setFecrea(Date fecrea) {
		this.fecrea = fecrea;
	}

	@Column(name = "VALOR", nullable = false, precision = 10, scale = 0)
	public long getValor() {
		return this.valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FEMOD", length = 7)
	public Date getFemod() {
		return this.femod;
	}

	public void setFemod(Date femod) {
		this.femod = femod;
	}

	@Column(name = "NUMERO", length = 15)
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "REC_TIP_PAGOS", schema = "DIARIOFICIAL", joinColumns = { @JoinColumn(name = "RECI_CONSECUTIVO", nullable = false, updatable = false),
			@JoinColumn(name = "RECI_IND_DROF_NUMERO", nullable = false, updatable = false), @JoinColumn(name = "RECI_IND_CONSECUTIVO", nullable = false, updatable = false) },

	inverseJoinColumns = { @JoinColumn(name = "TIPA_CODIGO", nullable = false, updatable = false), @JoinColumn(name = "TIPA_ENBA_CODIGO", nullable = false, updatable = false) })
	public Set<TiposPagos> getTiposPagoses() {
		return this.tiposPagoses;
	}

	public void setTiposPagoses(Set<TiposPagos> tiposPagoses) {
		this.tiposPagoses = tiposPagoses;
	}

}
