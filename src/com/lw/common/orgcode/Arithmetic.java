package com.lw.common.orgcode;

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec; 
public class Arithmetic { 
 
 
	public static SecretKeySpec getKey(String seed){ 
		SecretKeySpec key = null; 
		try { 
			key = new SecretKeySpec(hex2byte(seed.getBytes()), "AES"); 
		} catch (Exception e) { 
			
		} 
		return  key; 
	} 
     
	/** 
	 * 
	 *  
	 * @param strMing 
	 * @return 
	 */ 
	public static String getEncString(String strMing, String seed) { 
		byte[] byteMi = null; 
		byte[] byteMing = null; 
		String strMi = ""; 
		try { 
			return byte2hex(getEncCode(strMing.getBytes(),seed)); 
 
		} catch (Exception e) { 
			strMing = "error:04";
		} finally { 
			byteMing = null; 
			byteMi = null; 
		} 
		return strMi; 
	} 
 
	/** 
	 * 
	 *  
	 * @param strMi 
	 * @return 
	 */ 
	public static String getDesString(String strMi, String seed) { 
		byte[] byteMing = null; 
		byte[] byteMi = null; 
		String strMing = ""; 
		try { 
			return new String(getDesCode(hex2byte(strMi.getBytes()),seed)); 
 
		} catch (Exception e) { 
			//e.printStackTrace(); 
			strMing = "error:04";
		} finally { 
			byteMing = null; 
			byteMi = null; 
		} 
		return strMing; 
	} 
 
	/** 
	 * 
	 *  
	 * @param byteS 
	 * @return 
	 */ 
	private static byte[] getEncCode(byte[] byteS, String seed) { 
		SecretKeySpec key = getKey(seed); 
		byte[] byteFina = null; 
		Cipher cipher; 
		try { 
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			cipher.init(Cipher.ENCRYPT_MODE, key); 
			byteFina = cipher.doFinal(byteS); 
		} catch (Exception e) { 
			
		} finally { 
			cipher = null; 
		} 
		return byteFina; 
	} 
 
	/** 
	 * 
	 *  
	 * @param byteD 
	 * @return 
	 */ 
	private static byte[] getDesCode(byte[] byteD, String seed) { 
		SecretKeySpec key = getKey(seed); 
		Cipher cipher; 
		byte[] byteFina = null; 
		try { 
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			cipher.init(Cipher.DECRYPT_MODE, key); 
			byteFina = cipher.doFinal(byteD); 
		} catch (Exception e) { 
			
		} finally { 
			cipher = null; 
		} 
		return byteFina; 
	} 
 
	/** 
	 * 
	 *  
	 * @param b 
	 * @return 
	 */ 
	public static String byte2hex(byte[] b) { 
		String hs = ""; 
		String stmp = ""; 
		for (int n = 0; n < b.length; n++) { 
			stmp = (Integer.toHexString(b[n] & 0XFF)); 
			if (stmp.length() == 1) 
				hs = hs + "0" + stmp; 
			else 
				hs = hs + stmp; 
		} 
		return hs.toUpperCase(); 
	} 
 
	public static byte[] hex2byte(byte[] b) { 
		if ((b.length % 2) != 0) 
			throw new IllegalArgumentException(""); 
		byte[] b2 = new byte[b.length / 2]; 
		for (int n = 0; n < b.length; n += 2) { 
			String item = new String(b, n, 2); 
			b2[n / 2] = (byte) Integer.parseInt(item, 16); 
		} 
		return b2; 
	} 
	public static void main(String[] args) { 
		String seed = "66E78B90751C49CFA6503F66504B1797";
		String test1 = Arithmetic.getEncString("longwin",seed);// 
		System.out.println("sss:"+test1); 
		String test2 = Arithmetic.getDesString(test1, seed);
		System.out.println("ttt:"+test2);
 
	} 
	

}
