package mouseDisplay;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// Bring up a JFrame with squares to represent the cells
		
		Firebase ref = new Firebase("https://aar-project-aed83.firebaseio.com");
		ref.keepSynced(true);
//		ref.addValueEventListener(new ValueEventListener());
		
		
		final int DISPLAY_WIDTH = 2000;
		final int DISPLAY_HEIGHT = 2000;
		JFrame f = new JFrame();
		f.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		Display display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Mouse Display");
		f.add(display);
		f.setVisible(true);
		f.repaint();
	}
	
	public void onDataChange(DataSnapshot snapshot) {
        for (DataSnapshot child : snapshot.getChildren()) {
            
        }
    }
}
