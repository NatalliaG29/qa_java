import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class LionUnitTest {

    @Mock
    private Feline felineMock;

    @Test
    public void testGetKittens() throws Exception {
        Mockito.when(felineMock.getKittens()).thenReturn(2);
        Lion lion = new Lion("Самка", felineMock);
        int kittens = lion.getKittens();
        assertEquals(2, kittens);
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные, Птицы, Рыба");
        Mockito.when(felineMock.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineMock);
        List<String> food = lion.getFood();
        assertEquals(expectedFood, food);
    }
}
