package com.saldei.web.services.seguridad;
import java.util.LinkedList;
import java.util.List;
import com.saldei.util.commons.Util;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.seguridad.RptUsuariosForm;
import com.saldei.hibernate.querys.QuerysSeguridad;

public class RptUsuarios {
	JdbcHelper jdbc = new JdbcHelper();
	Util util  = new Util();
	
	public String query(RptUsuariosForm form){
		String query="";
		String fechaInicial=form.getFechainicial(),fechaFinal=form.getFechafinal(),estado=form.getEstado(),tipoUsuario=form.getTipousuario();		
		int flagEstado=0,flagTipoUsuario=0;
		if(estado.equals("A")) flagEstado=1;
		if(estado.equals("I")) flagEstado=2;
		if(estado.equals("est_usuario")) flagEstado=3;
		if(!tipoUsuario.equals("seleccione")){
			if(tipoUsuario.equals("1")) flagTipoUsuario=1;
			if(tipoUsuario.equals("2")) flagTipoUsuario=2;
			if(tipoUsuario.equals("3")) flagTipoUsuario=3;
		}
		
		if(fechaInicial.equals("") && fechaFinal.equals("") && tipoUsuario.equals("seleccione") && !estado.equals("seleccione"))
			query = QuerysSeguridad.usuariosSistema(flagEstado);
		
		if(!fechaInicial.equals("") && !fechaFinal.equals("") && tipoUsuario.equals("seleccione") && !estado.equals("seleccione"))
			query=QuerysSeguridad.usuariosSistema(util.getFechaFormato(form.getFechainicial()),util.getFechaFormato(form.getFechafinal()),flagEstado);
		
		if(fechaInicial.equals("") && fechaFinal.equals("") && !tipoUsuario.equals("seleccione") && !estado.equals("seleccione"))
			query=QuerysSeguridad.usuariosSistema(flagTipoUsuario, flagEstado);
		
		if(!fechaInicial.equals("") && !fechaFinal.equals("") && !tipoUsuario.equals("seleccione") && !estado.equals("seleccione"))
			query=QuerysSeguridad.usuariosSistema(util.getFechaFormato(form.getFechainicial()),util.getFechaFormato(form.getFechafinal()),flagTipoUsuario,flagEstado);
		
		return query;
	}
	
	public int hayDatos(RptUsuariosForm form){
		List list=new LinkedList();		
		String query="";
		query = this.query(form);				
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();
	}
}
