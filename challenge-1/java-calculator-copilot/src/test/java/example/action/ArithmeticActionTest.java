package example.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class ArithmeticActionTest {
    private ArithmeticAction action;

    @BeforeEach
    public void setUp() {
        action = new ArithmeticAction();
    }

    @Test
    public void testAddOperation() {
        action.setOperation("add");
        action.setOperand1(1.0);
        action.setOperand2(2.0);
        action.execute();
        Map<String, Object> resultJson = action.getResultJson();
        assertEquals(3.0, resultJson.get("result"));
    }
}
