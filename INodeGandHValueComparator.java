import java.util.Comparator;

public class INodeGandHValueComparator implements Comparator<INode> {

	@Override
	public int compare(INode o1, INode o2) {
		return (int) ((o1.getHValue() + o1.getGValue()) - (o2.getHValue() + o2.getGValue()));
	}

}
