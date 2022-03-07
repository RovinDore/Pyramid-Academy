import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapGrid {
    int size = 5, gridSize;
    private final ArrayList<GridSpot> layout = new ArrayList<>();
    private ArrayList<Human> entities = new ArrayList<>();
    private final ArrayList<TreasureChest> treasureDrops = new ArrayList<>();
    private final Land defaultLand = new Land();
    public boolean gameOver = false;
    private final Random rand = new Random();

    public MapGrid(){
        this.gridSize = size * size;
        IntStream.rangeClosed(0,gridSize - 1).forEach(land -> layout.add(defaultLand));

        Human Adam              = new Human(0);
        Random rand             = new Random();
        ArrayList<Goblin> goblins = new ArrayList<>();

        for(int i = 0; i < 1; i++) goblins.add(new Goblin(rand.nextInt(gridSize) + 1));
        goblins.forEach(gobby -> addToGrid(gobby));
        addToGrid(Adam).updateMapGrid();
    }

    public MapGrid addToGrid(Human man){
        entities.add(man);
        return this;
    }

    public Human findHuman(){
        for(Human obj: entities) {
            if (obj.toString() == Human.emoji) return obj;
        }
        return null;
    }

    public MapGrid pursuePlayer(){
        Human toFollow = findHuman();
        for(Human obj: entities){
            if(obj.toString() == Goblin.emoji){
                Goblin gobby = (Goblin)obj;
                gobby.pursuePlayer(toFollow);
            }
        }
        updateMapGrid();
        return this;
    }

    public MapGrid movePlayer(String direction){
        Human toMove = findHuman();
        if (toMove == null) return this;
        switch (direction){
            case "n":
                toMove.moveNorth();
                break;
            case "s":
                toMove.moveSouth();
                break;
            case "e":
                toMove.moveRight();
                break;
            case "w":
                toMove.moveLeft();
                break;
            default: return this;
        }
        updateMapGrid();
        return this;
    }

    public MapGrid showGrid(){
//        updateMapGrid();
        int numSpot = 1;
        System.out.println();
        for(GridSpot spot: layout){
            System.out.print(spot + " ");
            if(numSpot % size == 0) System.out.println();
            ++numSpot;
        }
        return this;
    }

    public MapGrid updateMapGrid(){
        IntStream.rangeClosed(0,gridSize - 1).forEach(land -> setGridSpot(land, defaultLand));
        Outcome anyConflicts = checkCombat();
        if(anyConflicts == Outcome.GoblinDies) addToGrid(new Goblin(rand.nextInt(gridSize - 1)));
        entities.forEach(h -> setGridSpot(h.getPosition(), h));
        updateTreasures();
        return this;
    }

    public void updateTreasures(){
        if(treasureDrops.size() > 0){
            ArrayList<TreasureChest> toBeRemoved = new ArrayList<>();
            treasureDrops.forEach(treasure -> {
                if (getGridSpot(treasure.getPosition()).toString().equals(Human.emoji)) {
                    findHuman().addPoints(treasure.getPoints());
                    System.out.println("You gained " + (int) treasure.getPoints() + " points!");
                    toBeRemoved.add(treasure);
                } else setGridSpot(treasure.getPosition(), treasure);
            });
            toBeRemoved.forEach(t -> treasureDrops.remove(t));;
        }
    }

    public Outcome checkCombat(){
        HashMap<Integer, Integer> duplicates = new HashMap<>(); //Mapgrid position, Position in humanlist
        Outcome combatOutcome = Outcome.Peace;
        for(int i = 0; i < entities.size(); i++){
            Human person = entities.get(i);
            if(duplicates.get(person.getPosition()) == null) duplicates.put(person.getPosition(), i);
            else{
                combatOutcome = person.combat();

                switch (combatOutcome){
                    case HumanDies:
                        System.out.println("You died!!");
                        this.gameOver = true;
//                        this.entities = entities.stream().filter(h -> h.toString() != Human.emoji ).collect(Collectors.toCollection(ArrayList::new));
                        break;
                    case GoblinDies:
                        System.out.println("The Goblin died!!");
                        int newRanSpot = rand.nextInt(gridSize - 1);
                        this.entities = entities.stream().filter(h -> h.toString() != Goblin.emoji ).collect(Collectors.toCollection(ArrayList::new));
                        TreasureChest newDrop = new TreasureChest(newRanSpot);
                        treasureDrops.add(newDrop);
                        break;
                }

            }

        }

        return combatOutcome;
    }

    public double getPlayerPoints(){
        return findHuman().getPoints();
    }

    public GridSpot getGridSpot(int index){
        return layout.get(index);
    }

    public void setGridSpot(int index, GridSpot spot){
        layout.set(index, spot);
//        return this;
    }

    @Override
    public String toString(){
        return layout.toString().replace(",", " ").replace("[", "").replace("]","");
    }
}
