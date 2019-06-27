package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	/* 
	 * This class manages information and functions of a player. 
	 */
	private String playerId;
	private String playerName;
	private int initialPoints;
	private int bet;
	private BetType betType;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		/* Constructor initialize the player with its id, name and starting point */
		this.playerId = playerId;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName() {
		/* Get this player name*/
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		/* Set this player name */
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		/* Get the player point */
		return this.initialPoints;
	}

	@Override
	public void setPoints(int points) {
		/* Set this player point */
		this.initialPoints = points;
	}

	@Override
	public String getPlayerId() {
		/* Get this p[layer id */
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		/* Set the player bet. If the bet larger or equal to 0, call resetBet() */
		if(bet <= 0) {
			this.resetBet();
		} else {
			if(this.initialPoints > 0 && this.initialPoints >= bet) {
				this.bet = bet;
				return true;
			}
		}
		return false;
	}

	@Override
	public int getBet() {
		/* Return the player bet */
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) {
		/* Set this player bet type */
		this.betType = betType;
	}

	@Override
	public BetType getBetType() {
		/* Get this player bet type*/
		return this.betType;
	}

	@Override
	public void resetBet() {
		/* Reset the bet to 0 */
		this.bet = 0;
	}
	
	@Override
	public String toString() { 
		/* Return player information as string */
		return String.format("Player: id=%s, name=%s, bet=%d, betType=%s, points=%d", 
				this.getPlayerId(), this.getPlayerName(), this.getBet(), this.getBetType(), this.getPoints());
	}

}
