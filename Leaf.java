public class Leaf extends Node {
    
    public Leaf(char ltr, int frq) {
        super.letter = ltr;
        super.frequency = frq;
		super.isLeaf = true;
    }
	
	public int getFrequency() { return super.frequency; }
    
    public void setCode(String code) { 
		super.code = code; 
	}
    
}