package fib;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.*;
import javax.swing.*;

public class ProgressDialog extends JDialog implements PropertyChangeListener {

    private JProgressBar jProgressBar;
    private JTextArea callInformations  = new JTextArea("");
    private JScrollPane callInformationsScrollPane  = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JPanel infoPanel = new JPanel(new GridLayout(6,2));  
    
    private JLabel durationValue = new JLabel ("");
    private JLabel callsValue = new JLabel ("");
    
    private Adapter calculationThread;   
    
    private static int dialodCounter = 0;
   
    public ProgressDialog(Gui frame, Adapter thread) 
    {
        super(frame, "ProgressDialog");
        dialodCounter++;
        calculationThread = thread;
       
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        this.setResizable(true);
        this.setSize(400, 500);
        this.centerWindow();
        this.setModal(false);
                	
        getContentPane().add(BorderLayout.NORTH,infoPanel);
    	infoPanel.add(new JLabel ("type"));
    	infoPanel.add(new JLabel(""+thread.getTypeName()));    	
    	infoPanel.add(new JLabel ("duration"));
    	infoPanel.add(durationValue);
    	infoPanel.add(new JLabel ("calls"));
    	infoPanel.add(callsValue);    	
    	infoPanel.add(new JLabel ("from"));
    	infoPanel.add(new JLabel(""+thread.getFrom()));
    	infoPanel.add(new JLabel ("to"));
    	infoPanel.add(new JLabel(""+thread.getTo()));
    	infoPanel.add(new JLabel ("steps"));
    	infoPanel.add(new JLabel(""+thread.getSteps()));
    	     	
    	getContentPane().add(BorderLayout.CENTER,callInformationsScrollPane);
    	callInformationsScrollPane.add(callInformations);		
    	callInformationsScrollPane.setSize(20, 40);
    	callInformationsScrollPane.setAutoscrolls(true);
    	callInformationsScrollPane.setViewportView(callInformations);	 
    	
    	jProgressBar = new JProgressBar();
        getContentPane().add(BorderLayout.SOUTH,jProgressBar);        
        jProgressBar.setStringPainted(true);
        jProgressBar.setMaximum(calculationThread.getMaxProgress());
        jProgressBar.setValue(0);       
    	
        calculationThread.addPropertyChangeListener(this);
        calculationThread.start();
       
        this.setVisible(true);       
    }   
   
    public void propertyChange(PropertyChangeEvent event) {
       
        String propertyName = event.getPropertyName();
       
        callInformations.setText(Helper.getCallInformations());
        
        if (propertyName.equals("PROGRESS_UPDATED")) 
        {
            jProgressBar.setValue(((Integer)event.getNewValue()).intValue());           
           
        } else if (propertyName.equals("THREAD_STOPPED")) {
            durationValue.setText(Double.parseDouble(""+Helper.getDuration())/1000000000+"s");
            callsValue.setText(""+Helper.countCallInformations());
        }
       
    }

    private void centerWindow() 
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim.height = dim.height >> 1;
        dim.width = dim.width >> 1;
        dim.height -= (this.getHeight() >> 1);
        dim.width -= (this.getWidth() >> 1);
        this.setLocation(dim.width+(dialodCounter*40), dim.height+(dialodCounter*10));
    }   
} 
