package itpdp;


import java.util.*;

import processing.core.PApplet;

public class RecordMapping {
	
	private ArrayList<RecordPoint> steps;
	private Date start;
	private Date end;
	private User user;
	private boolean recording;
	private boolean complete;
	private PApplet p;
	
	//Constructor 
	public RecordMapping(User user, PApplet p){
		this.setUser(user);
		this.setRecording(false);
		this.setComplete(false);
		this.steps = new ArrayList<RecordPoint>();
		this.p = p;
	}
	//Usage methods
	
	public void map(int key){
		if(recording){
			Date date = new Date();
			RecordPoint rc = new RecordPoint(date,key);
			steps.add(rc);
			this.p.println("mapped this key: " + key + date.getTime());
		}
		
	}
	
	public void start(){
		this.setRecording(true);
		Date now = new Date();
		this.setStart(now);
		this.p.println("Started recording - " + now.toString());
	}
	
	public ArrayList<ParsedRecord> parseToSet(){
		ArrayList<ParsedRecord> result = new ArrayList<ParsedRecord>();
		int i = 0;
		Long start = null;
		for(RecordPoint r : this.steps){
			if(i<1){
				start = r.getTime();
				result.add(new ParsedRecord(Long.parseLong("0"), r.getPort()));
			}else{
				Long time = r.getTime() - start;
				start = r.getTime();
				result.add(new ParsedRecord(time, r.getPort()));
			}	
			i++;
		}
		return result;
	}
	
	public void stop(){
		this.setRecording(false);
		this.setComplete(true);
		Date now = new Date();
		this.setEnd(now);
		this.p.println("Stopped recording - " + now.toString());
	}
	
	//Getters and Setters

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getEnd() {
		return end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStart() {
		return start;
	}

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	public boolean isRecording() {
		return recording;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isComplete() {
		return complete;
	}
	
	public String toString(){
		String result = "";
		for(RecordPoint r : this.steps){
			result += " " + r.getTime();
			
		}
		return result;

	}
	
	public void printforme(){
		this.p.println(this);
	}
	
	public void printParsed(){
		String result = "";
		for(ParsedRecord r : this.parseToSet()){
			result += " " + r.getTime();
			
		}
		this.p.println(result);
	}

}
