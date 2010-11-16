package player;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import util.FileHandling;
import util.MenuSelectable;

/**
 * Essa classe descreve um jogador, definido por seu nome, login, acertos e erros.
 * @author   Abmar, Thiago
 */
public class Player implements Serializable, MenuSelectable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String login;
	private long wrongs;
	private long rights;
	
	
	
	/**
	 * Construtor do Player
	 * @param name
	 * @param login
	 * @param wrongs
	 * @param rights
	 */
	public Player(String name, String login, long wrongs, long rights){
		setName(name);
		setLogin(login);
		setWrongs(wrongs);
		setRights(rights);
	}
	
	/**
	 * Construtor do Player, inicializando os erros e acertos com zero. 
	 * @param name
	 * @param login
	 */
	public Player(String name, String login) {
		setName(name);
		setLogin(login);
		setWrongs(0);
		setRights(0);
	}
	
	
	/**
	 * Retorna o nome do jogador
	 * @return  O nome do jogador
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Atualiza o nome do jogador
	 * @param name  O novo nome do jogador
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Retorna o numero de acertos do jogador
	 * @return  O numero de acertos do jogador
	 */
	public long getRights() {
		return rights;
	}
	
	/**
	 * Atualiza o numero de acertos do jogador
	 * @param rights  O novo numero de acertos do jogador
	 */
	public void setRights(long rights) {
		this.rights = rights;
	}
	
	/**
	 * Retorna o numero de erros do jogador
	 * @return  O numero de erros do jogador
	 */
	public long getWrongs() {
		return wrongs;
	}
	
	/**
	 * Atualiza o numero de erros do jogador
	 * @param wrongs  O novo numero de erros do jogador
	 */
	public void setWrongs(long wrongs) {
		this.wrongs = wrongs;
	}
	
	/**
	 * Incrementa o numero de acertos do jogador
	 * @return O numero atual de acertos apos o incremento
	 */
	public long addRight() {
		rights++;
		return(getRights());
	}
	
	/**
	 * Incrementa o numero de erros do jogador
	 * @return O numero atual de erros apos o incremento
	 */
	public long addWrongs() {
		wrongs++;
		return(getWrongs());
	}

	/**
	 * Retorna o login do jogador
	 * @return  O login do jogador
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Atualiza o login do jogador
	 * @param login  O novo login do jogador
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Cria um novo arquivo com os dados do jogador, verificando se o login ja existe,
	 * se ja existir, lanca um LoginExistsException.
	 * @throws IOException
	 * @throws LoginExistsException
	 */
	public void createPlayerFile() throws IOException, LoginExistsException {
		File dir = new File(FileHandling.PLAYERSDIR);
		File[] playerList = dir.listFiles();
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".plr");
			}
		};
		
		playerList = dir.listFiles(filter);
		boolean loginExists = false;
		for (int i = 0; i<playerList.length; i++) {
			String tempLogin = playerList[i].getName().substring(0, playerList[i].getName().lastIndexOf("."));
			if (tempLogin.equalsIgnoreCase(this.getLogin())) {
				loginExists = true;
			}
		}
		
		if (loginExists) {
			throw new LoginExistsException();
		} else {
			FileHandling.save(this, FileHandling.PLAYERSDIR + this.getLogin() + ".plr");
		}
	}
	
	/**
	 * Salva o jogador num arquivo ja existente
	 * @throws IOException
	 */
	public void savePlayer() throws IOException {
		FileHandling.save(this, FileHandling.PLAYERSDIR + this.getLogin() + ".plr" );
	}
	
	/**
	 * Le os dados do jogador de um arquivo
	 * @param plrLogin O login do jogador a ser carregado
	 * @return O jogador lido
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Player readPlayer(String plrLogin) throws IOException, ClassNotFoundException {
		return (Player)FileHandling.read(FileHandling.PLAYERSDIR + plrLogin + ".plr");
	}
	
	
	/**
	 * Le todos os jogadores no diretorio do JavABC e retorna um ArrayList
	 * @return um ArrayList contendo os Players
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Player> readAllPlayers() throws FileNotFoundException, IOException, ClassNotFoundException {
		File dir = new File(FileHandling.PLAYERSDIR);
		File[] fileList = dir.listFiles();
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		ObjectInputStream ois = null;
		for (int i = 0; i < fileList.length; i++) {
			ois = new ObjectInputStream(new FileInputStream(fileList[i]));
			allPlayers.add((Player)ois.readObject());
		}
		if(ois != null) {
			ois.close();
		}
		return allPlayers;
	}

	/*
	 *  (non-Javadoc)
	 * @see util.MenuSelectable#getMenuName()
	 */
	public String getMenuName() {
		return(login);
	}
	
	/**
	 * Apaga o arquivo do jogador (Player)
	 *
	 */
	public void removePlayer() {
		File dir = new File(FileHandling.PLAYERSDIR);
		File[] fileList = dir.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			String eachLogin = fileList[i].getName().substring(0, fileList[i].getName().lastIndexOf("."));
			if (getLogin().equals(eachLogin)) {
				fileList[i].delete();
			}
		}
	}
	
}
