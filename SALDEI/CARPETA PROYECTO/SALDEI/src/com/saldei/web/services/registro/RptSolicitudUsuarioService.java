/**
 * 
 */
package com.saldei.web.services.registro;
import java.util.LinkedList;
import java.util.List;
import com.saldei.util.commons.Util;


import com.saldei.util.jdbc.JdbcHelper;

import com.saldei.web.form.registro.RptSolicitudUsuarioForm;
/**
 * @author Carlos
 *
 */
public class RptSolicitudUsuarioService {
	JdbcHelper jdbc = new JdbcHelper();
	Util util  = new Util();
	
	public String query(RptSolicitudUsuarioForm form){
		String fechaInicial="",fechaFinal="";
		fechaInicial=form.getFechainicial();
		fechaFinal=form.getFechafinal();
		String query="";
		if(fechaInicial.equals("") && fechaFinal.equals("")){					
			query+="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante,tb2.email,cast(tb2.fechasoli as varchar) as fechasoli ";
			query+="from ( select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei union select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo )tb1 ";
			query+="inner join ( select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante,(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante,a.email as email,a.fecha_solicitud as fechasoli from seguridad.usuario a   ";
			query+="where a.est_usuario='S' ";
			query+=")tb2 on tb1.idusuario=tb2.idusuario order by tb1.idTipoUsuario,tb2.fechasoli ";
		}
		else
		{
			fechaInicial=util.getFechaFormato(form.getFechainicial());
			fechaFinal=util.getFechaFormato(form.getFechafinal());		
			query+="select tb1.idusuario,tb1.idTipoUsuario,tb2.primernom,tb2.primerape,tb2.nombrerestante,tb2.apellidorestante,tb2.email,cast(tb2.fechasoli as varchar) as fechasoli ";
			query+="from ( select codigo_empleado as idusuario,'1' as idTipoUsuario from seguridad.usuario_dei union select id_usuario as idusuario,'2' as idTipoUsuario from seguridad.usuario_externo union select carnet_estudiante as idusuario,'3' as idTipoUsuario from registro.estudiante )tb1 ";
			query+="inner join ( select a.id_usuario as idusuario,a.primer_nom as primernom,a.primer_ape as primerape,(case when a.nombre_restante is null then '' else a.nombre_restante end) as nombrerestante,(case when a.apellido_restante is null then '' else a.apellido_restante end) as apellidorestante,a.email as email,a.fecha_solicitud as fechasoli from seguridad.usuario a   ";
			query+="where a.est_usuario='S' and a.fecha_solicitud between '" + fechaInicial+"' and '"+fechaFinal+"'";
			query+=")tb2 on tb1.idusuario=tb2.idusuario order by tb1.idTipoUsuario,tb2.fechasoli ";	
		}
		
		return query;
	}
	public int hayDatos(RptSolicitudUsuarioForm form){
		List list=new LinkedList();
		String fechaInicial="",fechaFinal="";
		String query="";		
		if(fechaInicial.equals("") && fechaFinal.equals("")){
			query+="select id_usuario as idusuario,primer_nom as primernom,primer_ape as primerape,nombre_restante as nombrerest,apellido_restante as aperest,email as email,fecha_solicitud as fechasolicitud from seguridad.usuario ";
			query+="where est_usuario='S'";
		}
		else{
			fechaInicial=util.getFechaFormato(form.getFechainicial());
			fechaFinal=util.getFechaFormato(form.getFechafinal());
			query+="select id_usuario as idusuario,primer_nom as primernom,primer_ape as primerape,nombre_restante as nombrerest,apellido_restante as aperest,email as email,fecha_solicitud as fechasolicitud from seguridad.usuario ";
			query+="where est_usuario='S' and fecha_solicitud between '"+ fechaInicial+"' and '"+fechaFinal+"'";	
		}
				
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();
	}
}
