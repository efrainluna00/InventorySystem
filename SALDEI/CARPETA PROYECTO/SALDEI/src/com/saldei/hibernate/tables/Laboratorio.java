package com.saldei.hibernate.tables;

import java.util.HashSet;
import java.util.Set;


/**
 * Laboratorio generated by MyEclipse - Hibernate Tools
 */

public class Laboratorio  implements java.io.Serializable {


    // Fields    

     private Integer idLaboratorio;
     private String nombreLaboratorio;
     private String abrevLaboratorio;
     private Integer numFilas;
     private Integer numColumnas;
     private String estLaboratorio;
     private Set directorLaboratorios = new HashSet(0);


    // Constructors

    /** default constructor */
    public Laboratorio() {
    }

	/** minimal constructor */
    public Laboratorio(Integer idLaboratorio, String nombreLaboratorio, String estLaboratorio) {
        this.idLaboratorio = idLaboratorio;
        this.nombreLaboratorio = nombreLaboratorio;
        this.estLaboratorio = estLaboratorio;
    }
    
    /** full constructor */
    public Laboratorio(Integer idLaboratorio, String nombreLaboratorio, String abrevLaboratorio, Integer numFilas, Integer numColumnas, String estLaboratorio, Set directorLaboratorios) {
        this.idLaboratorio = idLaboratorio;
        this.nombreLaboratorio = nombreLaboratorio;
        this.abrevLaboratorio = abrevLaboratorio;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.estLaboratorio = estLaboratorio;
        this.directorLaboratorios = directorLaboratorios;
    }

   
    // Property accessors

    public Integer getIdLaboratorio() {
        return this.idLaboratorio;
    }
    
    public void setIdLaboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombreLaboratorio() {
        return this.nombreLaboratorio;
    }
    
    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public String getAbrevLaboratorio() {
        return this.abrevLaboratorio;
    }
    
    public void setAbrevLaboratorio(String abrevLaboratorio) {
        this.abrevLaboratorio = abrevLaboratorio;
    }

    public Integer getNumFilas() {
        return this.numFilas;
    }
    
    public void setNumFilas(Integer numFilas) {
        this.numFilas = numFilas;
    }

    public Integer getNumColumnas() {
        return this.numColumnas;
    }
    
    public void setNumColumnas(Integer numColumnas) {
        this.numColumnas = numColumnas;
    }

    public String getEstLaboratorio() {
        return this.estLaboratorio;
    }
    
    public void setEstLaboratorio(String estLaboratorio) {
        this.estLaboratorio = estLaboratorio;
    }

    public Set getDirectorLaboratorios() {
        return this.directorLaboratorios;
    }
    
    public void setDirectorLaboratorios(Set directorLaboratorios) {
        this.directorLaboratorios = directorLaboratorios;
    }
   








}