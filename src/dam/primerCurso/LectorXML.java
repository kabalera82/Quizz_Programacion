package dam.primerCurso;

// Importamos las clases necesarias para trabajar con archivos y XML
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class LectorXML {

    /**
     * Método estático que lee preguntas desde un archivo XML
     * y devuelve una lista de objetos Pregunta.
     * @param rutaArchivo Ruta al archivo XML
     * @return Lista de objetos Pregunta
     */
    public static List<Pregunta> leerPreguntasDesdeXML(String rutaArchivo) {
        // Creamos una lista vacía donde vamos a guardar todas las preguntas
        List<Pregunta> listaPreguntas = new ArrayList<>();

        try {
            // Creamos un objeto File que representa el archivo XML en el disco
            File archivoXML = new File(rutaArchivo);

            // Creamos una fábrica de documentos (patrón de diseño)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Obtenemos un DocumentBuilder que sabe cómo leer y construir documentos XML
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parseamos el archivo y obtenemos un objeto Document (representación en memoria del XML)
            Document doc = builder.parse(archivoXML);

            // Normalizamos el documento (importante para evitar nodos vacíos, limpiar espacios, etc.)
            doc.getDocumentElement().normalize();

            // Obtenemos todos los nodos con la etiqueta <pregunta>
            NodeList preguntasXML = doc.getElementsByTagName("pregunta");

            // Recorremos cada nodo <pregunta> uno por uno
            for (int i = 0; i < preguntasXML.getLength(); i++) {
                Node nodo = preguntasXML.item(i);

                // Verificamos que el nodo sea realmente un ELEMENT_NODE (ignora comentarios o espacios vacíos)
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    // Convertimos el nodo a Element para poder trabajar con atributos y subetiquetas
                    Element elemento = (Element) nodo;

                    // Leemos el atributo id de la pregunta (ejemplo: id="1")
                    String id = elemento.getAttribute("id");

                    // Leemos el texto del nodo <enunciado>
                    String enunciado = elemento.getElementsByTagName("enunciado").item(0).getTextContent().trim();

                    // Creamos una lista vacía para guardar las opciones (a, b, c, d)
                    List<String> opciones = new ArrayList<>();

                    // Obtenemos todos los nodos <opcion> que están dentro de esta pregunta
                    NodeList opcionesXML = elemento.getElementsByTagName("opcion");

                    // Recorremos cada <opcion> y agregamos su texto a la lista de opciones
                    for (int j = 0; j < opcionesXML.getLength(); j++) {
                        opciones.add(opcionesXML.item(j).getTextContent().trim());
                    }

                    // Leemos el texto del nodo <respuesta> (la opción correcta, ejemplo: "a")
                    String respuestaCorrecta = elemento.getElementsByTagName("respuesta").item(0).getTextContent().trim();

                    // Leemos el texto del nodo <explicacion>
                    String explicacion = elemento.getElementsByTagName("explicacion").item(0).getTextContent().trim();

                    // Creamos un objeto Pregunta con toda la información que hemos leído
                    Pregunta pregunta = new Pregunta(id, enunciado, opciones, respuestaCorrecta, explicacion);

                    // Agregamos la pregunta a la lista final
                    listaPreguntas.add(pregunta);
                }
            }
        } catch (Exception e) {
            // Si ocurre algún error al leer el archivo, lo mostramos por consola (útil para depuración)
            e.printStackTrace();
        }

        // Devolvemos la lista completa de preguntas leídas desde el XML
        return listaPreguntas;
    }
}
