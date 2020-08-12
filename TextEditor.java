import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    protected FileOpt fMenu;                //Object of fileOpt class which makes file menu
    protected Edit eMenu;                   //Object of Edit class which makes Edit menu
    protected Format oMenu;                   //Object of Format class which makes Edit menu
    protected Help hMenu;                   //Object of Help class which makes Edit menu
    protected static FontFrame fontFrame;   //Object of FontFormat class
    protected static JTextArea editorText;  //Object of TextArea to make text area

    //Constructor
    public TextEditor() {
        setTitle("Untitled"+" -Notepad");                                                                    //Set the title
        setIconImage(Toolkit.getDefaultToolkit().getImage("Notepad.png"));                           //Set image
        setDefaultCloseOperation(EXIT_ON_CLOSE);                                                             //When the close button is clicked
        int NUM_CHARS = 40;                                                                                  //Number of chars in text area
        int NUM_LINES = 20;                                                                                  //Number of lines in text area
        editorText=new JTextArea(NUM_LINES, NUM_CHARS);                                                      //Making text area
        setVisible(true);                                                                                    //Makes the frame visible
        editorText.setLineWrap(true);                                                                        //Line wrapping
        editorText.setWrapStyleWord(true);                                                                   //Line wrap with respect to word
        editorText.setBackground(Color.lightGray);                                                           //Setting the background color of text area
        JScrollPane scrollPane=new JScrollPane(editorText);                                                  //Adding the text area to scroll pane
        setLookAndFeel();                                                                                    //Set look and feel of content pane
        add(scrollPane);                                                                                     //Adding scroll pane into frame
        buildMenuBar();                                                                                      //Building the menu bar
        setBounds(50,50,700,500);                                                          //Setting the bounds of frame
        setMinimumSize(new Dimension(300,300));                                                  //Setting the minimum bounds of frame
        editorText.setFont(new Font(editorText.getFont().getName(),editorText.getFont().getStyle(),13)); //set font size to 13
        fontFrame = new FontFrame(editorText);                                                                //Built Font tab

    }
    public void buildMenuBar() {


        fMenu =new FileOpt();               //Creating the fileOPt class object
        fMenu.buildFileMenu();              //Calling the function of FileOpt class to build file menu
        setTitle((ActionListeners.fileName)+" -Notepad");    //Update the name of frame if required
        eMenu=new Edit();                   //Creating the edit class object
        oMenu=new Format();                   //Creating the format class object
        hMenu=new Help();                   //Creating the help class object
        eMenu.buildEditMenu();              //Calling the function of Edit class to build Edit menu
        oMenu.buildFormatMenu();              //Calling the function of Format class to build Format menu
        hMenu.buildHelpMenu();              //Calling the function of Help class to build Help menu
        JMenuBar menuBar = new JMenuBar();  //Creating the menu bar
        menuBar.add(fMenu.getFileMenu());   //Adding the file menu to menu bar
        menuBar.add(eMenu.getEditMenu());   //Adding the edit menu to menu bar
        menuBar.add(oMenu.getFormatMenu());   //Adding the format menu to menu bar
        menuBar.add(hMenu.getHelpMenu());   //Adding the help menu to menu bar
        setJMenuBar(menuBar);                   //Setting the menu bar
        ActionListeners.undoHelper();       //Calling this method to undo and redo

    }

    //Method which sets the look and feel of content pane.
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error changing Look and feel");
        }
    }
}
