package dam.primerCurso.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Question} class represents a quiz question.
 * <p>
 * Each question has:
 * </p>
 * <ul>
 *     <li>An {@code id} identifier</li>
 *     <li>A {@code statement} (the text of the question)</li>
 *     <li>A list of {@code options}</li>
 *     <li>The {@code correctAnswer}</li>
 *     <li>An {@code explanation} for the answer</li>
 * </ul>
 *
 * <p>
 * The class also implements the {@link IVerificable} interface,
 * providing a method to validate user answers with input control.
 * </p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class Question implements IVerificable {

    /** Identifier of the question */
    private String id;

    /** Statement or text of the question */
    private String statement;

    /** Dynamic list of options (A, B, C, D, etc.) */
    private List<String> options;

    /** The correct answer */
    private String correctAnswer;

    /** Explanation of the correct answer */
    private String explanation;

    /**
     * Constructs a new {@code Question} instance.
     *
     * @param id            the identifier of the question
     * @param statement     the question text
     * @param options       the list of possible options
     * @param correctAnswer the correct option
     * @param explanation   the explanation of the answer
     */
    public Question(String id, String statement, List<String> options,
                    String correctAnswer, String explanation) {
        this.id = id;
        this.statement = statement;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<String> getOptions() {
        return options;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * Verifies if the user's answer is valid and matches the correct answer.
     * <p>
     * Rules:
     * </p>
     * <ul>
     *     <li>Valid inputs: {@code a}, {@code b}, {@code c}, {@code d}, or {@code 0}</li>
     *     <li>{@code 0} terminates the program</li>
     *     <li>Answer is compared ignoring case and extra spaces</li>
     * </ul>
     *
     * @param userAnswer the answer entered by the user
     * @return {@code true} if the answer is valid and correct, {@code false} otherwise
     */
    @Override
    public boolean verificarPregunta(String userAnswer) {
        if (userAnswer == null) {
            return false;
        }

        String answer = userAnswer.trim().toLowerCase();

        // Set of valid inputs
        Set<String> validInputs = new HashSet<>(Set.of("a", "b", "c", "d", "0"));

        if (!validInputs.contains(answer)) {
            return false;
        }

        if (answer.equals("0")) {
            System.out.println("Program terminated by user.");
            System.exit(0);
        }

        return correctAnswer.equalsIgnoreCase(answer);
    }
}
