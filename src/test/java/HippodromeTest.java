import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    public void constructor_NullParam_IllegalArgumentException(){
        String expectedExceptionMessage = "Horses cannot be null.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
    @Test
    public void constructor_EmptyList_illegalArgumentException(){

        List<Horse> emptyList = new ArrayList<>();
        String expectedExceptionMessage = "Horses cannot be empty.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->{
            new Hippodrome(emptyList);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
    @Test
    public void method_getHorses_returnedHorses(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            horses.add(new Horse("Horse " + i, 5.5, 4.6));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        List<Horse> returnedHorse = hippodrome.getHorses();
        assertEquals(horses, returnedHorse);
    }
    @Test
    public void method_move_verifyMethodToAllHorses(){
        List<Horse> mockHorses = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            mockHorses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);

        hippodrome.move();

        for(Horse horse: mockHorses){
            verify(horse).move();
        }
    }
    @Test
    public void method_getWinner(){
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Horse1", 1, 10),
                new Horse("Horse2", 1, 20),
                new Horse("Horse3", 1, 30)
        ));
        assertEquals("Horse3", hippodrome.getWinner().getName());
    }
}