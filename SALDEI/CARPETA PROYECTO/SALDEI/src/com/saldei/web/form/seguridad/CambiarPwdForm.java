package com.saldei.web.form.seguridad;

import org.apache.struts.action.ActionForm;

public class CambiarPwdForm  extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String pwdActual;
	private String pwdNew;
	private String pwdConfirm;
	
	public String getPwdActual() {
		return pwdActual;
	}
	public void setPwdActual(String pwdActual) {
		this.pwdActual = pwdActual;
	}
	public String getPwdConfirm() {
		return pwdConfirm;
	}
	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
	}
	public String getPwdNew() {
		return pwdNew;
	}
	public void setPwdNew(String pwdNew) {
		this.pwdNew = pwdNew;
	}
	
}
