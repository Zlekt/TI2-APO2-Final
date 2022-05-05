package ui;

import java.io.IOException;
import java.util.Scanner;

import model.Controller;

public class Main {
	public static Scanner LECTOR; 
	public static Controller controller;
	public static int startTime;
	public static int finishTime;
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		LECTOR = new Scanner(System.in);
		controller = new Controller();
		
		controller.doDeserializar();
		
		System.out.println("******************************");
		System.out.println("*                            *");
		System.out.println("*      INTEGRADORA 2         *");
		System.out.println("*                            *");
		System.out.println("* Villegas / Botero / Flaker *");
		System.out.println("******************************");
		System.out.println("");
		
		
		System.out.println("-----------------------------");
		System.out.println("  Before the game starts,    ");
		System.out.println("  complete the following     ");
		System.out.println("-----------------------------");
		
		int numRows = 0;
		int numColumns = 0;
		int numSeeds = 0;
		
		boolean continuar = false;
		
		do {
			continuar = false;
			System.out.print("-> Number of rows in the game: ");
			numRows = LECTOR.nextInt();
			
			System.out.print("-> Number of columns in the game: ");
			numColumns = LECTOR.nextInt();
			
			System.out.print("-> Number of seeds in the game: ");
			numSeeds = LECTOR.nextInt();
			
			if(numSeeds > numRows*numColumns) {
				System.out.println("ERROR: the number of seeds is greater than the size of the board");
				continuar = true;
			}	
			
		} while (continuar);

		
		
		int numPortals = 0;
			
		do {
			continuar = false;
			System.out.print("-> Number of portals in the game: ");
			numPortals = LECTOR.nextInt();
			LECTOR.nextLine();
			if(numPortals > (numRows*numColumns)/2) {
				System.out.println("ERROR: the number of portals is greater than the size of the board");
				continuar=true;
			}
		} while(continuar);
		
		controller.initGameBoard(numRows, numColumns, numSeeds, numPortals); 

		
		System.out.println("");
		
		System.out.print("-> Username of who will play with Rick: ");
		String userNameOfRick = LECTOR.nextLine();
		
		
		System.out.print("-> Username of who will play with Morty: ");
		String userNameOfMorty = LECTOR.nextLine();
		
		controller.initializePlayers(userNameOfRick, userNameOfMorty);
		
		System.out.println("");
		System.out.println("EVERYTHING READY TO PLAY");
		System.out.println("");
		startTime  = (int) System.currentTimeMillis();
		menu();
		try {
			controller.doSerializar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void menu() {
		
		int chosenOption = 0;
		boolean ricksTurn = true;
		do {
			System.out.println("-----------------------------");
			if(ricksTurn) {
				System.out.println("	Rick's Turn");
			}
			else {
				System.out.println("	Morty's turn");
			}
			System.out.println("-----------------------------");
			System.out.println(" [1] Roll dice");
			System.out.println(" [2] See game board");
			System.out.println(" [3] See portals");
			System.out.println(" [4] Marker");
			chosenOption = LECTOR.nextInt();
			LECTOR.nextLine();
			switch (chosenOption) {
			case 1:
				int diceResult = controller.rollDice();
				System.out.println(diceResult);
				System.out.println("Are you moving forward(true) or backwards(false)");
				boolean move = LECTOR.nextBoolean();
				if(ricksTurn) {
					controller.moveRick(diceResult, move);	
					ricksTurn = false;
				}
				else {
					controller.moveMorty(diceResult, move);
					ricksTurn = true;
				}
				break;
			case 2:
				System.out.println(controller.toString()); 
				break;
			case 3:
				System.out.println(controller.portalToString());
				break;
			case 4:
				System.out.println(controller.showMarker());
				break;
			}

			System.out.println("");
			if(!controller.executeGame()) {
				chosenOption=5;
			}
			
		} while (chosenOption != 5);
		finishTime = (int) System.currentTimeMillis();
		long time=(finishTime-startTime)/1000;
		controller.setTime(time);
		controller.setScore();
		controller.addWinner();
		System.out.println(controller.showTop5());
	}

}
