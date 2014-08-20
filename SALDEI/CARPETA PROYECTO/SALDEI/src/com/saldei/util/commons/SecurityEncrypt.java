/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class SecurityEncrypt implements SecurityDAO{

	/*
	 * (non-Javadoc)
	 * @see com.adminlab.util.commons.SecurityDAO#getDecrypt(java.lang.String)
	 */
	public String getDecrypt(String text) {
		 return crypt(text, "wiwis", Cipher.DECRYPT_MODE);
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminlab.util.commons.SecurityDAO#getEncrypt(java.lang.String)
	 */
	public String getEncrypt(String text){
		return crypt(text, "wiwis", Cipher.ENCRYPT_MODE);
	}
	
	 private static String crypt(String input, String key, int mode){
		 try{
			 Provider sunJce = new com.sun.crypto.provider.SunJCE();		 
			 Security.addProvider(sunJce);  
			 KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
			 kgen.init(448);
			 SecretKey skey = kgen.generateKey();	
			 byte[] raw = key.getBytes();
			 SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");	     
			 Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			 cipher.init(mode, skeySpec);	
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
			 CipherOutputStream cos = new CipherOutputStream(bos, cipher);	
			 int length = 0;
			 byte[] buffer =  new byte[8192];	
			 while ((length = bis.read(buffer)) != -1) {
			    cos.write(buffer, 0, length);
			 }	
			 bis.close();
			 cos.close();	
			 return bos.toString();
		 }catch(Exception ex){
			 ex.printStackTrace();
			 return "";
		 }
	}
}//clase
