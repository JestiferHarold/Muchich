import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class ModTest {
	
	private Mod mode;

	@BeforeAll
	void setup(){
		mode = new Mod();
	}

	@Test
	void TestInt() {
		Assertions.assertEquals(5 , mode.add(1, 4));
	}
}
