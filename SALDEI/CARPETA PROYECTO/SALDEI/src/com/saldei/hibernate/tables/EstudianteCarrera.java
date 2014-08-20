package com.saldei.hibernate.tables;

import java.util.Date;


/**
 * EstudianteCarrera generated by MyEclipse - Hibernate Tools
 */

public class EstudianteCarrera  implements java.io.Serializable {


    // Fields    

     private EstudianteCarreraId id;
     private Date fechaFin;
     private String estEstCar;


    // Constructors

    /** default constructor */
    public EstudianteCarrera() {
    }

	/** minimal constructor */
    public EstudianteCarrera(EstudianteCarreraId id, String estEstCar) {
        this.id = id;
        this.estEstCar = estEstCar;
    }
    
    /** full constructor */
    public EstudianteCarrera(EstudianteCarreraId id, Date fechaFin, String estEstCar) {
        this.id = id;
        this.fechaFin = fechaFin;
        this.estEstCar = estEstCar;
    }

   
    // Property accessors

    public EstudianteCarreraId getId() {
        return this.id;
    }
    
    public void setId(EstudianteCarreraId id) {
        this.id = id;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstEstCar() {
        return this.estEstCar;
    }
    
    public void setEstEstCar(String estEstCar) {
        this.estEstCar = estEstCar;
    }
   








}