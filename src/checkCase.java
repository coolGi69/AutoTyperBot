public class checkCase {
    // Checks if the character is upper or lowe case

    public static boolean checkCase(char input) {
        if(input >= 'A' && input <= 'Z') {
            return true;
        } else if(input >= 'a' && input <= 'z') {
            return false;
        } else {
            return false;
        }
    }
}
