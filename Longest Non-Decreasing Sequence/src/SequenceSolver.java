public class SequenceSolver {
	
	//instance objects
	private String rawInput;
	
	//instance variables
	private int[] fullSequence;
	private int[][] sequences;
	
	//constructor
	public SequenceSolver(String raw) {
		rawInput = raw;
		fullSequence = parseString();
		
		sequences = new int[getAmountOfSequences()][];
		fillSequences();
	}
	
	//converts given sequence into int array
	private int[] parseString() {
		String[] split = rawInput.split(",");
		int[] rawSequence = new int[split.length];
		
		for(int i = 0; i < split.length; i++){
			try {
				rawSequence[i] = Integer.parseInt(split[i]);
			}catch (Exception e){}
		}
		return rawSequence;
	}
	
	//returns amount of sequences in input, used for initializing full array with correct length
	private int getAmountOfSequences(){		
		//keeps track of amount of sequences
		int seqs = 1;
		
		//keeps track of amount of numbers in each sequence.
		int seqCount = 1;
		
		int last = fullSequence[0];
		for(int i = 1; i < fullSequence.length; i++) {
			int current = fullSequence[i];
			if(current >= last) {
				//continuation of sequence
				seqCount++;
				
			}else if(seqCount >= 1) {
				//done with sequence (granted sequence >= 1 in length)
				seqs++;
				seqCount = 0;
				//decrement i so that the number that was used to see if the sequence was over can be used again in a new sequence
				i--;
			}
			last = current;
		}
		return seqs;
	}
	
	private void fillSequences() {
		//keeps track of amount of sequences
		int seqs = 0;
		
		//keeps track of amount of numbers in each sequence.
		int seqCount = 0;
		
		int last = fullSequence[0];
		for(int i = 0; i < fullSequence.length; i++) {
			int current = fullSequence[i];
			if(current >= last) {
				//continuation of sequence
				seqCount++;
				
				//if the last number in the full sequence continues the sequence
				if(i == fullSequence.length-1){
					//add sequence to 2d array
					int[] temp = new int[seqCount];
					int x = 0;
					for(int j = i - seqCount+1; j < i+1; j++) {
						temp[x] = fullSequence[j];
						x++;
					}
					sequences[seqs] = temp;
				}
			}else if(seqCount >= 1) {
				//done with sequence (granted sequence >= 1 in length)
				
				//add sequence to 2d array
				int[] temp = new int[seqCount];
				int x = 0;
				for(int j = i - seqCount; j < i; j++) {
					temp[x] = fullSequence[j];
					x++;
				}
				sequences[seqs] = temp;
				seqCount = 0;
				seqs++;
				i--;
			}
			last = current;
		}
	}

	public String toString() {
		String str = "";
		int high = highest();
		for(int i = 0; i < sequences.length; i++) {
			if(sequences[i].length != high)continue;
			if(high == 1)return "oneSequence";
			for(int j = 0; j < sequences[i].length; j++) {
				str += sequences[i][j]+ ",";
			}
			//remove last comma
			str = str.substring(0,str.length()-1);
			str += " and ";
		}
		//remove last ' and '
		return str.substring(0,str.length()-5);
	}
	
	//returns the highest length in 2d array, used for final comparison between sequences
	private int highest() {
		int high = 0;
		for(int i = 0; i < sequences.length; i++) {
			int current = sequences[i].length;
			if(current > high)high = current;
		}
		return high;
	}
}
