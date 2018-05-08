import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Problem problem = new Problem();

        String morse = in.next();
        problem.morse = morse;
        int nbWords = in.nextInt();
        for (int i = 0; i < nbWords; i++) {
            String word = in.next();
            problem.addEntry(word);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        ProblemSolver solver = new ProblemSolver(problem);
        System.out.println(solver.solve());
    }
}

class Problem {
    String morse;
    Set<String> dictionary = new TreeSet<>();

    void addEntry(String word) {
        dictionary.add(word);
    }

    void loadDictionary(String rawDictionary) {
        dictionary.addAll(Arrays.asList(rawDictionary.split("\\n")));
    }
}

class ProblemSolver {
    Problem problem;

    ProblemSolver(Problem problem) {
        this.problem = problem;
    }

    long solve() {

        return 0L;
    }
}

