/**
 * ETH Z�rich; Leitprogramm; Bin�re Suchb�ume ----- 
 * Das ist die Klasse eines einzelnen Knotens des Bin�ren Suchbaums. 
 * @author Bj�rn Steffen, Timur Erdag, Christina Class
 * @version 1.0 
 */
public class AVLNode {
    private int key;
    private AVLNode left, right;
    
    public AVLNode( int key ) {
        this.key = key;
        left=null;
        right=null;
    }
    
    public AVLNode( int key, AVLNode left, AVLNode right ) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
    
    /**
     * getter Methode f�r den Schl�ssel
     * @return den Schl�sselwert
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * setter Methode f�r den Schl�ssel
     * @param value der neue Schl�sselwert
     */
    public void setKey(int value)
    {
        key = value;
    }
    
    
    /**
     * getter Methode f�r das rechte Kind
     * @return rechtes Kind
     */
    public AVLNode getRight()
    {
        return right;
    }
    
    /**
     * setter Methode f�r das rechte Kind
     * @param newRight Referenz auf neues rechtes Kind
     */
    public void setRight(AVLNode newRight)
    {
        right=newRight;
    }
    
    /**
     * getter Methode f�r das linke Kind
     * @return linkes Kind
     */
    public AVLNode getLeft()
    {
        return left;
    }
    
    /**
     * setter Methode f�r das linke Kind
     * @param newLeft Referenz auf neues linkes Kind
     */
    public void setLeft(AVLNode newLeft)
    {
        left=newLeft;
    }
    
    
    // bestimmt die H�he eines Teilbaums, rekursiv
    // beim ersten Aufruf wird 1 als depth (Tiefe) �bergeben
    private int getHeight(int depth)
    {
        if (this == null) {
            return depth;
        }
        else {
            int leftHeight = depth;
            if (left != null) 
                leftHeight = left.getHeight(depth+1);
            int rightHeight = depth;
            if (right != null) 
                rightHeight = right.getHeight(depth+1);
            if (leftHeight > rightHeight) {
                return leftHeight;
            } else{
                return rightHeight;
            }
        }
    }
    
    /**
     * bestimmt den Balancefaktor eines Knotens
     * @param node Knoten f�r den der Balancefaktor bestimmt werden soll
     * @return Wert des Balancefaktors; 0, falls node null ist
     */
    public int getBalanceFactor()
    {
        if(this == null)
            return 0;
        int balance, lheight = 0, rheight = 0;
        if (left != null) 
            lheight = left.getHeight(1);
        if (right != null) 
            rheight = right.getHeight(1);
        balance = rheight - lheight;
        return balance; 
    }
    
    /**
     * Inorder Ausgabe des Wertes und der Balancefaktoren f�r den AVL Baum
     * @param node Wurzelknoten des AVL Baumes, der mit inorder ausgegeben werden soll
     */  
    public void inorderWithBalance(AVLNode node) {
        if (node != null) {
            inorderWithBalance(node.getLeft());
            System.out.println ("value: " + node.getKey() + ";  Balancefaktor: " + node.getBalanceFactor());
            inorderWithBalance(node.getRight());
        }
    }
    
    /**
     * rightrotation; diese Methode ist von Ihnen zu implementieren
     * @param node Wurzelknoten des AVL Baumes, der rotiert werden soll
     * @return neue Wurzel
     */  
    public AVLNode rightrotation(AVLNode node)
    {
        return node;
    }
    
    /**
     * leftrotation; diese Methode ist von Ihnen zu implementieren
     * @param node Wurzelknoten des AVL Baumes, der rotiert werden soll
     * @return neue Wurzel
     */
    public AVLNode leftrotation(AVLNode node)
    { 
        return node;
    }
  
}

