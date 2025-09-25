package dam.primerCurso.dao;

import dam.primerCurso.domain.Question;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for reading XML question files and converting them into {@link Question} objects.
 */
public class LectorXML {

    /**
     * Reads questions from XML file in `resources/` folder.
     *
     * @param filePath path to the XML file (e.g. "resources/preguntas.xml")
     * @return list of questions
     */
    public static List<Question> readQuestionsFromXML(String filePath) {
        List<Question> questionList = new ArrayList<>();
        try {
            File xmlFile = new File(filePath); // direct file path
            if (!xmlFile.exists()) {
                System.out.println("⚠ File not found: " + filePath);
                return questionList;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList xmlQuestions = doc.getElementsByTagName("question"); // ojo! etiqueta <question>

            for (int i = 0; i < xmlQuestions.getLength(); i++) {
                Node node = xmlQuestions.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String statement = element.getElementsByTagName("enunciado").item(0).getTextContent().trim();

                    List<String> options = new ArrayList<>();
                    NodeList xmlOptions = element.getElementsByTagName("opcion");
                    for (int j = 0; j < xmlOptions.getLength(); j++) {
                        options.add(xmlOptions.item(j).getTextContent().trim());
                    }

                    String correctAnswer = element.getElementsByTagName("respuesta").item(0).getTextContent().trim();
                    String explanation = element.getElementsByTagName("explicacion").item(0).getTextContent().trim();

                    questionList.add(new Question(id, statement, options, correctAnswer, explanation));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
