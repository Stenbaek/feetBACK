package itpdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import controlP5.ControlP5;

import de.bezier.data.sql.MySQL;
import processing.core.*;
import processing.serial.Serial;

public class Tester extends PApplet {

	private Serial myPort;
	private Serial myPort2;
	private Serial myPort3;
	private MySQL msql;
	private RecordController recordController;
	
	private ControlP5 controlP5;
	
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
	
	private Map<Integer,Integer> map1;
	private Map<Integer,Integer> map2;
	private Map<Integer,Integer> map3;
	
	private int m = 0;
	
	private int state = 1;
	private boolean record = false;

	private RecordMapping mapping;
	private int showLogin = 0;
	
	private PFont f;
	private int buttonsAdded = 0;
	
	public Tester(){
		
	}

	public void setup() {
		
		int stdwidth = 150;
		int stdheight = 220;
		int lh = ((stdheight * 2) / 3);
		int lw = ((stdwidth * 2) / 3);
		int gap = 60;
		
		this.map2 = new HashMap<Integer,Integer>();
		
		map2.put(1, 85);
		map2.put(2, 87);
		map2.put(3, 68);
		map2.put(4, 79);
		map2.put(5, 81);
		map2.put(6, 83);
		map2.put(7, 107);
		map2.put(8, 82);
		map2.put(9, 70);
		map2.put(10, 69);
		map2.put(11, 73);
		map2.put(12, 74);
		
		this.map1 = new HashMap<Integer,Integer>();
		
		map1.put(1, 72);
		map1.put(2, 65);
		map1.put(3, 71);
		map1.put(4, 192);
		map1.put(5, 191);
		map1.put(6, 38);
		map1.put(7, 32);
		map1.put(8, 222);
		map1.put(9, 84);
		map1.put(10, 9);
		map1.put(11, 89);
		map1.put(12, 76);
		map1.put(13, 8);
		map1.put(14, 91);
		map1.put(15, 135);
		map1.put(16, 75);
		map1.put(17, 37);
		map1.put(18, 66);
		map1.put(19, 78);
		map1.put(20, 59);
		map1.put(21, 40);
		map1.put(22, 39);
		map1.put(23, 47);
		map1.put(24, 92);
		
		this.p1 = new Pad(this, 6, 4, lh, lw, 20, 20, map1);
		this.p2 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap, map2);
		this.p3 = new Pad(this, 4, 3, stdheight, stdwidth, 20, lh + gap + stdwidth + gap, map2);
		
		this.c1 = new ColorBucket(102,255,51);
		this.c2 = new ColorBucket(200,200,200);
		
		this.pushed1 = new HashSet<Integer>();
		this.pushed2 = new HashSet<Integer>();
		this.pushed3 = new HashSet<Integer>();
		
		this.msql = new MySQLCon(this);
		
		size(1200, 800);
		this.background(60);
		redraw();
		
	    this.user = new User(this, this.msql);
	    println(this.user.getEmail());
		println(Serial.list());
		this.myPort = new Serial(this, Serial.list()[5], 9600);
		this.myPort.buffer(5);
		this.myPort2 = new Serial(this, Serial.list()[3], 9600);
		this.myPort2.buffer(3);
		this.myPort3 = new Serial(this, Serial.list()[1], 9600);
		this.myPort3.buffer(1);
		//noLoop();
		
		this.f = loadFont("AppleGothic-30.vlw");  
		
		this.mapping = new RecordMapping(user, this);
		this.controlP5 = new ControlP5(this);
		this.recordController = new RecordController(false, this);

		resetDrawing();
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
		this.background(60);
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
		
		if(this.showLogin == 1 && this.buttonsAdded == 0){
			this.controlP5.addButton("Record",255,400,200,250,50);
			controlP5.controller("Record").addListener(this.recordController);
			this.controlP5.addToggle("Show on floor",400,270,250,50);
			controlP5.controller("Show on floor").addListener(new SendController(this));
			this.buttonsAdded = 1;
		}
		
		this.p1.drawPad(c1,c2,pushed1);
		this.p2.drawPad(c1,c2,pushed2);
		this.p3.drawPad(c1,c2,pushed3);
		
		if(!user.equals(null)){
			this.textFont(this.f,46);                 // STEP 4 Specify font to be used
			this.fill(255);                        // STEP 5 Specify font color 
			this.text("FeetBACK",500,60);
			if(showLogin == 1){
			this.textFont(this.f,16); 
			this.text("Welcome \n" + user.getName(),400,120);
			}
			// STEP 6 Display Text
		}
			 
		redraw();
		
		
	}

	public void keyPressed(){
		if(this.state == 2){
			if(keyCode == 10){
				mapping.start();
				mapping.printforme();
				send();
			}else if(this.recordController.isRecording()){
				if(!pushed3.contains(keyCode) && this.map2.containsValue(keyCode)){
					pushed3.add(keyCode);
					mapping.map(keyCode);
					println("Added " + keyCode);
					println("Added " + key);
					mapping.printParsed();
				}
				if(!pushed1.contains(keyCode) && this.map1.containsValue(keyCode)){
					pushed1.add(keyCode);
					mapping.map(keyCode);
					println("Added " + keyCode);
					println("Added " + key);
					mapping.printParsed();
				}
			}
		}
		
	}
	
	public void send(){
		for(int keyt : pushed3){
			this.myPort.write(keyt);
			println("Sent " + keyt);
		}
		for(int keyt : pushed1){
			this.myPort3.write(keyt);
			this.myPort2.write(keyt);
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

	public RecordMapping getMapping() {
		return mapping;
	}

	public void setMapping(RecordMapping mapping) {
		this.mapping = mapping;
	}
	
	


	
}