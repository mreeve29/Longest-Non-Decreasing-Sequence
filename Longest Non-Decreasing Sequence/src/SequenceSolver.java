
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
	
	
}
