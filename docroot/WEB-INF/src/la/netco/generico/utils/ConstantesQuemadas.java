package la.netco.generico.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.faces.context.FacesContext;

public class ConstantesQuemadas {

	/**
	 * Navegacion para detalle
	 */
	public static final String NAV_VISORDETALLE = "visordetalle";
	/**
	 * Navegacion para busqueda por rango de fechas
	 */
	public static final String NAV_RANGOFECHAS = "fechadiario";
	/**
	 * Navegacion pra busqueda de ultimo diario
	 */
	public static final String NAV_ULTIMODIARIO = "ultimodiario";
	/**
	 * Navegacion para busqueda por numero de diario
	 */
	public static final String NAV_NUMERODIARIO = "numerodiario";
	/**
	 * Navegacion para busqueda por norma publicada
	 */
	public static final String NAV_NORMADIARIO = "normadiario";
	/**
	 * Nombre de archivo properties
	 */
	public static final String ARCHIVOPROPERTIES = "archivoPropiedades";
	/**
	 * Propiedad que contiene los tipos de normas en el archivo de propiedades
	 */
	public static final String PROPIEDADTINO = "codigostinolista";
	/**
	 * Llave usada para actualizacion del menu.
	 */
	public static final String LLAVEMENU = "llaveMenu";
	/**
	 * Valor usado para establecer el tabulador del menu por defecto en 0
	 */
	public static final String TABPORDEFECTOMENU = "0";
	public static final String TABPORNUMERODIARIO = "1";
	public static final String TABPORFECHASDIARIO = "2";
	public static final String TABPORNORMASDIARIO = "3";
	/**
	 * Valor usado para determinar si un diario oficial es publicoen el portal
	 * frente a los usuarios visitantes
	 */
	public static final String SIPUBLICA = "SI";

	/**
	 * Llave para cargar un objeto diario
	 */
	public static final String OBJDIARIO = "objDiario";
	public static final String NUMDIARIO = "numDiario";

	/**
	 * Tipo de Contenido PDF , exclusivo para la descarga.
	 */
	public static final String TIPOPDF = "application/pdf";

	/**
	 * Nombre del campo name , usado en configuracionbean para obtener la ayuda.
	 */
	public static final String CAMPONAME = "name";

	/**
	 * nombre de la categoria que contiene el texto de ayuda.
	 */
	public static final String PROPIEDADCAMPOAYUDA = "categoriaayuda";
	/**
	 * nombre de la categoria que contiene los textos del pie.
	 */
	public static final String PROPIEDADPIE = "categoriapie";

	// Util Para Propiedades Parametrizables
	public static String getValorPropiedad(String name) {

		Properties properties = new Properties();
		String value = null;
		try {

			String ruta = (String) FacesContext.getCurrentInstance().getExternalContext().getInitParameter(ARCHIVOPROPERTIES);
			properties.load(new FileInputStream(ruta));
			value = properties.getProperty(name);

		} catch (IOException e) {
			throw new InternalError("Unable to load properties : " + e.toString());
		}
		return value;
	}

	public static final String RUTA_ABSOLUTA_TEMPDOWNLOADS = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("rutaCompletaDescargaTemporal");
	public static final String RUTA_PUBLICA_TEMPDOWNLOADS = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("rutaPublicaDescargaTemporal");

	// Mensajes
	public static final String ERRORFALTAFECHA = "POR FAVOR VERIFIQUE LAS FECHAS DE BUSQUEDA.";
	public static final String ERRORSOLOUNAFECHA = "DEBE REALIZAR UN SOLO TIPO DE BUSQUEDA, POR RANGO O POR FECHA DEL DIARIO OFICIAL.";
	public static final String ERRORMENORFECHAFINAL = "LA FECHA FINAL NO PUEDE SER MENOR QUE LA FECHA INICIAL";
	public static final String ERRORMAYORFINALACTUAL = "LA FECHA FINAL NO PUEDE SER MAYOR A LA FECHA ACTUAL.";
	public static final String ERRORMAYORINICIALACTUAL = "LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA ACTUAL.";
	public static final String ERRORTIPOFECHA = "LA FECHA DIGITADA NO TIENE EL FORMATO DE FECHA SOLICITADO PARA LA BUSQUEDA.";
	public static final String INFOFALTACRITERIOFECHA = "DEBE REALIZAR LA BUSQUEDA POR RANGOS DE FECHAS O POR FECHA DEL DIARIO OFICIAL.";
	public static final String INFOSELECCIONECRITERIOS = "DEBE SELECCIONAR ALGUN CRITERIO DE BUSQUEDA.";
	public static final String INFODIGITENUMERO = "DEBE DIGITAR EL NUMERO DEL DIARIO OFICIAL A BUSCAR.";
	public static final String INFOACTUALIZADA = "LOS PARAMETROS DEL BUSCADOR HAN SIDO ACTUALIZADOS.";

}
