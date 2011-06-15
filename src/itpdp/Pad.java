package itpdp;

import java.util.Map;
import java.util.Set;

import processing.core.*;

public class Pad {
	
	private int numwidth;
	private int numheight;
	private int width;
	private int height;
	private int x;
	private int y;
	
	private Map<Integer,Integer> map;
	
	private PApplet p;
	
	public Pad(PApplet p, int numwidth, int numheight, int width, int height, int x, int y, Map<Integer,Integer> map) {
		this.p = p;
		this.numwidth = numwidth;
		this.numheight = numheight;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	public void drawPad(ColorBucket active, ColorBucket passive, Set<Integer> push){
		int m = 0;
		
		
		for(int i = 0; i < this.numwidth; i++){
			for(int j = 0; j < this.numheight; j++){
				m++;
				if(push.contains(map.get(m))){
					this.p.fill(active.getR(), active.getG(), active.getB());
				}else{
					this.p.fill(passive.getR(), passive.getG(), passive.getB());
				}
			    this.p.rect(this.x + ((this.width/this.numwidth)*i)+i,this.y + ((this.height/this.numheight)*j)+j,(this.width/this.numwidth), (this.height/this.numheight));
			}
		}
	}
	
	public int getField(int num){
		return this.map.get(num);
	}

	public int getNumwidth() {
		return numwidth;
	}

	public void setNumwidth(int numwidth) {
		this.numwidth = numwidth;
	}

	public int getNumheight() {
		return numheight;
	}

	public void setNumheight(int numheight) {
		this.numheight = numheight;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
