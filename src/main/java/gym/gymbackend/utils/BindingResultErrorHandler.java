package gym.gymbackend.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Random;

public class BindingResultErrorHandler {

    /**
     *  Turns binding errors into a readable string
     *
     * @param br BindingResult
     * @return {@link String}
     * @see String
     */
    @NotNull
    public static String bindingErrorsToString(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }
}

