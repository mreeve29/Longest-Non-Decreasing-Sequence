import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import BreezySwing.*;

public class SequenceGUI extends GBFrame{

	//instance objects
	private JLabel instructionLabel = addLabel("<html>Input numbers seperated by a comma<br/>"
			+ "If there are multiple sequences with the same length they will all be displayed</html>",1,1,3,1);
	
	private JTextField rawTextField = addTextField("",2,1,3,1);
	private JButton exitButton = addButton("Exit",3,3,1,1);
	private JButton enterButton = addButton("Enter",3,1,1,1);
	
	private JTextArea resultsTA = addTextArea("",4,1,3,2);
	
	private JButton resetButton = addButton("Reset",3,2,1,1);
	
	//constructor
	public SequenceGUI() {
		resultsTA.setEditable(false);
		resultsTA.setFont(new Font(Font.SANS_SERIF,1,20));
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.red);
		this.setMinimumSize(new Dimension(500,400));
	}
	
	//button event listener
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String raw = rawTextField.getText();
			rawTextField.setText(raw);
			
			if(!checkSequence(raw)) {
				resultsTA.setFont(new Font(Font.SANS_SERIF,1,20));
				SequenceSolver ss = new SequenceSolver(raw);
				String result = ss.toString();
				
				//result will be "oneSequence" when the SequenceSolver instance determines that the user only inputed sequences with a length of 1
				if(result.equals("oneSequence")) {
					resultsTA.setFont(new Font(Font.SANS_SERIF,1,10));
					resultsTA.setText("Sequences with a length of one are not sequences");
				}else{
					resultsTA.setText(result);
				}
			}
		}else if(button == exitButton) System.exit(1);
		else if(button == resetButton) {
			rawTextField.setText("");
			resultsTA.setFont(new Font(Font.SANS_SERIF,1,20));
			resultsTA.setText("");
		}
	}
	
	//error checking
	private boolean checkSequence(String input) {
		boolean error = false;
		String errorMessage = "";
		
		//check for no input / a char that isn't a number
		if(input.length() == 0 || !Character.isDigit(input.charAt(0))) {
			error = true;
			errorMessage+="Invalid input";
			resultsTA.setText(errorMessage);
			resultsTA.setFont(new Font(Font.SANS_SERIF,1,10));
			return error;
		}
		
		//check for multiple commas entered in a row
		boolean commas = false;
		for(int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			
			if(i != input.length()-1) {
				char next = input.charAt(i+1);
				
				if(current == ',' && next == ',' && commas == false) {
					commas = true;
					error = true;
					errorMessage += "Multiple ',' in a row\n";
					break;
				}
			}
		}
		
		//split input
		String[] split = input.split(",");
		
		if(split.length > 25) {
			error = true;
			errorMessage += "Too many numbers, please input less than 25 numbers";
		}
		
		//check for invalid inputs, like letters or symbols
		for(int i = 0; i < split.length; i++) {
			String current = split[i];
			try {
				Integer.parseInt(current);
			}catch(Exception e){
				error = true;
				errorMessage+="Invalid input\n";
				break;
			}
		}
		
		//check for trailing whitespace
		if(Character.isWhitespace(input.charAt(input.length()-1))){
			errorMessage+="Check trailing whitespaces\n";
			error = true;
		}
		
		//check for trailing comma
		if(input.charAt(input.length()-1)==',') {
			errorMessage+="Trailing comma\n";
			error = true;
		}
		
		//display error message
		if(error) {
			resultsTA.setText(errorMessage);
			resultsTA.setFont(new Font(Font.SANS_SERIF,1,10));
		}	
		return error;
	}
	
	public static void main(String[] args) {
		SequenceGUI frm = new SequenceGUI();
		frm.setTitle("Sequence");
		frm.setSize(400,400);
		frm.setVisible(true);
	}
}
