package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Menu_detail;


import java.util.List;

public interface MenuDetailService {
    List<Menu_detail> listMenuDetails();
    Menu_detail getMenuDetailById(int menu_detail_id);
    boolean saveMenu(Menu_detail menu_detail);
    boolean deleteMenu(int menu_detail_id);
    boolean updateMenu(Menu_detail menu_detail);
    List<Menu_detail> listMenuDerailByStatus(int status);
    int countMenuDetailByStatus(int status);

    boolean checkMenuDetailName(String menuDName, int menu_id);
    Page<Menu_detail> findPaginated(int pageNo, int pageSize);
}
