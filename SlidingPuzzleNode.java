import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SlidingPuzzleNode implements INode {

	private int level;
	private INode parent;
	private int[][] layout;
	private int space;
	
	public SlidingPuzzleNode(int[][] layout, INode parent) {
		this.layout = layout;
		space = layout.length * layout[0].length;
		this.parent = parent;
		this.level = parent == null ? 0 : parent.getLevel() + 1;
	}
	
	public SlidingPuzzleNode(SlidingPuzzleNode spn) {
		this.layout = deepCopy2dArray(spn.layout);
		this.parent = spn.parent;
		this.space = spn.space;
		this.level = spn.level;
	}
	@Override
	public List<INode> getNextNodes() {
		List<INode> childNodes = new LinkedList<INode>();
		int x = this.getIndexOfSpace() / layout.length;
		int y = this.getIndexOfSpace() % layout.length;
		if(x > 0) {
			int[][] childLayout = deepCopy2dArray(layout);
			childLayout[x][y] = childLayout[x - 1][y];
			childLayout[x - 1][y] = space;
			childNodes.add(new SlidingPuzzleNode(childLayout, this));
		}
		if(x < layout.length - 1) {
			int[][] childLayout = deepCopy2dArray(layout);
			childLayout[x][y] = childLayout[x + 1][y];
			childLayout[x + 1][y] = space;
			childNodes.add(new SlidingPuzzleNode(childLayout, this));
		}
		if(y > 0) {
			int[][] childLayout = deepCopy2dArray(layout);
			childLayout[x][y] = childLayout[x][y - 1];
			childLayout[x][y - 1] = space;
			childNodes.add(new SlidingPuzzleNode(childLayout, this));
		}
		if(y < layout[x].length - 1) {
			int[][] childLayout = deepCopy2dArray(layout);
			childLayout[x][y] = childLayout[x][y + 1];
			childLayout[x][y + 1] = space;
			childNodes.add(new SlidingPuzzleNode(childLayout, this));
		}
		return childNodes;
	}

	
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public INode getParent() {
		return parent;
	}

	@Override
	public double getHValue() {
		int hValue = 0;
		for(int i = 0; i < layout.length; i++) {
			for(int j = 0; j < layout[i].length; j++) {
				if(layout[i][j] == 0)
					continue;
				int positionValue = layout[i][j];
				int positionX = i;
				int positionY = j;
				int destinationX = positionValue / 4;
				int destinationY = (positionValue % 4) -1;
				hValue += (Math.max(positionX, destinationX) - Math.min(positionX, destinationX) + 
						Math.max(positionY, destinationY) - Math.min(positionY, destinationY));
			}
		}
		return hValue;
	}
	
	private int[][] deepCopy2dArray(int[][] input) {
	    if (input == null)
	        return null;
	    int[][] result = new int[input.length][];
	    for (int i = 0; i < input.length; i++) {
	        result[i] = input[i].clone();
	    }
	    return result;
	}

	@Override
	public double getGValue() {
		return level;
	}

	public int[][] getLayout() {
		return layout;
	}
	
	public int getIndexOfSpace() {
		int index = 0;
		//find the space x and y location
		for(int i = 0; i < layout.length; i++) {
			for(int j = 0; j < layout[i].length; j++) {
				if(layout[i][j] == space) {
					index = ((i * layout.length) + j);
				}
			}
		}
		return index;
	}
	@Override
	public String toString() {
		/* -----------------
		 * | 1 | 2 | 3 | 4 |
		 * -----------------
		 * | 5 | 6 | 7 | 8 |
		 * -----------------
		 * | 9 | 10| 11| 12|
		 * -----------------
		 * | 13| 14| 15|   |
		 * -----------------
		*/   
		StringBuilder sb = new StringBuilder();
		String line = "";
		for(int i = 0; i < layout.length; i++) {
			line += "----";
			if(i % 4 == 0)
				line += "-";
		}	
		for(int i = 0; i < layout.length; i++) {
			sb.append("\n" + line + "\n| ");
			for(int j = 0; j < layout[i].length; j++) {
				if(layout[i][j] == space)
					sb.append("  | ");
				else {
					sb.append(layout[i][j]);
					sb.append(layout[i][j] > 9 ? "| " : " | ");
				}
						
			}
		}
		sb.append("\n" + line);
		
		return sb.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(layout);
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
		SlidingPuzzleNode other = (SlidingPuzzleNode) obj;
		if (!Arrays.deepEquals(layout, other.layout))
			return false;
		return true;
	}
}
