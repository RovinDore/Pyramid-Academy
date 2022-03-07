import org.junit.Before;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameMap {
    MapGrid grid = new MapGrid();
    Human Adam = new Human(0);

    @Test
    void testPlayerMoveRight(){
        grid.movePlayer("e");
        assertEquals(1, grid.findHuman().getPosition());
    }

    @Test
    void testPlayerMoveLeft(){
        grid.findHuman().setPosition(5);
        grid.movePlayer("w");
        assertEquals(4, grid.findHuman().getPosition());
    }

    @Test
    void testPlayerMoveNorth(){
        grid.findHuman().setPosition(20);
        grid.movePlayer("n");
        assertEquals(15, grid.findHuman().getPosition());
    }

    @Test
    void testPlayerMoveSouth(){
        grid.movePlayer("s");
        assertEquals(5, grid.findHuman().getPosition());
    }

    @Test
    void findHuman(){
        assertEquals(Adam.toString(), grid.findHuman().toString());
    }

    @Test
    void testPursuePlayer(){
        MapGrid temp = grid;
        Goblin gobby = new Goblin(5);
        grid.addToGrid(gobby).pursuePlayer();
        assertEquals(4, gobby.getPosition());
    }

    @Test
    void testCombat(){
        grid.addToGrid(new Goblin(0));
        Outcome result = grid.checkCombat();
        assertNotEquals(Outcome.Peace, result);
    }

    @Test
    void testPlayerAttack(){
        assertEquals(Outcome.Peace, grid.checkCombat());
    }

}
