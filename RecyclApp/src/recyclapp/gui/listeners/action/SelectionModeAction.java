package recyclapp.gui.listeners.action;

import recyclapp.gui.MainWindow;
import recyclapp.gui.MainWindow.ApplicationMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionModeAction implements ActionListener {

	private final MainWindow outer;

	public SelectionModeAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//outer.setMode(ApplicationMode.SELECT);
	}
}
