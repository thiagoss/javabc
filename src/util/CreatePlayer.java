package util;

import java.io.IOException;
import java.util.Scanner;

import player.Player;

/**
 * Classe que auxilia a criação de um jogador via console
 * @author abmargb, thiagoss
 *
 */
public class CreatePlayer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		
		System.out.printf("Digite seu nome: \n");
		String name = scn.nextLine();
		
		System.out.printf("Digite seu login: \n");
		String login = scn.nextLine();
		
		Player p = new Player(name, login);
		
		p.savePlayer();

	}

}
