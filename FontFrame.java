import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FontFrame extends JFrame {

    protected static JTextArea fontText;
    protected JScrollPane fontPane;
    protected String[] fonts;
    protected static JList<String> fontList;
    protected static DefaultListModel<String> fontModel;
    protected static JComboBox<Integer> fontSize;
    protected static DefaultComboBoxModel<Integer> sizeModel;
    protected JButton bold;
    protected JButton italic;
    protected JButton colour;
    protected JButton ok;
    protected JButton cancel;
    public FontFrame(JTextArea editorText)
    {
        setTitle("Font");           //Setting title of frame
        setIconImage(Toolkit.getDefaultToolkit().getImage("Font.png"));     //Set image
        JLabel labelFont = new JLabel("Fonts");         //Label of list
        labelFont.setFont(new Font("Ariel", Font.PLAIN, 13));       //Font of list
        setLayout(null);        //Setting layout type
        setBounds(300, 300,400,400);    //Setting the size of window
        fontList = new JList<String>();     //Initialing fontListener
        fontModel = new DefaultListModel<String>();     //Initialing fontModel
        fontSize = new JComboBox<Integer>();        //Initialing fontSize drop down list
        sizeModel = new DefaultComboBoxModel<Integer>();        //Initialing fontModel
        Border fontListBorder = BorderFactory.createLineBorder(Color.lightGray, 1);     //initializing border for fontList
        bold = new JButton("B");        //Initialing Bold button
        italic = new JButton("I");      //Initialing Italic button
        colour = new JButton("Colour");     //Initialing Color button
        fontText = new JTextArea("This is sample text.");       //Initialing text area button
        fontText.setEditable(false);           //Making text area non-editable
        fontText.setBackground(editorText.getBackground());     //Getting background color of fontText
        fontText.setForeground(editorText.getForeground());     //Getting text color of fontText
        bold.setFont(new Font("Ariel", Font.BOLD, 11));          //Setting font for Bold button
        italic.setFont(new Font("Elephant", Font.ITALIC, 14));          //Setting font for Italic button
        colour.setFont(new Font("Ariel", Font.PLAIN, 11));          //Setting font for Color button
        ok = new JButton("Ok");        //Initialing Ok button
        cancel = new JButton("Cancel");        //Initialing Cancel button
        bold.addActionListener(new ActionListeners.BoldListener());//Performs action when bold button of font frame is clicked
        italic.addActionListener(new ActionListeners.ItalicListener());//Performs action when bold button of font frame is clicked
        colour.addActionListener(new ActionListeners.ColourListener());//Performs action when bold button of font frame is clicked
        ok.addActionListener(new ActionListeners.OkListener());//Performs action when bold button of font frame is clicked
        cancel.addActionListener(new ActionListeners.CancelListener());//Performs action when bold button of font frame is clicked
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();//Performs action when bold button of font frame is clicked
        //adding font to font model
        for (String s : fonts) {
            fontModel.addElement(s);
        }
        //adding size to size drop down model
        for (int i=8; i<=72; i++) {
            if(i<=14)
                sizeModel.addElement(i);
            else if(i<=28 && i%2==0) {
                sizeModel.addElement(i);
            }
            else if(i==36||i==48||i==72){
                sizeModel.addElement(i);
            }
        }
        //setting models
        fontList.setModel(fontModel);
        fontSize.setModel(sizeModel);
        //applying border to font list
        fontList.setBorder(fontListBorder);
        //selecting the default options of text
        fontList.setSelectedIndex(140);
        fontSize.setSelectedIndex(5);
        fontPane = new JScrollPane(fontList);       //initialing scroll pane for font list
        fontList.addListSelectionListener(new ActionListeners.ListListener()); //Performs action when a font from font list is clicked
        fontSize.addItemListener(new ActionListeners.SizeListener()); //Performs action when a size from font size drop down list is clicked
        //setting bounds
        labelFont.setBounds(40,30,45,30);
        fontText.setBounds(40,260, 300,30);
        bold.setBounds(250,100,39,35);
        italic.setBounds(300,100,39,35);
        colour.setBounds(250,140,88,35);
        fontPane.setBounds(40,60,190, 180);
        fontSize.setBounds(250,60,88,35);
        ok.setBounds(130,300,45,30);
        cancel.setBounds(200,300,70,30);
        //adding stuff to font frame
        add(labelFont);
        add(fontText);
        add(fontPane);
        add(fontSize);
        add(bold);
        add(italic);
        add(colour);
        add(ok);
        add(cancel);
        setResizable(false);
    }
    //return the font list
    public static JList<String> getFontList() {
        return fontList;
    }
    //return the font model
    public static DefaultListModel<String> getFontModel() {
        return fontModel;
    }
    //return the size model
    public static DefaultComboBoxModel<Integer> getSizeModel() {
        return sizeModel;
    }

}
