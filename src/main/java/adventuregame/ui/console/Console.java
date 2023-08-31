package adventuregame.ui.console;

import java.io.IOException;

class Console {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void println(String text) {
        System.out.println(text);
    }

    public char getKey() {
        char key;
        try {
            key = (char) RawConsoleInput.read(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
