import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

import java.io.File;
import java.io.IOException;

import static pooTV.POOTV.pooTVInit;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inputFilePath), Input.class);
//        Input inputDataTest = objectMapper.readValue(new File("checker/resources/in/basic_" + 7 + ".json"), Input.class);

        ArrayNode output = objectMapper.createArrayNode();
//        ArrayNode output1 = objectMapper.createArrayNode();

        pooTVInit(inputData, output);
//        pooTVInit(inputDataTest, output1);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFilePath), output);
//        objectWriter.writeValue(new File("out.txt"), output1);
    }
}
