import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class LionTest {

    @Mock
    private Feline felineMock;

    @Parameterized.Parameters
    public static Object[][] getSex() {
        return new Object[][]{
                { "Самец", true },
                { "Самка", false },
                { "Неизвестный пол", new Exception("Используйте допустимые значения пола животного - самец или самка") }
        };
    }

    private String sex;
    private Object expectedHasMane;

    public LionTest(String sex, Object expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testHasMane() {
        Feline felineMock = mock(Feline.class);
        try {
            Lion lion = new Lion(sex, felineMock);
            if (expectedHasMane instanceof Boolean) {
                boolean expected = (boolean) expectedHasMane;
                assertEquals(expected, lion.hasMane);
            } else if (expectedHasMane instanceof Exception) {
                fail("Исключение не выявлено");
            }
        } catch (Exception e) {
            if (expectedHasMane instanceof Exception) {
                Exception expected = (Exception) expectedHasMane;
                assertEquals(expected.getMessage(), e.getMessage());
            } else if (expectedHasMane instanceof Boolean) {
                fail("Исключение выявлено");
            }
        }
    }

    @Test
    public void testGetKittens() throws Exception {
        Mockito.when(felineMock.getKittens()).thenReturn(2);
        Lion lion = new Lion("Самка", felineMock);
        int kittens = lion.getKittens();
        assertEquals(2, kittens);
    }

    @Test
    public void testDoesHaveMane() throws Exception {
        Lion maleLion = new Lion("Самец", felineMock);
        Lion femaleLion = new Lion("Самка", felineMock);
        assertEquals(true, maleLion.doesHaveMane());
        assertEquals(false, femaleLion.doesHaveMane());

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
