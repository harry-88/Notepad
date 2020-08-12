import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

/***************************************************************
 * Class in which action listeners of every class is implemented
 **************************************************************/
public class ActionListeners extends TextEditor
{
    public static String fileName="";           //The object of String which contains name of file.
    private static final UndoManager undoManager=new UndoManager(); //Undo manager class
    //Constructor of class
    public ActionListeners() {
    }

    /**************************************************
     * The action listeners for menu items of file menu
     * and called in FileOpt class
     *************************************************/

    //Action listener for *new* menu item of file menu
    public static class NewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.setText("");
            fileName="";
        }
    }

    //Action listener for *open* menu item of file menu
    public static class OpenListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int chooserStatus;
            JFileChooser fileChooser=new JFileChooser();
            chooserStatus=fileChooser.showOpenDialog(null);
            try {
                if(chooserStatus==JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile=fileChooser.getSelectedFile();
                    fileName=selectedFile.getPath();
                    if(!openFile(fileName))
                    {
                        JOptionPane.showMessageDialog(null, "Error reading"+
                                fileName,"Error",JOptionPane.ERROR_MESSAGE);
                        fileName="";
                    }
                }
            }catch (HeadlessException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }

        }
    }

    //Method which return true if the file can open and vice versa
    private static boolean openFile(String fileName)
    {
        boolean success;
        String inputLine;
        StringBuilder editorString= new StringBuilder();
        try {
            //OPEN THE FILE
            File file=new File(fileName);
            Scanner inputFile=new Scanner(file);
            //READ THE FILE CONTENT INTO THE EDITOR
            while (inputFile.hasNext())
            {
                inputLine=inputFile.nextLine();
                editorString.append(inputLine).append("\n");
            }
            //DISPLAY THE STRING
            editorText.setText(editorString.toString());
            //CLOSE THE FILE
            inputFile.close();
            success=true;
        }
        catch (IOException e)
        {
            success=false;
        }
        return success;
    }

    //Action listener for *save* menu item of file menu
    public static class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            int chooserStatus;
            JFileChooser fileChooser=new JFileChooser();
            chooserStatus=fileChooser.showSaveDialog(null);
            if(chooserStatus==JFileChooser.APPROVE_OPTION)
            {
                fileChooser.getSelectedFile();
            }
            if(!saveFile(fileName))
            {
                JOptionPane.showMessageDialog(null, "Error saving"+
                        fileName+"Error"+JOptionPane.ERROR_MESSAGE);
            }
        }
        //The method which returns true if file saves and vice versa
        private boolean saveFile(String fileName)
        {
            boolean success;
            String editorString;
            PrintWriter outputFile;
            try {
                //Open the file
                outputFile=new PrintWriter(fileName);
                editorString=editorText.getText();
                outputFile.print(editorString);
                //Close the file
                outputFile.close();
                success=true;
            }catch (IOException e)
            {
                success=false;
            }
            return success;
        }
    }

    //Action listener for *Page setup* menu item of file menu
    public static class PageSetUpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                PrinterJob printerJob;
                PageFormat pageFormat;
                printerJob = PrinterJob.getPrinterJob();
                pageFormat = printerJob.pageDialog(printerJob.defaultPage());
            }catch (SecurityException securityException)
            {
                JOptionPane.showMessageDialog(null,securityException.getMessage());
            }
        }
    }

    //Action listener for *print* menu item of file menu
    public static class PrintListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                editorText.print();
            }catch (PrinterException evt)
            {
                JOptionPane.showMessageDialog(editorText,evt.getMessage());
            }
        }
    }

    //Action listener for *exit* menu item of file menu
    public static class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int chooserStatus;
            try {
                if(fileName.equals(""))
                {
                    int choice=JOptionPane.showConfirmDialog(null,"Do you want to save changes");
                    if (choice==0)
                    {
                        JFileChooser fileChooser=new JFileChooser();
                        chooserStatus=fileChooser.showSaveDialog(null);
                        if(chooserStatus==JFileChooser.APPROVE_OPTION)
                        {
                            fileChooser.getSelectedFile();
                        }
                    }
                    else if(choice==1)
                    {
                        System.exit(0);
                    }
                }
                else {
                    System.exit(0);
                }
            }catch (HeadlessException headlessException){
                JOptionPane.showMessageDialog(null,headlessException.getMessage());
            }
        }
    }

    /*******************************************************************************************************
     * The Action listeners for menu items of Edit menu
     * and called in Edit class
     *******************************************************************************************************/

    //Action Listener for *undo* menu item of edit menu
    public static class UndoListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            undo();
        }
    }

    //Action Listener for *redo* menu item of edit menu
    public static class RedoListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            redo();
        }
    }

    //The method which helps in undo and redo
    public static void undoHelper(){
        editorText.getDocument().addUndoableEditListener(
                e -> undoManager.addEdit(e.getEdit()));
    }
    //Method which is called to *undo* an event
    public static void undo(){
        try {
            undoManager.undo();
        }catch (CannotUndoException cannotUndoException){
            JOptionPane.showMessageDialog(null,"Nothing to undo");
        }
    }
    //Method which is called to *redo* an event
    public static void redo(){
        try {
            undoManager.redo();
        }catch (CannotRedoException cannotRedoException){
            JOptionPane.showMessageDialog(null,"Nothing to redo");
        }
    }

    //Action Listener for *Cut* menu item of edit menu
    public static class CutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.cut();
        }
    }

    //Action Listener for *copy* menu item of edit menu
    public static class CopyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.copy();
        }
    }

    //Action Listener for *paste* menu item of edit menu
    public static class PasteListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.paste();
        }
    }

    public static StringBuilder stringBuilder;
    public static String findString;
    public static String replaceString;
    public static int index;

    //Action Listener for *Find* menu item of edit menu
    public static class FindListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                stringBuilder = new StringBuilder(editorText.getText());
                findString = JOptionPane.showInputDialog(null, "Find");
                index = stringBuilder.indexOf(findString);
                editorText.setCaretPosition(index);
                editorText.setSelectionStart(index);
                editorText.setSelectionEnd(index+findString.length());
            }
            catch(IllegalArgumentException illegalArgumentException)
            {
                JOptionPane.showMessageDialog(null, "String not found");
            }catch(NullPointerException ignored){}
        }
    }

    //Action Listener for *Find next* menu item of edit menu
    public static class FindNextListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try
            {
                stringBuilder = new StringBuilder(editorText.getText());
                if(findString!=null)
                {
                    index = stringBuilder.indexOf(findString, editorText.getCaretPosition());
                    editorText.setCaretPosition(index);
                    editorText.setSelectionStart(index);
                    editorText.setSelectionEnd(index+findString.length());
                }
                else {
                    JOptionPane.showMessageDialog(null,"Use the Find option first");
                }
            }
            catch(IllegalArgumentException illegalArgumentException)
            {
                JOptionPane.showMessageDialog(null, "No more String found");
            }catch(NullPointerException ignored){}
        }
    }

    //Action Listener for *Replace* menu item of edit menu
    public static class ReplaceListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                stringBuilder=new StringBuilder(editorText.getText());
                findString=JOptionPane.showInputDialog(null,"Replace");
                index=stringBuilder.indexOf(findString);
                editorText.setCaretPosition(index);
                editorText.setSelectionStart(index);
                editorText.setSelectionEnd(index+findString.length());
                replaceString=JOptionPane.showInputDialog(null,"Replace with");
                editorText.replaceSelection(replaceString);
            }catch(IllegalArgumentException illegalArgumentException)
            {
                JOptionPane.showMessageDialog(null, "String not found");
            }catch(NullPointerException ignored){}
        }
    }

    //Action Listener for *Select all* menu item of edit menu
    public static class SelectAllListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.selectAll();
        }
    }

    //Action Listener for *Delete* menu item of edit menu
    public static class DeleteListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.replaceSelection("");
        }
    }

    //Action Listener for *Goto* menu item of edit menu
    public static class GoToListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                if(editorText.getText().length()==0)
                    return;
                goTo();
            }catch (NullPointerException nullPointerException)
            {
                JOptionPane.showMessageDialog(null,nullPointerException.getMessage());
            }
        }
    }
    //The method which is called to take the line number and
    //point the cursor to given line
    private static void goTo()
    {
        int lineNumber;
        try{
            lineNumber=editorText.getLineOfOffset(editorText.getCaretPosition())+1;
            String str=JOptionPane.showInputDialog(null,"Enter line number:",""+lineNumber);
            if (str==null)
            {
                return;
            }
            lineNumber=Integer.parseInt(str);
            editorText.setCaretPosition(editorText.getLineStartOffset(lineNumber));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    //Action Listener for *Time and date* menu item of edit menu
    public static class TimeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                editorText.insert(new Date().toString(),editorText.getSelectionStart());
            }catch (IllegalArgumentException illegalArgumentException){
                JOptionPane.showMessageDialog(null,illegalArgumentException.getMessage());
            }
        }
    }


    //Action Listener for *applying and removing word wrap* menu item of Format menu
    public static class WordWrapListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(((JCheckBoxMenuItem)e.getSource()).getState())
            {
                editorText.setLineWrap(true);
                editorText.setWrapStyleWord(true);
            } else if(!((JCheckBoxMenuItem)e.getSource()).getState())
            {
                editorText.setLineWrap(false);
                editorText.setWrapStyleWord(false);
            }
        }
    }


    //Action Listener for *opening font dialog menu* item of Format menu
    public static class FontListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            FontFrame.fontText.setBackground(editorText.getBackground());
            fontFrame.setVisible(true);
        }
    }

    //Action Listener for *font* list item of font frame
    public static class ListListener implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent e) {
            (FontFrame.getFontList()).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            FontFrame.fontText.setFont(new Font(FontFrame.getFontModel().get(((JList<String>)e.getSource()).getSelectedIndex()), FontFrame.fontText.getFont().getStyle(), FontFrame.fontText.getFont().getSize()));
        }
    }

    //Action Listener for *size of font* drop down menu item of font frame
    public static class SizeListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e) {
            FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(), FontFrame.fontText.getFont().getStyle(), (FontFrame.getSizeModel().getElementAt(((JComboBox<Integer>)e.getSource()).getSelectedIndex()))));
        }
    }

    //Action Listener for *Bold* button item of font frame
    public static class BoldListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(!FontFrame.fontText.getFont().isBold()){
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),FontFrame.fontText.getFont().getStyle()|Font.BOLD,FontFrame.fontText.getFont().getSize()));
            } else if(FontFrame.fontText.getFont().isItalic()){
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),Font.ITALIC,FontFrame.fontText.getFont().getSize()));
            } else{
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),Font.PLAIN,FontFrame.fontText.getFont().getSize()));
            }
        }
    }

    //Action Listener for *Italic* button item of font frame
    public static class ItalicListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(!FontFrame.fontText.getFont().isItalic()){
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),FontFrame.fontText.getFont().getStyle()|Font.ITALIC,FontFrame.fontText.getFont().getSize()));
            } else if(FontFrame.fontText.getFont().isBold()){
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),Font.BOLD,FontFrame.fontText.getFont().getSize()));
            } else{
                FontFrame.fontText.setFont(new Font(FontFrame.fontText.getFont().getName(),Font.PLAIN,FontFrame.fontText.getFont().getSize()));
            }
        }
    }

    //Action Listener for *jColorChooser* button item of font frame
    public static class ColourListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            FontFrame.fontText.setForeground(JColorChooser.showDialog(null, "Font colour",FontFrame.fontText.getForeground()));
        }
    }
    //Action Listener for *OK button* button item of font frame
    //Used to apply the font changes
    public static class OkListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.setFont(FontFrame.fontText.getFont());
            editorText.setForeground(FontFrame.fontText.getForeground());
            fontFrame.dispose();
        }
    }

    //Action Listener for *Bold* button item of font frame
    //Used to exit font frame without applying changes
    public static class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            fontFrame.dispose();
        }
    }

    //Action Listener for *JColorChooser* item of Format menu
    //Used to apply back ground color
    public static class BackgroundListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            editorText.setBackground(JColorChooser.showDialog(null, "Background Color", editorText.getBackground()));
        }
    }

    //Action Listener for *About frame* item of Help menu
    public static class AboutListener extends JFrame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setTitle("About");
            setIconImage(Toolkit.getDefaultToolkit().getImage("About.png"));
            JTextArea aboutText = new JTextArea();
            setBounds(200,200,570,300);
            aboutText.setLineWrap(true);
            aboutText.setWrapStyleWord(true);
            aboutText.setText("This is a free piece of software. This program is still in testing phase, so bugs might be expected.If you have any suggestions or bugs to report, please contact one of the developers below. Any suggestions or bugs reported will be hugely appreciated.\nName\t\t\t  Roll Number    Email\nMohammad Abubakar Iftikhar    F2019266396    f2019266396@umt.edu.pk\nMuhammad Abdullah\t     F2019266065    f2019266065@umt.edu.pk\n\nVersion 1.0 a\nThis is an unlicensed software.\n\nWe are not responsible for any data loss through the use of this software.");
            aboutText.setEditable(false);
            add(aboutText);
            setAlwaysOnTop(true);
            setVisible(true);
        }
    }


}