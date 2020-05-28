import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class FSMIOStringviewer
{
    private JFrame m_frame;
    private JLabel m_filenameLabel;
    private JLabel m_statusLabel;
    private JMenuBar m_menuBar;
    private JTextArea m_textArea;
    private File m_currentFile = null;
    private FSMIOString m_fsmioString;

    public FSMIOStringviewer()
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

    private void CreateMenu() 
    {
        m_menuBar = new JMenuBar();
        m_frame.setJMenuBar(m_menuBar);

        final JMenu menu = new JMenu("File");
        m_menuBar.add(menu);

        JMenuItem item = new JMenuItem("Open");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { OpenFile(); } });
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
    }

    private void OpenFile()
    {
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
            
            final JMenu transitionMenu = new JMenu("Transition");
            m_menuBar.add(transitionMenu);

            JMenuItem item = new JMenuItem("reset");
            item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { Reset(); } });
            transitionMenu.add(item);
            for(String i : m_fsmioString.getFSM().getPossiblesInputs())
            {
                item = new JMenuItem(i);
                item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { DoTransition(i); } });
                transitionMenu.add(item);
            }            

            JOptionPane.showMessageDialog(m_frame, fileName, "File loaded", JOptionPane.INFORMATION_MESSAGE);
            m_statusLabel.setText("FSMIO loaded. Current State: " + m_fsmioString.getFSM().getState());
            m_filenameLabel.setText("File: " + fileName);
            m_textArea.setText("States: " + m_fsmioString.getFSM().getStates() + m_fsmioString.toString());
        }
        else
            JOptionPane.showMessageDialog(m_frame, fileName + " is not a .fsm file", "File Load Error", JOptionPane.ERROR_MESSAGE);
        
        m_frame.pack();
    }

    private void Reset()
    {
        m_fsmioString.getFSM().reset();
        m_statusLabel.setText("Reset. New Satate: " + m_fsmioString.getFSM().getState().getName());
    }

    private void DoTransition(String in)
    {
        m_statusLabel.setText("New State: " + m_fsmioString.getFSM().getState() + " output: " + m_fsmioString.getFSM().doTransition(in)); 
    }

    private void CloseFile()
    {
        m_fsmioString = null;
        m_statusLabel.setText("No FSMIO. Open a fsm file to load a FSMIO.\nThe content of the file will appear here.");
        
        m_frame.pack();
    }
}