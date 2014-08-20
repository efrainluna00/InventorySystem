package com.saldei.web.services.seguridad;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;



import com.saldei.hibernate.querys.QuerysSeguridad;
import com.saldei.util.commons.Util;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.registro.RptEstudianteMateriaForm;
import com.saldei.web.form.seguridad.RptPerfilUsuarioForm;

public class RptPerfilUsuarioService {
	JdbcHelper jdbc = new JdbcHelper();
	private HibDAO dao = new HibDAOImpl();
	Util util = new Util();
	public String query(RptPerfilUsuarioForm form){
		String query="";
		String tipoUsuario="",idUsuario="";
		tipoUsuario=form.getTipousuario();
		idUsuario=form.getIdusuario();
		
		if(!idUsuario.equals("") && tipoUsuario.equals("seleccione"))
			query=QuerysSeguridad.perfilUsuario(idUsuario);		
		if(idUsuario.equals("") && !tipoUsuario.equals("seleccione"))
			query=QuerysSeguridad.perfilUsuario(Integer.parseInt(tipoUsuario));
		if(!idUsuario.equals("") && !tipoUsuario.equals("seleccione"))
			query = QuerysSeguridad.perfilUsuario(idUsuario, Integer.parseInt(tipoUsuario));
				
		return query;
	}
	public String query2(RptPerfilUsuarioForm form){
		String query="";
		String perfil=form.getPerfil();
		query = "select cast(a.id_perfil as varchar) as id_perfil,a.nom_perfil,b.nom_opcion,desc_opcion ";
		query+= "from seguridad.perfil a, seguridad.opcion b, seguridad.opcion_perfil c ";
		query+=	"where a.id_perfil="+perfil+ " and a.id_perfil=c.id_perfil and b.id_opcion=c.id_opcion ";
		query+=	"order by b.id_opcion";
		return query;
	}
	public String query3(RptPerfilUsuarioForm form){
		String query="";
		String tipoUsuario="";
		tipoUsuario=form.getTipousuario();	
		query = QuerysSeguridad.informacionPersonal(Integer.parseInt(tipoUsuario));				
		return query;	
	}
	
	public int hayDatos(RptPerfilUsuarioForm form) {
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
	public int hayDatos2(RptPerfilUsuarioForm form) {
		String perfil = form.getPerfil();
		String query = "select * from seguridad.opcion_perfil where id_perfil="+perfil;
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
	}
	public int hayDatos3(RptPerfilUsuarioForm form) {
		String query = this.query3(form);
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
	}
	public List getPerfiles(){
		List lstDei = new LinkedList();
		try {
			String hql = "from Perfil order by nomPerfil";
			lstDei = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstDei;		
	}
	public List getOpciones(){
		List lstDei = new LinkedList();
		try {
			String hql = "from Opcion where idOpcion in (select distinct idOpcionPadre from Opcion) order by idOpcion";
			lstDei = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstDei;		
	}
	public String recuperarOpcion(String idOpcion){
		String opcion = idOpcion;
		String nombreOpcion="";
		String query = "select nom_opcion from seguridad.opcion where id_opcion="+opcion;
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
			Iterator it = list.iterator();
			while(it.hasNext()){
				DynaBean dyna = (DynaBean) it.next();
				nombreOpcion  = dyna.get("nom_opcion").toString();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return nombreOpcion.toUpperCase();		
	}	
	public int hayDatos(){
		List list=new LinkedList();
		String query=this.query();
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
	}
	
	public String query(){		
		String query="select distinct cast(b.id_laboratorio as varchar) as id_laboratorio,b.nombre_laboratorio,u.id_usuario,u.primer_nom, ";
			   query+="u.primer_ape,cast(a.fecha_ini as varchar) as fecha_ini,cast(a.fecha_fin as varchar) as fecha_fin,u.email,u.telefono_trabajo, ";
			   query+="(case when u.apellido_restante is null then '' else u.apellido_restante end) as apellidoRestante, ";
			   query+="(case when u.nombre_restante is null then '' else u.nombre_restante end) as nombreRestante ";
			   query+="from registro.director_laboratorio a,registro.laboratorio b,seguridad.cargo_usuario_dei c,seguridad.usuario u, ";
			   query+="seguridad.usuario_dei d ";
			   query+="where a.id_laboratorio = b.id_laboratorio and a.id_cargo_usr = c.id_cargo_usr and c.id_usuario=u.id_usuario ";
			   query+="order by b.nombre_laboratorio,u.primer_ape ";				
				
		return query;
	}
	
}
