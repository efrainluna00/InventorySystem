package com.saldei.web.services.seguridad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.commons.beanutils.DynaBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.hibernate.tables.Carrera;
import com.saldei.hibernate.tables.CarreraId;
import com.saldei.hibernate.tables.Estudiante;
import com.saldei.hibernate.tables.EstudianteCarrera;
import com.saldei.hibernate.tables.EstudianteCarreraId;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.hibernate.tables.UsuarioDei;
import com.saldei.hibernate.tables.UsuarioExterno;
import com.saldei.hibernate.tables.UsuarioVigencia;
import com.saldei.hibernate.tables.UsuarioVigenciaId;
import com.saldei.util.commons.Constants;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.util.mail.ConfigEmail;
import com.saldei.web.bean.registro.EstudianteCarreraDto;
import com.saldei.web.bean.seguridad.UsuarioDto;
import com.saldei.web.form.seguridad.ModificarDatosForm;
import com.saldei.web.form.seguridad.UsuarioForm;

/**
 * Servicios de Usuario  Maneja todo lo relacionado a Usuario (Estudiante, Externo, DEI)
 * @author WiRoCaRo
 * @version 2.0
 */
public class UsuarioServices {

	private HibDAO dao = new HibDAOImpl();
	private Util util = new Util();
	private JdbcHelper jdbc = new JdbcHelper();
	private ConfigEmail email = new ConfigEmail();

	/**
	 * Valida que un formulario sea nulo 
	 * @param form Formulario de la pantalla de Mantenimiento de Usuario
	 * @return
	 */
	public boolean isNullUsuarioForm(UsuarioForm form)throws Exception{
		if(form.getEmail().equals("")     || form.getPrimerNom().equals("") || 
		   form.getPrimerApe().equals("") || form.getTipo().equals("")      || 
		   form.getTipo().equals("Seleccione") ) 
			return true;
		return false;
	}
	
	/**
	 * Verifica si un Usuario existe en la Tabla Usuario
	 * @param idUsuario  Identificador de usuario
	 * @return True | False
	 * @throws Exception 
	 */
	public boolean isUser(String p_idUsuario) throws Exception{
		String hql = QuerysSeguridad.getUser(p_idUsuario);
		List list  = dao.find(hql);
		if(list != null && list.size() > 0)
			return true;
		else
			return false;
	}	
	
	public List getAnyos() throws Exception{
		List<ElementDto> list = new LinkedList<ElementDto>();
		int anyo =  Integer.parseInt(util.getAnyoServidor());
		for (int i=10; i>0; i--){
			int anyoAux = anyo - i;
			ElementDto e = new ElementDto();
			e.setElement1(String.valueOf(anyoAux));
			list.add(e);
		}
		ElementDto e = new ElementDto();
		e.setElement1(String.valueOf(anyo));
		list.add(e);
		return list;		
	}
	
	/**
	 * Genera una Contraseña para ingresarsela al password
	 * @return Contraseña
	 */
	public String generarContrasena()throws Exception{
		String pwd = "";
		for(int i=0; i<5; i++){
			double r = Math.random();
			r = r * 100;
			String entero = String.valueOf(r);
			pwd += String.valueOf(entero.substring(0, entero.indexOf(".")));
		}
		return pwd;
	}
	
	/**
	 * Actualiza un Usuario de la tabla Usuario
	 * @param user Objeto de Usuario 
	 * @return True | False
	 */
	public boolean update(Usuario user)throws Exception{
		if (validarUsuario(user)){
			dao.update(user);	
			 return true;
		}
		return false;		
	}
	
