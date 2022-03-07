public class HVGGame {
    private int numMoves, damageDone;
    private String playerName;
    private UserInput getInput = new UserInput();


    public HVGGame(String name){
        this.numMoves = 0;
        this.damageDone = 0;
        this.playerName = name;
    }

    public void play(){
        MapGrid grid = new MapGrid();

        while(!grid.gameOver){
            grid.showGrid();
            System.out.println("\nWhat's your move? (n/s/w/e)");
            try{
                String playMove = getInput.getString();
                grid.pursuePlayer().movePlayer(playMove);
            } catch (Exception e){
                System.out.println("Something went wrong " + e.getMessage());
            }
            numMoves++;
        }

        System.out.print(playerName + " has lost the game! ");
        System.out.println("but gained " + (int)grid.getPlayerPoints() + " points!");

        if(tryAgain()) play();
        else System.out.println("\nGoodbye " + playerName + "!");

    }

    private Boolean tryAgain(){
        System.out.println("Would you like to play again? y/n");
        String playAgain = getInput.getString();
        if(playAgain.charAt(0) == 'y' || playAgain.charAt(0) == 'Y') return true;
        return false;
    }

}
