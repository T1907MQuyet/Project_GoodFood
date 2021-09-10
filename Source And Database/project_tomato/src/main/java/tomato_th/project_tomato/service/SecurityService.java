package tomato_th.project_tomato.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
