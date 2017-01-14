/*
 * @author Yu Lin CSCI 260 - SPRING 2016 - Patient List Project using GUI
 */

package Patient;

public class LLNode {
	public LLNode(Patient info) {
		this.info = info;
		link = null;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Object getInfo() {
		return info;
	}

	public void setLink(LLNode link) {
		this.link = link;
	}

	public LLNode getLink() {
		return link;
	}

	private LLNode link;
	private Object info;
}