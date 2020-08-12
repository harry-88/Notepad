import javax.swing.*;
import java.awt.event.KeyEvent;

public class Format extends JFrame {
    private JMenu formatMenu;
    private ActionListeners action;

    public JMenu getFormatMenu() {
        return formatMenu;
    }
    /********Method which builds the format menu,adds menu items to format menu,
     adds key short cuts and adds action listeners to format items*******/

    public void buildFormatMenu()
    {
        //Creating the menu objects
        //The menu items of Format menu
        //WORD WRAP OBJECT
        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem("Word Wrap", true);
        wordWrapItem.setMnemonic(KeyEvent.VK_W);
        wordWrapItem.addActionListener(new ActionListeners.WordWrapListener());
        //FONT OBJECT
        JMenuItem fontItem = new JMenuItem("Font...");
        fontItem.setMnemonic(KeyEvent.VK_F);
        fontItem.addActionListener(new ActionListeners.FontListener());
        //BCK GROUND COLOR OBJECT
        JMenuItem backgroundColourItem = new JMenuItem("Background Colour...");
        backgroundColourItem.setMnemonic(KeyEvent.VK_B);
        backgroundColourItem.addActionListener(new ActionListeners.BackgroundListener());

        //Creating the menu
        formatMenu = new JMenu("Format");
        formatMenu.setMnemonic(KeyEvent.VK_O);

        //Add items to Format menu
        formatMenu.add(wordWrapItem);
        formatMenu.add(fontItem);
        formatMenu.add(backgroundColourItem);


    }
}