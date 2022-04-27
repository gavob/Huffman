public class Branch extends Node {
    //private Node left; 
    //private Node right;  
    
    public Branch(Node left, Node right) {
        super.left = left;
        super.right = right;
		super.frequency = left.getFrequency() + right.getFrequency();
		super.isLeaf = false;
    }
	
	public void printBranch() {
		System.out.println("LEFT: "+this.left+" RIGHT: "+this.right);
	}

}