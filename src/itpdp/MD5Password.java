package itpdp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Password {

	private String password;
	
	public MD5Password(String password){
		this.password = password;
	}
	
	public String getMD5Password() throws NoSuchAlgorithmException {
	    MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(this.password.getBytes(),0,this.password.length());
	    String s = new BigInteger(1,m.digest()).toString(16);
	    if(s.length() == 31){
	    	return "0"+ s;
	    }
	    return s;
	}
	
	

}
