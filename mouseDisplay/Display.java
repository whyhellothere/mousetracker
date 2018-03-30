package mouseDisplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;





import org.jfree.data.general.PieDataset;

import com.google.gson.Gson;

public class Display extends JComponent implements MouseListener, MouseMotionListener{

	private Event[] events;
	private final int DISPLAY_WIDTH;
	private final int DISPLAY_HEIGHT;
	private final Font FONT = new Font("Times", 10, 10);

	//	private boolean reddit, facebook, instagram, twitter, quora;
	private boolean showInfo = false;
	private boolean paintLoop = false;
	
	private String imageName = "facebook2.PNG";
//	private String imageName = "Reddit.PNG";
	private Image background = (new ImageIcon("mouseDisplay/" + imageName)).getImage();
	
	private String fileName = "aar-project-aed83-_e_0rR7-export";
//	private String fileName = "aar-project-aed83-_e_021Y-export";	
//	private String fileName = "aar-project-aed83-G4oIf-export";
	

	private double scale = 1.5;
	private int increment = 1;
	private int current = 0;
	private long time = 0;
	private int speed = 1000; //100 is the base speed the time is in milliseconds
	private double baseHeight = 974;
	private double baseWidth = 1920;
	private int screenHeight = (int) (baseHeight/scale);
	private int screenWidth = (int) (baseWidth/scale);
	
	private int left = 0;
	private int right = 0;
	private int top = 0;
	private int bottom = 0;

	private StartButton startStop;
	private ValueButton values;	
	private ConnectButton connect;
	
//	private JPanel panel;
	
	
	public Display(int height, int width) throws FileNotFoundException {
		
//		this.panel
		
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;

		setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);

		addMouseListener(this);
		addMouseMotionListener(this);

		startStop = new StartButton();
		startStop.setBounds(100, 550, 100, 36);
		add(startStop);
		startStop.setVisible(true);
		
		values = new ValueButton();
		values.setBounds(100, 550, 100, 36);
		add(values);
		values.setVisible(true);
		
		connect = new ConnectButton();
		connect.setBounds(100, 550, 100, 36);
		add(connect);
		connect.setVisible(true);
		
		convert();
		calculateDistribution();
		displayDataset();

	}

	private void convert() throws FileNotFoundException{
		//converts JSON to array
		Gson gson = new Gson();	
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Jack/Desktop/AAR/Mousething/mouse 2.0/"+ fileName
				+ ".json"));
		events = gson.fromJson(br, Event[].class);
		System.out.println(events[0].getType());
	}

	private void calculateDistribution(){
		for(int i=0; i<events.length; i++){
			if(events[i].getX()/baseWidth>.75){
				bottom++;
			}
			else if(events[i].getX()/baseWidth<.25){
				top++;
			}
			if(events[i].getY()/baseHeight>.75){
				right++;
			}
			else if(events[i].getY()/baseHeight<.75){
				left++;
			}
		}
	}
	
	private void displayDataset(){
		System.out.println("top events:" + top);
		System.out.println("bottom events:" + bottom);
		System.out.println("left events:" + left);
		System.out.println("right events:" + right);
	}
	
	
	public void heatMap(){
		
//		for()
		
	}
	
	public void resetCalculations(){
		bottom = 0;
		top = 0;
		right = 0;
		left = 0;
	}
	
	
	public void togglePaintLoop() {
		paintLoop = !paintLoop;
	}

	public void paintComponent(Graphics g) {

		
		drawBackground(background, g);
		
//		if(!(current>=events.length-1-increment)){
//			time = (events[current+increment].getTime()-events[current].getTime())/speed;
//			drawEvent(4, g, showInfo, current);
//		}
//		
//		else{
//			paintLoop = false;
//		}
//		
//		if (paintLoop) {
//			
//			try {
//				Thread.sleep(time);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			repaint();
//		}
//		
//		current += increment;

		for(int current=0; current<events.length; current++){
			drawEvent(4, g, showInfo, current);
		}
		
		
		
		

	}

	public void drawBackground(Image background, Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
//		background.paintIcon(this, g2, 0, 0);
		g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
		
	}

	public void drawEvent(int radius, Graphics g, boolean draw, int i) {

		Graphics2D g2 = (Graphics2D) g;
		//		g2.setFont(font);

		//		for(int i=0; i<events.length; i+=2){

		//		System.out.println("drawEvent");
		//		System.out.println(events[i].getType());

		if(events[i].getType().equals("mouseMove")){
			g2.setColor(Color.black);
		}
		else if(events[i].getType().equals("mouseClick"))
		{
			g2.setColor(Color.blue);
		}
		else if(events[i].getType().equals("mouseScroll"))
		{
			g2.setColor(Color.red);
		}
		else if(events[i].getType().equals("mouseRClick"))
		{
			g2.setColor(Color.green);
		}
		g2.fillOval((int)(events[i].getX()/scale), (int)(events[i].getY()/scale), radius, radius);

		if(draw==true){
			g2.drawString(events[i].toString(), events[i].getX()+20, events[i].getY()+20);
			//				}

		}
	}


	public void connectEvents(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(connect.getText().equals("Yes")){
			// for loop drawing lines
			for(int i=0; i<events.length-1; i++){
				g2.drawLine(events[i].getX(), events[i].getY(), events[i+1].getX(), events[i+1].getY());
			}
		}
		repaint();
	}

	public void mouseDragged(MouseEvent e) { }

	public void mouseMoved(MouseEvent e) { }

	public void mouseClicked(MouseEvent e) { }

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }


	private class StartButton extends JButton implements ActionListener {
		StartButton() {
			super("Start");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			// nextGeneration(); // test the start button
			if (this.getText().equals("Start")) {
				togglePaintLoop();
				setText("Stop");
			} else {
				togglePaintLoop();
				setText("Start");
			}
			repaint();
		}
	}
	
	private class ValueButton extends JButton implements ActionListener {
		ValueButton() {
			super("Show Values");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			// nextGeneration(); // test the start button
			if (this.getText().equals("Start")) {
				togglePaintLoop();
				setText("Stop");
			} else {
				togglePaintLoop();
				setText("Start");
			}
			repaint();
		}
	}
	
	private class ConnectButton extends JButton implements ActionListener {
		ConnectButton() {
			super("Show Path");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			// nextGeneration(); // test the start button
			if (this.getText().equals("Show Path")) {
				togglePaintLoop();
				setText("Hide Path");
			} else {
				togglePaintLoop();
				setText("Show Path");
			}
			repaint();
		}
	}
	
	
	

}
