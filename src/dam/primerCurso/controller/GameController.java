package dam.primerCurso.controller;

import dam.primerCurso.dao.LectorXML;
import dam.primerCurso.domain.Question;
import dam.primerCurso.service.AppEngine;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code GameController} class acts as the <b>Control Layer</b> of the application.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *     <li>Load questions from XML files via {@link LectorXML} (DAO layer).</li>
 *     <li>Delegate quiz logic and scoring to {@link AppEngine} (Service layer).</li>
 *     <li>Manage the game loop and handle user input/output (console-based).</li>
 * </ul>
 *
 * <p>This design allows the controller to be reused in other user interfaces,
 * such as a graphical desktop application (JavaFX/Swing) or a web app,
 * without changing the core game logic.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class GameController {

    /** Scanner for reading user input (console-based UI). */
    private final Scanner scanner;

    /**
     * Constructs a {@code GameController} with the specified {@link Scanner}.
     *
     * @param scanner the {@link Scanner} used to capture user input
     */
    public GameController(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Starts a quiz session by loading questions from the specified XML file.
     *
     * <p>Game flow:</p>
     * <ol>
     *     <li>Load and shuffle questions.</li>
     *     <li>Ask the player how many questions to play.</li>
     *     <li>Iterate over questions, validate answers, update score.</li>
     *     <li>Show a final summary (correct/incorrect answers and score).</li>
     * </ol>
     *
     * @param filePath the resource path of the XML file containing the quiz questions
     */
    public void startQuiz(String filePath) {
        List<Question> questions = LectorXML.readQuestionsFromXML(filePath);

        if (questions.isEmpty()) {
            System.out.println("❌ No questions found or error reading file: " + filePath);
            return;
        }

        Collections.shuffle(questions);

        System.out.print("How many questions? (max " + questions.size() + "): ");
        int numQuestions;
        try {
            numQuestions = Integer.parseInt(scanner.nextLine());
            if (numQuestions <= 0 || numQuestions > questions.size()) {
                numQuestions = questions.size();
            }
        } catch (NumberFormatException e) {
            numQuestions = questions.size();
        }

        AppEngine app = new AppEngine(questions);

        for (int i = 0; i < numQuestions; i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + " of " + numQuestions);
            System.out.println(question.getStatement());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Answer (a/b/c/d or 0 to quit): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("⏹ Game ended by user.");
                break;
            }

            boolean correct = question.verificarPregunta(input);
            app.controlResponse(correct);

            if (correct) {
                System.out.println("✔ Correct!");
            } else {
                System.out.println("❌ Incorrect.");
            }
            System.out.println("Explanation: " + question.getExplanation());
        }

        // Final summary
        System.out.println("\n🎉 Game Summary:");
        System.out.println("Correct answers: " + app.getSuccesses());
        System.out.println("Incorrect answers: " + app.getFailures());
        System.out.printf("Final score: %.2f\n", app.getGameScore());
    }
}
