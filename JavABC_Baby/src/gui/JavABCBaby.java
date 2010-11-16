package gui;

import gui.SplashWindow;

public class JavABCBaby {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplashWindow.getInstance().runSplashScreen();
		JavABCBabyMainWindow game = new JavABCBabyMainWindow(); 
		game.setVisible(true);
	}

}
