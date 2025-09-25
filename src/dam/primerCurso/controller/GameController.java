package dam.primerCurso.controller;

import dam.primerCurso.dao.LectorXML;
import dam.primerCurso.domain.Question;
import dam.primerCurso.service.AppEngine;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code GameController} class acts as the Control Layer of the application.
 * <p>
 * It connects the Presentation Layer (e.g., console or GUI) with the Service and DAO layers:
 * <ul>
 *     <li>Loads questions from XML files using {@link LectorXML} (DAO layer).</li>
 *     <li>Uses {@link AppEngine} (Service layer) to manage game logic and scoring.</li>
 *     <li>Provides high-level methods to start and control the quiz flow.</li>
 * </ul>
 * </p>
 *
 * This makes the controller reusable both in a console app and in a future GUI (JavaFX, Swing, Web).
 *
 * @author kabalera82
 * @version 1.0
 */
public class GameController {

    private final Scanner scanner;

    public GameController(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Starts the quiz for a given XML file path.
     *
     * @param filePath XML file containing questions
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

        // Show summary
        System.out.println("\n🎉 Game Summary:");
        System.out.println("Correct answers: " + app.getSuccesses());
        System.out.println("Incorrect answers: " + app.getFailures());
        System.out.printf("Final score: %.2f\n", app.getGameScore());
    }
}
