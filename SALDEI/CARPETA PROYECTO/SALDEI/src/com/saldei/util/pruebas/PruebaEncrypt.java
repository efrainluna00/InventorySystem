/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.pruebas;

import com.saldei.util.commons.SecurityDAO;
import com.saldei.util.commons.SecurityEncrypt;


public class PruebaEncrypt {
	
	public static void main(String[] args)throws Exception{
		SecurityDAO dao = new SecurityEncrypt();
		String text = "carlos";
		String encrypt = dao.getEncrypt(text);
		System.out.println(encrypt);
		String decrypt = dao.getDecrypt(encrypt);
		System.out.println(decrypt);
	}//=±ŽÔbú‰ä will
}
