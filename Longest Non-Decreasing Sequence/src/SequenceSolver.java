
public class SequenceSolver {
	
	private String rawInput;
	private int[] fullSequence;
	private int[] longestSequence;
	
	
	public SequenceSolver(String raw) {
		rawInput = raw;
		fullSequence = parseString();
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
		
		int high = 0;
		
		int last = fullSequence[0];
		
		for(int i = 0; i < fullSequence.length; i++) {
			int current = fullSequence[i];
			if(current >= last) {
				seqCount++;
			}else if(seqCount > 1) {
				seqs++;
				seqCount = 0;
			}
			last = current;
		}
		return seqs;
	}
	
	
//	private int[][] longestSequence() {
//		int[][] finalArr = getArraySize();
//		
//		int last = fullSequence[0];
//		
//		int seqs = 0;
//		
//		for(int i = 1; i < fullSequence.length; i++) {
//			int seqCount = 1;
//			int current = fullSequence[i];
//			if(current >= last) {
//				//continue sequence
//				last = current;
//				finalArr[seqs][seqCount] = current;
//			}else if(seqCount > 1){
//				//end sequence
//				seqCount = 1;
//				
//			}else {
//				//sequence doesnt count
//				
//			}
//		}
//	}
}
