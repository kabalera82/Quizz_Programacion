package dam.primerCurso;

import java.util.List;

//Clase que controlará el flujo del juego
public class AppEngine {
    //Declaramos un atributo privado llamado preguntas que guardará objetos de ¡¡tipo!! Pregunta.
    private List<Pregunta> preguntas;
    private int aciertos;
    private int errores;
    private int totalPreguntas;
    private double puntuacion;

    //metodo para generar Lista donde vamos a guardar muchas preguntas y manejará diferentes aspectos del juego
    public AppEngine(List<Pregunta> preguntas){
        this.preguntas = preguntas;
        this.aciertos = 0;
        this.errores = 0;
        this.puntuacion = 0.0;
        //haremos que el total de preguntas sea el que quiera el usuario indicando el tamaño
        this.totalPreguntas = preguntas.size();
    }

    //Metodo par acontabilizar las preguntas
    public void controlRespuesta (boolean esCorrecta){
        if (esCorrecta) {
            aciertos++;
            puntuacion += 1;
        } else {
            errores++;
            puntuacion -= 0.33;
        }
    }

    //Metodo para crear el numero de preguntas
    public int getTotalPreguntas() {
        return totalPreguntas;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getErrores() {
        return errores;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }



}