	/**
	 * Obtiene los Usuarios Activos en el Sistema 
	 * @return Lista con objetos tipo Usuario
	 */
	public List getUsers(){
		try {
			String hql = QuerysSeguridad.getUserActivos();
			List lst = dao.find(hql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	/** 
	 * Valida que un Objeto Usuario no tenga los campos Obligatorios como nulos o vacios 
	 * @param usr Usuario
	 * @return True | False
	 */
	public static boolean validarUsuario(Usuario usr){
		try{
			if (usr.getIdUsuario() == null || usr.getIdUsuario().trim().equals(""))
				return false;
			if (usr.getPswUsuario() == null || usr.getPswUsuario().trim().equals(""))
				return false;
			if (usr.getPrimerNom() == null || usr.getPrimerNom().trim().equals(""))
				return false;
			if (usr.getPrimerApe() == null || usr.getPrimerApe().trim().equals(""))
				return false; 
			if (usr.getEmail() == null || usr.getEmail().trim().equals(""))
				return false;
			if (usr.getEstUsuario() == null || usr.getEstUsuario().trim().equals(""))
				return false;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Setea el IdUsuario al Dto segun el Tipo de Usuario que ha seleccionado. Si es Estudiante el Id es el carnet, 
	 * si es Empleado DEI el Id es el Carnet y si es Externo se genera 
	 * @param usrDto Objeto DTO de Usuario   
	 * @return Objeto DTO de Usuario
	 * @throws Exception
	 */
	public UsuarioDto setIdUsuarioTipo(UsuarioDto usrDto)throws Exception {
		if (usrDto != null){
			if (usrDto.getTipo().equals(Constants.Usuario_ESTUDIANTE))
				usrDto.setIdUsuario(usrDto.getCarnetEstudiante());
			else if (usrDto.getTipo().equals(Constants.Usuario_DEI)){
				usrDto.setIdUsuario(usrDto.getCodigoEmpleado());
			}else if (usrDto.getTipo().equals(Constants.Usuario_EXTERNO)){
				usrDto.setIdUsuario(this.createIdUsuario(usrDto));
			}
		}
		return usrDto;
	}
	
	/**
	 * Crea un Usuario
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String createIdUsuario(UsuarioDto dto)throws Exception{
		String idUsuario = "";
		if (dto != null && dto.getPrimerNom()!= null && dto.getPrimerApe() != null && dto.getPrimerNom().length() > 2 && dto.getPrimerApe().length()> 2 ){
			int userExist=0;
			idUsuario=dto.getPrimerNom().substring(0,2).toUpperCase()+ dto.getPrimerApe().substring(0,2).toUpperCase();
			if(this.isUser(idUsuario)){
				idUsuario+=String.valueOf(userExist);
				idUsuario+="EX";
			}
			else idUsuario+="EX";				
		}
		return idUsuario;
	}
		
	/**
	 * Obtiene los Usuarios pertenecientes al DEI
	 * @return lista con los Usuarios
	 */
	public List getUsersDEI()throws Exception {
		List lstDei = new LinkedList();
			String hql = QuerysSeguridad.getUserDei("A");
			lstDei     = dao.find(hql);
		return lstDei;		
	}

	/**
	 * Ingresa un Usuario en la Tabla de Usuario
	 * @param usrDto contenedor de Usuario
	 * @return True | False 
	 */
	public boolean saveUsuario(UsuarioDto usrDto, Session session)throws Exception{
		if (usrDto.getNit()	 == null) usrDto.setNit("");
		if (usrDto.getDui()	 == null) usrDto.setDui("");
		if (usrDto.getIsss() == null) usrDto.setIsss("");
		if (usrDto.getNup()	 == null) usrDto.setNup("");
		Usuario user = new Usuario();
		user.setIdUsuario(usrDto.getIdUsuario());
		user.setPswUsuario(usrDto.getPswUsuario());
		user.setPrimerNom(usrDto.getPrimerNom());
		user.setPrimerApe(usrDto.getPrimerApe());
		user.setNombreRestante(usrDto.getNombreRestante());
		user.setApellidoRestante(usrDto.getApellidoRestante());
		if(usrDto.getFechaNac()!= null && !usrDto.getFechaNac().equals(""))
			user.setFechaNac(util.fechaFormatoPostgres(usrDto.getFechaNac()));
		user.setSexo(usrDto.getSexo());
		user.setEstadoCivil(Integer.parseInt(usrDto.getEstadoCivil()));
		user.setDireccionPart(usrDto.getDireccionPart());
		user.setEmail(usrDto.getEmail());
		user.setTelefonoCasa(usrDto.getTelefonoCasa());
		user.setTelefonoCel(usrDto.getTelefonoCel());
		user.setTelefonoTrabajo(usrDto.getTelefonoTrabajo());
		user.setEstUsuario(usrDto.getEstUsuario());			
		user.setFechaSolicitud(util.getFechaServidor());
		user.setDui(usrDto.getDui());
		user.setNit(usrDto.getNit());
		user.setIsss(usrDto.getIsss());
		user.setNup(usrDto.getNup());
		user.setAutorizadoPor(usrDto.getAutorizadoPor());
		dao.save(user, session);
		return  true;	
	}
	
	/**
	 * Registra un Usuario en la Tabla Usuario_DEI
	 * @param usrDto
	 * @return
	 */
	public boolean saveUserDei(UsuarioDto usrDto, Session session)throws Exception {		
		if(usrDto.getExt() == null) usrDto.setExt("");
		/**Ingresa los datos del Dto al Objeto Hibernate  */
		UsuarioDei udei = new UsuarioDei();		
		Usuario    usr  = new Usuario();
		usr.setIdUsuario(usrDto.getIdUsuario());
		udei.setCodigoEmpleado(usrDto.getCodigoEmpleado());
		udei.setExtensionUca(usrDto.getExt());
		udei.setUsuario(usr);
		dao.save(udei,session);					
		return true;	
	}
	
	/**
	 * Registra un Usuario en la Tabla Usuario_Externo
	 * @param usrDto
	 * @return
	 */
	public boolean saveUserExt(UsuarioDto usrDto, Session session)throws Exception {
            if (usrDto.getComentario() != null && !usrDto.getComentario().equals("") && usrDto.getComentario().length() > 200){
            	usrDto.setComentario(usrDto.getComentario().substring(0, 199));
            }
			if(usrDto.getComentario() 	 == null) usrDto.setComentario("");
			if(usrDto.getSolicitadoPor() == null) usrDto.setSolicitadoPor("");
			UsuarioExterno uext = new UsuarioExterno();
			uext.setIdUsuario(usrDto.getIdUsuario());
			uext.setComentario(usrDto.getComentario());
			uext.setSolicitadoPor(usrDto.getSolicitadoPor());
			dao.save(uext, session);
			return true;
	}
	
	/**
	 * Registra una vigencia para un Usuario
	 * @param usrDto Objeto de Usuario
	 * @param session Session conectada a la Base
	 * @return
	 * @throws Exception
	 */
	public boolean saveUserVigencia(UsuarioDto usrDto, Session session) throws Exception{
		UsuarioVigencia userVig = new UsuarioVigencia();
		UsuarioVigenciaId  id   = new UsuarioVigenciaId();
		Usuario            user = new Usuario();
		user.setIdUsuario(usrDto.getIdUsuario());
		id.setFechaIni(util.fechaFormatoPostgres(usrDto.getIniVigencia()));
		id.setUsuario(user);
		userVig.setFechaFin(util.fechaFormatoPostgres(usrDto.getFinVigencia()));
		userVig.setId(id);
		dao.save(userVig, session);
		return true;
	}
	
	/**
	 * Guarda un Usuario de Tipo Estudiante en la Tabla de Estudiante
	 * @param usrDto   Objeto Bean de Usuario
	 * @param session  Session para manejar la conectividad con la Base
	 * @return  True | False 
	 * @throws Exception
	 */
	public boolean saveEstudiante(UsuarioDto usrDto, Session session)throws Exception {
		if (usrDto.getOyente()		== null) usrDto.setOyente("N");
			Estudiante est = new Estudiante();
			est.setCarnetEstudiante(usrDto.getIdUsuario());
			est.setOyente(usrDto.getOyente());
		if (usrDto.getCicloIngreso()!= null || usrDto.getAnyoIngreso() != null ){
			est.setCicloIngreso(usrDto.getCicloIngreso());
			est.setAnyoIngreso(usrDto.getAnyoIngreso());
		}
		else{
			est.setCicloIngreso("");
			est.setAnyoIngreso("");
		}		
		dao.save(est, session);
		return true;
	}
		
	public boolean saveEstudianteCarrera(UsuarioDto usrDto, Session session)throws Exception {
		if (usrDto.getIdCarrera() != null && !usrDto.getIdCarrera().equals("")){
			String[] carreraPlan =  usrDto.getIdCarrera().toString().split("-");
			if (carreraPlan.length > 1 ) {
				String idCarrera = carreraPlan[0].toString().trim();
				String plan 	 = carreraPlan[1].toString().trim();
				EstudianteCarrera estCar = new EstudianteCarrera();
				EstudianteCarreraId estCarId = new EstudianteCarreraId();
				Estudiante est = new Estudiante(); 
				Carrera  car = new Carrera();
				CarreraId id = new CarreraId();
				id.setIdCarrera(idCarrera);
				id.setPlanEstudio(plan);
				car.setId(id);
				est.setCarnetEstudiante(usrDto.getIdUsuario());
				estCarId.setCarrera(car);
				estCarId.setEstudiante(est);
				estCarId.setFechaIni(util.getFechaServidor());
				estCar.setId(estCarId);
				estCar.setEstEstCar("A");		
				dao.save(estCar, session);
				return true;
			}
			else
				return false;
		}
		else			
			return false;		
	}
	
	/**
	 * Proceso para Guardar Un usuario a la Base de Datos este guarda en la Tabla de Usuario y en la del Tabla segun el tipo 
	 * @param dto Objeto bean de Usuario
	 * @return True | False
	 * @throws Exception
	 */
	public boolean saveProcess(UsuarioDto dto) throws Exception{
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		boolean retorna = false;
		String tipoUsuario = "";
		try {
			if (dto!= null){
				this.saveUsuario(dto, session);
				this.saveUserVigencia(dto, session);
				if(dto.getTipo().equals("D")){
					this.saveUserDei(dto,session);
					tipoUsuario = "Usuario DEI";
				}
				else if(dto.getTipo().equals("E")){
					this.saveUserExt(dto, session);
					tipoUsuario = "Externo";
				}
				else if(dto.getTipo().equals("S")){
					tipoUsuario = "Estudiante";
					this.saveEstudiante(dto, session);
					this.saveEstudianteCarrera(dto, session);
				}
				tx.commit();
				retorna = true;
				this.mandarCorreoUsuario(dto.getEmail(), dto.getIdUsuario(), dto.getPswUsuario(), tipoUsuario);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			new Exception();			
		}
		finally{
			try {
				session.close(); 
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		return retorna;
	}
	
	private void mandarCorreoUsuario(String correo, String usuario,String pwd, String tipoUsuario){
		try{			
			String bodyString = "Ha sido ingresado en SALDEI. Su usuario es: " + usuario + ", su contraseña es: " + pwd + " y su tipo de Usuario es: " + tipoUsuario;
			String subject = "Ingreso al Sistema SALDEI";
			email.notificarEmail(correo, bodyString, subject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Proceso para actualizar un usuario, tambien guarda el Tipo de Usuario en su respectiva tabla 
	 * @param dto   Objeto bean Usuario  
	 * @param user  Usuario que Actualiza
	 * @return  True | False    
	 * @throws Exception 
	 */
	public boolean updateProcess(UsuarioDto dto, String user) throws Exception{
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		boolean retorna = false;
		try {
			if (dto!= null){
				this.updateUser(dto, session);
				this.updateUserVigencia(dto, session);
				if (dto.getTipo() != null && !dto.getTipo().equals("")){
					if(dto.getTipo().equals("D"))
						this.updateUserDei(dto,session);
					else if(dto.getTipo().equals("E"))
						this.updateUserExt(user, dto, session);	
					else if(dto.getTipo().equals("S"))
						this.updateEstudiante(dto, session);
				}	
				tx.commit();
				retorna = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			new Exception();			
		}
		finally{
			try {
				session.close(); 
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		return retorna;
	}

	/**
	 * Actualiza los datos de un Usuario
	 * @param usrDto   Objeto bean de Usuario
	 * @param session  Session para insertar en la Base de Datos, con esta se maneja la transaccionalidad
	 * @return True | False
	 * @throws Exception
	 */
	public boolean updateUser(UsuarioDto usrDto, Session session)throws Exception {
		String hql = QuerysSeguridad.getUser(usrDto.getIdUsuario());
		List list = dao.find(hql, session);
		if (list != null && list.size() >0) {
			Usuario user = (Usuario) list.get(0);
			//user.setIdUsuario(usrDto.getIdUsuario());
			if (usrDto.getPrimerNom() != null && !usrDto.getPrimerNom().equals(""))
				user.setPrimerNom(usrDto.getPrimerNom());
			if (usrDto.getPrimerApe()!= null && !usrDto.getPrimerApe().equals(""))
				user.setPrimerApe(usrDto.getPrimerApe());
			user.setNombreRestante(usrDto.getNombreRestante());
			user.setApellidoRestante(usrDto.getApellidoRestante());
			user.setDireccionPart(usrDto.getDireccionPart());
			if (usrDto.getEmail() !=null && !usrDto.getEmail().equals(""))
				user.setEmail(usrDto.getEmail());
			user.setTelefonoCasa(usrDto.getTelefonoCasa());
			user.setTelefonoCel(usrDto.getTelefonoCel());
			user.setTelefonoTrabajo(usrDto.getTelefonoTrabajo());
			user.setSexo(usrDto.getSexo());
			user.setEstadoCivil(Integer.parseInt(usrDto.getEstadoCivil()));
			if(!usrDto.getFechaNac().equals(""))
				user.setFechaNac(util.fechaFormatoPostgres(usrDto.getFechaNac()));
			user.setNit(usrDto.getNit());
			user.setDui(usrDto.getDui());
			user.setNup(usrDto.getNup());
			user.setIsss(usrDto.getIsss());
			dao.update(user, session);
			return true;
		}	
		return false;
	}
	
	/**
	 * Actualiza un Usuario DEI
	 * @param usrDto   Objeto bean de Usuario
	 * @param session  Session para insertar en la Base de Datos, con esta se maneja la transaccionalidad
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserDei(UsuarioDto usrDto, Session session)throws Exception {
		String hql = QuerysSeguridad.isUserDei(usrDto.getIdUsuario());
		List list = dao.find(hql, session);
		if (list != null && list.size() >0) {
			UsuarioDei dei = (UsuarioDei) list.get(0);
			if (usrDto.getCodigoEmpleado()!= null && !usrDto.getCodigoEmpleado().equals(""))
				dei.setCodigoEmpleado(usrDto.getCodigoEmpleado());
			if (usrDto.getExt() != null )
				dei.setExtensionUca(usrDto.getExt());
			dao.update(dei, session);
			return true;
		}
		return false;
	}

	/**
	 *  Actualiza un Usuario Externo
	 * @param usr      Usuario que esta realizando la acción
	 * @param usrDto   Objeto bean de Usuario
	 * @param session  Session para insertar en la Base de Datos, con esta se maneja la transaccionalidad
	 * @return True | False
	 * @throws Exception
	 */
	public boolean updateUserExt(String usr, UsuarioDto usrDto, Session session)throws Exception {
		String hql = QuerysSeguridad.isUserExt(usrDto.getIdUsuario());
		List list = dao.find(hql, session);
		if (list != null && list.size() >0) {
			UsuarioExterno ext = (UsuarioExterno) list.get(0);
			if(usrDto.getComentario() != null)
				ext.setComentario(usrDto.getComentario());
			ext.setSolicitadoPor(usrDto.getSolicitadoPor());
			dao.update(ext, session);
			return true;
		}
		return false;
	}
	
	/** 
	 * Actualiza un Estudiante
	 * @param usrDto   Objeto bean de Usuario
	 * @param session  Session para insertar en la Base de Datos, con esta se maneja la transaccionalidad
	 * @return  True | False        
	 * @throws Exception
	 */
	public boolean updateEstudiante(UsuarioDto usrDto, Session session)throws Exception {
		String hql = QuerysSeguridad.isEstudiante(usrDto.getIdUsuario());
		List list = dao.find(hql, session);
		if (list != null && list.size() >0) {
			Estudiante est = (Estudiante) list.get(0);
			if(usrDto.getOyente() != null && !usrDto.getOyente().equals(""))
				est.setOyente(usrDto.getOyente());
			if(usrDto.getCicloIngreso() !=null && !usrDto.getCicloIngreso().equals(""))
				est.setCicloIngreso(usrDto.getCicloIngreso());
			if(usrDto.getAnyoIngreso() !=null && !usrDto.getAnyoIngreso().equals(""))
				est.setAnyoIngreso(usrDto.getAnyoIngreso());
			dao.update(est, session);
			return true;
		}
		return false;
	}
	
	/**
	 * Actualiza la fecha de Finalizacion de un Periodo de Vigencia 
	 * @param usr
	 * @param usrDto
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserVigencia(UsuarioDto usrDto, Session session)throws Exception {
		String hql = QuerysSeguridad.isUserVigencia(usrDto.getIdUsuario(), util.fechaFormatoPostgres(usrDto.getIniVigencia()));
		List list = dao.find(hql, session);
		if (list != null && list.size() >0){
			UsuarioVigencia userVig = (UsuarioVigencia) list.get(0);
			 if (usrDto.getFinVigencia()!= null);
			 	userVig.setFechaFin(util.fechaFormatoPostgres(usrDto.getFinVigencia()));
			dao.update(userVig, session);
			return true;
		}
		return false;		
	}
	
	/**
	 * Realiza una Busqueda para Usuario dependeindo del filtro de la pantalla Busca tambien los tipos de Usuario
	 * @param usrFrm      Formulario de la pantalla de Usuario 
	 * @param p_Estado    Estado en el que se encuentra el Usuario
	 * @param isModificar Determina si es busqueda para modificación o no (True o False) 
	 * @return 			  Mapa con Objetos UsuarioDto
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map find(UsuarioForm usrFrm, String p_Estado, boolean isModificar )throws Exception {
		String sql = QuerysSeguridad.findUser(); 
		if (usrFrm.getTipo()!= null && usrFrm.getTipo().equals("D"))
			sql += " And  u.Id_Usuario =  d.codigo_empleado ";
		else if (usrFrm.getTipo()!= null && usrFrm.getTipo().equals("E"))
			sql += " And u2.id_usuario = e.id_usuario ";
		else if(usrFrm.getTipo()!= null && usrFrm.getTipo().equals("S"))
			sql += " And u3.id_usuario = s.carnet_estudiante ";
		
		if (p_Estado != null && !p_Estado.equals(""))
			sql += " And u.est_Usuario = '"+ p_Estado +"' ";
		if(usrFrm.getIdUsuario()!= null && !usrFrm.getIdUsuario().equals(""))
			sql += " And u.id_usuario like '" + usrFrm.getIdUsuario().trim() + "%' ";
		if(usrFrm.getPrimerNom()!= null && !usrFrm.getPrimerNom().equals(""))
			sql += " And lower(u.primer_nom) like lower('" + usrFrm.getPrimerNom().trim() + "%') ";
		if(usrFrm.getPrimerApe()!= null && !usrFrm.getPrimerApe().equals(""))
			sql += " And lower(u.primer_ape) like lower('" + usrFrm.getPrimerApe().trim() + "%') ";
		if(usrFrm.getNombreRestante()!= null && !usrFrm.getNombreRestante().equals(""))
			sql += " And lower(u.nombre_restante) like lower('" + usrFrm.getNombreRestante().trim() + "%') ";
		if(usrFrm.getApellidoRestante()!= null && !usrFrm.getApellidoRestante().equals(""))
			sql += " And lower(u.apellido_restante) like lower('" + usrFrm.getApellidoRestante().trim() + "%') ";
		if(usrFrm.getDireccionPart() != null && !usrFrm.getDireccionPart().trim().equals("null") && !usrFrm.getDireccionPart().trim().equals(""))
			sql += " And lower(u.direccion_part) like lower('%" + usrFrm.getDireccionPart().trim() + "%') ";
		if(usrFrm.getEmail()!= null && !usrFrm.getEmail().trim().equals(""))
			sql += " And lower(u.email) like lower('%" + usrFrm.getEmail().trim() + "%') ";
		if(usrFrm.getTelefonoCasa()!= null && !usrFrm.getTelefonoCasa().trim().equals(""))
			sql += " And u.telefono_casa like '" + usrFrm.getTelefonoCasa().trim() + "%' ";
		if(usrFrm.getTelefonoCel()!= null && !usrFrm.getTelefonoCel().trim().equals(""))
			sql += " And u.telefono_cel like '" + usrFrm.getTelefonoCel().trim() + "%' ";
		if(usrFrm.getTelefonoTrabajo()!= null && !usrFrm.getTelefonoTrabajo().trim().equals(""))
			sql += " And u.telefono_trabajo like '" + usrFrm.getTelefonoTrabajo().trim() + "%' ";
		if (usrFrm.getExt()!= null && !usrFrm.getExt().trim().equals(""))
			sql +=" And d.extension_uca like '"+usrFrm.getExt().trim()+"%' ";
		if (usrFrm.getCodigoEmpleado()!= null && !usrFrm.getCodigoEmpleado().trim().equals(""))
			sql +=" And lower(d.codigo_empleado) like lower('"+ usrFrm.getCodigoEmpleado().trim() +"%') ";
		if (usrFrm.getEstadoCivil()!= null && !usrFrm.getEstadoCivil().trim().equals("Seleccione") && !usrFrm.getEstadoCivil().trim().equals(""))
			sql +=" And u.estado_civil = "+ usrFrm.getEstadoCivil().trim()+" " ;
		if (usrFrm.getSexo()!= null && !usrFrm.getSexo().trim().equals("Seleccione") && !usrFrm.getSexo().trim().equals(""))
			sql +=" And u.sexo = '"+ usrFrm.getSexo().trim()+ "'" ;
		if (usrFrm.getIdCarrera() != null && !usrFrm.getIdCarrera().trim().equals("Seleccione")&& !usrFrm.getIdCarrera().trim().equals(""))
			sql +=" And s.id_carrera = '"+ usrFrm.getIdCarrera().trim()+ "'" ;
		if (usrFrm.getOyente() != null && !usrFrm.getOyente().trim().equals("Seleccione")&& !usrFrm.getOyente().trim().equals(""))
			sql +=" And s.oyente = '"+ usrFrm.getOyente().trim()+ "' " ;
		if (usrFrm.getDui() != null && !usrFrm.getDui().trim().equals(""))
			sql +=" And s.dui = '"+ usrFrm.getDui().trim()+ "' " ;		
		sql+="  order by u.Id_Usuario,u.Primer_Ape   ";		
		List list 			= jdbc.getQuery(sql, null);
		UsuarioDto usuario  = null;
		Map mapx 			= new HashMap();
		for(int i=0; i<list.size(); i++){
			String fecha = "";
			DynaBean dyna = (DynaBean) list.get(i);
			usuario = new UsuarioDto();			
			usuario.setIdUsuario(dyna.get("id_usuario").toString().trim());
			usuario.setPrimerNom(dyna.get("primer_nom").toString().trim());
			usuario.setPrimerApe(dyna.get("primer_ape").toString().trim());
			usuario.setPswUsuario(dyna.get("psw_usuario").toString().trim());
			if(dyna.get("carnet_estudiante") != null)
				usuario.setCarnetEstudiante(dyna.get("carnet_estudiante").toString().trim());
			if(dyna.get("codigo_empleado") != null)
				usuario.setCodigoEmpleado(dyna.get("codigo_empleado").toString().trim());			
			if(dyna.get("nombre_restante")   != null)
				usuario.setNombreRestante(dyna.get("nombre_restante").toString().trim());
			if(dyna.get("apellido_restante") != null)
				usuario.setApellidoRestante(dyna.get("apellido_restante").toString().trim());
			if(dyna.get("fecha_nac")  		 != null){
				fecha = dyna.get("fecha_nac").toString().trim();
				fecha = util.fechaDDMMYY(fecha);
				usuario.setFechaNac(fecha);
			}
			if(dyna.get("direccion_part")  != null)
				usuario.setDireccionPart(dyna.get("direccion_part").toString().trim());
			if(dyna.get("email") != null)
				usuario.setEmail(dyna.get("email").toString().trim());
			if(dyna.get("telefono_casa") != null)
				usuario.setTelefonoCasa(dyna.get("telefono_casa").toString().trim());
			if(dyna.get("telefono_cel") != null)
				usuario.setTelefonoCel(dyna.get("telefono_cel").toString().trim());
			if(dyna.get("telefono_trabajo") != null)
				usuario.setTelefonoTrabajo(dyna.get("telefono_trabajo").toString().trim());
			if(dyna.get("est_usuario") != null){
				if(dyna.get("est_usuario").toString().equals("A"))
					usuario.setEstUsuario("Activo");
				else
					usuario.setEstUsuario("Inactivo");
			}	
			if(dyna.get("fecha_ini")  != null){
				fecha = dyna.get("fecha_ini").toString().trim();
				fecha = util.fechaDDMMYY(fecha);
				usuario.setIniVigencia(fecha);
			}
			if(dyna.get("fecha_fin")  != null){
				fecha = dyna.get("fecha_fin").toString().trim();
				fecha = util.fechaDDMMYY(fecha);
				usuario.setFinVigencia(fecha);
			}
			if(dyna.get("ciclo_ingreso") != null)
				usuario.setCicloIngreso(dyna.get("ciclo_ingreso").toString().trim());
			if(dyna.get("anyo_ingreso") != null)
				usuario.setAnyoIngreso(dyna.get("anyo_ingreso").toString().trim());
			if(dyna.get("sexo") != null)
				usuario.setSexo(dyna.get("sexo").toString().trim());
			if(dyna.get("estado_civil") != null)
				usuario.setEstadoCivil(dyna.get("estado_civil").toString().trim());
			if(dyna.get("extension_uca") != null)
				usuario.setExt(dyna.get("extension_uca").toString().trim());
			if(dyna.get("comentario") != null)
				usuario.setComentario(dyna.get("comentario").toString().trim());
			if(dyna.get("id_carrera") != null && dyna.get("plan_estudio") != null)
				usuario.setIdCarrera(dyna.get("id_carrera").toString().trim()+ " - "+ dyna.get("plan_estudio").toString().trim()) ;
			if(dyna.get("oyente") != null)
				usuario.setOyente(dyna.get("oyente").toString().trim());
			if(dyna.get("nit") != null)
				usuario.setNit(dyna.get("nit").toString().trim());
			if(dyna.get("dui") != null)
				usuario.setDui(dyna.get("dui").toString().trim());
			if(dyna.get("isss") != null)
				usuario.setIsss(dyna.get("isss").toString().trim());
			if(dyna.get("nup") != null)
				usuario.setNup(dyna.get("nup").toString().trim());
			if(usuario.getCarnetEstudiante() != null && !usuario.getCarnetEstudiante().equals(""))
				usuario.setTipo("S");
			else if(usuario.getCodigoEmpleado() != null && !usuario.getCodigoEmpleado().equals(""))
				usuario.setTipo("D");
			else if(dyna.get("externo") != null)
				usuario.setTipo("E");
			
			if(isModificar){ 
				usuario.setAccion("<a href= \"#\" onclick= \"javascript:modificar( " +
								  " '"+ usuario.getIdUsuario()      +"', '"+usuario.getPrimerNom()       +"', '"+usuario.getPrimerApe()   +"', " +
								  "	'"+ usuario.getNombreRestante() +"', '"+usuario.getApellidoRestante()+"', '"+usuario.getFechaNac()    +"', " +
								  " '"+ usuario.getDireccionPart()  +"', '"+usuario.getEmail()           +"', '"+usuario.getTelefonoCasa()+"', " +
								  " '"+ usuario.getTelefonoCel()    +"', '"+usuario.getTelefonoTrabajo() +"', '"+usuario.getIniVigencia() +"', " + 
								  " '"+ usuario.getFinVigencia()    +"', '"+usuario.getSexo()			 +"', '"+usuario.getEstadoCivil() +"', " +
								  " '"+ usuario.getTipo() 			+"', '"+usuario.getCodigoEmpleado()  +"', '"+usuario.getExt()         +"', " +
								  " '"+ usuario.getComentario()     +"', '"+usuario.getIdCarrera()       +"', '"+usuario.getSolicitadoPor()+"'," +
								  " '"+ usuario.getOyente()			+"', '"+usuario.getCicloIngreso()    +"', '"+usuario.getDui()		  +"', " +
								  " '"+ usuario.getNit()		    +"', '"+usuario.getIsss() 			 +"', '"+usuario.getNup() 		  +"', " +
								  " '"+ usuario.getAnyoIngreso()    +"', '"+usuario.getCarnetEstudiante()+"'                                  " +
								  ")\" > Modificar</a> "); 
			}else{
				String estadox = "";
				String estado  = "";
				if(usuario.getEstUsuario().equals("Activo")){
					estadox = "Desactivar";
					estado   = "A";
				}
				else{
					estadox = "Activar";
					estado   = "I";
				}
				this.getAccionDto(usuario, "hash", estadox, estado);
				/*usuario.setAccion("<a href='" + Constants.contextPath + "usuario.do?cmd=hash&idUsuario=" + usuario.getIdUsuario() +"&primerNom=" + usuario.getPrimerNom() + 
										"&primerApe=" + usuario.getPrimerApe() + "&apeRest="+ usuario.getApellidoRestante()+ "&email=" + usuario.getEmail()+ "&estado="+estado+" '>" + estadox + "</a>");
				*/						
			}
			mapx.put(usuario.getIdUsuario(), usuario);
		}
		return mapx;
	}
	
	public boolean  guardarMap (Map p_mapa, String p_estado){
		try {
			for (Iterator i = p_mapa.keySet().iterator(); i.hasNext();)
			{
				String strKey  = (String) i.next();
				@SuppressWarnings("unused")
				boolean bool = guardarCambios(strKey, p_estado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
			return true;
	}
		
	public boolean guardarCambios(String p_IdUsuario, String p_Estado){
		String sql = "Update  Seguridad.Usuario set est_usuario = '"+p_Estado+"' where id_usuario= '"+p_IdUsuario+"'  " ;
		return jdbc.saveOrUpdate(sql);
	}
	
	public void getAccionDto (UsuarioDto p_dto, String p_cmd, String p_Accion, String p_estado){
		p_dto.setAccion("<a href='" + Constants.contextPath + "usuario.do?cmd="+ p_cmd +"&idUsuario=" + p_dto.getIdUsuario() +"&primerNom=" + p_dto.getPrimerNom() + 
				"&primerApe=" + p_dto.getPrimerApe() + "&apeRest="+ p_dto.getApellidoRestante()+ "&email=" + p_dto.getEmail()+ "&estado="+p_estado+" '>" + 
				p_Accion + "</a>");
	}
	
	/**
	 * Obtiene un Usuario en forma de Bean 
	 * @param idUsr Identificador de Usuario
	 * @return Objeto UsuarioDto     
	 * @throws Exception
	 */
	public UsuarioDto getUsuarioDto(String idUsr)throws Exception{
		UsuarioForm usrFrm = new UsuarioForm(); 
		usrFrm.setIdUsuario(idUsr); 
		Map mapUser = this.find(usrFrm, "A", false);
		if (mapUser != null && mapUser.size() >0){
			UsuarioDto dto = (UsuarioDto) mapUser.get(idUsr);
			return dto;
		}
		return null;		
	}
	
	public UsuarioDto modificarUsr(UsuarioDto dto, ModificarDatosForm form)throws Exception{
		if (form.getPrimerNom()!= null && !form.getPrimerNom().equals(""))
			dto.setPrimerNom(form.getPrimerNom());
		if (form.getPrimerApe()!= null && !form.getPrimerApe().equals(""))
			dto.setPrimerApe(form.getPrimerApe());
		if (form.getEmail() != null && !form.getEmail().equals(""))
			dto.setEmail(form.getEmail());
		if (form.getEstadoCivil() != null && !form.getEstadoCivil().equals(""))
			dto.setEstadoCivil(form.getEstadoCivil());
		if (form.getSexo() != null && !form.getSexo().equals(""))
			dto.setSexo(form.getSexo());
		
		dto.setNombreRestante(form.getNombreRestante());
		dto.setApellidoRestante(form.getApellidoRestante());
		dto.setFechaNac(form.getFechaNac());
		dto.setDireccionPart(form.getDireccionPart());
		dto.setTelefonoCasa(form.getTelefonoCasa());
		dto.setTelefonoCel(form.getTelefonoCel());
		dto.setTelefonoTrabajo(form.getTelefonoTrabajo());
		if (form.getTipo() != null){
			if (form.getTipo().equals("D")){
				if (form.getCarnetEmpleado()!= null && form.getCarnetEmpleado().equals(""))
					dto.setCodigoEmpleado(form.getCarnetEmpleado());
				dto.setExt(form.getExt());
			}
			else if (form.getTipo().equals("E")){
				dto.setComentario(form.getComentario());
			}
			else if (form.getTipo().equals("S")){
				if (form.getIdCarrera()!= null && !form.getIdCarrera().equals(""))
					dto.setIdCarrera(form.getIdCarrera());
				if (form.getOyente() != null && !form.getOyente().equals(""))
					dto.setOyente(form.getOyente());
				dto.setDui(form.getDui());
				dto.setIsss(form.getIsss());
				dto.setNit(form.getNit());
				dto.setNup(form.getNup());
			}	
		}	
		return dto;
	}

	public boolean saveEstCar(EstudianteCarreraDto dto, Session session)throws Exception{
		if(dto != null){
			EstudianteCarrera estCar = new EstudianteCarrera();
			EstudianteCarreraId   id = new EstudianteCarreraId();
			Estudiante           est = new Estudiante();
			Carrera              car = new Carrera();
			CarreraId          carId = new CarreraId();
			carId.setIdCarrera(dto.getIdCarrera()); 
			carId.setPlanEstudio(dto.getPlanEstudio());
			car.setId(carId);
			est.setCarnetEstudiante(dto.getCarnetEst());
			id.setCarrera(car);
			id.setEstudiante(est);
			id.setFechaIni(util.fechaFormatoPostgres(dto.getFechaIni()));
			estCar.setId(id);
			if (dto.getFechaFin()!= null && !dto.getFechaFin().equals(""))
				estCar.setFechaFin(util.fechaFormatoPostgres(dto.getFechaFin()));			
			estCar.setEstEstCar("A");
			dao.save(estCar, session);
			return true;
		}
		return false;		
	}
	public boolean updateEstCar(EstudianteCarreraDto dto, Session session)throws Exception{
		String hql = QuerysSeguridad.EstCarreraActiva(dto.getCarnetEst()); 
		List list  = dao.find(hql,session);
		if(list != null && list.size() >0){
			EstudianteCarrera estCar = (EstudianteCarrera) list.get(0);
			estCar.setEstEstCar("I");
			estCar.setFechaFin(util.getFechaServidor());
			dao.update(estCar, session);
		}
		return true;	
	}
	
	/**
	 * Verifica si un Estudiante ya posee la carrera ingresada
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean isEstCar(EstudianteCarreraDto dto)throws Exception{
		if(dto != null){
			String hql = QuerysSeguridad.isEstudianteCarrera(dto.getCarnetEst(), dto.getIdCarrera(), dto.getPlanEstudio()); 
			List list = dao.find(hql);
			if(list != null && list.size() >0)
			return true;			
		}
		return false;
	}
	public boolean saveEstCarProcess(EstudianteCarreraDto dto) throws Exception{
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		boolean retorna = false;
		try {
			if (dto!= null){
				this.updateEstCar(dto, session);
				this.saveEstCar(dto, session);
				tx.commit();
				retorna = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			new Exception();			
		}
		finally{
			try {
				session.close(); 
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		return retorna;
	}
	
	public List EstCarreraFind(String  usr)throws Exception{
		String hql = QuerysSeguridad.EstCarrera(usr); 
		List list  = dao.find(hql);
		List<EstudianteCarreraDto> lstEst = new LinkedList<EstudianteCarreraDto>();
		if(list != null && list.size() >0){
			for (int i =0; i< list.size(); i++){
			EstudianteCarrera estCar  = (EstudianteCarrera) list.get(i);
			EstudianteCarreraDto dtoEC = new EstudianteCarreraDto();
			dtoEC.setIdCarrera(estCar.getId().getCarrera().getId().getIdCarrera());
			dtoEC.setPlanEstudio(estCar.getId().getCarrera().getId().getPlanEstudio());
			dtoEC.setFechaIni(util.dateToStringDDMMYYYY(estCar.getId().getFechaIni()));
			if (estCar.getFechaFin() != null && !estCar.getFechaFin().equals(""))
				dtoEC.setFechaFin(util.dateToStringDDMMYYYY(estCar.getFechaFin()));
			dtoEC.setEstado(estCar.getEstEstCar()); 
			lstEst.add(dtoEC);
			}
		}
		return lstEst;
	}
	
}


