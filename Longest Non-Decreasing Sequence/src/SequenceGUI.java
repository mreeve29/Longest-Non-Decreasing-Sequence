import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import BreezySwing.*;

public class SequenceGUI extends GBFrame{

	private JTextField rawTextField = addTextField("",1,1,2,1);
	private JButton exitButton = addButton("Exit",2,2,1,1);
	private JButton enterButton = addButton("Enter",2,1,1,1);
	
	private JTextArea resultsTA = addTextArea("",3,1,2,2);
	
	
	public SequenceGUI() {
		resultsTA.setEditable(false);
		resultsTA.setFont(new Font(Font.SANS_SERIF,1,20));
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.red);
	}
	
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String raw = rawTextField.getText();
			rawTextField.setText(raw);
			if(!checkSequence(raw)) {
				resultsTA.setFont(new Font(Font.SANS_SERIF,1,20));
				SequenceSolver ss = new SequenceSolver(raw);
				resultsTA.setText(ss.toString());
			}
		}else if(button == exitButton) System.exit(1);
	}
	
	
	private boolean checkSequence(String input) {
		boolean errBool = false;
		String error = "";
		
		boolean commas = false;
		for(int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			
			if(i != input.length()-1) {
				char next = input.charAt(i+1);
				
				if(current == ',' && next == ',' && commas == false) {
					commas = true;
					errBool = true;
					error += "Multiple ',' in a row\n";
					break;
				}
			}
		}
		
		String[] split = input.split(",");
		
		boolean nums = false;
		boolean blank = false;
		for(int i = 0; i < split.length; i++) {
			String current = split[i];
			
			if(!ReeveHelper.isAllNumbers(current) && !nums) {
				error += "Text contains characters other than numbers and or \nthere are spaces between numbers\n\n";
				errBool = true;
				nums = true;
			}
			if(ReeveHelper.isBlank(current) && !blank){
				error += "Text contains whitespaces between commas\n\n";
				errBool = true;
				blank = true;
			}
		}
		
		if(errBool) {
			resultsTA.setText(error);
			resultsTA.setFont(new Font(Font.SANS_SERIF,1,10));
		}
		
		return errBool;
	}
	
	public static void main(String[] args) {
		SequenceGUI frm = new SequenceGUI();
		frm.setTitle("Sequence");
		frm.setSize(400,400);
		frm.setVisible(true);
	}

}
