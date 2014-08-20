
package com.saldei.web.services.administracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saldei.hibernate.tables.TipoMulticode;
import com.saldei.hibernate.tables.Usuario;
import com.saldei.util.commons.Auditoria;
import com.saldei.util.commons.Constants;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.bean.administracion.TipoMulticodeDto;
import com.saldei.web.form.administracion.TipoMulticodeForm;

public class TipoMulticodeServices {
	private HibDAO dao = new HibDAOImpl();
	private JdbcHelper jdbc= new JdbcHelper();
	private Auditoria auditoria = new Auditoria();
	
	/**
	 * Guarda un nuevo Registro de Tipo MUlticode
	 * @param cc  Formulario de Tipo Multicode
	 * @param usuarioAudit Usuario a guardar para auditoria
	 * @return  True | False
	 */
	public boolean save(TipoMulticodeForm cc, Usuario usuarioAudit){
		TipoMulticode tipoMulticode = null;
		boolean bandera = this.existeTipoMulticode(cc.getNombre());
		if(!bandera)
			return false;
		boolean flag=false;
		try{
			tipoMulticode= new TipoMulticode();
			int codigo = jdbc.getSequence("select max(id_tipo_multicode)+1 from registro.tipo_multicode");
			tipoMulticode.setIdTipoMulticode(codigo);
			tipoMulticode.setNomTipoMulticode(cc.getNombre());
			if (cc.getDesc()!= null && !cc.getDesc().equals("")){
				if (cc.getDesc().length() > 75)
					cc.setDesc(cc.getDesc().substring(0, 74));					
			}
			tipoMulticode.setDescripcion(cc.getDesc());			
			tipoMulticode.setEstTipoMulti("A");
			dao.save(tipoMulticode);
			auditoria.register(usuarioAudit.getIdUsuario(), "tipo_multicode", "INSERT", String.valueOf(tipoMulticode.getIdTipoMulticode()));
			flag=true;
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * Determina si un Tipo Multicode  exite o no 
	 * @param nombre  nombre del Tipo Multicode
	 * @return  True | False
	 */
	private boolean existeTipoMulticode(String nombre){
		String hql = "From TipoMulticode where lower(nomTipoMulticode) = ?";
		String[] params = {nombre.toLowerCase()};
		List list = dao.findByProps(hql, params);
		if(list.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * Actualiza un Registro de Tipo MUlticode
	 * @param cc  Formulario de Tipo Multicode
	 * @param usuarioAudit Usuario a guardar para auditoria
	 * @return  True | False
	 */
	public boolean update(TipoMulticodeForm cc, Usuario usuarioAudit){		
		TipoMulticode tipoMulticode=null;
		boolean flag=false;
		try{
			tipoMulticode= new TipoMulticode();		
			tipoMulticode.setIdTipoMulticode(Integer.parseInt(cc.getCodigoHid()));			
			tipoMulticode.setNomTipoMulticode(cc.getNombre());
			if (cc.getDesc()!= null && !cc.getDesc().equals("")){
				if (cc.getDesc().length() > 75)
					cc.setDesc(cc.getDesc().substring(0, 74));					
			}
			tipoMulticode.setDescripcion(cc.getDesc());;
			tipoMulticode.setEstTipoMulti(cc.getEstado());
			dao.update(tipoMulticode);
			flag=true;
			auditoria.register(usuarioAudit.getIdUsuario(), "tipo_multicode", "UPDATE", String.valueOf(tipoMulticode.getIdTipoMulticode()));
		}
		catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;			
	}

	/**
	 * Guarda un nuevo Registro de Tipo MUlticode Activas
	 * @param cc  Formulario de Tipo Multicode
	 * @param operacion Operacion que determina como seran las listas
	 * @return  Mapa
	 */
	public Map findActives(TipoMulticodeForm cc,int operacion){
		Map<String,TipoMulticodeDto> tiposCodigoActivos= new HashMap<String, TipoMulticodeDto>();
		try{
			String hql = this.obtenerHQL(cc,1);			
			List list= dao.find(hql);
			ArrayList<TipoMulticodeDto> listx = new ArrayList<TipoMulticodeDto>();					
			for(int i=0; i<list.size(); i++){
				TipoMulticode form = (TipoMulticode)list.get(i);			
				TipoMulticodeDto dto = new TipoMulticodeDto();			
				dto.setCodigo(String.valueOf(form.getIdTipoMulticode()));
				dto.setNombre(form.getNomTipoMulticode());
				dto.setDesc(form.getDescripcion());
				dto.setEstado(form.getEstTipoMulti());
				if(operacion==1)
					//dto.setAccion("<a href='" + Constants.contextPath + "tipoMulticodeMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Modificar</a>");
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado()  + "')\" > Modificar</a> ");
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "tipoMulticodeMto.do?cmd=desactivarTipoMulticode&codigoHid=" + dto.getCodigo()+ "'>Desactivar</a>" );
				//dtoAccion = "<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='" + Constants.contextPath + "WebContent/images/icons/mark-done.gif /></a>";
				//dto.setAccion(dtoAccion);
				
				//dto.setAccion("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='/SALDEI/WebContent/images/icons/mark-done.gif /></a>" +" ---- " + "<a href='" + Constants.contextPath + "perfMto.do?cmd=desactivarPerfil&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='/SALDEI/WebContent/images/icons/mark-done.gif /></a>");
//				dto.setAccionDesactivar("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Desactivar perfil</a>");		
				tiposCodigoActivos.put(form.getIdTipoMulticode().toString(),dto);
				listx.add(dto);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return tiposCodigoActivos;
	}
	
	/**
	 * Guarda un nuevo Registro de Tipo MUlticode Inactivas
	 * @param cc  Formulario de Tipo Multicode
	 * @param operacion Operacion que determina como seran las listas
	 * @return  Mapa
	 */
	public Map findInactives(TipoMulticodeForm cc,int operacion){
		Map<String,TipoMulticodeDto> tiposCodigoInactivos= new HashMap<String, TipoMulticodeDto>();
		try{
			String hql = this.obtenerHQL(cc,2);			
			List list= dao.find(hql);
			ArrayList<TipoMulticodeDto> listx = new ArrayList<TipoMulticodeDto>();					
			for(int i=0; i<list.size(); i++){
				TipoMulticode form = (TipoMulticode)list.get(i);			
				TipoMulticodeDto dto = new TipoMulticodeDto();			
				dto.setCodigo(String.valueOf(form.getIdTipoMulticode()));
				dto.setNombre(form.getNomTipoMulticode());
				dto.setDesc(form.getDescripcion());
				dto.setEstado(form.getEstTipoMulti());
				if(operacion==1)
					dto.setAccion("<a href= \"#\" onclick= \"javascript:modificar('" + dto.getCodigo() + "','" + dto.getNombre() + "','" + dto.getDesc() + "','" + dto.getEstado()  + "')\" > Modificar</a> ");					
				if(operacion==2)
					dto.setAccion("<a href='" + Constants.contextPath + "tipoMulticodeMto.do?cmd=ActivarTipoMulticode&codigoHid=" + dto.getCodigo()+ "'>Activar</a>" );
				//dtoAccion = "<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='" + Constants.contextPath + "WebContent/images/icons/mark-done.gif /></a>";
				//dto.setAccion(dtoAccion);
				
				//dto.setAccion("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='/SALDEI/WebContent/images/icons/mark-done.gif /></a>" +" ---- " + "<a href='" + Constants.contextPath + "perfMto.do?cmd=desactivarPerfil&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'><img src='/SALDEI/WebContent/images/icons/mark-done.gif /></a>");
//				dto.setAccionDesactivar("<a href='" + Constants.contextPath + "perfMto.do?cmd=getDataToPutForm&codigo=" + dto.getCodigo()+ "&nombre=" + dto.getNombre()+ "&desc=" + dto.getDesc() + "&estado=" + dto.getEstado() + "'>Desactivar perfil</a>");		
				tiposCodigoInactivos.put(form.getIdTipoMulticode().toString(),dto);
				listx.add(dto);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return tiposCodigoInactivos;
	}
	
	/**
	 * Obtiene el HQL para la busqueda
	 * @param cc  Formulario de Tipo Multicode
	 * @param estado Estado de los registros 
	 * @return  HQL
	 */
	public String obtenerHQL(TipoMulticodeForm cc,int estado){
		String hql = "";
		switch(estado){
			case 1:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='A' order by nomTipoMulticode";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='A' and lower(nomTipoMulticode) like '"+cc.getNombre().toLowerCase()+"%'  order by nomTipoMulticode";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='A' and lower(nomTipoMulticode) like '"+cc.getNombre().toLowerCase()+"%' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by nomTipoMulticode";
				if(cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='A' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by nomTipoMulticode";
				break;
			case 2:
				if(cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='I' order by nomTipoMulticode";
				if(!cc.getNombre().equals("") && cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='I' and lower(nomTipoMulticode) like '"+cc.getNombre().toLowerCase()+"%'  order by nomTipoMulticode";
				if(!cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='I' and lower(nomTipoMulticode) like '"+cc.getNombre().toLowerCase()+"%' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by nomTipoMulticode";
				if(cc.getNombre().equals("") && !cc.getDesc().equals(""))
					hql = "From TipoMulticode where estTipoMulti='I' and lower(descripcion) like '"+cc.getDesc().toLowerCase()+"%'  order by nomTipoMulticode";
		}
		return hql;
	}
	
	/**
	 * Verifica si el Formulario es Valido
	 * @param cc  Formulario de Tipo Multicode
	 * @return  True | False
	 */
	public boolean isNullMtoPerfilForm(TipoMulticodeForm cc){
		if(cc.getNombre().equals("") || cc.getDesc().equals(""))			
			return true;		
		return false;
	}
	
	/**
	 * Elimina un Registro de Tipo mUlticode
	 * @param cc  Formulario de Tipo Multicode
	 * @param usuarioAudit Usuario que ejecuta la eliminacion
	 * @return  True | False
	 */
	public boolean delete(TipoMulticodeForm cc, Usuario usuarioAudit){
		try{
			String hql = "from TipoMulticode where idTipoMulticode = " + cc.getCodigoHid();
			List list = dao.find(hql);
			if(list.size() != 0){
				TipoMulticode multi = (TipoMulticode) list.get(0);
				dao.delete(multi);
				auditoria.register(usuarioAudit.getIdUsuario(), "tipo_multicode", "DELETE", String.valueOf(multi.getIdTipoMulticode()));
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifica la Actualizacion de un Formulario
	 * @param cc  Formulario de Tipo Multicode
	 * @return  True | False
	 */
	public boolean isUpdatableMtoPerfilForm(TipoMulticodeForm cc){
		if(cc.getCodigoHid().equals("") || cc.getCodigo().equals("") || cc.getCodigo().equals(null))			
			return false;		
		return true;
	}
}
