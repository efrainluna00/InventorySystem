
package com.saldei.web.services.administracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.saldei.hibernate.querys.QuerysAdministracion;
import com.saldei.hibernate.tables.Multicode;
import com.saldei.hibernate.tables.TipoMulticode;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Auditoria;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.administracion.MulticodeDTO;
import com.saldei.web.form.administracion.MulticodeForm;

public class MulticodeServices {
	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc= new JdbcHelper();
	private Auditoria auditoria = new Auditoria();
	
	/**
	 * Elimina un Registro de  Multicode
	 * @param cc  Formulario de MUlticode
	 * @param usuarioAudit  Usuario que realiza la accion
	 * @return  True | False
	 */
	public boolean delete(MulticodeForm cc,Usuario usuarioAudit){
		try{
			String hql = "from Multicode where idMulticode = " + cc.getCodigoHid();
			List list = dao.find(hql);
			if(list.size() != 0){
				Multicode multi = (Multicode) list.get(0);				
				dao.delete(multi);
				auditoria.register(usuarioAudit.getIdUsuario(), "multicode", "DELETE", String.valueOf(multi.getIdMulticode()));
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Obtiene una lista Tipo Multicode
	 * @return  Lista
	 */
	public List getTipoMulticode(){
		List lstTipoMulticode = new LinkedList();
		try {
			String hql = "from TipoMulticode where estTipoMulti='A' order by nomTipoMulticode";
			lstTipoMulticode = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstTipoMulticode;		
	}
	
	/**
	 * Guarda un nuevo Registro de  Multicode
	 * @param cc  Formulario de MUlticode
	 * @param usuarioAudit  Usuario que realiza la accion
	 * @return  True | False
	 */
	public boolean save(MulticodeForm cc, Usuario usuarioAudit){
		Multicode multicode=null;
		@SuppressWarnings("unused")
		TipoMulticode tipoMulticode=null;
		boolean bandera = this.existeMulticode(cc.getNombre());
		if(!bandera)
			return false;
		boolean flag=false;
		try{			
			multicode= new Multicode();
			tipoMulticode=new TipoMulticode();
			//tipoMulticode=this.tipoMulticode(idTipoMulticode);						
			int codigo = jdbc.getSequence("select max(id_multicode)+1 from registro.multicode");
			multicode.setIdMulticode(codigo);
			multicode.setTipoMulticode(this.tipoMulticode(cc.getTipos()));
			multicode.setCodigo(cc.getNombre());	
			if (cc.getDesc()!= null && !cc.getDesc().equals("")){
				if (cc.getDesc().length() > 200)
					cc.setDesc(cc.getDesc().substring(0, 199));					
			}
			multicode.setDescripcion(cc.getDesc());					
			multicode.setOrden(10);
			multicode.setEstMulticode("A");			
			dao.save(multicode);	
			auditoria.register(usuarioAudit.getIdUsuario(), "multicode", "INSERT", String.valueOf(multicode.getIdMulticode()));
			flag=true;
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 *Verifica si existe un registro  Multicode
	 * @param codigo  Codigo de Multicode
	 * @return  True | False
	 */
	private boolean existeMulticode(String codigo){
		try{
			String hql = "From Multicode where lower(codigo) = ?";
			String[] params = {codigo.toLowerCase()};
			List list = dao.findByProps(hql, params);
			if(list.size() == 0)
				return true;
			return false;
		}catch(Exception ex){
			return false;
		}		
	}
	
	/**
	 * Actualiza un Registro de  Multicode
	 * @param cc  Formulario de MUlticode
	 * @param usuarioAudit  Usuario que realiza la accion
	 * @return  True | False
	 */
	public boolean update(MulticodeForm cc, Usuario usuarioAudit){		
		Multicode multicode=null;
		boolean flag=false;
		try{
			multicode= new Multicode();		
			multicode.setIdMulticode(Integer.parseInt(cc.getCodigoHid()));			
			multicode.setTipoMulticode(this.tipoMulticode(cc.getTipos()));
			multicode.setCodigo(cc.getNombre());
			if (cc.getDesc()!= null && !cc.getDesc().equals("")){
				if (cc.getDesc().length() > 200)
					cc.setDesc(cc.getDesc().substring(0, 199));					
			}
			multicode.setDescripcion(cc.getDesc());;
			multicode.setOrden(10);
			multicode.setEstMulticode(cc.getEstado());
			dao.update(multicode);
			auditoria.register(usuarioAudit.getIdUsuario(), "multicode", "UPDATE", String.valueOf(multicode.getIdMulticode()));
			flag=true;
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;			
	}
	
	/**
	 * Busca los Multicode Activos
	 * @param cc  Formulario de MUlticode
	 * @param operacion tipo de busqueda
	 * @return  Map
	 */
	public Map findActives(MulticodeForm cc,int operacion){
		Map<String,MulticodeDTO> multicodeActivos= new HashMap<String, MulticodeDTO>();
		Multicode multicode=null;
		try{
			String hql = this.obtenerHQL(cc,1);			
			List list= dao.find(hql);
			ArrayList<MulticodeDTO> listx = new ArrayList<MulticodeDTO>();					
			for(int i=0; i<list.size(); i++){
				multicode = (Multicode)list.get(i);				
				
//				MulticodeForm form = (MulticodeForm)list.get(i);			
				MulticodeDTO dto = new MulticodeDTO();
				dto.setCodigo(String.valueOf(multicode.getIdMulticode()));		//idmulticode
				dto.setCodigoHid(this.nombreTipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode())));//nombretipomulticode
				dto.setIdTipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode()));//idtipomulticode
				dto.setTipomulticode(this.tipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode())));//obtejo tipomulticode
				dto.setNombre(multicode.getCodigo());//codigo multicode
				dto.setDesc(multicode.getDescripcion());//descripcionn multicode
				dto.setEstado(multicode.getEstMulticode());//estado
				dto.setOrden(String.valueOf(multicode.getOrden()));
				dto.setTipos(multicode.getTipoMulticode().getIdTipoMulticode().toString());
				if(operacion==1)
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado()  +  "','" + dto.getOrden() +  "','" + dto.getIdTipoMulticode() +  "','" + dto.getCodigoHid() +"')\" > Modificar</a> ");
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "multicodeMto.do?cmd=desactivarMulticode&codigoHid=" + dto.getCodigo()+ "'>Desactivar</a>" );		
				multicodeActivos.put(multicode.getIdMulticode().toString(),dto);
				listx.add(dto);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return multicodeActivos;
	}
	
	/**
	 * Busca los Multicode Inactivos
	 * @param cc  Formulario de MUlticode
	 * @param operacion tipo de busqueda
	 * @return  Map
	 */
	public Map findInactives(MulticodeForm cc,int operacion){	
		Map<String,MulticodeDTO> multicodeInactivos= new HashMap<String, MulticodeDTO>();
		Multicode multicode=null;
		try{
			String hql = this.obtenerHQL(cc,2);			
			List list= dao.find(hql);
			ArrayList<MulticodeDTO> listx = new ArrayList<MulticodeDTO>();					
			for(int i=0; i<list.size(); i++){
				//MulticodeForm form = (MulticodeForm)list.get(i);
				multicode = (Multicode)list.get(i);				
				
				MulticodeDTO dto = new MulticodeDTO();
				dto.setCodigo(String.valueOf(multicode.getIdMulticode()));		//idmulticode
				dto.setCodigoHid(this.nombreTipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode())));//nombretipomulticode
				dto.setIdTipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode()));//idtipomulticode
				dto.setTipomulticode(this.tipoMulticode(String.valueOf(multicode.getTipoMulticode().getIdTipoMulticode())));//obtejo tipomulticode
				dto.setNombre(multicode.getCodigo());//codigo multicode
				dto.setDesc(multicode.getDescripcion());//descripcionn multicode
				dto.setEstado(multicode.getEstMulticode());//estado
				dto.setOrden(String.valueOf(multicode.getOrden()));
				dto.setTipos(multicode.getTipoMulticode().getIdTipoMulticode().toString());
				if(operacion==1)
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado()  +  "','" + dto.getOrden() +  "','" + dto.getIdTipoMulticode() +  "','" + dto.getCodigoHid() +"')\" > Modificar</a> ");
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "multicodeMto.do?cmd=activarMulticode&codigoHid=" + dto.getCodigo()+ "'>Activar</a>" );
						
				multicodeInactivos.put(multicode.getIdMulticode().toString(),dto);
				listx.add(dto);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return multicodeInactivos;
	}
	
	/**
	 * Obtiene un HQL de busqueda
	 * @param cc  Formulario de MUlticode
	 * @param estado Estado de Multicode
	 * @return  Map
	 */
	public String obtenerHQL(MulticodeForm cc,int estado){
		String idMulticode= "";
		idMulticode=cc.getTipos();//this.idTipoMulticode(cc.getTipos());
		String hql = "";
		switch(estado){
			case 1:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='A' and tipoMulticode.estTipoMulti='A' order by tipoMulticode.idTipoMulticode,codigo";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='A' and tipoMulticode.estTipoMulti='A' and lower(codigo) like '"+cc.getNombre().toLowerCase()+"%'  order by tipoMulticode.idTipoMulticode,codigo";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='A' and tipoMulticode.estTipoMulti='A' and lower(codigo) like '"+cc.getNombre().toLowerCase()+"%' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by tipoMulticode.idTipoMulticode,codigo";
				if(cc.getNombre().equals("") && cc.getDesc().equals("") && !cc.getTipos().equals("Seleccione"))
					hql = "From Multicode where estMulticode='A' and tipoMulticode.estTipoMulti='A' and tipoMulticode.idTipoMulticode= '"+ idMulticode + "'  order by tipoMulticode.idTipoMulticode,codigo";
				break;
			case 2:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='I' and tipoMulticode.estTipoMulti='A' order by tipoMulticode.idTipoMulticode,codigo";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='I' and tipoMulticode.estTipoMulti='A' and lower(codigo) like '"+cc.getNombre().toLowerCase()+"%'  order by tipoMulticode.idTipoMulticode,codigo";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From Multicode where estMulticode='I' and tipoMulticode.estTipoMulti='A' and lower(codigo) like '"+cc.getNombre().toLowerCase()+"%' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by tipoMulticode.idTipoMulticode,codigo";
				if(cc.getNombre().equals("") && cc.getDesc().equals("") && !cc.getTipos().equals("Seleccione"))
					hql = "From Multicode where estMulticode='I' and tipoMulticode.estTipoMulti='A' and tipoMulticode.idTipoMulticode= '"+ idMulticode + "' order by tipoMulticode.idTipoMulticode,codigo";
		
		}
		return hql;
	}
	
	/**
	 * Obtiene el nombre de Tipo Multicode
	 * @param idTipoMulticode  Id de tipo MUlticode
	 * @return  String
	 */
	private String nombreTipoMulticode(String idTipoMulticode){
		String nombreTipoCodigo="";		
		TipoMulticode tipoMulticode=null;
		try{
			String hql = "From TipoMulticode where idTipoMulticode = '"+ idTipoMulticode + "'";			
			List list= dao.find(hql);
			tipoMulticode = (TipoMulticode)list.get(0);
			nombreTipoCodigo = tipoMulticode.getNomTipoMulticode();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return nombreTipoCodigo;
	}
	
	/**
	 * Obtiene el Id de Tipo Multicode
	 * @param nombreTipoMulticode  Nombre  de tipo MUlticode
	 * @return  String
	 */
	@SuppressWarnings("unused")
	private String idTipoMulticode(String nombreTipoMulticode){
		String idTipoCodigo="";		
		TipoMulticode tipoMulticode=null;
		try{
			String hql = "From TipoMulticode where lower(nomTipoMulticode) = '"+ nombreTipoMulticode.toLowerCase() + "'";			
			List list= dao.find(hql);
			tipoMulticode = (TipoMulticode)list.get(0);
			idTipoCodigo = tipoMulticode.getIdTipoMulticode().toString();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return idTipoCodigo;
	}
	
	private TipoMulticode tipoMulticode(String idTipoMulticode){
		TipoMulticode tipoMulticode=null;
		try{
			String hql = "From TipoMulticode where idTipoMulticode = '"+ idTipoMulticode + "'";			
			List list= dao.find(hql);
			tipoMulticode = (TipoMulticode)list.get(0);
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return tipoMulticode;
	}
	
	/**
	 * Obtiene los Usuarios Activos en el Sistema 
	 * @return Lista con objetos tipo Usuario
	 */
	public List getEstadoCivil(){
		try {
			String hql = QuerysAdministracion.getEstadoCivil();
			List lst = dao.find(hql);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtiene el Identificador de la Tabla Multicode segun el Parametro enviado de la tabla Parametro, se utiliza para los 
	 * Casos en los que se quiere saber que Id es Catedratico o Estado Civil o Director de laboratorio 
	 * @param Parametro ombre del Parametro en la tabla Parametro 
	 * @return Identificador de Multicode para el Parametro si retorna 0 es que no encontro el Id
	 * @throws Exception 
	 */
	public int getIdMultiParametro(String Parametro)throws Exception{
		int id= 0;
		String hql = QuerysAdministracion.getIdMultiParametro(Parametro);
		List lst = dao.find(hql);
		if (lst != null && lst.size() > 0){
			Multicode multi = (Multicode) lst.get(0);
			id = multi.getIdMulticode();
		}
		return id;
	}

		
	
}
