import org.bcit.comp2522.labs.lab04.Window;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WindowTest {

    Window window;

    @BeforeEach
    void setUp() {
        window = new Window();
    }

    @Test
    void checkWindowSize(){
        window.setSize(569, 789);
        assertFalse(window.checkWindowSize(window));

        window.setSize(640, 360);
        assertTrue(window.checkWindowSize(window));
    }
}
