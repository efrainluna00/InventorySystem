/**
 * 
 */
package com.saldei.web.services.registro;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.registro.RptEstudianteMateriaForm;
import com.saldei.hibernate.querys.QuerysRegistro;

/**
 * @author Carlos
 *
 */
public class RptEstudianteMateriaService {
	private HibDAO dao = new HibDAOImpl();
	JdbcHelper jdbc = new JdbcHelper();
	public List getMaterias(){
		List lstDei = new LinkedList();
		try {
			String hql = "from Materia where estMateria='A' order by nomMateria";
			lstDei = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstDei;		
	}
	public List getCiclo(){
		List lstDei = new LinkedList();
		try {
			String hql = "from Ciclo where estCiclo='A' order by numCiclo,anyoCiclo";
			lstDei = dao.find(hql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lstDei;		
	} 
	public List getSeccion(){
		List list = new LinkedList();
		String query="select distinct id_seccion from registro.seccion_x_materia";
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int hayDatos(RptEstudianteMateriaForm form){
		List list=new LinkedList();
		String query=this.query(form);
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
	}	
	
	public String query(RptEstudianteMateriaForm form){
		String ciclo="",materia="",seccion="";
		String query="";
		ciclo=form.getCiclo();
		//materia=form.getMateria().substring(0,form.getMateria().indexOf("-"));
		//seccion=form.getSeccion().substring(form.getMateria().indexOf("-")+1,form.getMateria().length());
		//System.out.println("materia "+materia);
		//System.out.println(seccion);
		if(!form.getMateria().equals("Seleccione")){
			materia=form.getMateria().substring(0,form.getMateria().indexOf("-"));
			seccion=form.getMateria().substring(form.getMateria().indexOf("-")+1,form.getMateria().length());	
		}
		else 
			materia=form.getMateria();
		
		
		
		
		
		if(ciclo.equals("Seleccione"))
			ciclo = this.cicloActivo();
		
		if(materia.equals("Seleccione"))
			query=armarQuery(ciclo,materia,seccion,1);
		if(!materia.equals("Seleccione") )
			query=armarQuery(ciclo,materia,seccion,2);
//		if(materia.equals("Seleccione") && !seccion.equals("Seleccione"))
//			query=armarQuery(ciclo,materia,seccion,3);
//		if(!materia.equals("Seleccione") && !seccion.equals("Seleccione"))
//			query=armarQuery(ciclo,materia,seccion,4);
				
				
		return query;
	}
	private String armarQuery(String ciclo,String materia,String seccion,int metodo) {
		String query="",constante1="",constante2="";
		constante1+="select tb1.carnet as carnet,tb1.cic as ciclo,tb1.idmat as idmateria,tb1.codmat as codigomateria,tb1.mat as nombremateria, '0' || '' || tb1.sec as seccion,";
		constante1+="tb1.primernom as primernom,(case when tb1.nomrestante is null then '' else tb1.nomrestante end) as nombreresto,tb1.primerape as primerape,";
		constante1+="(case when tb1.aperestante is null then '' else tb1.aperestante end) as aperest,tb1.emailestudiante,tb2.iduser,tb2.primernom || ' ' || tb2.primerape as NombreCatedratico ";
		constante1+="from ( select a.carnet_estudiante as carnet,a.id_ciclo as cic,a.id_materia as idmat,a.id_seccion as sec,";
		constante1+="c.cod_materia as codmat,c.nom_materia as mat,d.primer_nom as primernom,d.primer_ape as primerape,d.nombre_restante as nomrestante,d.apellido_restante as aperestante,d.email as emailestudiante ";
		constante1+="from registro.materia_inscrita a,registro.ciclo b,registro.materia c,seguridad.usuario d,registro.materia_ciclo e ";
		constante2+="and a.id_ciclo=e.id_ciclo and a.id_materia=e.id_materia and a.id_seccion=e.id_seccion and e.id_materia=c.id_materia and c.est_materia='A' and e.id_ciclo=b.id_ciclo and b.est_ciclo='A' and a.carnet_estudiante=d.id_usuario and d.est_usuario='A')tb1 left join ( select a.id_usuario as iduser,primer_nom as primernom, primer_ape as primerape,c.id_materia as idmateria, ";
		constante2+="c.id_ciclo as idciclo,c.id_seccion as idseccion ";
		constante2+="from seguridad.usuario a, seguridad.cargo_usuario_dei b, registro.catedratico_materia c ";
		constante2+="where c.id_cargo_usr=b.id_cargo_usr and a.id_usuario=b.id_usuario ";
		constante2+=")tb2 on tb1.cic=tb2.idciclo and tb1.idmat=tb2.idmateria and tb1.sec=tb2.idseccion order by idmateria,seccion,primerape	";
		switch (metodo) {
			// solo ciclo 
			case 1:
				query+=constante1;
				query+="where a.id_ciclo='"+ciclo+"' and a.est_mat_inscrita='A' ";
				query+=constante2;			
				break;	
			// solo materia
			case 2:
				query+=constante1;
				query+="where a.id_materia="+ materia+" and a.id_seccion="+seccion+" and ";
				query+="a.id_ciclo='"+ciclo+"' and a.est_mat_inscrita='A' ";
				query+=constante2;			
				break;
			// solo seccion
			case 3:
				query+=constante1;
				query+="where a.id_seccion="+ seccion+" and ";
				query+="a.id_ciclo='"+ciclo+"' and a.est_mat_inscrita='A' " ;
				query+=constante2;
				break;
			// solo seccion y materia
			case 4:
				query+=constante1;
				query+="where a.id_seccion="+ seccion+" and a.id_materia="+materia+" and ";
				query+="a.id_ciclo='"+ciclo+"' and a.est_mat_inscrita='A' ";
				query+=constante2;
				break;
	
			default:
				break;
		}
		System.out.println(query);
		return query;
	}
	
	public String cicloActivo(){
		String ciclo="";
		Ciclo cc = null;
		List lstCiclo = new LinkedList();
		try {
			String hql = "from Ciclo where estCiclo='A' and cicloActivo='S'";
			lstCiclo = dao.find(hql);
			//cc = new Ciclo();
			cc = (Ciclo)lstCiclo.get(0);
			ciclo = cc.getIdCiclo();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ciclo;
	}
	public static void main(String[] args){
		try{
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public List MateriasCiclo(String idCiclo){		
		try{			
			String query=QuerysRegistro.materiasXCiclo(idCiclo);			
			List list = jdbc.getQuery(query, null);
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List SeccionMateria(String idCiclo,String idMateria){		
		try{			
			String query=QuerysRegistro.seccionXMateriaXciclo(idCiclo, idMateria);			
			List list = jdbc.getQuery(query, null);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List getMateriaCiclo(RptEstudianteMateriaForm form) throws Exception{		
		List list   = this.MateriasCiclo(form.getCiclo());
		List<ElementDto> lista  = new LinkedList<ElementDto>();		
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();			
			ElementDto e = new ElementDto();
			e.setElement1(dyna.get("id_materia").toString());
			e.setElement2(dyna.get("nom_materia").toString());
			lista.add(e);
		}
		return lista;
	}
	public List getSeccionXMateria(RptEstudianteMateriaForm form) throws Exception{		
		List list   = this.SeccionMateria(form.getCiclo(),form.getMateria());
		List<ElementDto> lista  = new LinkedList<ElementDto>();
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();			
			ElementDto e = new ElementDto();
			e.setElement1(dyna.get("id_seccion").toString());			
			lista.add(e);
		}
		return lista;
	}
	public String recuperarMateria(String idMateria){
		String materia = idMateria;		
		String nombreMateria="";
		String query = "select nom_materia from registro.materia where id_materia="+materia;
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
			Iterator it = list.iterator();
			while(it.hasNext()){
				DynaBean dyna = (DynaBean) it.next();
				nombreMateria  = dyna.get("nom_materia").toString();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return nombreMateria.toUpperCase();		
	}	
	
}
