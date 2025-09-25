package dam.primerCurso.service;

/** imports*/
import dam.primerCurso.domain.Question;

import java.util.List;

/**
 * The {@code AppEngine} class manages the flow and state of the quiz game.
 * <p>
 * It stores the collection of questions, tracks correct and incorrect answers,
 * calculates the total score, and provides access to game statistics.
 * </p>
 * <p>
 * Scoring rules:
 * </p>
 * <ul>
 *   <li>+1 point for a correct answer</li>
 *   <li>-0.33 points for an incorrect answer</li>
 * </ul>
 *
 * @author kabalera82
 * @version 1.0
 */
public class AppEngine {

    /** List of {@link Question} objects representing all questions. */
    private List<Question> questions;

    /** Correctly answered questions. */
    private int successes;

    /** Incorrectly answered questions. */
    private int failures;

    /** Total number of questions. */
    private int totalQuestions;

    /** Current game score. */
    private double gameScore;

    /**
     * Creates a new {@code AppEngine} instance with the provided list of questions.
     * <p>
     * Initializes counters and calculates the total number of questions.
     * </p>
     *
     * @param questions list of {@link Question} objects to be used in the game
     */
    public AppEngine(List<Question> questions) {
        this.questions = questions;
        this.successes = 0;
        this.failures = 0;
        this.gameScore = 0.0;
        this.totalQuestions = questions.size();
    }

    /**
     * Updates the game statistics based on whether the player's answer was correct.
     * <ul>
     *   <li>If correct: increments {@code successes} and adds 1 point to {@code gameScore}.</li>
     *   <li>If incorrect: increments {@code failures} and subtracts 0.33 points from {@code gameScore}.</li>
     * </ul>
     *
     * @param isCorrect {@code true} if the answer is correct, {@code false} otherwise
     */
    public void controlResponse(boolean isCorrect) {
        if (isCorrect) {
            successes++;
            gameScore += 1;
        } else {
            failures++;
            gameScore -= 0.33;
        }
    }

    /**
     * <p>AppEngine represents the game engine, and only it decides how the counters change.</p>
     * <p>Therefore, getters are exposed (so that other layers, such as the graphical interface,
     * can display scores or successes).</p>
     * <p>However, there are no setters, because that would compromise the consistency of the rules.</p>
     */

    /**
     * Returns the total number of questions in the game session.
     *
     * @return total number of questions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Returns the number of correctly answered questions.
     *
     * @return number of correct answers
     */
    public int getSuccesses() {
        return successes;
    }

    /**
     * Returns the number of incorrectly answered questions.
     *
     * @return number of incorrect answers
     */
    public int getFailures() {
        return failures;
    }

    /**
     * Returns the current score of the player.
     *
     * @return current game score
     */
    public double getGameScore() {
        return gameScore;
    }

    /**
     * Returns the list of questions used in the game.
     *
     * @return list of {@link Question} objects
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
