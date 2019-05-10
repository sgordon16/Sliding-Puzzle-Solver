import java.util.List;
import java.util.Random;

public class Program {

//	static final int[][] solution = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
	static final int[][] solution = new int[][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//	static final int[][] solution = new int[][] {{1,2}, {3,4}};

	public static void main(String[] args) {
		
		SlidingPuzzleNode solutionNode = new SlidingPuzzleNode(solution, null);
		System.out.println("Solution: \n" + solutionNode);
		AISearch searchBFS = new AISearch(solutionNode, new INodeHValueComparator());
		AISearch searchAstar = new AISearch(solutionNode, new INodeGandHValueComparator());
		SlidingPuzzleNode shuffledNode = new SlidingPuzzleNode(shuffleSlidingBoardByMoves(new SlidingPuzzleNode(solutionNode), 50));
		System.out.println("\nStarting layout: \n\n" + shuffledNode);
		System.out.println("\nHere are the moves.....\n");
		System.out.println("Best-First-Search:\n");
		searchBFS.search(new SlidingPuzzleNode(shuffledNode));
		System.out.println("\nA*:\n");
		searchAstar.search(new SlidingPuzzleNode(shuffledNode));
	}
	public static SlidingPuzzleNode shuffleSlidingBoardByMoves(SlidingPuzzleNode node, int moves) {
		Random rand = new Random();
		for(int i = 0; i < moves; i++) {
			List<INode> childNodes = node.getNextNodes();
			node = (SlidingPuzzleNode) childNodes.get(rand.nextInt(childNodes.size()));
		}
		return new SlidingPuzzleNode(node.getLayout(), null);
	}
	
	public static void shuffleSlidingBoardByManhattenDistance(SlidingPuzzleNode node, int mDistance) {
		Random rand = new Random();
		while(node.getGValue() < mDistance) { 
			List<INode> childNodes = node.getNextNodes();
			node = (SlidingPuzzleNode) childNodes.get(rand.nextInt(childNodes.size()));
		}
	}
}
