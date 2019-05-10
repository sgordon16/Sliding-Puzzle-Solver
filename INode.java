import java.util.List;

public interface INode {

	List<INode> getNextNodes();
	int getLevel();
	INode getParent();
	double getHValue();
	double getGValue();
	boolean equals(Object obj);
}
