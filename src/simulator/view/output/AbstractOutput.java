/**
 * 
 */
package simulator.view.output;

import java.util.Observable;
import java.util.Observer;

import simulator.model.Model;

/**
 * @author rc
 *
 */
public abstract class AbstractOutput implements Observer {

	protected Model model;

	/**
	 * 
	 */
	public AbstractOutput(Model model2) {
		model = model2;
		model.addObserver(this);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	abstract public void update(Observable o, Object arg);

}
