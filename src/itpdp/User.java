package itpdp;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import processing.core.PApplet;
import de.bezier.data.sql.*;

public class User {
	private MySQL msql;
	
	private PApplet parent; // The parent PApplet that we will render ourselves onto
	
	private String id;
	private String username;
	private String name;
	private String email;
	private String height;
	
	private ArrayList<RecordMapping> jumps;

	@SuppressWarnings("static-access")
	public User(PApplet p, MySQL msql){
		this.msql = msql;
		this.parent = p;
//		try{
//	    	this.parent.println("Please type your userid:");
//	    	char userid = (char) System.in.read();
//	    	this.setId(userid + "");
//	    }catch(Exception e){
//	    	this.parent.println("exception :" + e);
//	    }
		jumps = new ArrayList<RecordMapping>();
	}
	
	public void defineUser(String userid){
		
		if ( this.msql.connect() )
	    {
	    	String query = "SELECT * FROM users WHERE id = '" + userid + "'";
	    	//println(query);
	        this.msql.query(query);
	        msql.next();
	        this.parent.println( "Welcome " + msql.getString("name"));
	        this.setId(msql.getString("id"));
	        this.setName(msql.getString("name"));
	        this.setUsername(msql.getString("username"));
	        this.setEmail(msql.getString("email"));
	        this.setHeight(msql.getString("height"));
	    }
	    else
	    {
	    	parent.println( "failed" );
	    }
		
	}
	
	public void addJump(RecordMapping rm){
		
		this.jumps.add(rm);
		
	}
	
	@SuppressWarnings("static-access")
	public boolean loginUser(String username, String password){
		if ( msql.connect() )
	    {
			String passwordMD5 = null;
			try {
				passwordMD5 = new MD5Password(password).getMD5Password();
			} catch (NoSuchAlgorithmException e) {
				this.parent.println("Algoritmen virker ikke, kunne ikke MD5");
			}
	    	String query = "SELECT COUNT(*) FROM users WHERE username = '" + username + "' AND password = '"+ passwordMD5 + "'";
	    	//println(query);
	        msql.query(query);
	        msql.next();
	        if(msql.getInt(1) > 0){
	        	String query2 = "SELECT * FROM users WHERE username = '" + username + "' AND password = '"+ passwordMD5 + "'";
		    	//println(query);
		        msql.query(query2);
		        msql.next();
		        parent.println( "Welcome " + msql.getString("name"));
		        this.setId(msql.getString("id"));
		        this.setName(msql.getString("name"));
		        this.setUsername(msql.getString("username"));
		        this.setEmail(msql.getString("email"));
		        this.setHeight(msql.getString("height"));
		        return true;
	        }else{
	        	return false;
	        }
	        
	        
	    }
	    else
	    {
	    	parent.println( "failed" );
	    	return false;
	    }
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return height;
	}
	
	public ArrayList<RecordMapping> getJumps() {
		return jumps;
	}
	
}
