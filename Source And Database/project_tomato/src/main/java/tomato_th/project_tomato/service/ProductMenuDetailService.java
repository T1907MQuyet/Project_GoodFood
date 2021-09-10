package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Product_menu_detail;


import java.util.List;

public interface ProductMenuDetailService {
    List<Product_menu_detail> lisProMenuDetailByMenu(int menuDetail_id);
    List<Product_menu_detail> listProMenuDetailAll();
    Product_menu_detail getProMenuDById(int id);
    boolean saveProMenuDetail(Product_menu_detail product_menu_detail);
    boolean updateProMenuDetail(Product_menu_detail product_menu_detail);
    boolean deleteProMenuDetail(int id);
    boolean getProMDByMDIdbyProId(int menu_detail_id, int product_id);
    Page<Product_menu_detail> findPaginated(int pageNo, int pageSize, int menuDetail_id);

    Page<Product_menu_detail> findAllPaginated(int pageNo, int pageSize);


}
