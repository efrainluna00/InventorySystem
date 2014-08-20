/**
 * 
 */
package com.saldei.web.services.registro;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import com.saldei.util.commons.Constants;

import com.saldei.hibernate.querys.QuerysRegistro;
import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.commons.ElementDto;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.registro.RptMateriaCatedratico;

/**
 * @author Carlos
 *
 */
public class RptMateriaCatedraticoService {
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
	public List getCatedratico(){
		List list = new LinkedList();
		String query="select distinct a.id_usuario as idcatedratico,b.primer_nom || ' ' || b.primer_ape as catedratico ";
		query+="from registro.catedratico_materia a, seguridad.usuario b ";
		query+="where a.id_usuario=b.id_usuario and a.est_cat_mat='A' and b.est_usuario='A' ";
		query+="group by a.id_usuario,b.primer_nom,b.primer_ape ";
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String query(RptMateriaCatedratico form){
		String ciclo="",materia="",catedratico="";
		String query="";
		ciclo=form.getCiclo();
		catedratico=form.getCatedratico();
		
		if(ciclo.equals("Seleccione"))
			ciclo = this.cicloActivo();
		
		if(catedratico.equals("Seleccione"))
			query=armarQuery(ciclo,materia,catedratico,1);
		if(!catedratico.equals("Seleccione"))
			query=armarQuery(ciclo,materia,catedratico,3);				
		return query;
	}
	private String armarQuery(String ciclo,String materia,String catedratico,int metodo) {
		String query="",constante1="",constante2="";
		constante1+="select c.anyo_ciclo,c.num_ciclo,f.id_usuario,a.id_ciclo,a.id_materia,cast(a.id_seccion as varchar) as id_seccion,d.cod_materia,d.nom_materia,d.desc_materia,d.uni_valorativas,f.primer_nom,f.primer_ape, ";
		constante1+="(case when f.apellido_restante is null then '' else f.apellido_restante end) as aperestante, ";
		constante1+="(case when f.nombre_restante is null then '' else f.nombre_restante end) as nombrerestante ";
		constante1+="from registro.catedratico_materia a,registro.materia_ciclo b,registro.ciclo c,registro.materia d,seguridad.usuario f,seguridad.cargo_usuario_dei e ";		
		constante2+="and a.id_ciclo=b.id_ciclo and a.id_materia=b.id_materia and a.id_seccion=b.id_seccion and b.id_ciclo=c.id_ciclo and b.id_materia=d.id_materia and c.est_ciclo='A' and d.est_materia='A' ";
		constante2+="and a.id_cargo_usr=e.id_cargo_usr and e.id_usuario=f.id_usuario and f.est_usuario='A' group by f.id_usuario,a.id_materia,a.id_ciclo,a.id_seccion,d.nom_materia,d.uni_valorativas,f.primer_nom,f.primer_ape,f.apellido_restante,f.nombre_restante,d.cod_materia,d.desc_materia,c.anyo_ciclo,c.num_ciclo order by c.anyo_ciclo,c.num_ciclo,f.id_usuario,d.nom_materia,a.id_seccion ";
		switch (metodo) {
			// solo ciclo 
			case 1:
				query+=constante1;
				query+="where a.id_ciclo='"+ciclo+"'  ";
				query+=constante2;			
				break;	
			// solo materia
			case 2:
				query+=constante1;
				query+="where f.id_materia="+ materia+" and ";
				query+="a.id_ciclo='"+ciclo+"'  ";
				query+=constante2;			
				break;
			// solo catedratico
			case 3:
				query+=constante1;
				query+="where f.id_usuario='"+ catedratico+"' and ";
				query+="a.id_ciclo='"+ciclo+"'  " ;
				query+=constante2;
				break;
			// solo seccion y materia
			case 4:
				query+=constante1;
				query+="where f.id_usuario='"+ catedratico+"' and a.id_materia="+materia+" and ";
				query+="a.id_ciclo='"+ciclo+"'  ";
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
	public int hayDatos(RptMateriaCatedratico form){
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
	public static void main(String[] args){
		try{
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public List CatedraticosCiclo(String idCiclo,String parametro){		
		try{			
			String query=QuerysRegistro.catedraticosCiclo(idCiclo,parametro);			
			List list = jdbc.getQuery(query, null);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List getCatedraticosCiclo(RptMateriaCatedratico form) throws Exception{		
		List list   = this.CatedraticosCiclo(form.getCiclo(),Constants.Parametro_CATEDRATICO);
		List<ElementDto> lista  = new LinkedList<ElementDto>();
		for(Iterator iter = list.iterator(); iter.hasNext(); ){
			DynaBean dyna = (DynaBean) iter.next();			
			ElementDto e = new ElementDto();
			e.setElement1(dyna.get("idusuario").toString());
			e.setElement2(dyna.get("nombre").toString());			
			lista.add(e);
		}		
		return lista;
	}
}
