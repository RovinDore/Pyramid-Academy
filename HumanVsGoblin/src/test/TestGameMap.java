import org.junit.Before;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameMap {
    MapGrid grid = new MapGrid(5);
    Human Adam = new Human(0);

    @Test
    void testPlayerMove(){
        grid.movePlayer("e");
        assertEquals(1, grid.findHuman().getPosition());
    }

    @Test
    void findHuman(){
        assertEquals(Adam.toString(), grid.findHuman().toString());
    }

    @Test
    void testPursuePlayer(){
        MapGrid temp = grid;
        Goblin gobby = new Goblin(5);
        grid.addToGrid(gobby);
        grid.pursuePlayer();
        assertEquals(4, gobby.getPosition());
    }

    @Test
    void testCombat(){
        grid.addToGrid(new Goblin(0));
        assertNotEquals(Outcome.Peace, grid.checkCombat());
    }

    @Test
    void testPlayerAttack(){
        assertEquals(Outcome.Peace, grid.checkCombat());
    }

}
