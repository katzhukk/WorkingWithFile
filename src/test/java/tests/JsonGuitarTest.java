package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import tests.model.Guitar;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonGuitarTest {

    private static final ClassLoader cl = JsonGuitarTest.class.getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("guitar.json")
        )) {
            ObjectMapper mapper = new ObjectMapper();
            Guitar actualGuitars = mapper.readValue(reader, Guitar.class);

            assertThat(actualGuitars.getGuitars().get(0).getBrand()).isEqualTo("RockDale");
            assertThat(actualGuitars.getGuitars().get(0).getModel()).isEqualTo("Aurora D1");
            assertThat(actualGuitars.getGuitars().get(0).getType()).isEqualTo("Acoustic");
            assertThat(actualGuitars.getGuitars().get(0).getMaterial()).isEqualTo("Mahogany");
            assertThat(actualGuitars.getGuitars().get(0).getGuitarStrings().getStrings()).isEqualTo(6);
            assertThat(actualGuitars.getGuitars().get(0).getGuitarStrings().getMaterialString()).isEqualTo("Bronze");
            assertThat(actualGuitars.getGuitars().get(0).getGuitarStrings().getThicknessString()).isEqualTo("9-45");

            assertThat(actualGuitars.getGuitars().get(1).getBrand()).isEqualTo("YAMAHA");
            assertThat(actualGuitars.getGuitars().get(1).getModel()).isEqualTo("F310");
            assertThat(actualGuitars.getGuitars().get(1).getType()).isEqualTo("Acoustic");
            assertThat(actualGuitars.getGuitars().get(1).getMaterial()).isEqualTo("Mahogany");
            assertThat(actualGuitars.getGuitars().get(1).getGuitarStrings().getStrings()).isEqualTo(6);
            assertThat(actualGuitars.getGuitars().get(1).getGuitarStrings().getMaterialString()).isEqualTo("Steel");
            assertThat(actualGuitars.getGuitars().get(1).getGuitarStrings().getThicknessString()).isEqualTo("9-42");
        }
    }
}
