package helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Encryption {
	static String encript(String password) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
	
			try {
				md.update(password.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte[] digest = md.digest();
	
			md5 = new BigInteger(1,digest).toString(16);
	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
	static String decript(String encrPass){
		char[] tmp = encrPass.toCharArray();
		try {
            tmp = Hex.encodeHex(MessageDigest.getInstance("MD5").digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return tmp.toString();
	}
}
