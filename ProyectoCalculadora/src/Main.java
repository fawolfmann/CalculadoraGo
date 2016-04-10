import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTextField calcular;
	private JTextField resultado;
	private Calculadora calc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		
		
	}

	/**
	 * Create the application.
	 */
	public Main() {
		calc = new Calculadora();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		calcular = new JTextField();
		calcular.setBounds(26, 34, 232, 20);
		frame.getContentPane().add(calcular);
		calcular.setColumns(10);
		
		JButton button = new JButton("=");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!calc.esValido(calcular.getText())){
					resultado.setText("Sintaxis Incorrecta");
				}else{
				calc.reiniciar();
				calc.guardar(calcular.getText(),0);
				String res = calc.calcular(calcular.getText(),0,0);
				
				resultado.setText(res);
				}
			}
		});
		button.setBounds(279, 184, 89, 23);
		frame.getContentPane().add(button);
		
		resultado = new JTextField();
		resultado.setBorder(null);
		resultado.setEditable(false);
		resultado.setBounds(279, 34, 128, 20);
		frame.getContentPane().add(resultado);
		resultado.setColumns(10);
		
		
		
	}
}
