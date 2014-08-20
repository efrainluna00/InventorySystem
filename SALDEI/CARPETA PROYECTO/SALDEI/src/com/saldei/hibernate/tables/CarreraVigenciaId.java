package com.saldei.hibernate.tables;

import java.util.Date;


/**
 * CarreraVigenciaId generated by MyEclipse - Hibernate Tools
 */

public class CarreraVigenciaId  implements java.io.Serializable {


    // Fields    

     private Carrera carrera;
     private Date fechaIni;


    // Constructors

    /** default constructor */
    public CarreraVigenciaId() {
    }

    
    /** full constructor */
    public CarreraVigenciaId(Carrera carrera, Date fechaIni) {
        this.carrera = carrera;
        this.fechaIni = fechaIni;
    }

   
    // Property accessors

    public Carrera getCarrera() {
        return this.carrera;
    }
    
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Date getFechaIni() {
        return this.fechaIni;
    }
    
    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CarreraVigenciaId) ) return false;
		 CarreraVigenciaId castOther = ( CarreraVigenciaId ) other; 
         
		 return ( (this.getCarrera()==castOther.getCarrera()) || ( this.getCarrera()!=null && castOther.getCarrera()!=null && this.getCarrera().equals(castOther.getCarrera()) ) )
 && ( (this.getFechaIni()==castOther.getFechaIni()) || ( this.getFechaIni()!=null && castOther.getFechaIni()!=null && this.getFechaIni().equals(castOther.getFechaIni()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCarrera() == null ? 0 : this.getCarrera().hashCode() );
         result = 37 * result + ( getFechaIni() == null ? 0 : this.getFechaIni().hashCode() );
         return result;
   }   





}