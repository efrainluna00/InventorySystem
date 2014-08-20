package com.saldei.hibernate.tables;



/**
 * DirectorLaboratorioId generated by MyEclipse - Hibernate Tools
 */

public class DirectorLaboratorioId  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 1L;
     private Laboratorio laboratorio;
     private String director;
     private Integer idCargo;


    // Constructors

    /** default constructor */
    public DirectorLaboratorioId() {
    }

    
    /** full constructor */
    public DirectorLaboratorioId(Laboratorio laboratorio, String director, Integer idCargo) {
        this.laboratorio = laboratorio;
        this.director = director;
        this.idCargo = idCargo;
    }

   
    // Property accessors

    public Laboratorio getLaboratorio() {
        return this.laboratorio;
    }
    
    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getDirector() {
        return this.director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getIdCargo() {
        return this.idCargo;
    }
    
    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DirectorLaboratorioId) ) return false;
		 DirectorLaboratorioId castOther = ( DirectorLaboratorioId ) other; 
         
		 return ( (this.getLaboratorio()==castOther.getLaboratorio()) || ( this.getLaboratorio()!=null && castOther.getLaboratorio()!=null && this.getLaboratorio().equals(castOther.getLaboratorio()) ) )
 && ( (this.getDirector()==castOther.getDirector()) || ( this.getDirector()!=null && castOther.getDirector()!=null && this.getDirector().equals(castOther.getDirector()) ) )
 && ( (this.getIdCargo()==castOther.getIdCargo()) || ( this.getIdCargo()!=null && castOther.getIdCargo()!=null && this.getIdCargo().equals(castOther.getIdCargo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLaboratorio() == null ? 0 : this.getLaboratorio().hashCode() );
         result = 37 * result + ( getDirector() == null ? 0 : this.getDirector().hashCode() );
         result = 37 * result + ( getIdCargo() == null ? 0 : this.getIdCargo().hashCode() );
         return result;
   }   





}