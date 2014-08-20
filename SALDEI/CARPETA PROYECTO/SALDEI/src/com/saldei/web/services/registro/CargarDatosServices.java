/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.registro;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.saldei.hibernate.tables.Carrera;
import com.saldei.hibernate.tables.Perfil;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Auditoria;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.web.bean.registro.CargarDatosDto;
import com.saldei.web.form.registro.CargarDatosForm;

public class CargarDatosServices {
	
	private HibDAO dao = new HibDAOImpl();
	private Auditoria auditoria = new Auditoria();

	/**
	 * Metodo utilizado para mostrar los ciclos
	 * activos dentro de la JSP
	 * @return Lista de Ciclos Activos en el Sistema
	 */
	public List getCiclo(){
		String hql = "from Ciclo where estCiclo = 'A' order by idCiclo";
		List list = dao.find(hql);
		return list;
	}
	
	public ArrayList getCarreras(){
		String hql = "from Carrera where estCarrera = 'A' order by nomCarrera";
		List list = dao.find(hql);
		ArrayList<Carrera> lst = new ArrayList<Carrera>();
		for(int i=0; i<list.size(); i++){
			Carrera carrera = (Carrera) list.get(i);
			Carrera c = carrera;
			c.getId().setIdCarrera(carrera.getId().getIdCarrera() + "-" + carrera.getId().getPlanEstudio());
			c.setNomCarrera(carrera.getNomCarrera() + " - " + carrera.getId().getPlanEstudio());
			lst.add(c);
		}
		return lst;
	}
	
	/**	 
	 * Verifica si se ha ingresado la ruta de un archivo en la JSP
	 */
	public boolean isNullForm(CargarDatosForm cdf){
		if(cdf.getArchivo().getFileName().equals(""))
			return true;
		else
			return false;
	}
	
	public void validarCicloCarrera(){
		
	}
	
	/**	 
	 * Recupera toda la información del archivo de Excel y los almacena en Listas
	 * devolviendo la lista si recupera toda la información o NULL si ocurre un error
	 */
	public ArrayList recuperarInfoArchivo(CargarDatosForm cdf){
		InputStream inputStream = null;
		Workbook workbook = null;
		ArrayList<CargarDatosDto> list = new ArrayList<CargarDatosDto>();
		try {
			inputStream = cdf.getArchivo().getInputStream();
			workbook = Workbook.getWorkbook(inputStream);			
			Sheet sheet =  workbook.getSheet(0);
			CargarDatosDto dto = null;
			String ciclo = "", carrera = "", plan = "";
			
			Cell cellValid = null;
			cellValid = sheet.getCell("B2");			
			ciclo = cellValid.getContents();
			if(ciclo.equals(""))
				return null;
			if(ciclo.length() == 5){
				ciclo = "0" + ciclo.substring(4) + ciclo.substring(0, 4);
			}else
				ciclo = ciclo.substring(4, 6) + ciclo.substring(0, 4);
			if(!ciclo.equals(cdf.getCiclo()))
				return null;
			
			String[] datos = cdf.getCarrera().split("-");			
			cellValid = sheet.getCell("D2");
			plan = cellValid.getContents().substring(0,4);
			if(plan.equals(""))
				return null;
			if(!plan.equals(datos[1]))
				return null;
			
			for(int i=0; i<1000; i++){
				
				cellValid = sheet.getCell("C" + String.valueOf(i + 2));
				carrera = cellValid.getContents();
				if(carrera.equals(""))
					break;							
				if(!carrera.equals(datos[0])){}
				else{					
					dto = new CargarDatosDto();
					Cell cell = null;				
					cell = sheet.getCell("E" + String.valueOf(i + 2));
					if(cell.getContents().equals(""))
						break;
					dto.setCodMateria(cell.getContents());				
					
					cell = sheet.getCell("F" + String.valueOf(i + 2));
					dto.setNombreMateria(cell.getContents());
					
					cell = sheet.getCell("G" + String.valueOf(i + 2));
					dto.setUnidadesValorativas(cell.getContents());
					
					cell = sheet.getCell("H" + String.valueOf(i + 2));
					dto.setSeccionMateria(cell.getContents());
					
					cell = sheet.getCell("I" + String.valueOf(i + 2));
					dto.setCarnetEstudiante(cell.getContents());
					
					cell = sheet.getCell("J" + String.valueOf(i + 2));
					dto.setPrimerApellido(cell.getContents());
					
					cell = sheet.getCell("K" + String.valueOf(i + 2));
					dto.setPrimerNombre(cell.getContents());					
					
					cell = sheet.getCell("L" + String.valueOf(i + 2));
					dto.setEmail(cell.getContents());		
									
					dto.setCorrelativo(String.valueOf(i + 1));
					
					list.add(dto);
				}
			}
			workbook.close();
			inputStream.close();
			return list;
		}catch(Exception ex){			
			workbook.close();
			try{
				inputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}			
			return list;
		}
	}
	
