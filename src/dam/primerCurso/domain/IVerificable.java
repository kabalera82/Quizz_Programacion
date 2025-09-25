package dam.primerCurso.domain;

/**
 * The {@code IVerificable} interface defines the contract for validating
 * user responses in quiz questions.
 *
 * <p>By extracting this behavior into an interface, we ensure that
 * only the relevant classes (e.g., {@link Question}) implement it,
 * avoiding forcing unnecessary methods on other classes.</p>
 *
 * <p>This interface belongs to the <b>Domain Layer</b>, as it defines
 * the expected behavior for entities that can be verified.</p>
 *
 * @author kabalera82
 * @version 1.0
 */
public interface IVerificable {

    /**
     * Checks whether the user response matches the correct answer.
     *
     * @param respuestaUsuario the input provided by the user
     * @return {@code true} if the response is valid and correct,
     *         {@code false} otherwise
     */
    boolean verificarPregunta(String respuestaUsuario);
}
