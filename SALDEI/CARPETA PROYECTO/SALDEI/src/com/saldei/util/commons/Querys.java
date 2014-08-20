/**
 * 
 */
package com.saldei.util.commons;
/**
 * @author Carlos
 *
 */
public class Querys {
	public static String perfilSelect="select cast(id_perfil as varchar),nom_perfil,desc_perfil,est_perfil from seguridad.perfil order by est_perfil,nom_perfil";
	public static String opcionSelect="	select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,url_opcion,orden,metodo,is_separador,est_opcion from seguridad.opcion order by est_opcion,nom_opcion";
	public static String usuarioSelect="select id_usuario,psw_usuario,primer_nom,primer_ape,nombre_restante,apellido_restante,fecha_nac,direccion_part,email,telefono_casa,telefono_cel,telefono_trabajo,est_usuario from seguridad.usuario order by est_usuario,primer_ape,primer_nom";
	public static String materiaSelect="select cast(id_materia as varchar) as id_materia,cod_materia,nom_materia,desc_materia,est_materia from registro.materia order by est_materia,nom_materia";
	public static String laboratorioSelect="select cast(id_laboratorio as varchar) as id_laboratorio,nombre_laboratorio,abrev_laboratorio,cast(num_filas as varchar) as num_filas,cast(num_columnas as varchar) as num_columnas,est_laboratorio from registro.laboratorio  order by est_laboratorio,nombre_laboratorio";
	public static String medidaSelect="select cast(a.id_medida as varchar) as id_medida ,cast(a.id_tipo_medida as varchar) as id_tipo_medida,a.nom_medida as nom_medida,a.abv_medida as abv_medida,a.factor_conv as factor_conv,a.est_medida as est_medida, b.nom_tipo_medida as nom_tipo_medida from registro.medida a,registro.tipo_medida b where a.id_tipo_medida=b.id_tipo_medida ";
	public static String multicodeSelect="select cast(a.id_multicode as varchar) as id_multicode,cast(a.id_tipo_multicode as varchar) as id_tipo_multicode,a.codigo,a.descripcion,cast(a.orden as varchar) as orden,a.est_multicode,b.nom_tipo_multicode from registro.multicode a,registro.tipo_multicode b where a.id_tipo_multicode=b.id_tipo_multicode ";
	public static String tipoMedidaSelect="select cast(id_tipo_medida as varchar) as id_tipo_medida,nom_tipo_medida,est_tipo_medida from registro.tipo_medida  ";
	public static String tipoMulticodeSelect="select cast(id_tipo_multicode as varchar) as id_tipo_multicode,nom_tipo_multicode,descripcion,est_tipo_multi from registro.tipo_multicode ";
	public static String parametroSelect="select cast(id_parametro as varchar) as id_parametro,nom_parametro,descripcion,valor from registro.parametro order by nom_parametro";
	public static String materiasInscritas="";
	
