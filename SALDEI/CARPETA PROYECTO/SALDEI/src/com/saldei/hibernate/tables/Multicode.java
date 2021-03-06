package com.saldei.hibernate.tables;

import java.util.HashSet;
import java.util.Set;


/**
 * Multicode generated by MyEclipse - Hibernate Tools
 */

public class Multicode  implements java.io.Serializable {


    // Fields    

     private Integer idMulticode;
     private TipoMulticode tipoMulticode;
     private String codigo;
     private String descripcion;
     private Integer orden;
     private String estMulticode;
     private Set carreras = new HashSet(0);
     private Set reqMats = new HashSet(0);


    // Constructors

    /** default constructor */
    public Multicode() {
    }

	/** minimal constructor */
    public Multicode(Integer idMulticode, TipoMulticode tipoMulticode, String codigo, String estMulticode) {
        this.idMulticode = idMulticode;
        this.tipoMulticode = tipoMulticode;
        this.codigo = codigo;
        this.estMulticode = estMulticode;
    }
    
    /** full constructor */
    public Multicode(Integer idMulticode, TipoMulticode tipoMulticode, String codigo, String descripcion, Integer orden, String estMulticode, Set carreras, Set reqMats) {
        this.idMulticode = idMulticode;
        this.tipoMulticode = tipoMulticode;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.estMulticode = estMulticode;
        this.carreras = carreras;
        this.reqMats = reqMats;
    }

   
    // Property accessors

    public Integer getIdMulticode() {
        return this.idMulticode;
    }
    
    public void setIdMulticode(Integer idMulticode) {
        this.idMulticode = idMulticode;
    }

    public TipoMulticode getTipoMulticode() {
        return this.tipoMulticode;
    }
    
    public void setTipoMulticode(TipoMulticode tipoMulticode) {
        this.tipoMulticode = tipoMulticode;
    }

    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrden() {
        return this.orden;
    }
    
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getEstMulticode() {
        return this.estMulticode;
    }
    
    public void setEstMulticode(String estMulticode) {
        this.estMulticode = estMulticode;
    }

    public Set getCarreras() {
        return this.carreras;
    }
    
    public void setCarreras(Set carreras) {
        this.carreras = carreras;
    }

    public Set getReqMats() {
        return this.reqMats;
    }
    
    public void setReqMats(Set reqMats) {
        this.reqMats = reqMats;
    }
   








}