package jogo.rmi;

import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import jogo.view.PainelDoJogo;

/**
 * Classe Cliente
 * 
 * @copyright
 * @author Ruan de Oliveira
 */
public class Client {

	
	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws CustomException 
	 */
	public static void main(String[] args) throws Exception {
		
//			String objname = "ServerFactory";				
			painelJogo();
	}
	
	private static void painelJogo() throws Exception {
		try {
			JFrame frame = new JFrame();
			
			JPanel painel = new PainelDoJogo();
			frame.setContentPane(painel);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 500);
			frame.setResizable(false);
			frame.setVisible(true);

		} catch (RemoteException e) {
			throw new CustomException("Erro no objeto remoto");
		}
	}

}
