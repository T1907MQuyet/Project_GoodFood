package tomato_th.project_tomato.config.exeption;

public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ApiException(String message) {
        super(message);
    }
}
