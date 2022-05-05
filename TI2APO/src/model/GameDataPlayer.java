package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameDataPlayer {
	
	static String RUTA_DATA = "files\\GameBoardData.txt";
	private ArrayList<Player> playersList;
	
	public GameDataPlayer() {
		setPlayersList(new ArrayList<Player>());
	}
	
	public void addPlayerToData(Player player) {
		
		int potentialPosition = playerPosition(player.getName());
		
		if(potentialPosition != -1) {
			playersList.set(potentialPosition, player);
		} else { 
			playersList.add(player);
		}
		
		orderByScore();
	}
	
	
	public int playerPosition(String name) {
		int inicio = 0;
		int fin = playersList.size() - 1;
		
		return findPlayer(name, inicio, fin);
	}
	
	private int findPlayer(String name, int inicio, int fin) {
		compareByName();
		
		if(inicio > fin) {
			return -1;
		}
		
		int medio = -1;
		
		while(inicio <= fin) {
			medio = (inicio + fin)/2;
			
			if(playersList.get(medio).getName().compareTo(name) == 0) {				
				return medio;
				
			} else if(playersList.get(medio).getName().compareTo(name) < 0) {
				inicio = medio + 1;
				
			} else if(playersList.get(medio).getName().compareTo(name) > 0) {
				fin = medio - 1;
				
			}
		}
		
		return -1;
		
	}
	  	
	private void compareByName() {
		Collections.sort(playersList, 
				//clase anonima
				new Comparator<Player>() {
					@Override
					public int compare(Player o1, Player o2) {
						return o1.getName().compareTo(o2.getName());
						
					}
		
		});
	}
	
	
	public void orderByScore() {
		int n = playersList.size();
		boolean inversion = true;
		
		for (int i = 0; i < n && inversion; i++) {
			inversion = false;
	    	for (int j = 1; j < n - i; j++) {
	    		if (playersList.get(j).getScore() > playersList.get(j-1).getScore()) {
	    			
	    			Player temp = playersList.get(j);
	    			playersList.set(j, playersList.get(j-1));
	    			playersList.set(j-1, temp);
	    			inversion = true;
	    			
	    		}
	    	}
	    }
	}
	
	public String printTop5() {
		
		orderByScore();
		String info = "";
		
		info  = "===========================\n";
		info += "    	TOP 5 PLAYERS	    \n";
		info += "===========================\n";
		
		for(int m = 1; m <= playersList.size(); m++) {
			info += " [" + (m) + "] " +playersList.get(m-1).getName() + "\t" + playersList.get(m-1).getScore() + "\n";
			if(m==5)
			{
				break;
			}
		}
		
		return info;
	}
	
	
	public String printGameDataPlayer() {
		String info = "";
		
		for(int m = 0; m < playersList.size(); m++) {
			info += playersList.get(m).getName() + "/" + playersList.get(m).getScore() + "\n";
		}
		
		return info;
	}
	
	public void serialize() throws IOException {

		File file = new File(RUTA_DATA);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(getPlayersList());

		oos.close();
		fos.close();				
	}


	public void deserialize() throws IOException, ClassNotFoundException {
		File file = new File(RUTA_DATA);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")
		ArrayList<Player> objetosP = (ArrayList<Player>) ois.readObject();
		playersList.addAll(objetosP);

		ois.close();
		fis.close();	 
	}

	
	
	public ArrayList<Player> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(ArrayList<Player> playersList) {
		this.playersList = playersList;
	}
	
	
	
	
}