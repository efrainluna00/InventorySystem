package com.saldei.hibernate.tables;

import java.util.Date;


/**
 * AuditoriaId generated by MyEclipse - Hibernate Tools
 */

public class AuditoriaId  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 1L;
     private String idUsuario;
     private Date fechaAccion;
     private String nomTabla;
     private String accion;
     private String llavePrimaria;


    // Constructors

    /** default constructor */
    public AuditoriaId() {
    }

    
    /** full constructor */
    public AuditoriaId(String idUsuario, Date fechaAccion, String nomTabla, String accion, String llavePrimaria) {
        this.idUsuario = idUsuario;
        this.fechaAccion = fechaAccion;
        this.nomTabla = nomTabla;
        this.accion = accion;
        this.llavePrimaria = llavePrimaria;
    }

   
    // Property accessors

    public String getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaAccion() {
        return this.fechaAccion;
    }
    
    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public String getNomTabla() {
        return this.nomTabla;
    }
    
    public void setNomTabla(String nomTabla) {
        this.nomTabla = nomTabla;
    }

    public String getAccion() {
        return this.accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getLlavePrimaria() {
        return this.llavePrimaria;
    }
    
    public void setLlavePrimaria(String llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AuditoriaId) ) return false;
		 AuditoriaId castOther = ( AuditoriaId ) other; 
         
		 return ( (this.getIdUsuario()==castOther.getIdUsuario()) || ( this.getIdUsuario()!=null && castOther.getIdUsuario()!=null && this.getIdUsuario().equals(castOther.getIdUsuario()) ) )
 && ( (this.getFechaAccion()==castOther.getFechaAccion()) || ( this.getFechaAccion()!=null && castOther.getFechaAccion()!=null && this.getFechaAccion().equals(castOther.getFechaAccion()) ) )
 && ( (this.getNomTabla()==castOther.getNomTabla()) || ( this.getNomTabla()!=null && castOther.getNomTabla()!=null && this.getNomTabla().equals(castOther.getNomTabla()) ) )
 && ( (this.getAccion()==castOther.getAccion()) || ( this.getAccion()!=null && castOther.getAccion()!=null && this.getAccion().equals(castOther.getAccion()) ) )
 && ( (this.getLlavePrimaria()==castOther.getLlavePrimaria()) || ( this.getLlavePrimaria()!=null && castOther.getLlavePrimaria()!=null && this.getLlavePrimaria().equals(castOther.getLlavePrimaria()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdUsuario() == null ? 0 : this.getIdUsuario().hashCode() );
         result = 37 * result + ( getFechaAccion() == null ? 0 : this.getFechaAccion().hashCode() );
         result = 37 * result + ( getNomTabla() == null ? 0 : this.getNomTabla().hashCode() );
         result = 37 * result + ( getAccion() == null ? 0 : this.getAccion().hashCode() );
         result = 37 * result + ( getLlavePrimaria() == null ? 0 : this.getLlavePrimaria().hashCode() );
         return result;
   }   





}