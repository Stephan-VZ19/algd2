/**
 * ETH Zürich; Leitprogramm; Binäre Suchbäume ----- 
 * Das ist die Klasse eines einzelnen Knotens des Binären Suchbaums. 
 * @author Björn Steffen, Timur Erdag, Christina Class
 * @version 1.0 
 */
public class BinSearchTree {
    private int key;
    private BinSearchTree left, right;
    
    public BinSearchTree( int key ) {
        this.key = key;
        left=null;
        right=null;
    }
    
    public BinSearchTree( int key, BinSearchTree left, BinSearchTree right ) {
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
    public BinSearchTree getRight()
    {
        return right;
    }
    
    /**
     * setter Methode für das rechte Kind
     * @param newRight Referenz auf neues rechtes Kind
     */
    public void setRight(BinSearchTree newRight)
    {
        right=newRight;
    }
    
    /**
     * getter Methode für das linke Kind
     * @return linkes Kind
     */
    public BinSearchTree getLeft()
    {
        return left;
    }
    
    /**
     * setter Methode für das linke Kind
     * @param newLeft Referenz auf neues linkes Kind
     */
    public void setLeft(BinSearchTree newLeft)
    {
        left=newLeft;
    }
    
    /**
     * Inorder Ausgabe für den binären Suchbaum
     * @param node Wurzelknoten des binären Suchbaums, der mit inorder ausgegeben werden soll
     */  
    public void inorder(BinSearchTree node) {
        if (node != null) {
            inorder (node.getLeft());
            System.out.print (" " + node.getKey() + " ");
            inorder (node.getRight());
        }
    }
 

   
    /** 
     * Die Methode gibt zurück, ob der übergebene Wert im Baum vorhanden ist.
     * @param node die Wurzel des Suchbaumes
     * @param key der Suchschlüssel
     * @return ob die Suche erfolgreich war
     */
    // Implementiere diese Methode nach den Vorgaben der Aufgabe 4.3.
    // Geben Sie true zurück, wenn der Wert gefunden wurde,
    // false sonst.
    public boolean search(BinSearchTree node, int key) {
        if (node != null) {
            if (key < node.getKey()) {
                return search(node.getLeft(), key);
            }
            else {
                if (key > node.getKey()) {
                    return search(node.getRight(), key);
                }
                else {
                    return true;
                }
            }
        }
        else {
            return false;
        }
      }

 /*
      // das ist die Lösung für die Additum-Aufgabe 4.5
      
      Beginn JavaDoc des Additum:
      * Die Methode gibt zurück, ob der übergebene Wert im Baum vorhanden ist.
      * Auch werden alle Vorkommen des Schlüssels ausgegeben. (Aufgabe 4.5)
      * @param node die Wurzel des Suchbaumes
      * @param key der Suchschlüssel
      * @return ob die Suche erfolgreich war
      Ende JavaDoc des Additum
 
      public boolean search(BinSearchTree node, int key) {
        if (node != null) {
            if (key < node.getKey()) {
                return search(node.getLeft(), key);
            }
            else {
                if (key > node.getKey()) {
                    return search(node.getRight(), key);
                }
                else {
                    System.out.println("gefunden:" + node.getKey());
                    search(node.getRight(), key);
                    return true;
                }
            }
        }
        else {
            return false;
        }
      }   
   
    */
   
    /**
     * Diese Methode fügt einen neuen Knoten mit dem Schlüssel key in den
     * binären Suchbaum ein.
     * Aufruf dieser Methode für einen Baum mit Wurzel root mit dem Wert 10: 
     * root = insert (root, 10);
     * @param node Wurzel des Baumes
     * @param Schlüsselwert, den der neue Knoten haben soll
     * @return die neue Wurzel des Baumes.  
     */
    // Implementieren Sie diese Methode nach den Vorgaben der Aufgabe.
    // Geben Sie zur Kontrolle den Wert jedes besuchten Knoten aus!

    public BinSearchTree insert(BinSearchTree node, int key) 
    {
        if (node != null) {
            System.out.print (node.getKey() + " ");
            if (key < node.getKey()) {
                node.setLeft(insert(node.getLeft(), key));
            } 
            else {
                node.setRight(insert(node.getRight(), key));                
            }
        } 
        else {
            node = new BinSearchTree(key);
        }
        return node;
    }


    /**
     * Diese Methode löscht alle Knoten mit dem Schlüssel key in dem
     * binären Suchbaum. (Aufgabe 4.13. (Additum))
     * Aufruf dieser Methode für einen Baum mit Wurzel root mit dem Schlüsselwert 10: 
     * root = delete(root, 10);
     * @param node Wurzel des Baumes
     * @param Schlüsselwert, den der neue Knoten haben soll
     * @return die neue Wurzel des Baumes.  
     */   
    // Als Additum kann auch das Löschen implementiert werden
    // als //# taucht immer die Zeile des Pseudocodes in der Musterlösung des Leitprogramms auf
    public BinSearchTree delete(BinSearchTree node, int key) 
    {
    	//# 2
        if (node != null) { 
        	//#3
            if (key < node.getKey()) { 
            	//# 4
            	// zu löschender Knoten muss im linken Teilbaum gesucht werden
                node.setLeft(delete(node.getLeft(), key));
            }
            //# 5
            else {
            	//# 6
                if (key > node.getKey()) {
                	//# 7
                	// zu löschender Knoten muss im rechten Teilbaum gesucht werden
                    node.setRight(delete(node.getRight(), key));
                }
                //# 8
                else {
          			//# 11
                    // aktueller Knoten ist zu löschen
                    if (node.getLeft() == null) {
                    	//# 12
                        if (node.getRight() == null) {
                            // Fall 1: zu entfernender Knoten ist ein Blatt
                            //# 14
                            node =  null;
                        }
                        //# 15
                        else {
                        	//# 17
                            // Fall 2: der zu entfernde Knoten ist ein innerer
                            // Knoten mit einem Kind rechts
                            node = node.getRight();       
                        }
                    } 
                    //# 19
                    else { // das linke Kind des aktuellen Knoten existiert
                    	//# 20
                        if (node.getRight() == null) {
                        	//# 22
                            // Fall 2: der zu entfernde Knoten ist ein innerer
                            // Knoten mit einem Kind links
                            node =  node.getLeft();
                        }
                        //# 23
                        else { // Fall 3
                        
                        	//# 25
                            // suche Nachfolger
                            // um den Nachfolger zu ersetzen, muss man den Vater des Nachfolgers finden
                            // (es muss ja der Verweis im Vaterknoten geändert werden)
                            BinSearchTree nachfolgerVater = node;
                            BinSearchTree nachfolger = node.getRight();
                            if (node.getRight().getLeft() != null) {
                            	// der rechte Sohn ist nicht der Nachfolger (Knoten ist also auch nicht der NachfolgerVater)
                                nachfolgerVater = node.getRight();
                                while (nachfolgerVater.getLeft().getLeft() != null) {
                                    nachfolgerVater = nachfolgerVater.getLeft();
                                }
                                // merke den Nachfolgerknoten
                                nachfolger = nachfolgerVater.getLeft();
                            }
                        
                            //# 26 und 27                        
                            // lösche den Nachfolger (ersetze ihn durch seinen rechten Sohn
                            // je nachdem, ob der zu löschende Knoten der Vater des Nachfolgers ist, oder nicht, müssen die Verweise
                            // anders gesetzt werden
                            if (node == nachfolgerVater) {
                            	// in diesem Fall gilt ja: nachfolger = node.right
                            	// es gibt kein: node.right.left 
                            	// node kann also direkt durch Nachfolger ersetzt werden (der sich allerdings den linken Teilbaum von node
                            	// merken muss)
                                nachfolger.setLeft(node.getLeft());
                                node = nachfolger; 
                            }
                            else { 
                            	// ersetze den Nachfolger Knoten durch seinen linken Sohn
                                nachfolgerVater.setLeft(nachfolger.getRight());
                                // der Nachfolger Knoten merkt sich den linken und den rechten Teilbaum von node
                                nachfolger.setLeft(node.getLeft());
                                nachfolger.setRight(node.getRight());
                                // node wird zum Nachfolger gesetzt
                                node=nachfolger;
                            }
                        } // endif node.getRight() == null
                    }  // endif node.getLeft() == null
                    // der Knoten ist gelöscht
                    // wir löschen nun im verbleibenden Baum weitere, evtl. vorhandene Vorkommnisse des Knotens
                    //# 30
                    if (node != null) 
                        node = node.delete(node, key);
                } // endif key > node.getKey()
            } // endif key < node.getKey()
        } // endif node != null
        return node;
    }
}

