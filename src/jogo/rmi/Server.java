package jogo.rmi;

import java.rmi.*;
import java.rmi.registry.*;

import jogo.controler.ControladorJogo;
import jogo.controler.IControladorJogoVelha;

/**
 * Classe Servidor
 * 
 * @copyright
 * @author Ruan de Oliveira
 */
public class Server {

    private static final int PORT = 1099;
    private static Registry registry;

    // Inicia registro do Servidor
    public static void startRegistry() throws RemoteException {
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    // Registra objeto do Servidor
    public static void registerObject(String name, Remote remoteObj) throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        //System.out.println("Registered: " + name + " -> " + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }
    
	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws Exception {
        startRegistry();
        registerObject(IControladorJogoVelha.class.getSimpleName(), new ControladorJogo());
        // Mensagem confirmando inicialização do servidor
        System.out.println("Servidor iniciado com sucesos. Aguardando conexão.");
		
	}

}
