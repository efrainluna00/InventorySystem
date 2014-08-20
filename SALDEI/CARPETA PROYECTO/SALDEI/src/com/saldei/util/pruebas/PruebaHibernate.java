/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.pruebas;

import java.util.List;

import com.saldei.util.hibernate.dao.HibDAO;
import com.saldei.util.hibernate.dao.HibDAOImpl;

public class PruebaHibernate {

	public static void main(String args[])throws Exception{
		try{
			HibDAO dao = new HibDAOImpl();
			String hql="select new list (a.id.idSeccion || '-' || a.id.materia.idMateria as idMateria  , " +
			" '0' || a.id.idSeccion || ' - ' || d.nomMateria as NombreMateria) " +
			"from MateriaCiclo a,Ciclo b,Materia d " +
			"where a.id.ciclo.idCiclo= ?  " +
			"and a.id.ciclo.idCiclo=b.idCiclo " +
			"and b.estCiclo='A' " +										
			"and a.id.materia.idMateria=d.idMateria " +
			"and d.estMateria='A' " +
			"order by d.nomMateria,a.id.idSeccion";
	Object[] params = {"012008"};
			List list = dao.findByProps(hql, params);
			System.out.println(list.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}//main
}//class
