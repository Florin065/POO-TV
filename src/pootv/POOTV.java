package pootv;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class POOTV {
    private POOTV() {
    }

    /**
     *
     * @param inputData
     * @param output
     */
    public static void pooTVInit(final Input inputData, final ArrayNode output) {
        Menu menu = new Menu(inputData, output);
        menu.actionsPOOTV();
    }
}
