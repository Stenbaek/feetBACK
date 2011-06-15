package itpdp;

import processing.core.PApplet;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlWindow;
import controlP5.Textfield;

public class LoginProcess implements ControlListener {
	
	private Textfield username;
	private Textfield password;
	private Button login;
	private PApplet p;
	private Tester t;
	private ControlWindow cw;

	public LoginProcess(Textfield username, Textfield password, Button login, PApplet p, Tester t, ControlWindow cw) {
		this.username = username;
		this.password = password;
		this.login = login;
		this.p = p;
		this.t = t;
		this.cw = cw;
	}


	@SuppressWarnings("static-access")
	@Override
	public void controlEvent(ControlEvent arg0) {
		this.p.println("push your mom instead!");
		
		User user = new User(this.p,this.t.getMsql());
		if(user.loginUser(this.username.getText(), this.password.getText())){
			this.t.notifyMain(1);
			this.p.println("logged in as: " + user.getUsername());
			this.t.setUser(user);
			this.cw.hide();
		}else{
			this.p.println("You could not be logged in - try again");
		}
	}

}
