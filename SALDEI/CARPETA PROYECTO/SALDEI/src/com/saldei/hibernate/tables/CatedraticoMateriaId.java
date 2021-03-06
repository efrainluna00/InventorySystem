package com.saldei.hibernate.tables;



/**
 * CatedraticoMateriaId generated by MyEclipse - Hibernate Tools
 */

public class CatedraticoMateriaId  implements java.io.Serializable {


    // Fields    

     private Integer idCargoUsr;
     private MateriaCiclo materiaCiclo;


    // Constructors

    /** default constructor */
    public CatedraticoMateriaId() {
    }

    
    /** full constructor */
    public CatedraticoMateriaId(Integer idCargoUsr, MateriaCiclo materiaCiclo) {
        this.idCargoUsr = idCargoUsr;
        this.materiaCiclo = materiaCiclo;
    }

   
    // Property accessors

    public Integer getIdCargoUsr() {
        return this.idCargoUsr;
    }
    
    public void setIdCargoUsr(Integer idCargoUsr) {
        this.idCargoUsr = idCargoUsr;
    }

    public MateriaCiclo getMateriaCiclo() {
        return this.materiaCiclo;
    }
    
    public void setMateriaCiclo(MateriaCiclo materiaCiclo) {
        this.materiaCiclo = materiaCiclo;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CatedraticoMateriaId) ) return false;
		 CatedraticoMateriaId castOther = ( CatedraticoMateriaId ) other; 
         
		 return ( (this.getIdCargoUsr()==castOther.getIdCargoUsr()) || ( this.getIdCargoUsr()!=null && castOther.getIdCargoUsr()!=null && this.getIdCargoUsr().equals(castOther.getIdCargoUsr()) ) )
 && ( (this.getMateriaCiclo()==castOther.getMateriaCiclo()) || ( this.getMateriaCiclo()!=null && castOther.getMateriaCiclo()!=null && this.getMateriaCiclo().equals(castOther.getMateriaCiclo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdCargoUsr() == null ? 0 : this.getIdCargoUsr().hashCode() );
         result = 37 * result + ( getMateriaCiclo() == null ? 0 : this.getMateriaCiclo().hashCode() );
         return result;
   }   





}