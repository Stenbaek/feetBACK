package itpdp;

import java.util.*;
public class RecordPoint {
	
	private long time;
	private int port;
	private Date timeFull;
	
	public RecordPoint(Date time, int i){
		this.setTime(time.getTime());
		this.setPort(i);
		this.setTimeFull(time);
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

	public void setTimeFull(Date timeFull) {
		this.timeFull = timeFull;
	}

	public Date getTimeFull() {
		return timeFull;
	}
	
}
