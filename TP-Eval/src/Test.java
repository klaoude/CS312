import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Test extends FSMIOViewer{

    public Test()
    {
        super();
    }

    @Override
    protected void CreateMenu() 
    {
        super.CreateMenu();

        final JMenu transitionMenu = new JMenu("Automate");
        m_menuBar.add(transitionMenu);

        JMenuItem item = new JMenuItem("new FSMIO");
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new_FSMIO(); } });
        transitionMenu.add(item);

        item = new JMenuItem("add state");
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_state(); } });
        transitionMenu.add(item);  

        item = new JMenuItem("add Transition");  
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_transi(); } });
        transitionMenu.add(item);  
        
        item = new JMenuItem("delete state");  
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_transi(); } });
        transitionMenu.add(item);

        item = new JMenuItem("delete transi");  
        item.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { add_transi(); } });
        transitionMenu.add(item);
    }

    private void new_FSMIO()
    {
        m_fsmioString = new FSMIOString();
        m_textArea.setText("States: " + m_fsmioString.getFSM().getStates() + m_fsmioString.toString());
    }

    private void add_state()
    {
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Ajoutez un état!", "ADD_STATE", JOptionPane.QUESTION_MESSAGE);
        m_fsmioString.getFSM().addState(new State(nom));
        m_textArea.setText("States: " + m_fsmioString.getFSM().getStates() + m_fsmioString.toString());
    }

    private void add_transi()
    {
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Ajoutez une transition ! [origin input output dest]", "ADD_TRANSITION", JOptionPane.QUESTION_MESSAGE);
        State s1 = null, s2 = null;
        String[] args = nom.split(" ");
        for(State s : m_fsmioString.getFSM().getStates())
        {
            if(s.getName().equals(args[0]))
                s1 = s;
            else if(s.getName().equals(args[3]))
                s2 = s;
        }
        if(s1 == null || s2 == null)
            System.out.printf("Pas de state %s/%s", args[0], args[3]);
        else
        {
            m_fsmioString.getFSM().addTransition(s1, args[1], args[2], s2);
            m_textArea.setText("States: " + m_fsmioString.getFSM().getStates() + m_fsmioString.toString());
        }
    }
}
