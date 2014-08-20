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
import com.saldei.web.form.registro.RptRequerimientoXMateriaForm;
import com.saldei.hibernate.querys.QuerysRegistro;

/**
 * @author Carlos
 *
 */
public class RptRequerimientoXMateriaService {
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
	public String query(RptRequerimientoXMateriaForm form){
		String ciclo="",materia="";
		String query="";
		ciclo=form.getCiclo();		
		materia=form.getMateria().substring(0,form.getMateria().indexOf("-"));
		if(materia.equals("Seleccione"))
			query=QuerysRegistro.requerimientosXMateria(ciclo);
		else
			query=QuerysRegistro.requerimientosXMateria(ciclo, materia);		
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
	public int hayDatos(RptRequerimientoXMateriaForm form) {
		 String ciclo="",materia="";
		String query="";
		ciclo=form.getCiclo();
		materia=form.getMateria().substring(0,form.getMateria().indexOf("-")); 
		if(materia.equals("Seleccione"))
			query=QuerysRegistro.requerimientosXMateria(ciclo);
		else
			query=QuerysRegistro.requerimientosXMateria(ciclo, materia);
		List list=new LinkedList();		
		try {
			list = jdbc.getQuery(query, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();		
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
	public List getMateriaCiclo(RptRequerimientoXMateriaForm form) throws Exception{		
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
