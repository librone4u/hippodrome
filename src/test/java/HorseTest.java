import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.configuration.IMockitoConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    private Horse horse = new Horse("Name", 5.5, 5.8);
    @Test
    public void ShouldReturnIllegalArgumentExceptionWhenNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.2, 2.3);
        });
    }
    @Test
    public void ExceptionShouldHaveMsg(){
        try{
            new Horse(null, 5.5, 4.6);
        }catch (Exception e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void ExceptionShouldHaveNameCannotBeBlankMsg(String value){
        try{
            new Horse(value, 6.6, 5.6);
        }catch (Exception e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }
    @Test
    public void TestConstructorWithNegativeNumberOfSecondValue(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Horse("ASd", -3.5, 5.6);
        });
    }
    @Test
    public void ShouldBeExceptionMsgSpeedCannotBeNegativeOfSecondValue(){
        try{
            new Horse("sASD", -5.5, 5.5);
        }catch (Exception e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }
    @Test
    public void TestConstructorWithNegativeNumberOfThirdValue(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Horse("ASd", 3.5, -5.6);
        });
    }
    @Test
    public void ShouldBeExceptionMsgSpeedCannotBeNegativeOfThirdValue(){
        try{
            new Horse("sASD", 5.5, -5.5);
        }catch (Exception e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
    @Test
    public void ShouldReturnName(){
        assertEquals("Name", horse.getName());
    }
    @Test
    public void ShouldReturnSpeed(){
        assertEquals(5.5, horse.getSpeed());
    }
    @Test
    public void method_getDistance_returnNumber(){
        assertEquals(5.8, horse.getDistance());
    }
    @Test
    public void method_getDistance_return_0_IfConstructorHas2Param(){
        Horse horse1 = new Horse("TestName", 5.5);

        assertEquals(0, horse1.getDistance());
    }
    @Test
    public void method_move_verifyGetRandomDouble(){
        try(MockedStatic<Horse> mockedStatic =  mockStatic(Horse.class)){
            double expectedParam1 = 0.2;
            double expectedParam2 = 0.9;

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(expectedParam1, expectedParam2));
        }
    }
    @Test
    public void method_move_checkingDistansFormula(){
        double speed = 5.5;
        double distance = 4.7;

        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse testHorse = new Horse("TestName", speed, distance);
            testHorse.move();

            double expectedDistance = distance + speed * 0.5;
            assertEquals(expectedDistance, testHorse.getDistance());
        }
    }
}