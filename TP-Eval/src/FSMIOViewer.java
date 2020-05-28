import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class FSMIOViewer extends FSMIOStringviewer
{
    public FSMIOViewer()
    {
        super();
    }

    @Override
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
    }

    private void SaveFile()
    {
        m_fsmioString.getFSM().saveObject(m_currentFile.getAbsolutePath());
    }

    @Override
    protected void OpenFile()
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

        if(ext.equals(".ser"))
        {
            m_currentFile = selectedFile;
            m_fsmioString = new FSMIOString();
            m_fsmioString.LoadFromSerialized(selectedFile.getAbsolutePath());
            
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
            JOptionPane.showMessageDialog(m_frame, fileName + " is not a .ser file", "File Load Error", JOptionPane.ERROR_MESSAGE);
        
        m_frame.pack();
    }
}