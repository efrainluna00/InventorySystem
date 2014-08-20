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
import com.saldei.web.form.registro.RptMateriaInscritaHistoricoForm;
/**
 * @author Carlos
 *
 */
public class RptMateriaInscritaHistoricoService {
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
	public int hayDatos(RptMateriaInscritaHistoricoForm form){
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
	public String query(RptMateriaInscritaHistoricoForm form){
		String cicloInicial="",cicloFinal="",carnet="";
		String query="";
		cicloInicial=this.fechaInicioCiclo(form.getCicloini());
		cicloFinal=this.fechaInicioCiclo(form.getCiclofin());
		carnet=form.getCarnet();
		query=armarQuery(cicloInicial,cicloFinal,carnet);				
		return query;
	}
	private String armarQuery(String fechaInicioCiclo1,String fechaInicioCiclo2,String carnet) {
		String query="",constante1="",constante2="";
		constante1+="select d.num_ciclo,d.anyo_ciclo,a.carnet_estudiante,a.id_materia as idmateria,a.id_ciclo,'0' || a.id_seccion as seccion,c.cod_materia as codmateria,c.nom_materia as nombre,c.desc_materia as descripcion,cast(c.uni_valorativas as varchar) as uni_valorativas,  ";
		constante1+="e.primer_nom,e.primer_ape,(case when e.nombre_restante is null then '' else e.nombre_restante end) as nombrerestante,(case when e.apellido_restante is null then '' else e.apellido_restante end) as apellidorestante,b.oyente,b.ciclo_ingreso,b.anyo_ingreso ";
		constante1+="from registro.materia_inscrita a,registro.estudiante b,registro.materia c,registro.ciclo d,seguridad.usuario e,registro.materia_ciclo f ";
		constante2+="and a.id_ciclo=f.id_ciclo and a.id_materia=f.id_materia and a.id_seccion=f.id_seccion and f.id_materia=c.id_materia and f.id_ciclo=d.id_ciclo and a.carnet_estudiante=b.carnet_estudiante and b.carnet_estudiante=e.id_usuario ";
		constante2+="order by d.anyo_ciclo,d.num_ciclo,c.nom_materia,a.id_seccion";
		query+=constante1;
		query+="where a.id_ciclo in (select distinct id_ciclo from registro.ciclo where fecha_ini_ciclo between '"+fechaInicioCiclo1+"' and '"+fechaInicioCiclo2+"'" +") and a.carnet_estudiante='"+carnet+"' ";
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
