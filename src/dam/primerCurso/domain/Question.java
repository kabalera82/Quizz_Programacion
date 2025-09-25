package dam.primerCurso.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Question} class represents a single quiz question in the game.
 *
 * <p>Each question contains:</p>
 * <ul>
 *     <li>A unique identifier ({@code id})</li>
 *     <li>A statement or question text</li>
 *     <li>A list of possible options (a, b, c, d)</li>
 *     <li>The correct answer</li>
 *     <li>An explanation shown after answering</li>
 * </ul>
 *
 * <p>This class belongs to the <b>Domain Layer</b>, as it defines
 * the core model of the application.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class Question implements IVerificable {

    /** Question ID */
    private String id;

    /** Question text */
    private String statement;

    /** List of possible answer options */
    private List<String> options;

    /** Correct answer (a/b/c/d) */
    private String correctAnswer;

    /** Explanation shown after answering */
    private String explanation;

    /**
     * Creates a new {@code Question}.
     *
     * @param id            question identifier
     * @param statement     the question text
     * @param options       list of possible answers
     * @param correctAnswer the correct option (a/b/c/d)
     * @param explanation   explanation displayed after answering
     */
    public Question(String id, String statement, List<String> options,
                    String correctAnswer, String explanation) {
        this.id = id;
        this.statement = statement;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    /**
     * Gets the question ID.
     *
     * @return the question ID
     */
    public String getId() { return id; }

    /**
     * Sets the question ID.
     *
     * @param id the new question ID
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets the question text.
     *
     * @return the statement text
     */
    public String getStatement() { return statement; }

    /**
     * Sets the question text.
     *
     * @param statement the new statement text
     */
    public void setStatement(String statement) { this.statement = statement; }

    /**
     * Gets the list of answer options.
     *
     * @return the list of options
     */
    public List<String> getOptions() { return options; }

    /**
     * Sets the list of answer options.
     *
     * @param options the new options
     */
    public void setOptions(List<String> options) { this.options = options; }

    /**
     * Gets the correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() { return correctAnswer; }

    /**
     * Sets the correct answer.
     *
     * @param correctAnswer the new correct answer
     */
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    /**
     * Gets the explanation shown after answering.
     *
     * @return the explanation text
     */
    public String getExplanation() { return explanation; }

    /**
     * Sets the explanation text.
     *
     * @param explanation the new explanation
     */
    public void setExplanation(String explanation) { this.explanation = explanation; }

    /**
     * Verifies the user's response.
     *
     * <p>Accepted responses: {@code a}, {@code b}, {@code c}, {@code d}, or {@code 0}.</p>
     * <ul>
     *   <li>If the input is one of {@code a/b/c/d}, it checks correctness.</li>
     *   <li>If the input is {@code 0}, the program terminates immediately.</li>
     *   <li>If the input is invalid, returns {@code false}.</li>
     * </ul>
     *
     * @param userAnswer the answer provided by the user
     * @return {@code true} if the answer matches the correct one,
     *         {@code false} otherwise
     */
    @Override
    public boolean verificarPregunta(String userAnswer) {
        if (userAnswer == null) return false;

        String respuesta = userAnswer.trim().toLowerCase();
        Set<String> validas = new HashSet<>(Set.of("a", "b", "c", "d", "0"));

        if (!validas.contains(respuesta)) return false;

        if (respuesta.equals("0")) {
            System.out.println("Program ended by user.");
            System.exit(0);
        }

        return correctAnswer.equalsIgnoreCase(respuesta);
    }
}
