import javax.swing.*;
import java.awt.event.KeyEvent;

public class Help extends JFrame {
    private JMenu helpMenu;
    private ActionListeners action;

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public void buildHelpMenu()
    {
        //Creating the menu objects
        //VIEW HELP OBJECT
        //ABOUT OBJECT
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListeners.AboutListener());

        //Creating the menu
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        //Add items to Help menu
        helpMenu.add(aboutItem);

    }
}