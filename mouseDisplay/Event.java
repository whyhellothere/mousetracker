package mouseDisplay;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import com.firebase.client.DataSnapshot;

public class Event{

	private int x, y;
	private String type, site;
	private long time;
	private int changed;
	
	public Event(int x, int y, String type, long time, String site, int changed){
		this.x = x;
		this.y = y;
		this.type = type;
		this.time = time;
		this.site = site;
		this.changed = changed;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getType(){
		return type;
	}
	
	public long getTime(){
		return time;
	}
	
	public String getSite(){
		return site;
	}
	
	public String toString(){
		return "(x:" + x + ", y:" + y + ", time:" + time + ", changed:" + changed + ")";
	}
	
	
//	public void draw(int radius, Graphics g){
//		if(type.equals("Click")) g.setColor(Color.blue);
//		else if(type.equals("Scroll")) g.setColor(Color.red);
//		g.fillOval(x, y, radius, radius);
//	}
	
	
}
