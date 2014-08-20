package com.saldei.web.services.seguridad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.CargoUsuarioDei;
import com.saldei.hibernate.tables.CargoUsuarioDeiId;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.seguridad.OrgDeiDto;
import com.saldei.web.bean.seguridad.OrganizacionDeiDto;
import com.saldei.web.form.seguridad.OrganizacionDEIForm;

public class OrganizacionDEIServices {
	
	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc = new JdbcHelper();
	
	public boolean isNullUsers(OrganizacionDEIForm orgForm){
		if(orgForm.getUsr().equals("Seleccione"))
			return true;
		return false;
	}
	
	public List getUsers(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			String hql = "from Usuario where estUsuario = 'A'  " +
					"and idUsuario in (select b.usuario.idUsuario from UsuarioDei b)" +
					"Order By idUsuario";			
			List lst = dao.find(hql);
			for(int i=0; i<lst.size(); i++){
				Usuario usuario = (Usuario) lst.get(i);
				usuario.setPrimerNom(usuario.getPrimerNom() + " " + usuario.getPrimerApe());
				lista.add(usuario);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List getCargos(){
		List lstDei = new LinkedList();
		try {
			String hql = "from Multicode m where m.tipoMulticode.idTipoMulticode = 3";
			lstDei = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstDei;		
	} 
	
	/**
	 * Obtiene los Cargos de un Usuario
	 * @param orgForm Formulario de Pantalla
	 * @return
	 */
	public ArrayList cargoUser(OrganizacionDEIForm orgForm){
		JdbcHelper jdbc  						 = new JdbcHelper();		
		ArrayList<OrgDeiDto> MapCargo = new ArrayList<OrgDeiDto>();				
		try {
			String strSQL = getCargoUser(orgForm.getUsr());
			List lstAux   = jdbc.getQuery(strSQL, null);
			OrgDeiDto orgDto = null;
			for(int i=0; i< lstAux.size(); i++){
				DynaBean dyna = (DynaBean) lstAux.get(i);
				orgDto = new OrgDeiDto();
				orgDto.setCargof(dyna.get("id_multicode").toString());
				orgDto.setNombreCargof(dyna.get("codigo").toString());
				orgDto.setDescripcionf(dyna.get("descripcion").toString());
				orgDto.setIdUsuariof(orgForm.getUsr());
				orgDto.setFechaInif(getFecha(dyna.get("fechaini").toString()));
				orgDto.setFechaFinf(getFecha(dyna.get("fechafin").toString()));
				orgDto.setAccionf("<a href='" + Constants.contextPath + "orgDEI.do?cmd=Remove&cargo= " + 
						orgDto.getCargof()+"&idUsuario= " + orgDto.getIdUsuariof() + "&fechaIni= " + orgDto.getFechaInif()+ "&fechaFin=" + orgDto.getFechaFinf() + "" +
						"&nombreCargo= "+ orgDto.getNombreCargof()+"&descripcion="+orgDto.getDescripcionf()+"'>Remover Cargo</a>");
				//getAccion("Remover Cargo", orgDto, "Remove");
				//orgDto.setAccion("<a href='" + Constants.contextPath + "orgDEI.do?cmd=RemoverCargo&cargo= " + 
				//		orgDto.getcargo()+"&nombreCargo= "+ orgDto.getNombreCargo()+"&descripcion="+orgDto.getDescripcion()+"'>Remover Cargo</a>");
				MapCargo.add(orgDto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return MapCargo;
	}
	
	public String getFecha(String fecha){		
		String[] f = fecha.split("-");		
		if(f.length == 3){
			String a = f[2] +  "/" + f[1] + "/" + f[0];
			return a;
		}else
			return fecha;
	}
	
	public static String getCargoUser(String p_Usuario){
		 String sql = " Select M.Id_Multicode, M.Codigo, M.Descripcion, C.fecha_ini as fechaini, C.fecha_fin as fechafin  From Registro.Multicode M Inner Join     " +
					  " Seguridad.Cargo_Usuario_Dei C ON  M.Id_Tipo_Multicode= '"+ Constants.Table_Cargo+"' And " + 
					  " C.Id_Cargo = M.ID_Multicode And M.Est_Multicode = 'A' And C.Est_Cargo_Dei = 'A'         " +
					  " Where C.Id_Usuario = '"+p_Usuario +"'                                                   ";
		return sql;
	}	
	
	/** 
	 * Obtiene los Cargos que no posee el usuario
	 * @param orgForm Formulario de la Organizacion DEI
	 * @return
	 */
	public ArrayList cargo(OrganizacionDEIForm orgForm){
		JdbcHelper jdbc   = new JdbcHelper();
		ArrayList<OrganizacionDeiDto> MapCargo = new ArrayList<OrganizacionDeiDto>();		
		try {
			String strSQL =getCargosQuery();
			List lstAux = jdbc.getQuery(strSQL, null);
			OrganizacionDeiDto orgDto = null;
			for(int i=0; i< lstAux.size(); i++){
				DynaBean dyna = (DynaBean) lstAux.get(i);
				orgDto = new OrganizacionDeiDto();
				orgDto.setcargo(dyna.get("id_multicode").toString());
				orgDto.setNombreCargo(dyna.get("codigo").toString());
				orgDto.setDescripcion(dyna.get("descripcion").toString());
				orgDto.setIdUsuario(orgForm.getUsr());				
				int num = i + 1;
				orgDto.setAccion("<a href='javascript:obtener(" + num + ")'>Asignar Cargo</a>");
//				getAccion("Asignar Cargo", orgDto, "Add");
				//orgDto.setAccion("<a href='" + Constants.contextPath + "orgDEI.do?cmd=AsignarCargo&cargo= " + 
				//				orgDto.getcargo()+"&nombreCargo= "+ orgDto.getNombreCargo()+"&descripcion="+orgDto.getDescripcion()+"'>Asignar Cargo</a>");
				MapCargo.add(orgDto); 
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return MapCargo;
	}
	
	public  String getCargosQuery(){
		String sql = " SELECT M.ID_MULTICODE, M.CODIGO, M.descripcion FROM REGISTRO.MULTICODE M    " +
					 " WHERE M.ID_TIPO_MULTICODE = '"+ Constants.Table_Cargo+"' ";
		return sql;
	}
	
	/** 
	 * Setea la Accion de un Dto
	 * @param Accion Nombre del a href 
	 * @param dto    Dto a setear
	 * @param cmd    Comando a ejecutar
	 * @return
	 */
	public OrganizacionDeiDto getAccion(String Accion,OrganizacionDeiDto dto, String cmd){
		dto.setAccion("<a href='" + Constants.contextPath + "orgDEI.do?cmd="+cmd+"&cargo= " + 
				dto.getcargo()+"&nombreCargo= "+ dto.getNombreCargo()+"&descripcion="+dto.getDescripcion()+"'>"+Accion+"</a>");
		return dto;		
	}
	
	public boolean saveAll(ArrayList list, String usuario){
		try{
			String delete = "delete from seguridad.cargo_usuario_dei where id_usuario = '" + usuario + "'"; 
			jdbc.delete(delete, null);
			OrgDeiDto orgDto = null;
			for(int i=0; i<list.size(); i++){	
				orgDto = (OrgDeiDto) list.get(i);
				int sequence = this.getSequence();
				String sql = "insert into seguridad.cargo_usuario_dei(id_cargo_usr,id_usuario,id_cargo,fecha_ini," +
						"fecha_fin,est_cargo_dei) values(" + sequence+ ",'" + usuario+ "'," + orgDto.getCargof()+ "" +
								",'" + getFechaFormato(orgDto.getFechaInif())+ "','" + getFechaFormato(orgDto.getFechaFinf()) + "','A')";
				jdbc.saveOrUpdate(sql, null);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	private String getFechaFormato(String fecha){
		String[] datos = fecha.split("/");
		String f = datos[1] + "/" + datos[0] + "/" + datos[2];
		return f;
	}
	private int getSequence(){
		String query = "select max(id_cargo_usr) + 1 as num from seguridad.cargo_usuario_dei";
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
	
	public void actualizaCargo(OrganizacionDeiDto orgDto){		
		try {
			
			CargoUsuarioDeiId cargoUsr = new CargoUsuarioDeiId();
			CargoUsuarioDei   cargoDei = new CargoUsuarioDei();
			Usuario           usuario  = new Usuario();
			usuario.setIdUsuario(orgDto.getIdUsuario().trim());
			cargoUsr.setUsuario(usuario);
			cargoUsr.setIdCargo(new Integer(orgDto.getcargo().trim()));
			cargoDei.setEstCargoDei(orgDto.getEstado().trim());
			//cargoDei.setId(cargoUsr);
			dao.update(cargoDei);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveCargo(OrganizacionDeiDto orgDto){		
		try {
			
			CargoUsuarioDeiId cargoUsr = new CargoUsuarioDeiId();
			CargoUsuarioDei   cargoDei = new CargoUsuarioDei();
			Usuario           usuario  = new Usuario();
			usuario.setIdUsuario(orgDto.getIdUsuario().trim());
			cargoUsr.setUsuario(usuario);
			cargoUsr.setIdCargo(new Integer(orgDto.getcargo().trim()));
			cargoDei.setEstCargoDei(orgDto.getEstado().trim());
			//cargoDei.setId(cargoUsr);
			dao.save(cargoDei);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean RemoverAllCargo(Map mapCargo){
		boolean boolRet = true;
		try {
			if (mapCargo  != null && mapCargo.size() > 0 ){
				for(Iterator i = mapCargo.keySet().iterator(); i.hasNext();)
				{
				    String key 					= (String)i.next();
					OrganizacionDeiDto orgDto 	= (OrganizacionDeiDto) mapCargo.get(key);
					orgDto.setEstado("I");
					actualizaCargo(orgDto);
				}	 
			}
		} catch (Exception e) {
			boolRet = false;
			e.printStackTrace();
		}		
		return boolRet;
	}

	public ArrayList cargoUserAll(OrganizacionDEIForm orgForm)
	{
		JdbcHelper jdbc   = new JdbcHelper();
		String strSQL = "SELECT M.ID_MULTICODE, M.CODIGO, M.descripcion FROM REGISTRO.MULTICODE M INNER JOIN " +
						" SEGURIDAD.cargo_usuario_dei C ON C.ID_CARGO = M.ID_MULTICODE  AND " + 
						"M.EST_MULTICODE ='A' WHERE  C.ID_USUARIO = ?";
		ArrayList<OrganizacionDeiDto> MapCargo = new ArrayList<OrganizacionDeiDto>();				
		try {
			String[] params = {orgForm.getUsr()};
			List lstAux = jdbc.getQuery(strSQL, params);
			OrganizacionDeiDto orgDto = null;
				
			for(int i=0; i< lstAux.size(); i++){
				DynaBean dyna = (DynaBean) lstAux.get(i);
				orgDto = new OrganizacionDeiDto();
				orgDto.setIdUsuario(orgForm.getUsr());
				orgDto.setcargo(dyna.get("id_multicode").toString());
				orgDto.setNombreCargo(dyna.get("codigo").toString());
				orgDto.setDescripcion(dyna.get("descripcion").toString());
				orgDto.setIdUsuario(orgForm.getUsr());
				orgDto.setAccion("<a href='" + Constants.contextPath + "orgDei.do?cmd=RemoverCargo&cargo= " + 
						orgDto.getcargo()+"&nombreCargo= "+ orgDto.getNombreCargo()+"&descripcion="+orgDto.getDescripcion()+"'>Remover Cargo</a>");

				MapCargo.add(orgDto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return MapCargo;
	}
	
	
}
