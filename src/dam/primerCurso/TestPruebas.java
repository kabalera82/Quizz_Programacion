package dam.primerCurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ¡OJO! No necesitas importar explícitamente las clases del mismo paquete:
//// import dam.primerCurso.Pregunta;
//// import dam.primerCurso.LectorXML;
//// import dam.primerCurso.AppEngine;

public class TestPruebas {
    /* 1ª FASE DE LAS PRUEBAS // TEST DE COMPROBACIÓN DE ERRORES
    public static void main(String[] args) {
        // Creamos lista de opciones
        List<String> opciones = new ArrayList<>();
        opciones.add("a) True");
        opciones.add("b) False");
        opciones.add("c) Maybe");
        opciones.add("d) I don't know");

        // Creamos una pregunta de ejemplo
        Pregunta pregunta = new Pregunta(
                "1",
                "¿Cuál es la respuesta correcta?",
                opciones,
                "a", // respuesta correcta
                "La opción correcta es 'a'."
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println(pregunta.getEnunciado());
        for (String opcion : pregunta.getOpciones()) {
            System.out.println(opcion);
        }

        while (true) {
            System.out.print("Introduce tu respuesta (a/b/c/d o 0 para salir): ");
            String input = scanner.nextLine();

            boolean correcta = pregunta.verificarPregunta(input);

            if (input.equalsIgnoreCase("0")) {
                break;
            }

            if (correcta) {
                System.out.println("✔ ¡Respuesta correcta!");
                System.out.println(pregunta.getExplicacion());
                break;
            } else {
                System.out.println("❌ Respuesta incorrecta o entrada inválida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
    */

    public static void main(String[] args) {
        String rutaArchivo = "preguntas.xml";

        // Cargamos las preguntas desde el archivo usando LectorXML
        List<Pregunta> preguntas = LectorXML.leerPreguntasDesdeXML(rutaArchivo);

        if (preguntas.isEmpty()) {
            System.out.println("❌ No se encontraron preguntas en el archivo XML o hubo un error al leerlo.");
            return;
        }

        System.out.println("✅ Preguntas cargadas: " + preguntas.size());

        Scanner scanner = new Scanner(System.in);

        // Creamos AppEngine para controlar el juego
        AppEngine app = new AppEngine(preguntas);

        for (Pregunta pregunta : preguntas) {
            System.out.println("\n" + pregunta.getEnunciado());
            for (String opcion : pregunta.getOpciones()) {
                System.out.println(opcion);
            }

            System.out.print("Introduce tu respuesta (a/b/c/d o 0 para salir): ");
            String input = scanner.nextLine();

            boolean correcta = pregunta.verificarPregunta(input);
            app.controlRespuesta(correcta);

            if (input.equalsIgnoreCase("0")) {
                System.out.println("⏹ Saliste del juego.");
                break;
            }

            if (correcta) {
                System.out.println("✔ ¡Respuesta correcta!");
                System.out.println("Explicación: " + pregunta.getExplicacion());
            } else {
                System.out.println("❌ Respuesta incorrecta.");
            }
        }

        System.out.println("\n🎉 Resumen del juego:");
        System.out.println("Total preguntas: " + app.getTotalPreguntas());
        System.out.println("Aciertos: " + app.getAciertos());
        System.out.println("Errores: " + app.getErrores());
        System.out.println("Puntuación final: " + app.getPuntuacion());

        scanner.close();
    }
}
