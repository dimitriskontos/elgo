package beans;
import org.apache.struts.tiles.beans.SimpleMenuItem;
public class simplemen extends SimpleMenuItem{
	private boolean hasChild=false;
	public boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public simplemen()
	{
		super();
	}
	
	
}
