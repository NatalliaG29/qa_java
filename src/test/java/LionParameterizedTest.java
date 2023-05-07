import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class LionParameterizedTest {

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

    public LionParameterizedTest(String sex, Object expectedHasMane) {
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
                assertEquals(expected, lion.doesHaveMane());
            } else if (expectedHasMane instanceof Exception) {
                assertThrows(Exception.class, ()->{new Lion(any(), any());});
            }
        } catch (Exception e) {
            if (expectedHasMane instanceof Exception) {
                Exception expected = (Exception) expectedHasMane;
                assertEquals(expected.getMessage(), e.getMessage());
            } else if (expectedHasMane instanceof Boolean) {
                assertThrows(Exception.class, ()->{new Lion(any(), any());});
            }
        }
    }
}
