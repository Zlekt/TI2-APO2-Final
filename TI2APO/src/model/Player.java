package model;

import java.io.Serializable;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	private Charac characterName;
	private int seeds;
	
	public Player(String name, int nameCharacter) {
		this.name = name;
		this.score = 0;
		
		if(nameCharacter == 1) {
			this.characterName = Charac.RICK;
		}else {
			this.characterName = Charac.MORTY;
		}
		
	}
	
	public void calculateScore(int score, long sTime) {
		int finalScore =  (int) ((score*120) - sTime);
		this.score = finalScore;
	}
	
	public void collectSeed() {
		
		setSeeds(getSeeds() + 1);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public Charac getCharacterName() {
		return characterName;
	}

	public void setCharacterName(Charac characterName) {
		this.characterName = characterName;
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}
	
	
}
