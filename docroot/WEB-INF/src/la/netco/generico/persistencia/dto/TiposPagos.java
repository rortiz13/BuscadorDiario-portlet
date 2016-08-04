package la.netco.generico.persistencia.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import la.netco.generico.utils.Esquemas;

/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "TIPOS_PAGOS", schema = Esquemas.DIARIOFICIAL)
public class TiposPagos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private TiposPagosId id;
	private EntidadesBancarias entidadesBancarias;
	private String tipo;
	private Set<Recibos> reciboses = new HashSet<Recibos>(0);

	public TiposPagos() {
	}

	public TiposPagos(TiposPagosId id, EntidadesBancarias entidadesBancarias, String tipo) {
		this.id = id;
		this.entidadesBancarias = entidadesBancarias;
		this.tipo = tipo;
	}

	public TiposPagos(TiposPagosId id, EntidadesBancarias entidadesBancarias, String tipo, Set<Recibos> reciboses) {
		this.id = id;
		this.entidadesBancarias = entidadesBancarias;
		this.tipo = tipo;
		this.reciboses = reciboses;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "codigo", column = @Column(name = "CODIGO", nullable = false, length = 2)),
			@AttributeOverride(name = "enbaCodigo", column = @Column(name = "ENBA_CODIGO", nullable = false, length = 2)) })
	public TiposPagosId getId() {
		return this.id;
	}

	public void setId(TiposPagosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENBA_CODIGO", nullable = false, insertable = false, updatable = false)
	public EntidadesBancarias getEntidadesBancarias() {
		return this.entidadesBancarias;
	}

	public void setEntidadesBancarias(EntidadesBancarias entidadesBancarias) {
		this.entidadesBancarias = entidadesBancarias;
	}

	@Column(name = "TIPO", nullable = false, length = 60)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "REC_TIP_PAGOS", schema = "DIARIOFICIAL",

	joinColumns = { @JoinColumn(name = "TIPA_CODIGO", nullable = false, updatable = false), @JoinColumn(name = "TIPA_ENBA_CODIGO", nullable = false, updatable = false) },

	inverseJoinColumns = { @JoinColumn(name = "RECI_CONSECUTIVO", nullable = false, updatable = false),
			@JoinColumn(name = "RECI_IND_DROF_NUMERO", nullable = false, updatable = false), @JoinColumn(name = "RECI_IND_CONSECUTIVO", nullable = false, updatable = false) }

	)
	public Set<Recibos> getReciboses() {
		return this.reciboses;
	}

	public void setReciboses(Set<Recibos> reciboses) {
		this.reciboses = reciboses;
	}

}
