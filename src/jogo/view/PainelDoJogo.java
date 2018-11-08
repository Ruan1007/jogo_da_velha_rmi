package jogo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jogo.controler.IControladorJogoVelha;

/**
 * Classe Painel do Jogo
 * 
 * @copyright
 * @author Ruan de Oliveira
 */
public class PainelDoJogo extends JPanel {

	private static final long serialVersionUID = -6987492713448399504L;
	
	private PainelPrincipal painelPrincipal;
	
	// Porta do servico.
	private static final int registryPort = 1099;

    private Registry registry;
	
    private static final String host = "localhost"; 
	
	private IControladorJogoVelha controlador;
	
	private int idJogador=-1;
	
	public PainelDoJogo() throws RemoteException {
		
		try {
			// Objtem o servi�o de nomes
				System.out.println("Referencia obtida.");
	            // Conecta no servidor
	            this.conectarServidor();
	            // Cria ID do usuário
	            this.criarIdUsuario();
	            // Tenta entrar em uma sala existente
			
			setLayout(new BorderLayout());
			
			JLabel labelTexto = new JLabel("");
			JLabel labelJogadas = new JLabel(" :X - O: ");
			
			JLabel labelvx = new JLabel("0");
			JLabel labelvo = new JLabel("0");
			
			
			JPanel painelVitorias = new JPanel();
			painelVitorias.add(labelTexto, BorderLayout.PAGE_START);
			painelVitorias.add(labelvx, BorderLayout.CENTER);
			painelVitorias.add(labelJogadas, BorderLayout.CENTER);
			painelVitorias.add(labelvo, BorderLayout.CENTER);
			add(painelVitorias, BorderLayout.NORTH);
			
			painelPrincipal = new PainelPrincipal(this.idJogador, labelTexto,labelvx, labelvo, this.controlador);
			add(painelPrincipal, BorderLayout.CENTER);
			
			JButton restart = new JButton("Restart");
			restart.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						painelPrincipal.restart();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			JPanel painelBotao = new JPanel();
			painelBotao.add(restart);
			add(painelBotao, BorderLayout.SOUTH);
		} catch (Exception ex) {
			Logger.getLogger(PainelDoJogo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// Abre uma conexão com o servidor
	private void conectarServidor() throws Exception{
	    // Conecta usando RMI
	    this.registry = LocateRegistry.getRegistry(host, registryPort);
	    this.controlador = (IControladorJogoVelha) registry.lookup(IControladorJogoVelha.class.getSimpleName());
	}
	
	// Solicita novo ID para o usuário
    private void criarIdUsuario() throws Exception{
        this.idJogador = this.controlador.criarIdJogador();
    }

}
