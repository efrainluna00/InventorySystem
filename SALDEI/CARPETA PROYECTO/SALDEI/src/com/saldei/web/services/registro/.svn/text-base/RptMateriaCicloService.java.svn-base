/**
 * 
 */
package com.saldei.web.services.registro;
import java.util.LinkedList;
import java.util.List;

import com.saldei.hibernate.tables.Ciclo;
import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;
import com.saldei.util.jdbc.JdbcHelper;
import com.saldei.web.form.registro.RptMateriaCicloForm;
/**
 * @author Carlos
 *
 */
public class RptMateriaCicloService {
	private HibDAO dao = new HibDAOImpl();
	JdbcHelper jdbc = new JdbcHelper();
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
	public int hayDatos(RptMateriaCicloForm form){
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
	public String query(RptMateriaCicloForm form){
		String ciclo="";
		String query="";
		ciclo=form.getCiclo();		
		
		if(ciclo.equals("Seleccione"))
			ciclo = this.cicloActivo();		
		query=armarQuery(ciclo);				
		return query;
	}
	private String armarQuery(String ciclo) {
		String query="",constante1="",constante2="";
		constante1+="select tb1.idmateria,'0' || tb1.seccion as seccion,tb1.codmateria,tb1.nombre,tb1.descripcion,tb1.uni_valorativas, (case when tb2.alumnos is null then '0' else tb2.alumnos end) as alumnos from ( select a.id_ciclo,a.id_materia as idmateria,a.id_seccion as seccion,b.cod_materia as codmateria,b.nom_materia as nombre, ";
		constante1+="b.desc_materia as descripcion,cast(b.uni_valorativas as varchar) as uni_valorativas from registro.materia_ciclo a,registro.materia b,registro.ciclo c ";		
		constante2+="and a.id_ciclo=c.id_ciclo and c.est_ciclo='A' and a.id_materia=b.id_materia and b.est_materia='A' order by b.nom_materia,a.id_seccion )tb1 left join ( select id_materia,id_ciclo,id_seccion,count(*) as alumnos from registro.materia_inscrita group by id_materia,id_ciclo,id_seccion )tb2 on tb1.idmateria=tb2.id_materia and tb1.id_ciclo=tb2.id_ciclo and tb1.seccion=tb2.id_seccion ";
		
		query+=constante1;
		query+="where a.id_ciclo='"+ciclo+"' ";
		query+=constante2;
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
			cc = (Ciclo)lstCiclo.get(0);
			ciclo = cc.getIdCiclo();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ciclo;
	}
	
}
