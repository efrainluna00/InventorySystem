package com.saldei.web.services.seguridad;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.util.commons.ElementDto;
import com.saldei.web.form.seguridad.RptAuditoriaForm;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.util.commons.Util;



public class RptAuditoriaService {
	JdbcHelper jdbc = new JdbcHelper();
	Util util = new Util();
	public List usuarioAuditoria(){		
		try{			
			String query=QuerysSeguridad.usuarioAuditoria();			
			List list = jdbc.getQuery(query, null);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List getUsuarioAuditoria() throws Exception{		
		List list   = this.usuarioAuditoria();
		List<ElementDto> lista  = new LinkedList<ElementDto>();
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();			
			ElementDto e = new ElementDto();
			e.setElement1(dyna.get("idusuario").toString());
			e.setElement2(dyna.get("primernom").toString() + " " + dyna.get("primerape").toString() );			
			lista.add(e);
		}
		return lista;
	}
	public List getTablaAuditoria() throws Exception{
		try{			
			String query=tablaAuditoria();		
			List list = jdbc.getQuery(query, null);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public String tablaAuditoria(){
		String sql="select distinct nom_tabla from seguridad.auditoria where accion <>  'cargadatos'";
		return sql;
	}
	public String query(RptAuditoriaForm form){
		String query="";
		String fechaIni="",fechaFin="",idUsuario="",tabla="";
		fechaIni=util.getFechaFormatoYYYYMMDD(form.getFechainicial());
		fechaFin=util.getFechaFormatoYYYYMMDD(form.getFechafinal());
		idUsuario=form.getUsuario();
		tabla=form.getTabla();
		if(idUsuario.equals("Seleccione") && tabla.equals("Seleccione"))
			query=QuerysSeguridad.auditoriaMaestroFechas(fechaIni, fechaFin);
		if(!idUsuario.equals("Seleccione") && tabla.equals("Seleccione"))
			query=QuerysSeguridad.auditoriaMaestroUsuario(fechaIni, fechaFin, idUsuario);
		if(idUsuario.equals("Seleccione") && !tabla.equals("Seleccione"))
			query=QuerysSeguridad.auditoriaMaestroTabla(fechaIni, fechaFin, tabla);
		if(!idUsuario.equals("Seleccione") && !tabla.equals("Seleccione"))
			query=QuerysSeguridad.auditoriaMaestroTodos(fechaIni, fechaFin, tabla, idUsuario);		
		return query;
	}
	
	public int hayDatos(RptAuditoriaForm form) {
		String query = this.query(form);
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
	}

}
