package recyclapp.gui.listeners.action;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import recyclapp.gui.MainWindow;
import recyclapp.gui.MainWindow.ApplicationMode;


public class AdditionModeAction implements ActionListener {

	private final MainWindow outer;

	public AdditionModeAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//outer.setMode(ApplicationMode.ADD);           
	}
}
