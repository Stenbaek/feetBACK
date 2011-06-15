package itpdp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import processing.core.PApplet;

public class KeyPresser extends KeyAdapter{
	@SuppressWarnings("static-access")
	
	private PApplet p;
	public KeyPresser(PApplet p){
		this.p = p;
		
	}
	public void keyPressed(KeyEvent ke){
	  char i = ke.getKeyChar();
	  String str = Character.toString(i);
	  this.p.println(str);
	  }
}