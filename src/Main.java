import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import pootv.DataBase;
import pootv.Menu;
import pootv.recommendation.Recommendation;

import java.io.File;
import java.io.IOException;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inputFilePath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        Menu menu = new Menu(inputData, output);
        menu.actionsPooTv();

        /*
         * Input inputDataTest = objectMapper.readValue(new File("checker/resources/in/basic_" + 1 + ".json"), Input.class);
         * ArrayNode outputTest = objectMapper.createArrayNode();
         * Menu menuTest = new Menu(inputDataTest, outputTest);
         * menuTest.actionsPooTv();
         *
         * used for testing a single input file
         */

        Recommendation.recommendation();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFilePath), output);

        // objectWriter.writeValue(new File("out.txt"), outputTest); // used for testing a single input file
    }
}
