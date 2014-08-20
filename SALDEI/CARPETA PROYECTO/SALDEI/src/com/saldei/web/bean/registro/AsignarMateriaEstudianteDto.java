/**
 * 
 */
package com.saldei.web.bean.registro;

/**
 * @author Carlos
 *
 */
public class AsignarMateriaEstudianteDto {
	private static final long serialVersionUID = 1L;
	private String idCic;
	private String idMat;
	private String idSec;
	private String idEst;
	private String carnet;
	private String nombres;
	private String apellidos;
	private String accion;
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the idCic
	 */
	public String getIdCic() {
		return idCic;
	}
	/**
	 * @param idCic the idCic to set
	 */
	public void setIdCic(String idCic) {
		this.idCic = idCic;
	}
	/**
	 * @return the idEst
	 */
	public String getIdEst() {
		return idEst;
	}
	/**
	 * @param idEst the idEst to set
	 */
	public void setIdEst(String idEst) {
		this.idEst = idEst;
	}
	/**
	 * @return the idMat
	 */
	public String getIdMat() {
		return idMat;
	}
	/**
	 * @param idMat the idMat to set
	 */
	public void setIdMat(String idMat) {
		this.idMat = idMat;
	}
	/**
	 * @return the idSec
	 */
	public String getIdSec() {
		return idSec;
	}
	/**
	 * @param idSec the idSec to set
	 */
	public void setIdSec(String idSec) {
		this.idSec = idSec;
	}
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return the carnet
	 */
	public String getCarnet() {
		return carnet;
	}
	/**
	 * @param carnet the carnet to set
	 */
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
}
