package tomato_th.project_tomato.service.Impl.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Menu;
import tomato_th.project_tomato.repository.core.MenuRepository;
import tomato_th.project_tomato.service.MenuService;


import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public List<Menu> lisMenus() {
        try{
            List<Menu> list = menuRepository.findActiveMenuStatus();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Menu getMenyById(int menu_id) {
        try{
            Menu menu = menuRepository.findById(menu_id).get();
            return menu;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> listMenuByStatus(int status) {
        try{
            List<Menu> list = menuRepository.findAllByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveMenu(Menu menu) {
        try{
            menuRepository.save(menu);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        try{
            menuRepository.save(menu);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMenu(int menu_id) {
        try{
            Menu menu = menuRepository.findById(menu_id).get();
            menu.setStatus(3);
            menuRepository.save(menu);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countMenuByStatus(int status) {
        try{
            List<Menu> list = menuRepository.findAllByStatus(status);
            return list.size();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean checkMenuName(String menu_name) {
        Menu menu = menuRepository.findByMenuName(menu_name);
        if (menu==null)
        {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Page<Menu> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.menuRepository.findPaginateMenuStatus(pageable);
    }
}
