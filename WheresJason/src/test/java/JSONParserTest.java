package io.github.clupthegreat.wheresjason;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JSONParserTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private JSONParser jsonParser;

    @BeforeEach
    public void setUp() {
        jsonParser = new JSONParser();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testJsonReader_printsJsonFileContent() throws FileNotFoundException {
        jsonParser.JsonReader(jsonParser.sample_data_path);

        String output = outputStreamCaptor.toString().trim();

        // Replace this with some line or key you know exists in your JSON
        assertTrue(output.contains("{") || output.contains("}"), "Output should contain JSON content");
    }
}
}