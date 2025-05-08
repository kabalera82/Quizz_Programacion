package dam.primerCurso;

//Interfaz con los métodos de verificación de las preguntas
//En una interfaz a parte de lso métodos de juego apra que pregunta no implemente métodos que no necesita
public interface Verificable {
    boolean verificarPregunta(String respuestaUsuario);
}
