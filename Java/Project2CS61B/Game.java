package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import byog.World.MapGenerator;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        MapGenerator map = new MapGenerator(ter,WIDTH,HEIGHT);
        map.startGame();
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public void playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        MapGenerator map = new MapGenerator(ter,WIDTH,HEIGHT);
        StringBuilder str = new StringBuilder();
        StringBuilder moves = new StringBuilder();
        Character initialCommand = input.charAt(0);
        int length = input.length();


        for (int i=1;i<length; i++) {
            char previos = input.charAt(i-1);
            char val = input.charAt(i);
            if (Character.isDigit(val)) {
                str.append(val);
            } else if (Character.isAlphabetic(val) &&  Character.toLowerCase(val) != 'l' && !Character.isDigit(previos)) {
                moves.append(val);
            }
        }


        if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
            System.exit(0);
        }

         // return the seed and start the game
        if (str.length() == 0) {
            map.startGameWithInputString(0,initialCommand,moves.toString());
        } else {
            map.startGameWithInputString(Long.parseLong(str.toString()), initialCommand,moves.toString());
        }
    }
}
