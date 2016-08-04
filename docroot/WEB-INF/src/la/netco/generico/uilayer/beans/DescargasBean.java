package la.netco.generico.uilayer.beans;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import la.netco.generico.persistencia.dto.DiariosPdf;
import la.netco.generico.servicios.ServiceDao;
import la.netco.generico.servicios.dao.FileSystemRepositoryService;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class DescargasBean {

	@ManagedProperty(value = "#{serviceDao}")
	private transient ServiceDao daoServicio;

	@ManagedProperty(value = "#{fileSystemRepositoryService}")
	transient private FileSystemRepositoryService repoService;

	private StreamedContent file;
	private DiariosPdf diariodscarga;

	public DescargasBean() {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/images/icon_epub.png");
		file = new DefaultStreamedContent(stream, "image/png",
				"downloaded_optimus.png");
	}

	public StreamedContent getFile() {
		return file;
	}

	public StreamedContent descargar(String idDiario) {
		try {
			DiariosPdf diario = (DiariosPdf) daoServicio.getGenericCommonDao()
					.read(DiariosPdf.class, idDiario);
			InputStream stream = diario.getTexto().getBinaryStream();

			StreamedContent file = new DefaultStreamedContent(stream,
					((StreamedContent) diario).getContentType(),
					diario.getDrofNumero());

			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
