package com.saldei.hibernate.tables;

import java.util.Date;


/**
 * CarreraVigencia generated by MyEclipse - Hibernate Tools
 */

public class CarreraVigencia  implements java.io.Serializable {


    // Fields    

     private CarreraVigenciaId id;
     private Date fechaFin;


    // Constructors

    /** default constructor */
    public CarreraVigencia() {
    }

	/** minimal constructor */
    public CarreraVigencia(CarreraVigenciaId id) {
        this.id = id;
    }
    
    /** full constructor */
    public CarreraVigencia(CarreraVigenciaId id, Date fechaFin) {
        this.id = id;
        this.fechaFin = fechaFin;
    }

   
    // Property accessors

    public CarreraVigenciaId getId() {
        return this.id;
    }
    
    public void setId(CarreraVigenciaId id) {
        this.id = id;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
   








}