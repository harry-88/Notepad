import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FileOpt extends JFrame {
    private JMenu fileMenu;
    //Constructor of the class
    public FileOpt() {
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }
    /********Method which builds the file menu,adds menu items to it,
     adds key short cuts and adds action listeners to menu items*******/
    public void buildFileMenu()
    {
        //The menu items of File menu
        JMenuItem newItem = new JMenuItem("New");       //NEW menu item
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));//Sets the short key to new
        newItem.addActionListener(new ActionListeners.NewListener());   //Performs action when new menu item is clicked

        JMenuItem openItem = new JMenuItem("Open");     //OPEN menu item
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));//Sets the short key to open
        openItem.addActionListener(new ActionListeners.OpenListener()); //Performs action when open menu item is clicked

        JMenuItem saveItem = new JMenuItem("Save");     //SAVE menu item
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));//Sets the short key to save
        saveItem.addActionListener(new ActionListeners.SaveListener()); //Performs action when save menu item is clicked

        JMenuItem saveAsItem = new JMenuItem("SaveAs");     //SAVE AS menu item
        saveAsItem.addActionListener(new ActionListeners.SaveListener());   //Performs action when save as menu item is clicked

        JMenuItem pageSetupItem = new JMenuItem("Page Setup...");       //PAGE SETUP menu item
        pageSetupItem.addActionListener(new ActionListeners.PageSetUpListener());   //Performs action when page st up menu item is clicked

        JMenuItem printItem = new JMenuItem("Print...");            //PRINT menu item
        printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));//Sets the short key to print
        printItem.addActionListener(new ActionListeners.PrintListener());//Performs action when print menu item is clicked

        JMenuItem exitItem = new JMenuItem("Exit");     //EXIT OBJECT
        exitItem.addActionListener(new ActionListeners.ExitListener()); //Performs action when exit menu item is clicked

        fileMenu=new JMenu("File");         //Creating the menu

        //Adding items to File menu
        fileMenu.add(newItem);      //Adding new menu item to file menu
        fileMenu.add(openItem);     //Adding open menu item to file menu
        fileMenu.add(saveItem);     //Adding save menu item to file menu
        fileMenu.add(saveAsItem);   //Adding saveAs menu item to file menu
        fileMenu.addSeparator();    //Adding separator to file menu
        fileMenu.add(pageSetupItem);//Adding page setup menu item to file menu
        fileMenu.add(printItem);    //Adding print menu item to file menu
        fileMenu.addSeparator();    //Adding separator to file menu
        fileMenu.add(exitItem);     //Adding exit menu item to file menu
    }
}