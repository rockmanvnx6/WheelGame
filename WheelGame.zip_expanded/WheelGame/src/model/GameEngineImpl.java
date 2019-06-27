package model;

import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import model.SlotImpl;
import model.enumeration.*;
import java.lang.Math;

public class GameEngineImpl implements GameEngine {
	/*
	 * The main game engine, it contains the players, GameEngineCallBack, Slots
	 * detail Use to initialize the slot, Spin the wheel,
	 * 
	 */
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private List<Slot> slots = new ArrayList<Slot>();
	private final int startPoint = (int) (Math.random() * SlotImpl.WHEEL_SIZE);
	private final int number[] = new int[] { 0, 27, 10, 25, 29, 12, 8, 19, 31, 18, 6, 21, 33, 16, 4, 23, 35, 14, 2, 0,
			28, 9, 26, 30, 11, 7, 20, 32, 17, 5, 22, 34, 15, 3, 24, 36, 13, 1 };

	public GameEngineImpl() {
		/*
		 * Generate slot and add it into slots array. if the number is even then the
		 * color will be black. Else it's red. For position 0 and 19, it will be GREEN00
		 * and GREEN0 respectively.
		 * 
		 */
		for (int i = 0; i < SlotImpl.WHEEL_SIZE; i++) {
			Color color;
			if (i == 0) {
				color = Color.GREEN00;
			} else if (i == 19) {
				color = Color.GREEN0;
			} else {
				if (i % 2 == 0) {
					color = Color.BLACK;
				} else {
					color = Color.RED;
				}
			}
			slots.add(new SlotImpl(i, color, number[i]));
		}
	}

	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		/*
		 * This will spin the gameWheel. First it will check to see if there is any
		 * gameEngine, if there is then we execute the spinning code. if the position
		 * exceed the array size, we reset it back to the first position. The slot it
		 * stops at will be then passed to the
		 * 
		 */
		try {
			int i = (int) (Math.random() * SlotImpl.WHEEL_SIZE); // Index of slot i
			while (initialDelay <= finalDelay) {
			
				i %= SlotImpl.WHEEL_SIZE;

				for (GameEngineCallback gameEngine : gameEngineCallbacks) {
					gameEngine.nextSlot(slots.get(i), this);
				}

				i += 1;
				initialDelay += delayIncrement;
				/* if roll over the limit then restart back to the first position */
				try {
					Thread.sleep(initialDelay);
				} catch (Exception e) {
				}
			}

			this.calculateResult(slots.get(i));
			for (GameEngineCallback gameEngine : gameEngineCallbacks) {
				gameEngine.result(slots.get(i), this);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void calculateResult(Slot winningSlot) {
		/* This will apply win loss for each player */
		for (Player player : players.values()) {
			if (player.getBetType() != null) {
				player.getBetType().applyWinLoss(player, winningSlot);
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		/* Add a player to the list */
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		/* Return the Player according to player id */
		for (Player player : players.values()) {
			if (player.getPlayerId() == id) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		/* Remove the player in the players list */
		for (int i = 0; i < players.size(); i++) {
			if (players.get(player.getPlayerId()) == player) {
				players.remove(player.getPlayerId());
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		/* Add game engine callback */
		this.gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		/* Remove game engine callback */
		for (int i = 0; i < this.gameEngineCallbacks.size(); i++) {
			if (this.gameEngineCallbacks.get(i) == gameEngineCallback) {
				this.gameEngineCallbacks.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		/* return all the players */
		return Collections.unmodifiableCollection(players.values());
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		/* Place bet of a player */
		if (player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots() {
		/* Return all of the wheel slots. */
		return Collections.unmodifiableCollection(this.slots);
	}

}
