import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class AISearch implements IPuzzleSearch {
	
	private PriorityQueue<INode> nodes; 
	private HashMap<Integer, Integer> coloredNodes = new HashMap<Integer, Integer>();
	INode solution; 
	
	public AISearch(INode solution, Comparator<INode> comparator) {
		this.solution = solution;
		nodes = new PriorityQueue<INode>(comparator);
	}
	
	@Override
	public void search(INode node) {
		coloredNodes.put(node.hashCode(), node.getLevel());
		while(!node.equals(solution)) {
			//System.out.println(nodes.peek());
			for(INode n : node.getNextNodes()) {
				if(!(coloredNodes.containsKey(n.hashCode()) && n.getLevel() > coloredNodes.get(n.hashCode()))) {
					nodes.add(n);
					coloredNodes.put(n.hashCode(), n.getLevel());
				}
			}
			node = nodes.poll();
		}
		Stack<String> stack = new Stack<String>();
		while(node != null) {	
			stack.add(node.toString());
			node = node.getParent();
		}
		while(!stack.isEmpty())
			System.out.println(stack.pop());
	}
}
