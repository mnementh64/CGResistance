import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Problem problem = new Problem();

        long t0 = System.currentTimeMillis();
        problem.morse = in.next();
        int nbWords = in.nextInt();
        for (int i = 0; i < nbWords; i++) {
            String word = in.next();
            problem.addEntry(word.toUpperCase());
        }
        problem.pack();
        long t1 = System.currentTimeMillis();
        System.err.println("Init in " + (t1 - t0) + "ms");

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        ProblemSolver solver = new ProblemSolver(problem);
        long result = solver.solve();
        System.out.println(result);
    }
}

class Problem {
    String morse;
    Set<String> dictionary = new TreeSet<>();
    Set<String> dictionaryFirstLetters = new TreeSet<>();
    Map<String, List<String>> wordsInMorseByFirstLetter = new HashMap<>();

    void addEntry(String word) {
        dictionary.add(word);
    }

    void loadDictionary(String rawDictionary) {
        dictionary.addAll(Arrays.asList(rawDictionary.split("\\n")));
    }

    void pack() {
        dictionaryFirstLetters = dictionary.stream()
                .map(word -> word.substring(0, 1))
                .collect(Collectors.toSet());

        for (String word : dictionary) {
            String firstLetter = word.substring(0, 1);
            wordsInMorseByFirstLetter.computeIfAbsent(firstLetter, (letter) -> new ArrayList<>()).add(Morse.buildMorseSequence(word));
        }
    }
}

class ProblemSolver {
    Problem problem;
    Map<String, Long> sequencesToCountMap = new HashMap<>();

    ProblemSolver(Problem problem) {
        this.problem = problem;
        this.sequencesToCountMap.put(problem.morse, 0L); // initial value
    }

    long solve() {
        // Max duration = 1.8s

        boolean changed = findFirstWord();
        while (changed) {
            changed = findFirstWord();
        }

        return sequencesToCountMap.entrySet().stream()
                .filter(e -> e.getKey().equals(""))
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    private boolean findFirstWord() {
        Map<String, Long> sequenceMap = new HashMap<>();
        boolean hasFoundSomething = false;

        for (String sequence : sequencesToCountMap.keySet()) {
            if (!sequence.isEmpty()) {
                Map<String, Long> sequenceMapForThisWord = findFirstWord(sequence, sequencesToCountMap.get(sequence));  // remove the entry --> will vbe replaced by sub-entries
                if (!sequenceMapForThisWord.isEmpty()) {
                    hasFoundSomething = true;

                    for (String key : sequenceMapForThisWord.keySet()) {
                        long nbCombi = sequenceMap.getOrDefault(key, 0L);
                        sequenceMap.put(key, nbCombi + sequenceMapForThisWord.get(key));
                    }
                }
            } else {
                sequenceMap.put(sequence, sequencesToCountMap.get(sequence));
            }
        }

        sequencesToCountMap = sequenceMap;

        return hasFoundSomething;
    }

    private Map<String, Long> findFirstWord(String sequence, long nbCombi) {
        Map<String, Long> localMap = new HashMap<>();

        // check all first letters of the dictionary
        for (String firstLetter : problem.dictionaryFirstLetters) {
            if (Morse.startByLetter(sequence, firstLetter)) {
                // get all words starting by this letter
                List<String> wordsInMorse = problem.wordsInMorseByFirstLetter.get(firstLetter);
                for (String wordInMorse : wordsInMorse) {
                    // if the sequence starts by the word, it makes a new combination
                    String remainingSequence = Morse.startByWord(sequence, wordInMorse);
                    if (remainingSequence != null) {
                        Long nbOccurences = localMap.getOrDefault(remainingSequence, 0L) + 1;
                        localMap.put(remainingSequence, nbOccurences);
                    }
                }
            }
        }

        long multiplicator = nbCombi != 0 ? nbCombi : 1;
        return localMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * multiplicator));
    }
}

class Morse {
    private static Map<String, String> MORSE_DICTIONARY = createMorseDictionary();

    public static boolean startByLetter(String sequence, String firstLetter) {
        String morseSequence = MORSE_DICTIONARY.get(firstLetter);
        return sequence.length() >= morseSequence.length() && sequence.startsWith(morseSequence);
    }

    public static String startByWord(String sequence, String morseSequence) {
        int morseSequenceLength = morseSequence.length();
        if (sequence.length() >= morseSequenceLength && sequence.startsWith(morseSequence)) {
            return sequence.substring(morseSequenceLength);
        }

        return null;
    }

    public static String buildMorseSequence(String word) {
        return word.chars().mapToObj(c -> MORSE_DICTIONARY.get(String.valueOf((char) c))).collect(Collectors.joining());
    }

    private static Map<String, String> createMorseDictionary() {
        Map<String, String> morse = new HashMap<>();
        morse.put("A", ".-");
        morse.put("B", "-...");
        morse.put("C", "-.-.");
        morse.put("D", "-..");
        morse.put("E", ".");
        morse.put("F", "..-.");
        morse.put("G", "--.");
        morse.put("H", "....");
        morse.put("I", "..");
        morse.put("J", ".---");
        morse.put("K", "-.-");
        morse.put("L", ".-..");
        morse.put("M", "--");
        morse.put("N", "-.");
        morse.put("O", "---");
        morse.put("P", ".--.");
        morse.put("Q", "--.-");
        morse.put("R", ".-.");
        morse.put("S", "...");
        morse.put("T", "-");
        morse.put("U", "..-");
        morse.put("V", "...-");
        morse.put("W", ".--");
        morse.put("X", "-..-");
        morse.put("Y", "-.--");
        morse.put("Z", "--..");
        return morse;
    }
}