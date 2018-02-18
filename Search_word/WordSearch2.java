//package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Searches for a word in a 2-D character array. Same character can be present at two different locations in the array.
 * Uses DFS to search the input array in the board.
 */
public class WordSearch2 {
 private char[][] board;
 private int ROW, COL;
 
 public WordSearch2(char[][] board) {
  super();
  this.board = board;
  this.ROW = board.length;
  this.COL = board[0].length;
 }

 /**
  * DFS search.
  * 
  * @param yetToBeSearchedInputStr
  *            string which is yet to get searched in the 2-D array
  * @param currPos
  *            abstracts the character and it's row, col in the 2-D board
  * @param alreadyTravelled
  *            stores nodes which are already covered/travelled
  * @return true if the word was found in the board; false otherwise
  */
 private boolean search(String yetToBeSearchedInputStr, Node currPos,
   Set<Node> alreadyTravelled) {
  if (Objects.isNull(yetToBeSearchedInputStr)
    || yetToBeSearchedInputStr.length() == 0) {
   return true;
  }

  alreadyTravelled.add(currPos);
  List<Node> neighbors = getNeighbors(currPos);
  for (Node node : neighbors) {
   if (!alreadyTravelled.contains(node)
     && node.ch == yetToBeSearchedInputStr.charAt(0)) {
    return search(yetToBeSearchedInputStr.substring(1), node,
      alreadyTravelled);
   }
  }
  return false;
 }

 /**
  * Returns all valid neighbors (left, right, up, down) of the given node
  * @param currPos node for which all neighbors needs to be found
  * @return list of neighbors
  */
 private List<Node> getNeighbors(Node currPos) {
  int row = currPos.row;
  int col = currPos.col;

  List<Node> neighbors = new ArrayList<>();
  if (col - 1 >= 0) {
   neighbors.add(new Node(board[row][col - 1], row, col - 1));
  }
  if (col + 1 < COL) {
   neighbors.add(new Node(board[row][col + 1], row, col + 1));
  }
  if (row - 1 >= 0) {
   neighbors.add(new Node(board[row - 1][col], row - 1, col));
  }
  if (row + 1 < ROW) {
   neighbors.add(new Node(board[row + 1][col], row + 1, col));
  }
  return neighbors;
 }

 @Override
 public String toString() {
  for (int i = 0; i < ROW; i++) {
   System.out.println();
   for (int j = 0; j < COL; j++) {
    System.out.print(board[i][j] + " ");
   }
  }
  return "\n ROW=" + ROW + ", COL=" + COL;
 }

 /**
  * Abstracts the character and it's position (row and col) in the board.
  * This is required as same character can be present at two different
  * locations.
  */
 private class Node {
  char ch;
  int row;
  int col;

  public Node(int r, int c) {
   this.row = r;
   this.col = c;
  }

  public Node(char ch, int r, int c) {
   this(r, c);
   this.ch = ch;
  }
  
  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + getOuterType().hashCode();
   result = prime * result + ch;
   result = prime * result + col;
   result = prime * result + row;
   return result;
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (obj == null)
    return false;
   if (getClass() != obj.getClass())
    return false;
   Node other = (Node) obj;
   if (!getOuterType().equals(other.getOuterType()))
    return false;
   if (ch != other.ch)
    return false;
   if (col != other.col)
    return false;
   if (row != other.row)
    return false;
   return true;
  }

  @Override
  public String toString() {
   return "Node [ch=" + ch + ", row=" + row + ", col=" + col + "]";
  }

  private WordSearch2 getOuterType() {
   return WordSearch2.this;
  }
 }

 /**
  * Test method
  */
 public static void main(String[] args) {
  char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
    { 'A', 'D', 'E', 'E' } };
  WordSearch2 wordSearch = new WordSearch2(board);
  System.out.println(wordSearch);
  Set<Node> alreadyTravelled = new HashSet<>();
  String inputString = "SEE";
//  String inputString = "FCS";

  //first find if the first character exists in the board. Start below search for all the positions where it could be found
  //Not given logic to find the first character in the board.

  boolean result=false;
  
  outer:for(int row=0; row<board.length;row++){
	
	  for(int col=0; col<board[row].length;col++){
		
		  result = wordSearch.search(inputString.substring(1),
				    wordSearch.new Node(board[row][col], row, col), alreadyTravelled);
		  if(result)
			  break outer;
	  }
  }

  System.out.println("\nInput String, "+ inputString +" exists in the 2-D character array? " + result);
 }
}