import javax.swing.*;
import BreezySwing.*;

public class SequenceGUI extends GBFrame{

	JTextField rawTextField = addTextField("",1,1,1,1);
	
	JButton enterButton = addButton("Enter",2,1,1,1);
	
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String raw = rawTextField.getText();
			raw = raw.replaceAll(" ", "");
			SequenceSolver ss = new SequenceSolver(raw);
			int[] test = ss.getRawIntArray();
			//System.out.println(ss.getAmountOfSequences());
//			for(int i = 0; i < test.length; i++) {
//				System.out.println(test[i]);
//			}
		}
	}
	
	public static void main(String[] args) {
		SequenceGUI frm = new SequenceGUI();
		frm.setTitle("Sequence");
		frm.setSize(400,400);
		frm.setVisible(true);
	}

}
