package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Menu;


import java.util.List;

public interface MenuService {
    List<Menu> lisMenus();
    Menu getMenyById(int menu_id);
    List<Menu> listMenuByStatus(int status);
    boolean saveMenu(Menu menu);
    boolean updateMenu(Menu menu);
    boolean deleteMenu(int menu_id);
    int countMenuByStatus(int status);

    boolean checkMenuName(String menu_name);
    Page<Menu> findPaginated(int pageNo, int pageSize);
}