	public String perfilParametros(String parametro){
		String select="";
		select="select cast(id_perfil as varchar),nom_perfil,desc_perfil,est_perfil from seguridad.perfil where est_perfil=";
		select+="'"+parametro+ "'";
		select+=" order by est_perfil,nom_perfil";
		return select;
	}
	public String opcionParametros(String parametro){
		String select="";
		select="select id_opcion,id_opcion_padre,nom_opcion,desc_opcion,url_opcion,orden,metodo,is_separador,est_opcion from seguridad.opcion where est_opcion=";
		select+="'"+parametro+ "'";
		select+=" order by est_opcion,nom_opcion";
		return select;
	}
	public String usuarioParametros(String parametro){
		String select="";
		select="select id_usuario,psw_usuario,primer_nom,primer_ape,nombre_restante,apellido_restante,fecha_nac,direccion_part,email,telefono_casa,telefono_cel,telefono_trabajo,est_usuario from seguridad.usuario where est_usuario=";
		select+="'"+parametro+ "'";
		select+=" order by est_usuario,primer_ape,primer_nom";
		return select;
	}
	public String materiaParametros(String parametro){
		String select="";
		select="select cast(id_materia as varchar) as id_materia,cod_materia,nom_materia,desc_materia,est_materia from registro.materia where est_materia=";
		select+="'"+parametro+ "'";
		select+="  order by est_materia,nom_materia";
		return select;
	}
	public String laboratorioParametros(String parametro){
		String select="";
		select="select cast(id_laboratorio as varchar) as id_laboratorio,nombre_laboratorio,abrev_laboratorio,cast(num_filas as varchar) as num_filas,cast(num_columnas as varchar) as num_columnas,est_laboratorio from registro.laboratorio  where est_laboratorio=";
		select+="'"+parametro+ "'";
		select+=" order by est_laboratorio,nombre_laboratorio";
		return select;
	}
	public String medidaParametros(String parametro){
		String select="";
		select="select cast(a.id_medida as varchar),cast(a.id_tipo_medida as varchar),a.nom_medida,a.abv_medida,a.factor_conv,a.est_medida, b.nom_tipo_medida from registro.medida a,registro.tipo_medida b where a.id_tipo_medida=b.id_tipo_medida and b.est_tipo_medida='A' and a.est_medida=";
		select+="'"+parametro+ "'";
		select+=" order by est_medida,id_tipo_medida,nom_tipo_medida,nom_medida";
		return select;
	}
	public String multicodeParametros(String parametro){
		String select="";
		select="select a.id_multicode,a.id_tipo_multicode,a.codigo,a.descripcion,a.orden,a.est_multicode,b.nom_tipo_multicode from registro.multicode a,registro.tipo_multicode b where a.id_tipo_multicode=b.id_tipo_multicode and b.est_tipo_multi='A' and a.est_multicode=";
		select+="'"+parametro+ "'";
		select+="  order by a.est_multicode,b.id_tipo_multicode,b.nom_tipo_multicode,a.codigo";
		return select;
	}
	public String tipoMedidaParametros(String parametro){
		String select="";
		select="select cast(id_tipo_medida as varchar),nom_tipo_medida,est_tipo_medida from registro.tipo_medida where est_tipo_medida=";
		select+="'"+parametro+ "'";
		select+="  order by est_tipo_medida,nom_tipo_medida";
		return select;
	}
	public String tipoMulticodeParametros(String parametro){
		String select="";
		select="select id_tipo_multicode,nom_tipo_multicode,descripcion,est_tipo_multi from registro.tipo_multicode where est_tipo_multi=";
		select+="'"+parametro+ "'";
		select+=" order by est_tipo_multi,nom_tipo_multicode";
		return select;
	}
	public String laboratorioActivoVacio(){
		String select="";
		select="select  distinct '' AS id_laboratorio,'' AS nombre_laboratorio,'' AS abrev_laboratorio,'' AS num_filas,'' AS num_columnas,'A' AS est_laboratorio ";
		return select;	
	}
	public String laboratorioInactivoVacio(){
		String select="";
		select="select  distinct '' AS id_laboratorio,'' AS nombre_laboratorio,'' AS abrev_laboratorio,'' AS num_filas,'' AS num_columnas,'I' AS est_laboratorio ";
		return select;	
	}
	public String materiaActivaVacio(){
		String select="";
		select="select distinct '' AS id_materia,'' AS cod_materia,'' AS nom_materia,'' AS desc_materia,'A' AS est_materia from registro.materia ";
		return select;	
	}
	public String materiaInactivaVacio(){
		String select="";
		select="select distinct '' AS id_materia,'' AS cod_materia,'' AS nom_materia,'' AS desc_materia,'I' AS est_materia from registro.materia  ";
		return select;	
	}
	public String parametroVacio(){
		String select="";
		select="select '' as id_parametro,'' as nom_parametro,'' as descripcion,'' as valor ";
		return select;	
	}
	public String medidaActivaVacio(){
		String select="";
		select="select  '' as id_medida,'' as id_tipo_medida,'' as nom_medida,'' as abv_medida,'' as factor_conv,'A' as est_medida,'' as nom_tipo_medida ";
		return select;	
	}
	public String medidaInactivaVacio(){
		String select="";
		select="select  '' as id_medida,'' as id_tipo_medida,'' as nom_medida,'' as abv_medida,'' as factor_conv,'I' as est_medida,'' as nom_tipo_medida  ";
		return select;	
	}
	public String multicodeActivoVacio(){
		String select="";
		select="select '' as id_multicode,'' as id_tipo_multicode,'' as codigo,'' as descripcion,'' as orden,'A' as est_multicode,'' as nom_tipo_multicode ";
		return select;	
	}
	public String multicodeInactivoVacio(){
		String select="";
		select="select '' as id_multicode,'' as id_tipo_multicode,'' as codigo,'' as descripcion,'' as orden,'I' as est_multicode,'' as nom_tipo_multicode  ";
		return select;	
	}
	public String tipoMedidaActivoVacio(){
		String select="";
		select="select '' as id_tipo_medida,'' as nom_tipo_medida,'A' as est_tipo_medida  ";
		return select;	
	}
	public String tipoMedidaInactivoVacio(){
		String select="";
		select="select '' as id_tipo_medida,'' as nom_tipo_medida,'I' as est_tipo_medida ";
		return select;	
	}
	public String tipoMulticodeActivoVacio(){
		String select="";
		select="select '' as id_tipo_multicode,'' as nom_tipo_multicode, '' as descripcion,'A' as est_tipo_multi ";
		return select;	
	}
	public String tipoMulticodeInactivoVacio(){
		String select="";
		select="select '' as id_tipo_multicode,'' as nom_tipo_multicode, '' as descripcion,'I' as est_tipo_multi ";
		return select;	
	}
	public String perfilActivoVacio(){
		String select="";
		select="select '' as id_perfil,'' as nom_perfil,'' as desc_perfil,'A' as est_perfil";
		return select;
	}
	public String perfilInactivoVacio(){
		String select="";
		select="select '' as id_perfil,'' as nom_perfil,'' as desc_perfil,'I' as est_perfil";
		return select;
	}
	
	
	
}
