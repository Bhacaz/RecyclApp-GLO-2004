package recyclapp.gui.listeners.mouse;

import recyclapp.gui.MainWindow;
import recyclapp.gui.MainWindow.ApplicationMode;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class ModeHandleListener extends MouseAdapter {

	final MainWindow mainWindow;

	public ModeHandleListener(MainWindow outer) {
		this.mainWindow = outer;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point mousePoint = e.getPoint();
		mainWindow.actualMousePoint = e.getPoint();
		//ApplicationMode actualMode = mainWindow.getActualMode();
//		if (actualMode == ApplicationMode.ADD) {
//			ajoutEntreeDialog ajoutEntree = new ajoutEntreeDialog(mainWindow,true);
//                        ajoutEntree.setVisible(true);
//		} //else if (actualMode == ApplicationMode.SELECT
		//		&& SwingUtilities.isLeftMouseButton(e)) {
		//	mainWindow.controller.switchSelection(e.getPoint().x,
		//			e.getPoint().y);
		//}
	}
}
