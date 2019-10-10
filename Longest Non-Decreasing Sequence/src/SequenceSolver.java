
public class SequenceSolver {
	
	private String rawInput;
	private int[] fullSequence;
	private int[][] sequences;
	
	
	public SequenceSolver(String raw) {
		rawInput = raw;
		fullSequence = parseString();
		sequences = new int[getAmountOfSequences()][];
		fillSequences();
		print();
	}
	
	public int[] getRawIntArray() {
		return fullSequence;
	}
	
	private int[] parseString() {
		String[] split = rawInput.split(",");
		int[] rawSequence = new int[split.length];
		
		for(int i = 0; i < split.length; i++){
			try {
				rawSequence[i] = Integer.parseInt(split[i]);
			}catch (Exception e){
				
			}
		}
		return rawSequence;
	}
	
	public int getAmountOfSequences(){		
		int seqs = 1;
		int seqCount = 0;
		
		int last = fullSequence[0];
		
		for(int i = 1; i < fullSequence.length; i++) {
			int current = fullSequence[i];
			if(current >= last) {
				seqCount++;
			}else if(seqCount >= 1) {
				//done with sequence (granted sequence >= 1 in length)
				seqs++;
				seqCount = 0;
				i--;
			}
			last = current;
		}
		return seqs;
	}
	
	
	private void fillSequences() {
		int seqs = 0;
		int seqCount = 0;
		
		int high = seqCount;
		
		int last = fullSequence[0];
		
		for(int i = 0; i < fullSequence.length; i++) {
			int current = fullSequence[i];
			if(current >= last) {
				seqCount++;
				System.out.println("(" + i + ")" + " adding " + current + " to seq " + seqs + ", total length for this seq is " + seqCount);
				if(i == fullSequence.length-1){
					System.out.println("(" + i + ")" + " last --> " + current);
					int[] temp = new int[seqCount];
					int x = 0;
					for(int j = i - seqCount+1; j < i+1; j++) {
						temp[x] = fullSequence[j];
						x++;
					}
					sequences[seqs] = temp;
				}
			}else if(seqCount >= 1) {
				System.out.println("(" + i + ")" + " seq " + seqs + " over");
				if(seqCount > high)high = seqCount;
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
	//17,31,40,5,6,10,3,37,53,12,9,86
	private void print() {
		int high = highest();
		for(int i = 0; i < sequences.length; i++) {
			if(sequences[i].length != high)continue;
			for(int j = 0; j < sequences[i].length; j++) {
				System.out.print(sequences[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	private int highest() {
		int high = 0;
		for(int i = 0; i < sequences.length; i++) {
			int current = sequences[i].length;
			if(current > high)high = current;
		}
		return high;
	}
}
