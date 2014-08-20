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
import com.saldei.web.form.registro.RptMateriaCicloHistoricoForm;
/**
 * @author Carlos
 *
 */
public class RptMateriaCicloHistoricoService {
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
	public int hayDatos(RptMateriaCicloHistoricoForm form){
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
	public String query(RptMateriaCicloHistoricoForm form){
		String cicloInicial="",cicloFinal="";
		String query="";
		cicloInicial=this.fechaInicioCiclo(form.getCicloini());
		cicloFinal=this.fechaInicioCiclo(form.getCiclofin());	
		query=armarQuery(cicloInicial,cicloFinal);				
		return query;
	}
	private String armarQuery(String fechaInicioCiclo1,String fechaInicioCiclo2) {
		String query="",constante1="",constante2="";
		constante1+="select c.id_ciclo,c.num_ciclo,c.anyo_ciclo,a.id_materia as idmateria,'0'  || a.id_seccion as seccion,b.cod_materia as codmateria,b.nom_materia as nombre,b.desc_materia as descripcion,cast(b.uni_valorativas as varchar) as uni_valorativas  ";
		constante1+="from registro.materia_ciclo a,registro.materia b,registro.ciclo c ";		
		constante2+="and a.id_ciclo=c.id_ciclo and a.id_materia=b.id_materia ";
		constante2+=" order by c.anyo_ciclo,c.num_ciclo,b.nom_materia,a.id_seccion";
		query+=constante1;
		query+="where a.id_ciclo in (select distinct id_ciclo from registro.ciclo where fecha_ini_ciclo between '"+fechaInicioCiclo1+"' and '"+fechaInicioCiclo2+"'" +") " ;
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
	public String fechaInicioCiclo(String idCiclo){
		String ciclo="";
		Ciclo cc = null;
		List lstCiclo = new LinkedList();
		try {
			String hql = "from Ciclo where idCiclo='"+idCiclo+"'";
			lstCiclo = dao.find(hql);
			cc = (Ciclo)lstCiclo.get(0);
			ciclo = cc.getFechaIniCiclo().toString();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ciclo;
	}
	
}
