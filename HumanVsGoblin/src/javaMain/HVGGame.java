public class HVGGame {
    private int numMoves, damageDone;
    private String playerName;

    public HVGGame(String name){
        this.numMoves = 0;
        this.damageDone = 0;
        this.playerName = name;
    }

    public void play(){
        MapGrid grid            = new MapGrid();
        UserInput getInput      = new UserInput();

        while(!grid.gameOver){
            grid.showGrid();
            System.out.println("\nWhat's your move? (n/s/w/e)");
            String playMove = getInput.getString();
            grid.movePlayer(playMove).pursuePlayer();
        }

        if(grid.gameOver) {
            System.out.println(playerName + " have lost the game!");
            System.out.println("Would you like to play again? y/n");
            String playAgain = getInput.getString();
            if(playAgain.charAt(0) == 'y' || playAgain.charAt(0) == 'Y') play();
            else System.out.println("\nGoodbye! " + playerName);
        }

    }


}
