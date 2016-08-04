package la.netco.generico.servicios.impl;

import javax.annotation.Resource;

import la.netco.generico.servicios.dao.FileSystemRepositoryService;
import la.netco.generico.servicios.dao.GenericCommonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fileDao")
public class FileSystemRepositoryServiceImpl implements
		FileSystemRepositoryService {

//	@Value("${archivos.rutaRegistro}")
//	private String rutaRootRepository;

	@Autowired
	@Resource(name = "genericCommonDao")
	private GenericCommonDao genericCommonDao;

//	@Override
//	public void guardarArchivo(UploadedFile upLoadArchivo,
//			ArchivoRegistro archivoRegistro) throws Exception {
//		System.out.println(" rutaRootRepository" + rutaRootRepository);
//		Calendar fechaArchivo = Calendar.getInstance();
//		fechaArchivo.setTime(archivoRegistro.getFechaRegistro());
//		int year = fechaArchivo.get(Calendar.YEAR);
//		int month = (fechaArchivo.get(Calendar.MONTH) + 1);
//		int day = fechaArchivo.get(Calendar.DAY_OF_MONTH);
//
//		File folderRoot = new File(rutaRootRepository + File.separator + year
//				+ File.separator + month + File.separator + day
//				+ File.separator + archivoRegistro.getRegistro().getRegId());
//		if (!folderRoot.exists()) {
//			folderRoot.mkdirs();
//		}
//		
//		File archivo = new File(folderRoot.getAbsolutePath() + File.separator
//				+ archivoRegistro.getName());
//		OutputStream output = new FileOutputStream(archivo);
//		IOUtils.copy(upLoadArchivo.getInputStream(), output);
//		output.close();
//
//		genericDao.create(ArchivoRegistro.class, archivoRegistro);
//
//	}
//
//	@Override
//	public InputStream obtenerInputStream(ArchivoRegistro archivoRegistro)
//			throws Exception {
//		System.out.println(" rutaRootRepository " + rutaRootRepository);
//		Calendar fechaArchivo = Calendar.getInstance();
//		fechaArchivo.setTime(archivoRegistro.getRegistro().getRegFecReal());
//		int year = fechaArchivo.get(Calendar.YEAR);
//		int month = (fechaArchivo.get(Calendar.MONTH) + 1);
//		int day = fechaArchivo.get(Calendar.DAY_OF_MONTH);
//
//		File archivo = new File(rutaRootRepository + File.separator + year
//				+ File.separator + month + File.separator + day
//				+ File.separator + archivoRegistro.getRegistro().getRegId()
//				+ File.separator + archivoRegistro.getName());
//		InputStream inputStream = new FileInputStream(archivo);
//		return inputStream;
//
//	}
//
//	@Override
//	public void  guardarArchivo(ArchivoRegistro archivoRegistro)
//			throws Exception {
//		System.out.println(" rutaRootRepository " + rutaRootRepository);
//		Calendar fechaArchivo = Calendar.getInstance();
//		fechaArchivo.setTime(archivoRegistro.getRegistro().getRegFecReal());
//		int year = fechaArchivo.get(Calendar.YEAR);
//		int month = (fechaArchivo.get(Calendar.MONTH) + 1);
//		int day = fechaArchivo.get(Calendar.DAY_OF_MONTH);
//
//		String rutaArchivoObt = rutaRootRepository + File.separator + year
//				+ File.separator + month + File.separator + day
//				+ File.separator + archivoRegistro.getRegistro().getRegId()
//				+ File.separator + archivoRegistro.getName();
//		
//		File archivoCopiar =  new File(rutaArchivoObt);
//		OutputStream salidaArchivo = new FileOutputStream(archivoCopiar)  ;
//		InputStream entradaArchivo =  new FileInputStream(archivoCopiar);
//		IOUtils.copy(entradaArchivo, salidaArchivo);
//		genericDao.create(ArchivoRegistro.class, archivoRegistro);
//	}
//
//	@Override
//	public void moverCarpeta(Date fechaAct, Date nuevaFecha, Integer idFolder,
//			String tipoCorrespondencia) throws Exception {
//		 String rutaRootRepository = null;
//		 if(tipoCorrespondencia.equals(ConstantsKeysFire.ENTRADA)){
//		 rutaRootRepository = this.rutaRootRepository;
//		 }else if (tipoCorrespondencia.equals(ConstantsKeysFire.SALIDA)){
//		 rutaRootRepository = this.rutaRootSalidasRepository;
//		 }
//		 if(rutaRootRepository != null){
//		 Calendar fechaArchivo = Calendar.getInstance();
//		 fechaArchivo.setTime(fechaAct);
//		 int year = fechaArchivo.get(Calendar.YEAR);
//		 int month = (fechaArchivo.get(Calendar.MONTH)+1);
//		 int day = fechaArchivo.get(Calendar.DAY_OF_MONTH);
//		
//		 File folderRoot = new File(rutaRootRepository + File.separator + year
//		 + File.separator + month + File.separator + day + File.separator +
//		 idFolder);
//		 if(folderRoot.exists()){
//		 Calendar fechaNuevaArchivo = Calendar.getInstance();
//		 fechaNuevaArchivo.setTime(nuevaFecha);
//		 int yearN = fechaNuevaArchivo.get(Calendar.YEAR);
//		 int monthN = (fechaNuevaArchivo.get(Calendar.MONTH)+1);
//		 int dayN = fechaNuevaArchivo.get(Calendar.DAY_OF_MONTH);
//		
//		 File newFolderDay = new File(rutaRootRepository + File.separator +
//		 yearN + File.separator + monthN + File.separator + dayN);
//		 if(!newFolderDay.exists()){
//		 folderRoot.mkdirs();
//		 }
//		
//		 File newFolder = new File(rutaRootRepository + File.separator + yearN
//		 + File.separator + monthN + File.separator + dayN + File.separator +
//		 idFolder);
//
//				Files.move(folderRoot.toPath(), newFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			}
//		}
//
//	}
//
//	@Override
//	public File guardararchivo(String html, ArchivoRegistro archivoRegistro) {
//		try {
//			System.out.println(" rutaRootRepository" + rutaRootRepository);
//			Calendar fechaArchivo = Calendar.getInstance();
//			fechaArchivo.setTime(archivoRegistro.getFechaRegistro());
//			int year = fechaArchivo.get(Calendar.YEAR);
//			int month = (fechaArchivo.get(Calendar.MONTH) + 1);
//			int day = fechaArchivo.get(Calendar.DAY_OF_MONTH);
//			File folderRoot = new File(rutaRootRepository + File.separator
//					+ year + File.separator + month + File.separator + day
//					+ File.separator + archivoRegistro.getRegistro().getRegId());
//			if (!folderRoot.exists()) {
//				folderRoot.mkdirs();
//			}
//
//			File archivo = new File(folderRoot.getAbsolutePath()
//					+ File.separator + Constants.NOMBRE_ARCHIVO);
//			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
//			bw.write(html);
//			bw.close();
//			return archivo;
//		} catch (Exception e) {
//			System.out.println(">>> Exception guardararchivo String");
//			e.printStackTrace();
//			return null;
//		}
//
//	}

}
