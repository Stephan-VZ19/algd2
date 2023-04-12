/**
 * ETH Zürich; Leitprogramm; Binäre Suchbäume ----- 
 * Das ist die Klasse eines einzelnen Knotens des Binären Suchbaums. 
 * @author Björn Steffen, Timur Erdag, Christina Class
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
     * getter Methode für den Schlüssel
     * @return den Schlüsselwert
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * setter Methode für den Schlüssel
     * @param value der neue Schlüsselwert
     */
    public void setKey(int value)
    {
        key = value;
    }
    
    
    /**
     * getter Methode für das rechte Kind
     * @return rechtes Kind
     */
    public AVLNode getRight()
    {
        return right;
    }
    
    /**
     * setter Methode für das rechte Kind
     * @param newRight Referenz auf neues rechtes Kind
     */
    public void setRight(AVLNode newRight)
    {
        right=newRight;
    }
    
    /**
     * getter Methode für das linke Kind
     * @return linkes Kind
     */
    public AVLNode getLeft()
    {
        return left;
    }
    
    /**
     * setter Methode für das linke Kind
     * @param newLeft Referenz auf neues linkes Kind
     */
    public void setLeft(AVLNode newLeft)
    {
        left=newLeft;
    }
    
    
    // bestimmt die Höhe eines Teilbaums, rekursiv
    // beim ersten Aufruf wird 1 als depth (Tiefe) übergeben
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
     * @param node Knoten für den der Balancefaktor bestimmt werden soll
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
     * Inorder Ausgabe des Wertes und der Balancefaktoren für den AVL Baum
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