	/**
	 * Validación de los registros de Excel separando los registros que no poseen error
	 * con los que si en listas diferentes
	 */
	public ArrayList[] validarInfoExcel(ArrayList list){
		ArrayList<CargarDatosDto> listBuenos = new ArrayList<CargarDatosDto>();
		ArrayList<CargarDatosDto> listMalos = new ArrayList<CargarDatosDto>();
		ArrayList[] listas = new ArrayList[2];		
		try{								
			for(Iterator iter = list.iterator(); iter.hasNext(); ){
				boolean flag = true;
				CargarDatosDto dto = (CargarDatosDto) iter.next();				
					if(!Util.esNumero(dto.getUnidadesValorativas())){
								flag = false;
								dto.setError(ErroresCargaDatos.ERROR_NUM_UV);
							}else
								if(!Util.esNumero(dto.getCarnetEstudiante())){
									flag = false;
									dto.setError(ErroresCargaDatos.ERROR_NUM_CARNET);;
								}else
									if(dto.getCarnetEstudiante().length() != 8){
										dto.setError(ErroresCargaDatos.ERROR_CARNET_SIZE);
										flag = false;
									}else
												if(dto.getCodMateria().length() > 10){
													dto.setError(ErroresCargaDatos.ERROR_MATERIA_SIZE);
													flag = false;
												}						
													else{
																if(!this.validarCorreo(dto.getEmail())){
																	dto.setError(ErroresCargaDatos.ERROR_CORREO);
																	flag = false;
																}else{
																		if(!this.validarSeccion(dto.getSeccionMateria())){
																			dto.setError(ErroresCargaDatos.ERROR_SECCION_MATERIA);
																			flag = false;
																		}else
																			flag = true;
																	}
																
														}
				if(flag)
					listBuenos.add(dto);
				else
					listMalos.add(dto);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		listas[0] = listBuenos;
		listas[1] = listMalos;
		return listas;
	}
	
	/**
	 * Valida si la seccion es valida 
	 */
	private boolean validarSeccion(String seccion){
		if(!Util.esNumero(seccion))
			return false;
		int numSeccion = new Integer(seccion);
		if(numSeccion == 1 || numSeccion == 2 || numSeccion == 3 || numSeccion == 4)
			return true;
		return false;
	}
	

	/**
	 * Valida si las fechas que se digitarón en el archivo de Excel estan correctas 
	 */
//	private boolean validarFechas(String fecha){
//		try{
//			String[] formato = fecha.split("/");
//			if(formato.length != 3)
//				return false;
//			if(new Integer(formato[0]) > 31 || new Integer(formato[0]) < 1)
//				return false;
//			if(new Integer(formato[1]) > 12 || new Integer(formato[1]) < 1)
//				return false;
//			if(new Integer(formato[2]) > 2100 || new Integer(formato[2]) < 0)
//				return false;
//			return true;
//		}catch(Exception ex){
//			return false;
//		}
//	}
	
	/**
	 * Valida si el correo que se digito en el archivo de Excel este correcto 
	 */
	private boolean validarCorreo(String correo){
		try{
			String[] formato = correo.split("@");
			if(formato.length != 2)
				return false;
			int posicion = formato[1].indexOf(".");
			if(posicion ==  0 || posicion == -1)
				return false;
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
//	/**
//	 * Valida si el sexo que se digito en el archivo de Excel este correcto 
//	 */
//	private boolean validarSexo(String sexo){
//		if(sexo.toLowerCase().equals("m") || sexo.toLowerCase().equals("masculino") ||
//				sexo.toLowerCase().equals("f") || sexo.toLowerCase().equals("femenino"))
//			return true;
//		return false;
//	}
	
	/**
	 * Guarda los registros de Excen en la BD
	 */
	public boolean cargarDatos(ArrayList list, String ciclo, String carrera, Usuario usuarioAudit){
		CargaDatosUtilities utilCarga = new CargaDatosUtilities();
		try{
			int idPerfilEstudiante = this.getPerfilEstudiante();			
			for(Iterator iter = list.iterator(); iter.hasNext();){
				CargarDatosDto dto = (CargarDatosDto) iter.next();
				utilCarga.guardarUsuario(dto, idPerfilEstudiante);
				utilCarga.guardarEstudiante(dto);
				utilCarga.guardarEstudianteXCarrera(dto, carrera);
				utilCarga.guardarMateria(dto);
				utilCarga.guardarMateriaCiclo(dto, ciclo);
				utilCarga.guardarMateriaInscrita(dto, ciclo);
			}
			utilCarga.disconnect();
			String[] datos = carrera.split("-");
			auditoria.register(usuarioAudit.getIdUsuario(), ciclo, "cargadatos", datos[0]);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			utilCarga.disconnect();
			return false;
		}
	}
	
	public String getNombreCarrera(String id){
		String nombre = "";
		try{
			String hql = "from Carrera where id.idCarrera = ?";
			Object[] params = {id};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0){
				Carrera c = (Carrera) list.get(0);
				nombre = c.getNomCarrera();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nombre;
	}
	
	public int getPerfilEstudiante(){
		try{
			String hql = "from Perfil where lower(nomPerfil) = 'estudiante' ";
			List list = dao.find(hql);
			int id = 0;
			if(list.size() != 0){
				Perfil perfil = (Perfil) list.get(0);
				id = perfil.getIdPerfil();
			}
			return id;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}		
	}
	
	public ArrayList mostrarHistorial(){
		ArrayList<com.saldei.hibernate.tables.Auditoria> lista = new ArrayList<com.saldei.hibernate.tables.Auditoria>();
		try{
			String hql = "from Auditoria where accion = 'cargadatos' order by fechaAccion";			
			List list = dao.find(hql);
			for(int i=0; i<list.size(); i++){
				com.saldei.hibernate.tables.Auditoria a = (com.saldei.hibernate.tables.Auditoria) list.get(i);
				a.setLlavePrimaria(this.getNombreCarrera(a.getLlavePrimaria()));				
				lista.add(a);
			}
			return lista;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
///////FUNCIONES DE INSERT
//	public boolean guardarUsuario(CargarDatosDto dto){
//		try{
//			String hql = "from Usuario where lower(idUsuario) = ?";
//			String[] params = {dto.getCarnetEstudiante().toLowerCase()};
//			List list = dao.findByProps(hql, params);
//			if(list.size() == 0){
//				Usuario usuario = new Usuario();
//				String pwd = usuarioServices.generarContrasena();
//				usuario.setIdUsuario(dto.getCarnetEstudiante());
//				usuario.setPswUsuario(pwd);
//				usuario.setPrimerNom(dto.getPrimerNombre());
//				usuario.setPrimerApe(dto.getPrimerApellido());
//				usuario.setSexo("M");
//				usuario.setEstadoCivil(1);
//				usuario.setEmail(dto.getEmail());
//				usuario.setFechaNac(util.getFechaServidor());
//				usuario.setEstUsuario("A");
//				dao.save(usuario);
//				return true;
//			}			
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	/*public boolean guardarCarrera(CargarDatosDto dto){
//		try{
//			String hql = "from Carrera where lower(id.idCarrera) = ?";
//			String[] params = {dto.getCodCarrera().toLowerCase()};
//			List list = dao.findByProps(hql, params);
//			if(list.size() == 0){
//				String sql = "insert into registro.carrera(id_carrera, nom_carrera, id_facultad, est_carrera) values(?, ?, ?, ?)";
//				Object[] paramsX = {dto.getCodCarrera(),dto.getNombreCarrera(), new Integer("6"), "A"};
//				jdbc.saveOrUpdate(sql, paramsX);
//				return true;
//			}
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}*/
//	
//	public boolean guardarEstudiante(CargarDatosDto dto){
//		try{
//			String hql = "from Estudiante where carnetEstudiante = ?";
//			String[] params = {dto.getCarnetEstudiante()};
//			List list = dao.findByProps(hql, params);
//			if(list.size() == 0){
//				Estudiante estudiante = new Estudiante();
//				estudiante.setCarnetEstudiante(dto.getCarnetEstudiante());
//				estudiante.setOyente("N");				
//				dao.save(estudiante);
//				return true;
//			}			
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public boolean guardarEstudianteXCarrera(CargarDatosDto dto, String carrera){
//		try{
//			String[] datos = carrera.split("-");
//			String hql = "from EstudianteCarrera where id.estudiante.carnetEstudiante = ? and id.carrera.id.idCarrera = ? and id.carrera.id.planEstudio = ?";
//			String[] params = {dto.getCarnetEstudiante(), datos[0], datos[1]};
//			List list = dao.findByProps(hql, params);
//			if(list.size() == 0){
//				String sql = "insert into registro.estudiante_carrera(carnet_estudiante, id_carrera, plan_estudio, fecha_ini, est_est_car) values" +
//						"('" + dto.getCarnetEstudiante() + "', '" + datos[0] + "', '" + datos[1]+ "', current_date, 'A')";
//				//Object[] paramsx = {dto.getCarnetEstudiante(), dto.getCodCarrera(), datos[1], util.getFechaServidor(), "A"};
//				jdbc.saveOrUpdate(sql, null);
//				return true;
//			}
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public boolean guardarMateria(CargarDatosDto dto){
//		try{
//			String hql = "from Materia where codMateria = ?";
//			String[] params = {dto.getCodMateria()};
//			List list = dao.findByProps(hql, params);
//			if(list.size() == 0){
//				Materia materia = new Materia();
//				List lst = dao.getAll(Materia.class);
//				int idMateria = lst.size() + 1;
//				materia.setIdMateria(idMateria);
//				materia.setCodMateria(dto.getCodMateria());
//				materia.setNomMateria(dto.getNombreMateria());
//				materia.setEstMateria("A");
//				materia.setUniValorativas(new Integer(dto.getUnidadesValorativas()));
//				dao.save(materia);
//				return true;
//			}
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public boolean guardarMateriaCiclo(CargarDatosDto dto, String ciclo){
//		try{
//			String hqlMateria = "from Materia where codMateria = ?";
//			String[] paramsMateria = {dto.getCodMateria()};
//			List listMateria = dao.findByProps(hqlMateria, paramsMateria);
//			if(listMateria .size() != 0){
//				Materia materia = (Materia) listMateria.get(0);								
//				String hql = "from MateriaCiclo where id.ciclo.idCiclo = ? and id.materia.idMateria = ? and id.idSeccion = ?";
//				Object[] params = {ciclo, new Integer(materia.getIdMateria()), 
//											new Integer(dto.getSeccionMateria())};
//				List list = dao.findByProps(hql, params);
//				if(list.size() == 0){
//					String sql = "insert into registro.materia_ciclo(id_ciclo, id_materia, id_seccion, est_mat_ciclo) values(?,?,?,?)";
//					Object[] paramsX = {ciclo, new Integer(materia.getIdMateria()), 
//							                       new Integer(dto.getSeccionMateria()), "A"};
//					jdbc.saveOrUpdate(sql, paramsX);
//					return true;
//				}
//				return false;
//			}			
//			return false;
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
//
//	public boolean guardarMateriaInscrita(CargarDatosDto dto, String ciclo){
//		try{
//			String hqlMateria = "from Materia where codMateria = ?";
//			String[] paramsMateria = {dto.getCodMateria()};
//			List listMateria = dao.findByProps(hqlMateria, paramsMateria);			
//			if(listMateria .size() != 0){				
//				Materia materia = (Materia) listMateria.get(0);
//				String query = "select * from registro.materia_inscrita where carnet_estudiante = ? and id_materia = ? and id_seccion = ? and id_ciclo = ?";				
//				Object[] params = {dto.getCarnetEstudiante(), new Integer(materia.getIdMateria()), new Integer(dto.getSeccionMateria()), ciclo};
//				List list = jdbc.getQuery(query, params);
//				if(list.size() == 0){
//					String sql = "insert into registro.materia_inscrita(carnet_estudiante, id_materia, id_seccion, id_ciclo," +
//									   "retirada, est_mat_inscrita) values(?, ?, ?, ?, ?, ?)";
//					Object[] paramsX = {dto.getCarnetEstudiante(), materia.getIdMateria(), new Integer(dto.getSeccionMateria()),
//							ciclo, "N","A"};
//					jdbc.saveOrUpdate(sql, paramsX);
//					return true;
//				}
//				return false;
//			}
//			return false;			
//		}catch(Exception ex){
//			ex.printStackTrace();
//			return false;
//		}
//	}
}//class
