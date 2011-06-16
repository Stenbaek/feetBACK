package itpdp;
import processing.core.PApplet;
import controlP5.*;



public class GuiTest extends PApplet {

	ControlP5 controlP5;

	ListBox l;
	int cnt = 0;
	public void setup() {
	  size(400,400);
	  frameRate(30);
	  controlP5 = new ControlP5(this);
	  l = controlP5.addListBox("myList",100,100,120,120);
	  l.setItemHeight(15);
	  l.setBarHeight(15);

	  l.captionLabel().toUpperCase(true);
	  l.captionLabel().set("something else");
	  l.captionLabel().style().marginTop = 3;
	  l.valueLabel().style().marginTop = 3; // the +/- sign
	  //l.setBackgroundColor(color(100,0,0));
	  for(int i=0;i<80;i++) {
	    l.addItem("item "+i,i);
	  }
	  l.setColorBackground(color(255,128));
	  l.setColorActive(color(0,0,255,128));
	}

	public void keyPressed() {
	  if(key=='1') {
	    // set the height of a listBox should alwyays be a multiple of itemHeight
	    l.setHeight(210); 
	  } 
	  else if(key=='2') {
	    // set the height of a listBox should alwyays be a multiple of itemHeight
	    l.setHeight(120);
	  }
	  else if(key=='i') {
	    // set the height of a listBoxItem, should alwyays be a fraction of the listBox
	    l.setItemHeight(30); 
	  } 
	  else if(key=='u') {
	    // set the height of a listBoxItem, should alwyays be a fraction of the listBox
	    l.setItemHeight(10);
	    l.setBackgroundColor(color(100,0,0));
	  } 
	  else if(key=='a') {
	    int n = (int)(random(100000));
	    l.addItem("item "+n, n);
	  } 
	  else if(key=='d') {
	    l.removeItem("item "+cnt);
	    cnt++;
	  }
	}

	void controlEvent(ControlEvent theEvent) {
	  // ListBox is if type ControlGroup.
	  // 1 controlEvent will be executed, where the event
	  // originates from a ControlGroup. therefore
	  // you need to check the Event with
	  // if (theEvent.isGroup())
	  // to avoid an error message from controlP5.
	  theEvent.isController();
	 // if (theEvent.isGroup()) {
	    // an event from a group e.g. scrollList
	    println(theEvent.value()+" from "+theEvent.value());
	 // }
	}

	public void draw() {
	  background(128);
	  // scroll the scroll List according to the mouseX position
	  // when holding down SPACE.
	  if(keyPressed && key==' ') {
	    //l.scroll(mouseX/((float)width)); // scroll taks values between 0 and 1
	  }
	  if(keyPressed && key==' ') {
	    l.setWidth(mouseX);
	  }
	}

}
