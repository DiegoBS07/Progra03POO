package Servidor;

import Comands.*;

import java.util.ArrayList;

public class TurnManager {

    private final ArrayList<ThreadServidor> players;
    private int currentTurnIndex = 0;
    private boolean startedGame = false;

    public TurnManager(ArrayList<ThreadServidor> players) {
        this.players = players;

    }

    public synchronized ThreadServidor getCurrentPlayer() {
        return players.get(currentTurnIndex);
    }

    public synchronized void processCommand(Command comando) {
        if (!startedGame){
            return;
        }
//        if (comando instanceof CommandHealing || comando instanceof CommandPower || comando instanceof CommandResistance){
//            return;
//        }

        if (comando instanceof CommandAttack || comando instanceof CommandGiveup || comando instanceof CommandSkipTurn) {
            nextTurn();
        }
    }

    private synchronized void nextTurn() {
        if (countActivePlayers() <= 1) {
            endGame();
            return;
        }

        do {
            currentTurnIndex = (currentTurnIndex + 1) % players.size();
        } while (!players.get(currentTurnIndex).isActive); // Saltar inactivos

        notifyTurnChange();
    }

    private void notifyTurnChange() {
        for (int i = 0; i < players.size(); i++) {
            ThreadServidor player = players.get(i);
            boolean isTurn = (i == currentTurnIndex && player.isActive);
            player.setTurn(isTurn);
            try {
                CommandPrivateMessage turnCommand;
                if (isTurn) {
                    String [] args = {"private_message", player.name, "IS YOUR TURN"};
                    turnCommand = new CommandPrivateMessage(args);
                    turnCommand.setSenderName("SERVER");
                } else {
                    String [] args = {"private_message", player.name, "WAIT IS NOT YOUR TURN"};
                    turnCommand = new CommandPrivateMessage(args);
                    turnCommand.setSenderName("SERVER");
                }
                player.respondAction(turnCommand);
            } catch (Exception e) {
                System.out.println("Error notificando turno: " + e.getMessage());
            }
        }
    }
    public void startTurns(){
        notifyTurnChange();
    }
    public boolean startGame() {
        if (players.size() == 0) {
            return false;
        }
        if  (countReadyPlayers() == players.size()) {
            this.startedGame = true;
            return true;
        }
        return false;
    }
    private int countActivePlayers() {
        int count = 0;
        for (ThreadServidor player : players) {
            if (player.isActive) count++;
        }
        return count;
    }

    private int countReadyPlayers() {
        int count = 0;
        for (ThreadServidor player : players) {
            if (player.isReady()) count++;
        }
        return count;
    }

    private void endGame() {
        ThreadServidor winner = null;
        for (ThreadServidor player : players) {
            if (player.isActive) {
                winner = player;
                break;
            }
        }
        if (winner != null) {
            broadcastWinner(winner);
        }
    }

    private void notifyPlayerDeath(ThreadServidor deadPlayer) {
        for (ThreadServidor player : players) {
            try {
                String[] args = {"message", "El jugador " + deadPlayer.name + " ha muerto"};
                CommandMessage deathMessage = new CommandMessage(args);
                deathMessage.setSenderName("SERVER");
                player.respondAction(deathMessage);
            } catch (Exception e) {
                System.out.println("Error notificando muerte: " + e.getMessage());
            }
        }
    }


    private void broadcastWinner(ThreadServidor winner) {
        System.out.println("3");
        for (ThreadServidor player : players) {
            try {
                String[] args = {"message", "GAME OVER. El ganador ha sido: " + winner.name};
                CommandMessage winMessage = new CommandMessage(args);
                winMessage.setSenderName("SERVER");
                player.respondAction(winMessage);
            } catch (Exception e) {
                System.out.println("Error enviando ganador: " + e.getMessage());
            }
        }
    }

    public int getCurrentTurnIndex() {
        return currentTurnIndex;
    }

    public boolean isStartedGame() {
        return startedGame;
    }

    public void setStartedGame(boolean startedGame) {
        this.startedGame = startedGame;
    }
}
