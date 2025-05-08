package dam.primerCurso;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Creamos la clase pregunta
public class Pregunta implements Verificable {

    //Con atributos privados para acceder a ellos mediante getters y Setters.
    private String id;//id de la pregunta por comodidad para ahorrar código lo tratamos como String para evitar Casteos.
    private String enunciado;//
    private List<String> opciones;//Declaramos lista dinamica dejando abierta la opcion a preguntas con diferente Nº de respuestas.
    private String respuestaCorrecta; //
    private String explicacion;


    //Creamos el contructor propio de la Clase pregunta
    public Pregunta(String id,String enunciado, List<String> opciones, String respuestaCorrecta, String explicacion){
        this.id = id;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.explicacion = explicacion;
    }

    //Creamos los Getters y los Setters

    //Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Enunciado
    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    //Opciones
    public List<String> getOpciones() {
        return opciones;
    }
    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    //RespuestaCorrecta
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    //Explicación
    public String getExplicacion() {
        return explicacion;
    }
    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    @Override
    //metodo de la interfaz para verificar preguntas con control de entradas erroneas por medio de hash set
    public boolean verificarPregunta(String respuestaUsuario) {
        if(respuestaUsuario == null){
          return false;
        }
        //trim espacios. lowercase minusculas
        String respuesta = respuestaUsuario.trim().toLowerCase();

        //creamos el conjunto donde guardaremos las respuestas validas
        Set<String> validas = new HashSet<>(Set.of("a","b", "c","d","0"));

        if(!validas.contains(respuesta)){
            return false;
        }
        if (respuesta.equals("0")) {
            System.out.println("Programa finalizado por el usuario.");
            System.exit(0);
        }

        return respuestaCorrecta.equalsIgnoreCase(respuesta);

    }
}
