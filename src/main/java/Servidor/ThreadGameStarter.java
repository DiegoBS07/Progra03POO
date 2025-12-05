package Servidor;

public class ThreadGameStarter extends Thread {
    private TurnManager turnManager;

    public ThreadGameStarter(TurnManager turnManager) {
        this.turnManager = turnManager;
    }

    public void run(){
        while(!turnManager.startGame()){
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
        turnManager.startTurns();
    }
}
