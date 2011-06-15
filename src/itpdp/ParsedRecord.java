package itpdp;

import java.util.*;
public class ParsedRecord {
		
	private long time;
	private int port;
	
	public ParsedRecord(long time, int i){
		this.setTime(time);
		this.setPort(i);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
