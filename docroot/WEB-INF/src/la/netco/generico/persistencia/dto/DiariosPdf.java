package la.netco.generico.persistencia.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import la.netco.generico.utils.Esquemas;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author cpineros
 * 
 */
@Entity
@Table(name = "DIARIOS_PDF", schema = Esquemas.DIARIOFICIAL)
@NamedQueries(@NamedQuery(name = DiariosPdf.NOM_CONSULPORDIARIOFICIAL, query = DiariosPdf.CONSULPORDIARIOFICIAL))
public class DiariosPdf implements Serializable {

	private static final long serialVersionUID = 1L;
	private String drofNumero;
	private DiariosOficiales diariosOficiales;
	private Blob texto;
	private Date fecrea;

	public DiariosPdf() {
	}

	public DiariosPdf(DiariosOficiales diariosOficiales, Blob texto) {
		this.diariosOficiales = diariosOficiales;
		this.texto = texto;
	}

	public DiariosPdf(DiariosOficiales diariosOficiales, Blob texto, Date fecrea) {
		this.diariosOficiales = diariosOficiales;
		this.texto = texto;
		this.fecrea = fecrea;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "diariosOficiales"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DROF_NUMERO", unique = true, nullable = false, length = 12)
	public String getDrofNumero() {
		return this.drofNumero;
	}

	public void setDrofNumero(String drofNumero) {
		this.drofNumero = drofNumero;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public DiariosOficiales getDiariosOficiales() {
		return this.diariosOficiales;
	}

	public void setDiariosOficiales(DiariosOficiales diariosOficiales) {
		this.diariosOficiales = diariosOficiales;
	}

	@Column(name = "TEXTO", nullable = false)
	public Blob getTexto() {
		return this.texto;
	}

	public void setTexto(Blob texto) {
		this.texto = texto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECREA", length = 7)
	public Date getFecrea() {
		return this.fecrea;
	}

	public void setFecrea(Date fecrea) {
		this.fecrea = fecrea;
	}

	public static final String NOM_CONSULPORDIARIOFICIAL = "consulPorDiarioficial";
	public static final String CONSULPORDIARIOFICIAL = "FROM DiariosPdf dpdf WHERE  dpdf.diariosOficiales.numero = ?";

}
