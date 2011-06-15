package itpdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.bezier.data.sql.MySQL;
import processing.core.*;
import processing.serial.Serial;

public class Tester extends PApplet {

	private Serial myPort;
	private MySQL msql;
	
	private User user = null;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Pad p1;
	private Pad p2;
	private Pad p3;
	
	private ColorBucket c1;
	private ColorBucket c2;
	
	private Set<Integer> pushed1;
	private Set<Integer> pushed2;
	private Set<Integer> pushed3;
	
	
	private int m = 0;
	
	private int state = 1;
	private boolean record = false;

	private RecordMapping mapping;
	private int showLogin = 0;
	
	private PFont f;
	
	public Tester(){
		
	}

	public void setup() {
		
		int stdwidth = 150;
		int stdheight = 220;
		int lh = ((stdheight * 2) / 3);
		int lw = ((stdwidth * 2) / 3);
		int gap = 60;
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		map.put(1, 85);
		map.put(2, 87);
		map.put(3, 68);
		map.put(4, 79);
		map.put(5, 81);
		map.put(6, 83);
		map.put(7, 107);
		map.put(8, 82);
		map.put(9, 70);
		map.put(10, 69);
		map.put(11, 73);
		map.put(12, 74);
		
		this.p1 = new Pad(this, 6, 4, lh, lw, 20, 20, map);
		this.p2 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap, map);
		this.p3 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap + stdwidth + gap, map);
		
		this.c1 = new ColorBucket(255,255,255);
		this.c2 = new ColorBucket(200,200,200);
		
		this.pushed1 = new HashSet<Integer>();
		this.pushed2 = new HashSet<Integer>();
		this.pushed3 = new HashSet<Integer>();
		
		this.msql = new MySQLCon(this);
		
		size(800, 600);
		this.background(60);
		redraw();
		
	    this.user = new User(this, this.msql);
	    println(this.user.getEmail());
		println(Serial.list());
		this.myPort = new Serial(this, Serial.list()[1], 9600);
		this.myPort.buffer(1);
		//noLoop();
		
		this.f = loadFont("AppleGothic-30.vlw");  
		
		this.mapping = new RecordMapping(user, this);

	}
	
	public void resetDraw(){

		this.background(60);
		
		fill(250,0,0);
		rect(0,0,200, 200);
	}

	public void draw() {
		if(state == 1){
			showLogin();
		}else if(state == 2){
			resetDrawing();
		}
		
	}
	
	public void notifyMain(int event){
		if(event == 1){
			setState(2);
		}
		
	}
	
	private void showLogin() {
		LoginHandle login = new LoginHandle(this);
		if(this.showLogin == 0){
			login.showLogin(this);
		}
		this.showLogin = 1;
	}

	public void resetDrawing(){
		
		int a = 200;
		int b = 200;
		int c = 200;

//		int stdwidth = 100;
//		int stdheight = 150;
//		int lh = ((stdheight * 2) / 3);
//		int lw = ((stdwidth * 2) / 3);
//		int gap = 30;
//		
//		Pad p1 = new Pad(this, 4, 6, lw, lh, 20, 0);
//		Pad p2 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap);
//		Pad p3 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap + stdwidth + gap);
//		
//		ColorBucket c1 = new ColorBucket(255,255,255);
//		ColorBucket c2 = new ColorBucket(200,200,200);
//		
//		Set<Integer> pushed1 = new HashSet<Integer>();
//		Set<Integer> pushed2 = new HashSet<Integer>();
//		Set<Integer> pushed3 = new HashSet<Integer>();
		
		this.p1.drawPad(c1,c2,pushed1);
		this.p2.drawPad(c1,c2,pushed2);
		this.p3.drawPad(c1,c2,pushed3);
		
		if(!user.equals(null)){
			this.textFont(this.f,46);                 // STEP 4 Specify font to be used
			this.fill(255);                        // STEP 5 Specify font color 
			this.text("FeetBACK",500,60);
			this.textFont(this.f,16); 
			this.text("Welcome \n" + user.getName(),400,120);
			// STEP 6 Display Text
		}
			 
		redraw();
	}

	public void keyPressed(){
		if(this.state == 2){
			if(keyCode == 10){
				this.record = true;
				mapping.start();
				mapping.printforme();
				send();
				println("print " + keyCode);
			}else if(this.record){
				pushed3.add(keyCode);
				mapping.map(keyCode);
				println("Added " + keyCode);
				mapping.printParsed();
				mapping.printParsed();
			}
		}
		
	}
	
	public void send(){
		for(int keyt : pushed3){
			this.myPort.write(keyt);
			println("Sent " + keyt);
		}
	}
	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public MySQL getMsql() {
		return msql;
	}

	public void setMsql(MySQL msql) {
		this.msql = msql;
	}


	
}