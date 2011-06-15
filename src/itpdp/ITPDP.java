package itpdp;

import processing.core.PApplet;
import de.bezier.data.sql.*;

import java.io.IOException;
import java.util.*;
import processing.serial.*;


public class ITPDP extends PApplet {

	private User user;
	private MySQL msql;
	
	private Serial myPort;

	ArrayList<Integer> keys = new ArrayList<Integer>();
	int keyOne = 0;
	int keyTwo = 0;
	int keyThree = 0;
	int keyFour = 0;
	int keyFive = 0;
	int keySix = 0;
	int keySeven = 0;
	int keyEight = 0;
	int keyNine = 0;
	int keyTen = 0;
	int keyEleven = 0;
	int keyTwelve = 0;
	
	public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "ITPDP" });
	}
	
	public void setup()
	{    
		size(800, 600);
	    background(0);
	    
	    this.msql = new MySQLCon(this);
		
	    this.user = new User(this, this.msql);
	    println(this.user.getEmail());
	    
	    println(Serial.list());
	    this.myPort = new Serial(this, Serial.list()[1], 9600);
	    this.myPort.buffer(1);
	    
	    //this.noLoop();
    	println("Please type your userid:");
    	char hej;
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.storeKey(85);
    	this.show();
    	
    	this.addKeyListener(new KeyPresser(this));
	}

	public void draw() {
		
		int a = 200;
		int b = 127;
		int c = 255;
	  //println(keyOne);
	  if(this.keyOne == 1){
	     this.fill(b,c,0);
	     rect(0,0,width/4, width/3);
	  }else{
	    this.fill(200,0,0);
	    this.rect(0,0,this.width/4, this.height/3);
	  }
	  if(keyTwo == 1){
	     this.fill(b,c,0);
	     rect(200,0,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(200,0,width/4, height/3);
	  }
	  if(keyThree == 1){
	     this.fill(b,c,0);
	     rect(400,0,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(400,0,width/4, height/3);
	  }
	  if(keyFour == 1){
	     this.fill(b,c,0);
	     rect(600,0,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(600,0,width/4, height/3);
	  }
	  if(keyFive == 1){
	     this.fill(b,c,0);
	     rect(0,200,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(0,200,width/4, height/3);
	  }
	  if(keySix == 1){
	     this.fill(b,c,0);
	     rect(200,200,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(200,200,width/4, height/3);
	  }
	  if(keySeven == 1){
	    this.fill(b,c,0);
	     rect(400,200,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(400,200,width/4, height/3);
	  }
	  if(keyEight == 1){
	     this.fill(b,c,0);
	     rect(600,200,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(600,200,width/4, height/3);
	  }
	  if(keyNine == 1){
	     this.fill(b,c,0);
	     rect(0,400,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(0,400,width/4, height/3);
	  }
	  if(keyTen == 1){
	     this.fill(b,c,0);
	     rect(200,400,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(200,400,width/4, height/3);
	  }
	  if(keyEleven == 1){
	     this.fill(b,c,0);
	     rect(400,400,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(400,400,width/4, height/3);
	  }
	  if(keyTwelve == 1){
	     this.fill(b,c,0);
	     rect(600,400,width/4, height/3);
	  }else{
	    this.fill(a,0,0);
	    rect(600,400,width/4, height/3);
	  }
	  
	  

	  
	}

	public void storeKey(int key){
	  if(!keys.contains(key)){
	    keys.add(key); 
	    println("Added " + key);
	  }
	}

	public void keyPressed(){
	  if(keyCode == 10){
	    show();
	  }else{
	    storeKey(key);
	    print(key + " " + keyCode);
	    if(keyCode == 85){
	      println("nice");
	      keyOne = 1;
	    }
	    if(keyCode == 79){
	      println("nice");
	      keyTwo = 1;
	    }
	    if(keyCode == 107){
	      println("nice");
	      keyThree = 1;
	    }
	    if(keyCode == 69){
	      println("nice");
	      keyFour = 1;
	    }
	    if(keyCode == 87){
	      println("nice");
	      keyFive = 1;
	    }
	    if(keyCode == 81){
	      println("nice");
	      keySix = 1;
	    }
	    if(keyCode == 82){
	      println("nice");
	      keySeven = 1;
	    }
	    if(keyCode == 73){
	      println("nice");
	      keyEight = 1;
	    }
	    if(keyCode == 68){
	      println("nice");
	      keyNine = 1;
	    }
	    if(keyCode == 83){
	      println("nice");
	      keyTen = 1;
	    }
	    if(keyCode == 70){
	      println("nice");
	      keyEleven = 1;
	    }
	    if(keyCode == 74){
	      println("nice");
	      keyTwelve = 1;
	    }
	    if(keyCode == 75){
	      println("hello");
	      reset();
	    }
	    redraw();
	  }
	}


	

	public void reset(){
	keyOne = 0;
	keyTwo = 0;
	keyThree = 0;
	keyFour = 0;
	keyFive = 0;
	keySix = 0;
	keySeven = 0;
	keyEight = 0;
	keyNine = 0;
	keyTen = 0;
	keyEleven = 0;
	keyTwelve = 0;
	keys.clear();
	myPort.write(107);
	}
}
