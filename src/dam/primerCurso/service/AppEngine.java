package dam.primerCurso.service;

import dam.primerCurso.domain.Question;
import java.util.List;

/**
 * The {@code AppEngine} class manages the state of a quiz session.
 *
 * <p>Responsibilities include:</p>
 * <ul>
 *     <li>Tracking correct and incorrect answers</li>
 *     <li>Maintaining the total score (with penalties)</li>
 *     <li>Providing statistics such as number of successes, failures, and final score</li>
 * </ul>
 *
 * <p>This class belongs to the <b>Service Layer</b>, because it implements
 * the core game logic independently of the UI or data sources.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class AppEngine {

    /** List of all questions in the current quiz session. */
    private final List<Question> questions;

    /** Number of correctly answered questions. */
    private int successes;

    /** Number of incorrectly answered questions. */
    private int failures;

    /** Total number of questions in the session. */
    private int totalQuestions;

    /** Final score (with penalties for wrong answers). */
    private double gameScore;

    /**
     * Creates a new {@code AppEngine} for the given set of questions.
     *
     * @param questions the list of {@link Question} objects for the session
     */
    public AppEngine(List<Question> questions) {
        this.questions = questions;
        this.successes = 0;
        this.failures = 0;
        this.gameScore = 0.0;
        this.totalQuestions = questions.size();
    }

    /**
     * Updates the quiz statistics based on the correctness of a response.
     *
     * <p>If the response is correct: +1 point.
     * If incorrect: -0.33 penalty.</p>
     *
     * @param isCorrect {@code true} if the answer is correct, {@code false} otherwise
     */
    public void controlResponse(boolean isCorrect) {
        if (isCorrect) {
            successes++;
            gameScore += 1.0;
        } else {
            failures++;
            gameScore -= 0.33;
        }
    }

    /**
     * Gets the total number of questions in the session.
     *
     * @return total number of questions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Gets the number of correctly answered questions.
     *
     * @return number of successes
     */
    public int getSuccesses() {
        return successes;
    }

    /**
     * Gets the number of incorrectly answered questions.
     *
     * @return number of failures
     */
    public int getFailures() {
        return failures;
    }

    /**
     * Gets the final score of the session.
     *
     * @return score (positive with correct answers, negative if too many penalties)
     */
    public double getGameScore() {
        return gameScore;
    }

    /**
     * Gets the list of all questions in the session.
     *
     * @return list of {@link Question}
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
