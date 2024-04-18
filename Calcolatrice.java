import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 

@SuppressWarnings("serial")
public class Calcolatrice extends JFrame implements ActionListener { 
	private JTextField risultato;
	private double operando = Double.NaN;
	private char operatore;
	private JButton[] pulsanti;
	private JButton uguale;
	private String[] testoBottoni = {"7", "8", "9", "*", "4","5","6","-","1","2","3","+","\u00B1","0",".","/"};
	
	public Calcolatrice() {
		super();
		creaGUI();
	}
  
	private void creaGUI() {
		pulsanti = new JButton[16];
		
		JPanel pannelloCentro = new JPanel();
		pannelloCentro.setLayout(new GridLayout(4, 4, 3, 3));

		for (int i = 0; i< 16; i++) {
			pulsanti[i] = new JButton(testoBottoni[i]);
			pulsanti[i].setFont(new Font("Courier New", Font.BOLD, 22));
			pulsanti[i].addActionListener(this);
			pannelloCentro.add(pulsanti[i]);
		}

		risultato = new JTextField("");
		risultato.setFont(new Font("Courier New", Font.BOLD, 16));
		risultato.setPreferredSize(new Dimension(120, 25));
		
		pannelloCentro.setBackground(Color.decode("#2266BB"));
		this.add(pannelloCentro, BorderLayout.CENTER);
		
		uguale = new JButton("=");
		uguale.addActionListener(this);
		JPanel pannelloSud = new JPanel();
		pannelloSud.add(uguale);
		pannelloSud.add(risultato);

		pannelloSud.setBackground(Color.decode("#66A0B8"));
		this.add(pannelloSud, BorderLayout.SOUTH);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calcola");
		pack();
		setVisible(true); 
  }
  
	public void actionPerformed(ActionEvent e) {
		try {
			String s = risultato.getText();
			// se il tasto è un numero o '.' devo solo aggiungere in risultato
			if (Character.isDigit(((JButton)e.getSource()).getText().charAt(0))
				|| ((JButton)e.getSource()).getText().equals("."))
				risultato.setText(s + ((JButton)e.getSource()).getText());
			else {
				// se il tasto è uguale e operando diverso da NaN vado a calcola()
				if (((JButton)e.getSource()).getText().equals("=")){
					if (operando != Double.NaN)
						risultato.setText(String.valueOf(calcola()));
				}
				else {
					// se è cambio segno aggiungo in testa il - DA SISTEMARE
					if (((JButton)e.getSource()).getText().charAt(0) == '\u00B1')
						risultato.setText('-' + risultato.getText()); //BUG
					else {
						/* altrimenti avanzano i 4 operatori
						   che devono 
						   1) settare operatore da ricordare
						   2) settare operando da ricordare
						   3) cancellare testo di risultato */
						operatore = ((JButton)e.getSource()).getText().charAt(0);
						operando = Double.valueOf(risultato.getText());
						risultato.setText("");
					}
				}
			}
		}
		catch (Exception err){
			JOptionPane.showMessageDialog(this, "Pirla");
		}
	}

	private double calcola() {
		/* valuto operatore ed eseguo operazione tra operatore
		   precedentemente salvato e contenuto di risultato */
		double r = Double.NaN;
		switch (operatore) {
			case '+':
				r = operando + Double.valueOf(risultato.getText());
				break;
			case '-':
				r = operando - Double.valueOf(risultato.getText());
				break;
			case '/':
				r = operando / Double.valueOf(risultato.getText());
				break;
			case '*':
				r = operando * Double.valueOf(risultato.getText());
				break;
		}
		return r;
	}
} 
