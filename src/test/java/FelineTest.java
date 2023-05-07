import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Mock
    Feline felineMock;

    @Test
    public void testGetFamily() {
        String expected = "Кошачьи";
        Mockito.when(felineMock.getFamily()).thenReturn(expected);
        String actual = felineMock.getFamily();
        assertEquals(expected, actual);
    }

    @Test
    public void testEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(felineMock.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = felineMock.eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetKittens() {
        int expected = 1;
        Mockito.when(felineMock.getKittens()).thenReturn(expected);
        int actual = felineMock.getKittens();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetKittensWithParam() {
        int expected = 4;
        int kittensCount = 4;
        Mockito.when(felineMock.getKittens(kittensCount)).thenReturn(expected);
        int actual = felineMock.getKittens(4);
        assertEquals(expected, actual);
    }
}
