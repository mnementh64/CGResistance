import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(chuckNorrisEncode(message, false));
    }

    static String chuckNorrisEncode(String message, boolean debug) {
        // translate from ASCII to binary
        String binaryForm = asBinary(message.getBytes(), debug);
        if (debug) System.err.println(binaryForm);

        // translate from binary to chuck norris
        return asChuckNorris(binaryForm);
    }

    /**
     * Remove heading 0s
     */
    private static String cleanBinary(String binaryForm) {
        // too long ?
        if (binaryForm.length() > 7) {
            int indexOfFirstOne = binaryForm.indexOf("1");
            return binaryForm.substring(indexOfFirstOne);
        }

        // too short ?
        if (binaryForm.length() < 7) {
            int nbHeadingZero = 7 - binaryForm.length();
            String heading = IntStream.rangeClosed(1, nbHeadingZero).mapToObj(i -> "0").collect(Collectors.joining());
            return heading + binaryForm;
        }

        return binaryForm;
    }

    private static String asBinary(byte[] bytes, boolean debug) {
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;

            StringBuilder byteAsBinary = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                byteAsBinary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            if (debug) System.err.println(" --> " + byteAsBinary);
            binary.append(cleanBinary(cleanBinary(byteAsBinary.toString())));
        }

        return binary.toString();
    }

    private static String asChuckNorris(String message) {
        int i = 0;
        Character c = null;
        StringBuilder sb = new StringBuilder();

        int nb = 0;
        while (i < message.length()) {
            // first char
            if (c == null) {
                c = message.charAt(i);
            }

            // change char
            if (message.charAt(i) != c) {
                sb.append(chuckNorrisBlock(c, nb));

                // re-init for next char
                c = message.charAt(i);
                nb = 0;
            }

            nb++;
            i++;
        }

        sb.append(chuckNorrisBlock(c, nb));

        return sb.toString().substring(0, sb.length() - 1);
    }

    private static String chuckNorrisBlock(char c, int nb) {
        return (c == '1' ? "0 " : "00 ") +
                IntStream.rangeClosed(1, nb).mapToObj(i -> "0").collect(Collectors.joining()) +
                " ";
    }
}