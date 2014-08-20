/**
 * Proyecto: SALDEI
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.web.services.registro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.CargoUsuarioDei;
import com.saldei.hibernate.tables.DirectorLaboratorio;
import com.saldei.hibernate.tables.DirectorLaboratorioId;
import com.saldei.hibernate.tables.Laboratorio;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.registro.AsigDirecLabDto;
import com.saldei.web.bean.registro.AsigDirecLabDtoForm;
import com.saldei.web.form.registro.AsigDirecLabForm;
import com.saldei.util.mail.ConfigEmail;

public class AsigDirecLabServices {

	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc = new JdbcHelper();	
		
	public List getLaboratorios(){
		String hql = "from Laboratorio where estLaboratorio = 'A' order by nombreLaboratorio";
		List list = dao.find(hql);		
		return list;
	}
	
	public ArrayList getDirectores(){
		String hql = "from CargoUsuarioDei where estCargoDei = 'A'  and idCargo in " +
		"(select valor from Parametro where nomParametro = '" + Constants.Parametro_DIRECTOR + "') order by id.usuario.idUsuario";;
		List list = dao.find(hql);
		AsigDirecLabDtoForm form = null;
		ArrayList<AsigDirecLabDtoForm> lst = new ArrayList<AsigDirecLabDtoForm>();
		int i = 1;
		for(Iterator iter= list.iterator(); iter.hasNext();){
			form = new AsigDirecLabDtoForm();
			CargoUsuarioDei dei = (CargoUsuarioDei) iter.next();
			form.setIdUsuariof(dei.getUsuario().getIdUsuario());
			form.setNombref(this.nombreUsuario(dei.getUsuario().getIdUsuario()));
			form.setIdMultif(String.valueOf(dei.getIdCargo()));
			form.setDescf(this.getDescripcion(String.valueOf(dei.getIdCargo())));
			form.setIdf(String.valueOf(dei.getIdCargo()));
			form.setLabosf(this.getLabByDirector(form.getIdUsuariof(), ""));
			form.setAccionf("<a href='javascript:obtener(" + i + ")'>Asignar</a>");
			lst.add(form);
			i = i +1;
		}
		return lst;
	}
	
	public String getLabByDirector(String director, String id){
		int cargousr = this.getCargoDirector(director);
		String sql = "select  distinct a.nombre_laboratorio as nombre " +
				"from registro.laboratorio a, registro.director_laboratorio b, seguridad.cargo_usuario_dei c " +
				"where c.id_cargo_usr =  " + cargousr +
				" and   c.id_cargo_usr = b.id_cargo_usr " +
				" and   b.id_laboratorio = a.id_laboratorio" +
				" order by 1";
		List listjdbc = jdbc.getQuery(sql, null);
		String l = "";
		for(int i=0; i<listjdbc.size(); i++){
			DynaBean dyna = (DynaBean) listjdbc.get(i);
			l += dyna.get("nombre").toString() + "<br>";
		}
		return l;		
	}
	
	public void mostrar(AsigDirecLabForm asl, HttpServletRequest request){
		try{
			String hql = "from DirectorLaboratorio where laboratorio.idLaboratorio = ?";
			Object[] params = {new Integer(asl.getLaboratorio())};
			List list = dao.findByProps(hql, params);
			AsigDirecLabDto dto = null;
			ArrayList<AsigDirecLabDto> newList = new ArrayList<AsigDirecLabDto>();
			for(int i=0; i<list.size(); i++){
				DirectorLaboratorio dl = (DirectorLaboratorio) list.get(i);
				dto = new AsigDirecLabDto();
				dto.setIdUsuario(this.obtenerUsuarioDeCargo(dl.getIdCargoUsr()));
				dto.setNombre(this.nombreUsuario(dto.getIdUsuario()));
				dto.setFechaini(dl.getFechaIni());
				dto.setFechafin(dl.getFechaFin());
				
				SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("dd/MM/yyyy");
				StringBuilder fi = new StringBuilder( dateformatYYYYMMDD.format(dl.getFechaIni()) );
				SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
				StringBuilder ff = new StringBuilder( datef.format(dl.getFechaFin()) );
				
				dto.setFechaIniDto(fi.toString());
				dto.setFechaFinDto(ff.toString());
				dto.setRemove("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=remove&fechaIni=" + dto.getFechaIniDto() + "&fechaFin=" + dto.getFechaFinDto() + "&idUsuario=" +
						"" + dto.getIdUsuario() + "&desc= " + dto.getDesc() + "&nombre=" + dto.getNombre() + "'>Remover</a>");
				newList.add(dto);
			}
			request.getSession().setAttribute("listDsigDirec", newList);
			request.getSession().setAttribute("listAsigDirec", this.getDirectores());
//			String hqlA = "from CargoUsuarioDei where estCargoDei = 'A'  and idCargo in " +
//					"(select valor from Parametro where nomParametro = '" + Constants.Parametro_DIRECTOR + "') order by id.usuario.idUsuario";
//			List listA = dao.findSession(hqlA);
//			ArrayList[] listas = this.notInAsig(listA, asl.getLaboratorio());			
//			ArrayList arrayB = this.prepareList(listas[1], asl.getLaboratorio(), false);			
//			request.getSession().setAttribute("listDsigDirec", arrayB);
//			request.getSession().setAttribute("listAsigDirec", this.getDirectores());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public ArrayList prepareListDirectorsNotAsign(String laboratorio){
		String hql = "from DirectorLaboratorio where id.director not in(select id.director from DirectorLaboratorio where id.laboratorio.idLaboratorio = " + laboratorio + ")";
		List list = dao.findSession(hql);
		AsigDirecLabDto dto = null;
		ArrayList<AsigDirecLabDto> listAux = new ArrayList<AsigDirecLabDto>();
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			//DirectorLaboratorio direclab = (DirectorLaboratorio) iter.next();
			dto = new AsigDirecLabDto();
			//dto.setIdUsuario(direclab.getId().getDirector());			
			//dto.setLaboratorio(direclab.getId().getLaboratorio().getNombreLaboratorio());
			dto.setEstado("A");
			//dto.setAccion("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=hash&laboratorio=" + direclab.getId().getLaboratorio().getIdLaboratorio() + "&idUsuario=" +
			//		"" + dto.getIdUsuario() + "&desc=" + dto.getDesc() + "&idCargo=" + dto.getIdMulti()  + "&nombre=" + dto.getNombre() + "&estado=" + dto.getEstado() + "'>Agregar</a>");
			dto.setNombre(this.nombreUsuario(dto.getIdUsuario()));
			listAux.add(dto);
		}
		dao.close();
		return listAux;
	}
	
	/*private ArrayList[] notInAsig(List list, String labo){
		ArrayList[] listas = new ArrayList[2];
		String hql = "from DirectorLaboratorio where id.laboratorio.idLaboratorio = ?";
		Integer[] params = {new Integer(labo)};
		List listLabo = dao.findByProps(hql, params);		
		ArrayList<CargoUsuarioDei> lista = new ArrayList<CargoUsuarioDei>();
		ArrayList<CargoUsuarioDei> listax = new ArrayList<CargoUsuarioDei>();
		for(int i=0; i<list.size(); i++){
			CargoUsuarioDei cud = (CargoUsuarioDei) list.get(i);
			if(this.existeAsig(listLabo, cud.getUsuario().getIdUsuario()))				
				lista.add(cud);			
			else
				listax.add(cud);
		}
		listas[0] = lista;
		listas[1] = listax;
		return listas;
	}*/
	
