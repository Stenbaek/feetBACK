package itpdp;
import processing.core.PApplet;
import controlP5.*;
import de.bezier.data.sql.MySQL;

public class LoginHandle {
	
	private PApplet p;
	private ControlP5 controlP5; 
	
	public LoginHandle(PApplet p){
		this.p = p;		
	}
	
	@SuppressWarnings("deprecation")
	public void showLogin(Tester t){
		controlP5 = new ControlP5(this.p);
		
		ControlWindow cw = controlP5.addControlWindow("Login Window",262,180);
		cw.hideCoordinates();
		  // create a new group of controllers and 
		  // move them into the control window.
		  ControlGroup cg = controlP5.addGroup("feetbackLogin",30,30);
		  cg.moveTo(cw);
		  controlP5.begin(cg,0,10);
		  Textfield username = controlP5.addTextfield("username",30,35,200,20);
		  username.setWindow(cw);
		  username.linebreak();
		  Textfield password = controlP5.addTextfield("password",30,75,200,20);
		  password.setPasswordMode(true);
		  password.setWindow(cw);
		  Button login = controlP5.addButton("Login", 1, 30, 115, 80, 40);
		  controlP5.controller("Login").addListener(new LoginProcess(username, password, login, this.p, t, cw));
		  login.setWindow(cw);
		  controlP5.end();
	}
	
	public void hideWindow(){
		
	}
	
}
