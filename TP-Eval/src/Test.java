import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Test {

    protected JFrame m_frame;
    protected JLabel m_filenameLabel;
    protected JLabel m_statusLabel;
    protected JMenuBar m_menuBar;
    protected JTextArea m_textArea;
    protected File m_currentFile = null;
    protected FSMIOString m_fsmioString;
    private FSMIO fsmio;

    public Test()
    {
        CreateFrame();
    }

    private void CreateFrame()
    {
        m_frame = new JFrame("FSMIOStringviewer");
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setPreferredSize(new Dimension(800, 600));

        CreateMenu();

        final Container contentPane = m_frame.getContentPane();

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        m_filenameLabel = new JLabel("No file displayed.");
        contentPane.add(m_filenameLabel, BorderLayout.NORTH);

        m_textArea = new JTextArea("No FSMIO. Open a fsm file to load a FSMIO.\nThe content of the file will appear here.");
        m_textArea.setEditable(false);
        contentPane.add(m_textArea, BorderLayout.CENTER);

        m_statusLabel = new JLabel("1.0");
        contentPane.add(m_statusLabel, BorderLayout.SOUTH);

        m_frame.pack();

        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        m_frame.setLocation(d.width / 2 - m_frame.getWidth() / 2, d.height / 2 - m_frame.getHeight() / 2);
        m_frame.setVisible(true);
    }

    protected void CreateMenu() 
    {
        m_menuBar = new JMenuBar();
        m_frame.setJMenuBar(m_menuBar);

        final JMenu menu = new JMenu("File");
        m_menuBar.add(menu);

        JMenuItem item = new JMenuItem("Open");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { OpenFile(); } });
        menu.add(item);

        item = new JMenuItem("Save");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { SaveFile(); } });
        menu.add(item);

        item = new JMenuItem("Close");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { CloseFile(); } });
        menu.add(item);

        menu.addSeparator();

        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0); } });
        menu.add(item);

        final JMenu transitionMenu = new JMenu("Transition");
        m_menuBar.add(transitionMenu);

        item = new JMenuItem("reset");
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { Reset(); } });
        transitionMenu.add(item);    

        item = new JMenuItem("new FSMIO");
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new_FSMIO(); } });
        transitionMenu.add(item);

        item = new JMenuItem("add state");
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_state(); } });
        transitionMenu.add(item);  

        item = new JMenuItem("add Transition");  
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_transi(); } });
        transitionMenu.add(item);   
    }

    private void new_FSMIO()
    {
        fsmio = new FSMIO<String, String>((State)new State("cazou"));
        m_textArea.setText("");
        return;
    }

    private void add_state()
    {
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Ajoutez un état!", "ADD_STATE", JOptionPane.QUESTION_MESSAGE);
        fsmio.addState(new State(nom));
        m_textArea.append("State : "+ nom);
        return;
    }

    private void add_transi()
    {
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Ajoutez un état!", "ADD_STATE", JOptionPane.QUESTION_MESSAGE);
        return;
    }

    private void SaveFile()
    {
        if( m_currentFile != null )
        {
            m_fsmioString.getFSM().saveObject(m_currentFile.getAbsolutePath() + ".ser");
            JOptionPane.showMessageDialog(m_frame, m_currentFile.getAbsolutePath() + " saved !", "File Saved", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected void OpenFile()
    {
        if(m_currentFile != null)
            CloseFile();

        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fileChooser.showOpenDialog(m_frame);

        if(returnVal != JFileChooser.APPROVE_OPTION)
            return;

        File selectedFile = fileChooser.getSelectedFile();
        String fileName = selectedFile.getName();
        String ext = "";

        System.out.println("Opening file: " + selectedFile);
        if(fileName.contains("."))
            ext = fileName.substring(fileName.lastIndexOf("."));

        if(ext.equals(".fsm"))
        {
            m_currentFile = selectedFile;
            m_fsmioString = new FSMIOString(m_currentFile.getAbsolutePath());
           

            JOptionPane.showMessageDialog(m_frame, fileName, "File loaded", JOptionPane.INFORMATION_MESSAGE);
            m_statusLabel.setText("FSMIO loaded. Current State: " + m_fsmioString.getFSM().getState());
            m_filenameLabel.setText("File: " + fileName);
            m_textArea.setText("States: " + m_fsmioString.getFSM().getStates() + m_fsmioString.toString());
        }
        else
            JOptionPane.showMessageDialog(m_frame, fileName + " is not a .fsm file", "File Load Error", JOptionPane.ERROR_MESSAGE);
        
        m_frame.pack();
    }

    protected void Reset()
    {
        m_fsmioString.getFSM().reset();
        m_statusLabel.setText("Reset. New State: " + m_fsmioString.getFSM().getState().getName());
    }

    protected void DoTransition(String in)
    {   
        m_statusLabel.setText("New State: " + m_fsmioString.getFSM().getState() + " output: " + m_fsmioString.getFSM().doTransition(in)); 
    }

    protected void CloseFile()
    {
        m_fsmioString = null;
        if( m_menuBar.getMenuCount() > 1 )
            m_menuBar.remove(1);
        m_textArea.setText("No FSMIO. Open a fsm file to load a FSMIO.\nThe content of the file will appear here.");
        m_filenameLabel.setText("No file loaded.");
        m_statusLabel.setText("1.0");
        m_frame.pack();
        m_currentFile = null;
    }
}
