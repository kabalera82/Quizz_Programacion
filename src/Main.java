import dam.primerCurso.controller.GameController;

import java.util.Scanner;

/**
 * Entry point of the Quiz Application (UI Layer).
 * <p>
 * Delegates the quiz logic to {@link GameController}. This keeps the UI code minimal
 * and allows replacing the console with a GUI in the future.
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(scanner);

        while (true) {
            showMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    String filePath = selectTopic(scanner);
                    controller.startQuiz(filePath);
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

    private static void showMenu() {
        System.out.println("\n==== MAIN MENU ====");
        System.out.println("1. Start quiz");
        System.out.println("2. View instructions");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void showInstructions() {
        System.out.println("\n==== INSTRUCTIONS ====");
        System.out.println("→ Choose the topic you want to play.");
        System.out.println("→ Then select how many questions to answer.");
        System.out.println("→ Answer with a, b, c or d.");
        System.out.println("→ You can exit at any time by typing 0.");
    }

    private static String selectTopic(Scanner scanner) {
        System.out.println("\n==== SELECT TOPIC ====");
        System.out.println("1. Java");
        System.out.println("2. Databases");
        System.out.println("3. Markup Languages");
        System.out.print("Choose a topic: ");

        String option = scanner.nextLine();
        switch (option) {
            case "1":
                return "resources/preguntas.xml";
            case "2":
                return "resources/bbdd.xml";
            case "3":
                return "resources/lenguajeMarcas.xml";
            default:
                System.out.println("❌ Invalid option. Defaulting to Java.");
                return "resources/preguntas.xml";
        }
    }
}
