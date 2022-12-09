package pooTV;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public class POOTV {
    public static void pooTVInit(Input inputData, ArrayNode output) {
        Menu menu = new Menu(inputData, output);
        menu.actionsPOOTV();
    }
}