//	private boolean existeAsig(List list, String usuario){
//		for(int i=0; i<list.size(); i++){
//			DirectorLaboratorio labo = (DirectorLaboratorio) list.get(i);
//			String user = this.obtenerUsuarioDeCargo(labo.getIdCargoUsr());
//			if(user.equals(usuario))
//				return false;				
//		}
//		return true;
//	}
	
	private String obtenerUsuarioDeCargo(int id){
		String hql = "from CargoUsuarioDei where idCargoUsr = ?";
		Object[] params = {new Integer(String.valueOf(id))};
		List list = dao.findByPropsSession(hql, params);
		if(list.size() != 0){
			CargoUsuarioDei cargo = (CargoUsuarioDei) list.get(0);
			String usuario = "";
			usuario = cargo.getUsuario().getIdUsuario();
			dao.close();
			return usuario;
		}
		return "";
	}
	
	/*private ArrayList prepareList(List list, String labo, boolean flag){
		AsigDirecLabDto dto = null;
		ArrayList<AsigDirecLabDto> newList = new ArrayList<AsigDirecLabDto>();
		int i= 1;
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			dto = new AsigDirecLabDto();
			CargoUsuarioDei cud = (CargoUsuarioDei) iter.next();
			dto.setIdUsuario(cud.getUsuario().getIdUsuario());
			dto.setNombre(this.nombreUsuario(cud.getUsuario().getIdUsuario()));
			dto.setIdMulti(String.valueOf(cud.getIdCargo()));
			dto.setDesc(this.getDescripcion(String.valueOf(cud.getIdCargo())));
			dto.setLabos(this.getLabByDirector(dto.getIdUsuario(), labo));
			dto.setId(String.valueOf(cud.getIdCargo()));
			if(flag){
				dto.setEstado("A");
				dto.setAccion("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=hash&id=" + dto.getId() + "&labos=" + dto.getLabos() + "&laboratorio=" + labo + "&idUsuario=" +
						"" + dto.getIdUsuario() + "&desc=" + dto.getDesc() + "&idCargo=" + dto.getIdMulti()  + "&nombre=" + dto.getNombre() + "&estado=" + dto.getEstado() + "'>Agregar</a>");
				dto.setAccion("<a href='javascript:obtener(" + i + ")'>Agregar</a>");
			}	
			else{
				dto.setEstado("I");
				dto.setAccion("<a href='" + Constants.contextPath + "asigDirecLab.do?cmd=hash&id=" + dto.getId() + "&labos=" + dto.getLabos() + "&laboratorio=" + labo + "&idUsuario=" +
						"" + dto.getIdUsuario() + "&desc=" + dto.getDesc() + "&idCargo=" + dto.getIdMulti()  + "&nombre=" + dto.getNombre() + "&estado=" + dto.getEstado() + "'>Remover</a>");
				dto.setAccion("<a href='javascript:obtener(" + i + ")'>Agregar</a>");
			}
			newList.add(dto);
			i = i + 1;
		}
		dao.close();
		return newList;
	}*/
	
	public ArrayList removeFormToList(ArrayList list, String id){
		ArrayList<AsigDirecLabDto> newList = new ArrayList<AsigDirecLabDto>();
		for(int i=0; i<list.size(); i++){
			AsigDirecLabDto dto = (AsigDirecLabDto) list.get(i);
			if(!dto.getIdUsuario().equals(id))
				newList.add(dto);
		}
		return newList;
	}
	
	public boolean saveAll(ArrayList list, String labox, AsigDirecLabForm asl){
		try{
			String[] usuarios = this.getDirectoresActuales(labox);
			String delete = "delete from registro.director_laboratorio where id_laboratorio = " + labox;
			jdbc.delete(delete, null);
			String[] usuariosNuevos = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				AsigDirecLabDto adl = (AsigDirecLabDto) list.get(i);
				usuariosNuevos[i] = adl.getIdUsuario();
				int valor = this.getCargoDirector(adl.getIdUsuario());				
					int sequence = this.getSequence();
					/*String queryInsert = "insert into registro.director_laboratorio(id_dir_lab, id_laboratorio,id_cargo_usr, fecha_ini, fecha_fin,est_dir_lab) " +
							"values(" + sequence + "," + labox + "," + valor + ")";*/
					DirectorLaboratorio dl = new DirectorLaboratorio();					
					dl.setIdDirLab(sequence);
					Laboratorio labo = this.getLabo(labox);
					dl.setLaboratorio(labo);					
					dl.setIdCargoUsr(valor);
					dl.setFechaIni(stringToDate(adl.getFechaIniDto()));
					dl.setFechaFin(stringToDate(adl.getFechaFinDto()));
					dl.setEstDirLab("A");					
					dao.save(dl);				
			}
			String nombreLaboratorio = this.getNombreLaboratorio(labox);
			this.enviarCorreoDirectoresNuevos(usuarios, usuariosNuevos, nombreLaboratorio);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	private String getNombreLaboratorio(String id){
		try{
			String hql = "select laboratorio.nombreLaboratorio from DirectorLaboratorio where laboratorio.idLaboratorio = ?";
			Object[] params = {new Integer(id)};
			List list = dao.findByProps(hql, params);
			if(list.size() != 0)
				return (String) list.get(0);
			return "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	private String[] getDirectoresActuales(String idLabo){
		try{
			String query = "select b.id_usuario as user " +
					"from   registro.director_laboratorio a, seguridad.cargo_usuario_dei b, seguridad.usuario c " +
					"where  a.id_laboratorio = ? " +
					"and    a.est_dir_lab = 'A' " +
					"and    a.id_cargo_usr = b.id_cargo_usr " +
					"and    b.id_usuario = c.id_usuario";
			Object[] params = {new Integer(idLabo)};
			List list = jdbc.getQuery(query, params);
			String[] usuarios = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				DynaBean dyna = (DynaBean) list.get(i);
				usuarios[i] = dyna.get("user").toString();
			}
			return usuarios;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private void enviarCorreoDirectoresNuevos(String[] directores, String[] usuariosNuevos, String nombreLaboratorio){
		try{
			ConfigEmail email = new ConfigEmail();
			for(int i=0; i<usuariosNuevos.length; i++){
				String u = this.getUsuarioCorreo(usuariosNuevos[i], directores);
				if(!u.equals("")){
					String correo = this.getCorreosDirectores(u);
					String bodyString = "Ha sido asignado como director del laboratorio " + nombreLaboratorio;
					String subject = "Correo de SALDEI";
					email.notificarEmail(correo, bodyString, subject);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String getUsuarioCorreo(String usuario, String[] arreglos){
		try{
			for(int i=0; i<arreglos.length; i++){
				if(usuario.equals(arreglos[i]))
					return "";
			}
			return usuario;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	private String getCorreosDirectores(String directores){
		try{
			String query = "select email from seguridad.usuario where id_usuario = ?";
			Object[] params = {directores};
			List list = jdbc.getQuery(query, params);
			String correos = "";
			for(int i=0; i<list.size(); i++){
				DynaBean dyna = (DynaBean)list.get(i);
				correos = dyna.get("email").toString();
			}
			return correos;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Date stringToDate(String fecha){
		try{
			String format = "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date newDate = sdf.parse(fecha);
			return newDate;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public int getCargoDirector(String usuario){
		List list = dao.find("select valor from Parametro where nomParametro = '" + Constants.Parametro_DIRECTOR + "'");
		String valor = "";
		if(list.size() != 0){
			valor = (String) list.get(0);			
		}
		String hql = "from CargoUsuarioDei where usuario.idUsuario = ? and idCargo = ?";		
		Object[] params = {usuario, new Integer(valor)};
		List lista = dao.findByProps(hql, params);
		if(lista.size() != 0){
			CargoUsuarioDei dei = (CargoUsuarioDei) lista.get(0);
			return dei.getIdCargoUsr();
		}
		return 0;
	}
	
	private int getSequence(){
		String query = "select max(id_dir_lab) + 1 as num from registro.director_laboratorio";
		List l = jdbc.getQuery(query, null);
		String num = "0";
		for(int i=0; i<l.size(); i++){
			DynaBean dyna = (DynaBean) l.get(i);
			if(dyna.get("num") != null)
				num = dyna.get("num").toString();
			else
				num = "1";
		}
		return new Integer(num);
	}
	
	public boolean delete(ArrayList list, String labox){
		try{
			for(int i=0; i<list.size(); i++){				
				AsigDirecLabDto adl = (AsigDirecLabDto) list.get(i);
					DirectorLaboratorio dl = new DirectorLaboratorio();
					DirectorLaboratorioId id = new DirectorLaboratorioId();
					id.setDirector(adl.getIdUsuario());
					id.setIdCargo(new Integer(adl.getIdMulti()));
					Laboratorio labo = this.getLabo(labox);
					id.setLaboratorio(labo);
					//dl.setId(id);
					dao.delete(dl);	
				
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/*private boolean existSave(String id){
		String hql = "from DirectorLaboratorio where idDirLab = ? ";
		Object[] params = { new Integer(id)};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return true;
		else
			return false;
	}*/
	
	public boolean deleteAll(String laboratorio){
		try{
			String sql = "delete from registro.director_laboratorio where id_laboratorio = ?";
			String[] params = {laboratorio};
			jdbc.delete(sql, params);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	private Laboratorio getLabo(String id){
		try{
			String hql = "from Laboratorio where idLaboratorio = ? order by nombreLaboratorio";
			Integer[] params = {new Integer(id)};
			List list = dao.findByProps(hql, params);
			return (Laboratorio) list.get(0);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private String nombreUsuario(String id){
		try{
			String hql = "select primerNom || ' ' || primerApe from Usuario where idUsuario = ?";
			String[] params = {id};
			List list = dao.findByPropsSession(hql, params);
			String nombre = (String) list.get(0);
			return nombre;
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}		
	}
	
	private String getDescripcion(String id){
		try{
			String hql = "select descripcion from Multicode where idMulticode = ?";
			Integer[] params = {new Integer(id)};
			List list = dao.findByProps(hql, params);
			String desc = (String) list.get(0);
			return desc;
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}		
	}
	
}//class
