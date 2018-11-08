package jogo.controler;

import java.rmi.RemoteException;

/**
 * Classe Fabrica Controlador do Jogo da Velha
 * 
 * @copyright
 * @author Ruan de Oliveira
 */
public class FabricaControladorJogoVelha {

	public static IControladorJogoVelha create() throws RemoteException {
		return new ControladorJogo();
	}
}
