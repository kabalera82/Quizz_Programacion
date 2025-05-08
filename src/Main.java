import dam.primerCurso.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    String rutaArchivo = seleccionarTema(scanner);
                    jugar(rutaArchivo, scanner);
                    break;
                case "2":
                    mostrarInstrucciones();
                    break;
                case "3":
                    System.out.println("👋 ¡Gracias por jugar! Hasta pronto.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n==== MENÚ PRINCIPAL ====");
        System.out.println("1. Iniciar cuestionario");
        System.out.println("2. Ver instrucciones");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void mostrarInstrucciones() {
        System.out.println("\n==== INSTRUCCIONES ====");
        System.out.println("→ Elige el tema que quieres jugar.");
        System.out.println("→ Luego elige cuántas preguntas responder.");
        System.out.println("→ Responde con a, b, c o d.");
        System.out.println("→ Puedes salir en cualquier momento escribiendo 0.");
    }

    private static String seleccionarTema(Scanner scanner) {
        System.out.println("\n==== SELECCIONAR TEMA ====");
        System.out.println("1. Java");
        System.out.println("2. Bases de Datos");
        System.out.println("3. Lenguaje de Marcas");
        System.out.print("Elige el tema: ");

        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                return "src/preguntas.xml";
            case "2":
                return "src/bbdd.xml";
            case "3":
                return "src/lenguajeMarcas.xml";

            default:
                System.out.println("❌ Opción no válida. Se usará Java por defecto.");
                return "dam/primerCurso/preguntas.xml";
        }
    }

    private static void jugar(String rutaArchivo, Scanner scanner) {
        List<Pregunta> preguntas = LectorXML.leerPreguntasDesdeXML(rutaArchivo);

        if (preguntas.isEmpty()) {
            System.out.println("❌ No se encontraron preguntas o hubo un error al leer el archivo.");
            return;
        }

        Collections.shuffle(preguntas);

        System.out.print("¿Cuántas preguntas quieres jugar? (máx. " + preguntas.size() + "): ");
        int numPreguntas;
        try {
            numPreguntas = Integer.parseInt(scanner.nextLine());
            if (numPreguntas <= 0 || numPreguntas > preguntas.size()) {
                numPreguntas = preguntas.size();
            }
        } catch (NumberFormatException e) {
            numPreguntas = preguntas.size();
        }

        AppEngine app = new AppEngine(preguntas);

        for (int i = 0; i < numPreguntas; i++) {
            Pregunta pregunta = preguntas.get(i);
            System.out.println("\nPregunta " + (i + 1) + " de " + numPreguntas);
            System.out.println(pregunta.getEnunciado());
            for (String opcion : pregunta.getOpciones()) {
                System.out.println(opcion);
            }

            System.out.print("Respuesta (a/b/c/d o 0 para salir): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("⏹ Juego terminado por el usuario.");
                break;
            }

            boolean correcta = pregunta.verificarPregunta(input);
            app.controlRespuesta(correcta);

            if (correcta) {
                System.out.println("✔ Correcto!");
            } else {
                System.out.println("❌ Incorrecto.");
            }
            System.out.println("Explicación: " + pregunta.getExplicacion());
        }

        System.out.println("\n🎉 Resumen del juego:");
        System.out.println("Aciertos: " + app.getAciertos());
        System.out.println("Errores: " + app.getErrores());
        System.out.printf("Puntuación final: %.2f\n", app.getPuntuacion());
    }
}
