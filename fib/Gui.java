package fib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame implements ActionListener
{
	private JPanel buttonPanel = new JPanel(new GridLayout(3,2));
	private JPanel configPanel = new JPanel(new GridLayout(1,6));
	
	private JTextField fromPosTextField = new JTextField("15");
	private JTextField toPosTextField   = new JTextField("15");
	private JTextField stepsTextField   = new JTextField("1");
	
	private JButton runFibIterButton 		 = new JButton("Fibonacci Iterativ");
	private JButton runFibIterCashedButton  = new JButton("Fibonacci Iterativ-Cashed");
	private JButton runFibRekButton 		 = new JButton("Fibonacci Rekursiv");
	private JButton runFibRekCashedButton   = new JButton("Fibonacci Rekursiv-Cashed");
	private JButton runFibFormelButton 	 = new JButton("Fibonacci Formel");
	private JButton runFibMatrixButton 	 = new JButton("Fibonacci Matrix");
				
	public static void main(String[] args) 
	{
		new Gui();
	}
	
	public Gui()
	{
		super("Fibonacci Laufzeitvergleich");
					
		runFibIterButton.addActionListener(this);
		runFibIterCashedButton.addActionListener(this);
		runFibRekButton.addActionListener(this);
		runFibRekCashedButton.addActionListener(this);
		runFibFormelButton.addActionListener(this);
		runFibMatrixButton.addActionListener(this);
				
		getContentPane().add(BorderLayout.CENTER,buttonPanel);		
		buttonPanel.add(runFibIterButton);
		buttonPanel.add(runFibIterCashedButton);
		buttonPanel.add(runFibRekButton);
		buttonPanel.add(runFibRekCashedButton);
		buttonPanel.add(runFibFormelButton);
		buttonPanel.add(runFibMatrixButton);
	
		getContentPane().add(BorderLayout.NORTH,configPanel);		
		configPanel.add(new JLabel("from"));
		configPanel.add(fromPosTextField);
		configPanel.add(new JLabel("to"),false);
		configPanel.add(toPosTextField);
		configPanel.add(new JLabel("steps"));
		configPanel.add(stepsTextField);
			   		
		setSize(500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}	

	public void actionPerformed(ActionEvent e) 
	{		 
		 int fromPos  = Integer.parseInt(fromPosTextField.getText());
		 int toPos    = Integer.parseInt(toPosTextField.getText());
		 int steps    = Integer.parseInt(stepsTextField.getText());
		 	 
		 Helper.resetCallInformations();		 
				
		 Adapter calculationThread = new Adapter();
		 if(e.getSource() == runFibIterCashedButton)
		 	 calculationThread.config(Adapter.ITERATIV,fromPos,toPos,steps);
		 
		 else if(e.getSource() == runFibRekButton)
		 	 calculationThread.config(Adapter.RECURSIVE,fromPos,toPos,steps);
		 
		 else if(e.getSource() == runFibRekCashedButton)
		 	 calculationThread.config(Adapter.RECURSIVE_CASHED,fromPos,toPos,steps);	
		 
		 else if(e.getSource() == runFibFormelButton)
		 	 calculationThread.config(Adapter.FORMEL,fromPos,toPos,steps);
		 
		 else if(e.getSource() == runFibMatrixButton)
			 calculationThread.config(Adapter.MATRIX,fromPos,toPos,steps);
		 
		 else
			 calculationThread.config(Adapter.ITERATIV,fromPos,toPos,steps);
		         
         new ProgressDialog(this, calculationThread); 		 	 
    }
}