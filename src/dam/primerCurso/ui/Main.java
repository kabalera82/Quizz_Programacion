package dam.primerCurso.ui;

import dam.primerCurso.controller.GameController;
import java.util.Scanner;

/**
 * The {@code dam.primerCurso.ui.Main} class is the entry point of the Quiz Application.
 *
 * <p>This class belongs to the <b>UI Layer</b>. Its responsibilities are:</p>
 * <ul>
 *     <li>Display the main menu in the console</li>
 *     <li>Read user input</li>
 *     <li>Delegate game logic to {@link GameController}</li>
 *     <li>Provide instructions and exit control</li>
 * </ul>
 *
 * <p>Future-proof: This class can be easily replaced by a GUI (e.g., JavaFX)
 * while keeping the rest of the architecture unchanged.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class Main {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * All methods are static, and the application should only be launched
     * through the {@link #main(String[])} method.
     * </p>
     */
    private Main() {
        // Prevent instantiation
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(scanner);

        while (true) {
            showMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    // Select resource (inside /resources/)
                    String resourcePath = selectTopic(scanner);
                    System.out.println("🔍 DEBUG: Loading resource " + resourcePath);
                    controller.startQuiz(resourcePath);
                    break;
                case "2":
                    showInstructions();
                    break;
                case "3":
                    System.out.println("👋 Thanks for playing! Bye.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays the main menu options.
     */
    private static void showMenu() {
        System.out.println("\n==== MAIN MENU ====");
        System.out.println("1. Start quiz");
        System.out.println("2. View instructions");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Displays the game instructions for the player.
     */
    private static void showInstructions() {
        System.out.println("\n==== INSTRUCTIONS ====");
        System.out.println("→ Choose the topic you want to play.");
        System.out.println("→ Then select how many questions to answer.");
        System.out.println("→ Answer with a, b, c or d.");
        System.out.println("→ You can exit at any time by typing 0.");
    }

    /**
     * Allows the player to select a topic.
     *
     * <p>The returned path will later be passed to
     * {@code LectorXML.readQuestionsFromXML}, so it MUST start with
     * {@code "/"} to be located via {@code getResourceAsStream}.</p>
     *
     * @param scanner the scanner used to read user input
     * @return the absolute resource path of the chosen XML file
     */
    private static String selectTopic(Scanner scanner) {
        System.out.println("\n==== SELECT TOPIC ====");
        System.out.println("1. Java");
        System.out.println("2. Databases");
        System.out.println("3. Markup Languages");
        System.out.print("Choose a topic: ");

        String option = scanner.nextLine();
        switch (option) {
            case "1":
                return "/preguntas.xml";
            case "2":
                return "/bbdd.xml";
            case "3":
                return "/lenguajeMarcas.xml";
            default:
                System.out.println("❌ Invalid option. Defaulting to Java.");
                return "/preguntas.xml";
        }
    }
}
