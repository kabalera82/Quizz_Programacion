package dam.primerCurso.dao;

import dam.primerCurso.domain.Question;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code LectorXML} class loads quiz questions from XML resources.
 *
 * <p>This class belongs to the <b>DAO Layer</b> (Data Access Object), since it is
 * responsible for retrieving external data (XML) and mapping it into
 * {@link Question} objects usable by the application.</p>
 *
 * <p><b>Expected XML structure:</b></p>
 * <pre>
 * &lt;question id="1"&gt;
 *     &lt;enunciado&gt;What is ...?&lt;/enunciado&gt;
 *     &lt;opciones&gt;
 *         &lt;opcion&gt;a) Option A&lt;/opcion&gt;
 *         &lt;opcion&gt;b) Option B&lt;/opcion&gt;
 *     &lt;/opciones&gt;
 *     &lt;respuesta&gt;b&lt;/respuesta&gt;
 *     &lt;explicacion&gt;Explanation text&lt;/explicacion&gt;
 * &lt;/question&gt;
 * </pre>
 *
 * <p>The parser extracts the following for each question: {@code id},
 * statement, options, correct answer, and explanation.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public class LectorXML {

    /**
     * Default constructor.
     * <p>
     * Declared explicitly to remove Javadoc warnings about the
     * implicit default constructor.
     * </p>
     */
    public LectorXML() {
        // No initialization required
    }

    /**
     * Reads quiz questions from an XML resource in the classpath.
     *
     * <p>The method uses {@link Class#getResourceAsStream(String)}, so the
     * {@code resourcePath} must be relative to the root of the compiled
     * resources (e.g. {@code "/preguntas.xml"}).</p>
     *
     * @param resourcePath path to the XML file inside {@code resources/}
     * @return a list of {@link Question} objects.
     *         Returns an empty list if the resource is not found or an error occurs.
     */
    public static List<Question> readQuestionsFromXML(String resourcePath) {
        List<Question> questionList = new ArrayList<>();

        try (InputStream input = LectorXML.class.getResourceAsStream(resourcePath)) {
            if (input == null) {
                System.err.println("Resource not found: " + resourcePath);
                return questionList;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();

            NodeList xmlQuestions = doc.getElementsByTagName("question");

            for (int i = 0; i < xmlQuestions.getLength(); i++) {
                Node node = xmlQuestions.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String statement = element.getElementsByTagName("enunciado")
                            .item(0).getTextContent().trim();

                    List<String> options = new ArrayList<>();
                    NodeList xmlOptions = element.getElementsByTagName("opcion");
                    for (int j = 0; j < xmlOptions.getLength(); j++) {
                        options.add(xmlOptions.item(j).getTextContent().trim());
                    }

                    String correctAnswer = element.getElementsByTagName("respuesta")
                            .item(0).getTextContent().trim();
                    String explanation = element.getElementsByTagName("explicacion")
                            .item(0).getTextContent().trim();

                    questionList.add(
                            new Question(id, statement, options, correctAnswer, explanation)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
