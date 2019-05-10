import java.util.Comparator;

public class INodeHValueComparator implements Comparator<INode>{

	@Override
	public int compare(INode o1, INode o2) {
		return (int) ((o1.getHValue()) - o2.getHValue());
	}

}
