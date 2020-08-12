import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Edit extends JFrame  {
    //Edit menu
    private JMenu EditMenu;
    //Menu items of edit menu
    public JMenuItem undoItem;      //Menu item to undo action
    public JMenuItem redoItem;      //Menu item to redo action
    private JMenuItem cutItem;      //Menu item to cut selected text
    private JMenuItem copyItem;     //Menu item to copy selected text
    private JMenuItem deleteItem;   //Menu item to delete selected text
    private JMenuItem findItem;     //Menu item to find a text
    private JMenuItem findNextItem; //Menu item to find same text next
    private JMenuItem replaceItem;  //Menu item to replace some text
    private JMenuItem selectAllItem; //Menu item to select all text in text editor

    public JMenu getEditMenu() {
        return EditMenu;
    }

    /****  Method which builds the edit menu,menu items of edit menu,
     * adds short cuts,action listeners and adds menu items to edit menu ****/
    public void buildEditMenu(){
        EditMenu=new JMenu("Edit");     //Edit Menu

        undoItem = new JMenuItem("Undo");       //Undo menu item of Edit menu
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListeners.UndoListener());//Performs action when undo menu item is clicked

        redoItem = new JMenuItem("Redo");       //Redo menu item of Edit menu
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListeners.RedoListener());//Performs action when redo menu item is clicked

        cutItem = new JMenuItem("Cut");         //Cut menu item of Edit menu
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cutItem.addActionListener(new ActionListeners.CutListener());//Performs action when cut menu item is clicked

        copyItem = new JMenuItem("Copy");       //Copy menu item of Edit menu
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        copyItem.addActionListener(new ActionListeners.CopyListener());//Performs action when copy menu item is clicked

        //Menu item to paste selected text
        JMenuItem pasteItem = new JMenuItem("Paste");       //Paste menu item of Edit menu
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        pasteItem.addActionListener(new ActionListeners.PasteListener());//Performs action when paste menu item is clicked

        deleteItem = new JMenuItem("Delete");       //Delete menu item of Edit menu
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteItem.addActionListener(new ActionListeners.DeleteListener());//Performs action when delete menu item is clicked

        findItem = new JMenuItem("Find...");        //Find menu item of Edit menu
        findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        findItem.addActionListener(new ActionListeners.FindListener());//Performs action when find menu item is clicked

        findNextItem = new JMenuItem("Find Next");     //Find next menu item of Edit menu
        findNextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
        findNextItem.addActionListener(new ActionListeners.FindNextListener());//Performs action when find next menu item is clicked

        replaceItem = new JMenuItem("Replace...");    //Replace menu item of Edit menu
        replaceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        replaceItem.addActionListener(new ActionListeners.ReplaceListener());//Performs action when replace menu item is clicked

        JMenuItem goToItem = new JMenuItem("Go To...");         //Goto menu item of Edit menu
        goToItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        goToItem.addActionListener(new ActionListeners.GoToListener());//Performs action when goto menu item is clicked

        selectAllItem = new JMenuItem("Select All");  //Select all menu item of Edit menu
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        selectAllItem.addActionListener(new ActionListeners.SelectAllListener());//Performs action when select all menu item is clicked

        JMenuItem timeItem = new JMenuItem("Time/Date");        //Time and date menu item of Edit menu
        timeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
        timeItem.addActionListener(new ActionListeners.TimeListener());//Performs action when time and date menu item is clicked

        //Adding menu items to Edit menu
        EditMenu.add(undoItem);     //Adding undo menu item to edit menu
        EditMenu.add(redoItem);     //Adding redo menu item to edit menu
        EditMenu.addSeparator();    //Adding separator to edit menu
        EditMenu.add(cutItem);      //Adding cut menu item to edit menu
        EditMenu.add(copyItem);     //Adding copy menu item to edit menu
        EditMenu.add(pasteItem);    //Adding paste menu item to edit menu
        EditMenu.add(deleteItem);   //Adding delete menu item to edit menu
        EditMenu.addSeparator();    //Adding separator to edit menu
        EditMenu.add(findItem);     //Adding find menu item to edit menu
        EditMenu.add(findNextItem); //Adding find next menu item to edit menu
        EditMenu.add(replaceItem);  //Adding replace menu item to edit menu
        EditMenu.add(goToItem);     //Adding goto menu item to edit menu
        EditMenu.addSeparator();    //Adding separator to edit menu
        EditMenu.add(selectAllItem);//Adding select all menu item to edit menu
        EditMenu.add(timeItem);     //Adding time and date menu item to edit menu
        //Adding menu listener to edit menu
        EditMenu.addMenuListener(editListener);
    }
    /******Implementation of an interface menu listener which
     sets the menu items of edit menu enabled and disabled according to requirements */
    MenuListener editListener=new MenuListener() {
        @Override
        public void menuSelected(MenuEvent e) {
            //The condition which sets menu items disabled if there is no text written in text editor
            if (TextEditor.editorText.getText().length() == 0) {
                replaceItem.setEnabled(false);
                findItem.setEnabled(false);
                findNextItem.setEnabled(false);
                selectAllItem.setEnabled(false);
            }
            //The condition which sets menu items enabled if there is some text written in text editor
            else {
                replaceItem.setEnabled(true);
                findItem.setEnabled(true);
                findNextItem.setEnabled(true);
                selectAllItem.setEnabled(true);
            }
            //The condition which sets menu items disabled if some text is not selected
            if(TextEditor.editorText.getSelectionStart()==TextEditor.editorText.getSelectionEnd())
            {
                cutItem.setEnabled(false);
                copyItem.setEnabled(false);
                deleteItem.setEnabled(false);
            }
            //The condition which sets menu items enabled if some text is selected
            else {
                cutItem.setEnabled(true);
                copyItem.setEnabled(true);
                deleteItem.setEnabled(true);
            }
        }
        @Override
        public void menuDeselected(MenuEvent e) {}
        @Override
        public void menuCanceled(MenuEvent e) {}
    };

}