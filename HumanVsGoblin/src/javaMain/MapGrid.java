import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapGrid {
    int size, gridSize;
    private final ArrayList<GridSpot> layout = new ArrayList<>();
    private ArrayList<Human> humans = new ArrayList<>();
    private final ArrayList<GridSpot> treasureDrops = new ArrayList<>();
    private final Bush defaultLand = new Bush();
    public boolean gameOver = false;
    private final Random rand = new Random();

    public MapGrid(int size){
        this.size = size;
        this.gridSize = size * size;
        IntStream.rangeClosed(0,gridSize - 1).forEach(land -> layout.add(defaultLand));

        Human Adam              = new Human(0);
        Random rand             = new Random();
        ArrayList<Goblin> goblins = new ArrayList<>();

        for(int i = 0; i < 2; i++) goblins.add(new Goblin(rand.nextInt(gridSize)));
        goblins.forEach(gobby -> addToGrid(gobby));
        addToGrid(Adam).updateMapGrid();
    }

    public MapGrid addToGrid(Human man){
        humans.add(man);
        return this;
    }

    public Human findHuman(){
        for(Human obj: humans) {
            if (obj.toString() == Human.emoji) return obj;
        }
        return null;
    }

    public MapGrid pursuePlayer(){
        Human toFollow = findHuman();
        for(Human obj: humans){
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
        IntStream.rangeClosed(0,gridSize - 1).forEach(land -> layout.set(land, defaultLand));
        Outcome anyConflicts = checkCombat();
        if(anyConflicts == Outcome.GoblinDies) addToGrid(new Goblin(rand.nextInt(gridSize - 1)));
        if(treasureDrops.size() > 0) treasureDrops.forEach(treasure -> setGridSpot(treasure.getPosition(), treasure));
        humans.forEach(h -> setGridSpot(h.getPosition(), h));
        return this;
    }

    public Outcome checkCombat(){
        HashMap<Integer, Integer> duplicates = new HashMap<>(); //Mapgrid position, Position in humanlist
        Outcome combatOutcome = Outcome.Peace;
        for(int i = 0; i < humans.size(); i++){
            Human person = humans.get(i);
            if(duplicates.get(person.getPosition()) == null) duplicates.put(person.getPosition(), i);
            else{
                combatOutcome = person.combat();

                switch (combatOutcome){
                    case HumanDies:
                        System.out.println("Human dies");
                        this.gameOver = true;
                        this.humans = humans.stream().filter(h -> h.toString() != Human.emoji ).collect(Collectors.toCollection(ArrayList::new));
                        break;
                    case GoblinDies:
                        System.out.println("Goblin dies");
                        int newRanSpot = rand.nextInt(gridSize - 1);
                        this.humans = humans.stream().filter(h -> h.toString() != Goblin.emoji ).collect(Collectors.toCollection(ArrayList::new));
                        TreasureChest newDrop = new TreasureChest(newRanSpot);
                        treasureDrops.add(newDrop);
                        break;
                }

            }

        }

        return combatOutcome;
    }

    public GridSpot getGridSpot(int index){
        return layout.get(index);
    }

    public GridSpot setGridSpot(int index, GridSpot spot){
        return layout.set(index, spot);
    }

    @Override
    public String toString(){
        return layout.toString().replace(",", " ").replace("[", "").replace("]","");
    }
}
