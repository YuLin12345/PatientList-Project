/*
 * @author Yu Lin CSCI 260 - SPRING 2016 - Patient List Project using GUI
 */

package Patient;

public interface ListInterface {
	int size();

	void add(Patient element);

	boolean contains(Patient element);

	boolean remove(Patient element);

	Patient get(Patient element);

	@Override
	String toString();

	void reset();

	Patient getNext();
}