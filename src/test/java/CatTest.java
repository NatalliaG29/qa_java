import com.example.Cat;
import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline felineMock;

    @Test
    public void testGetSound() {
        Cat cat = new Cat(felineMock);
        String expectedSound = "Мяу";
        String actualSound = cat.getSound();
        assertEquals(expectedSound, actualSound);
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(felineMock.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(felineMock);
        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        Mockito.verify(felineMock).eatMeat();
    }
}







