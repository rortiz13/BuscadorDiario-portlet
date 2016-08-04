package la.netco.generico.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.netco.generico.persistencia.dto.DiariosPdf;
import la.netco.generico.servicios.ServiceDao;
import la.netco.generico.servicios.impl.SpringApplicationContext;

import org.apache.commons.io.IOUtils;

public class ServletDescarga extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletDescarga() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext context = getServletContext();
			String rutaAbsoluta = context.getInitParameter("rutaCompletaDescargaTemporal");
			String rutaPublica = context.getInitParameter("rutaPublicaDescargaTemporal");
			String numeroObjeto = request.getParameter("numeroObjeto");
			ServiceDao service = (ServiceDao) SpringApplicationContext.getBean("serviceDao");

			HashMap<Integer, String> paramsBusqueda = new HashMap<Integer, String>();
			paramsBusqueda.put(0, numeroObjeto);

			List<DiariosPdf> listaDiarioPDF = (List<DiariosPdf>) service.getGenericCommonDao().executeFind(DiariosPdf.class, paramsBusqueda, DiariosPdf.NOM_CONSULPORDIARIOFICIAL);
			DiariosPdf diarioPdfCargado = listaDiarioPDF.get(0);

			InputStream streamCargado = diarioPdfCargado.getTexto().getBinaryStream();

			File fileCarga = new File(rutaAbsoluta);

			if (!fileCarga.exists()) {
				fileCarga.mkdirs();
			}

			String auxiNum = diarioPdfCargado.getDrofNumero().replace('.', 'D');
			long tiempoAuxi = System.currentTimeMillis();
			String nombreGenerado = auxiNum + tiempoAuxi + ".pdf";

			String rutaFile = fileCarga.getPath() + File.separator + nombreGenerado;
			fileCarga = new File(rutaFile);
			if (!fileCarga.exists()) {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				OutputStream salidaFile = new DigestOutputStream(new FileOutputStream(rutaFile), digest);
				try {
					IOUtils.copyLarge(streamCargado, salidaFile);
				} finally {
					salidaFile.close();
				}
			}

			String rutaPort = getProtocolHttp(request) + request.getServerName();

			System.out.println("###Documento Encontrado");
			
			response.sendRedirect(rutaPort + rutaPublica  + nombreGenerado);

		} catch (IndexOutOfBoundsException eindex) {
			System.out.println("Documento No Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getProtocolHttp(HttpServletRequest request) {
		String protocol = "http://";
		if (request.isSecure())
			protocol = "https://";
		return protocol;
	}

}