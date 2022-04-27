public abstract class Node implements Comparable<Node> { 
    protected int frequency; 
	protected Node left=null; 
	protected Node right=null; 
	protected boolean isLeaf;
	protected String code = "";
	protected char letter;
    
    // constructor
    public Node() { }
	
	public char getLetter() { return this.letter; }
	
	public int compareTo(Node node) {
		int diff = this.getFrequency() - node.getFrequency();
		return diff;
	}
	
	public void setCodes() {
		if(this.left!=null) {
			if(this.left.getIsLeaf()) {
				this.left.setCode(this.code+"0");
			} else {
				this.left.setCodes(this.code+"0");
			}
		}
		if(this.right!=null) {
			if(this.right.getIsLeaf()) {
				this.right.setCode(this.code+"1");
			} else {
				this.right.setCodes(this.code+"1");
			}
		}
	}
	
	public void setCodes(String bCode) {
		this.code = bCode;
		setCodes();
	}
	
	public void setCode(String code) { this.code = code; }
	
	public void incrementFreq() { this.frequency++; }
	
	public int getFrequency() { return this.frequency; }
	
	public Node getLeft() { return this.left; }
    
	public Node getRight() { return this.right; }
	
	public boolean getIsLeaf() { return this.isLeaf; }
	
	public String getCode() { return this.code; }
}