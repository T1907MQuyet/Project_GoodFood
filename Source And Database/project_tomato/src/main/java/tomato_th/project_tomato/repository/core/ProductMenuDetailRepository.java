package tomato_th.project_tomato.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.core.Product_menu_detail;

import java.util.List;

public interface ProductMenuDetailRepository extends JpaRepository<Product_menu_detail,Integer> {
    @Query("SELECT p FROM Product_menu_detail p WHERE  menu_detail_id = ?1 ")
    List<Product_menu_detail> getProMenuByMenu(int menu_id);

    @Query("SELECT p FROM Product_menu_detail p WHERE  p.menu_detail.menu_detail_id = ?1 ")
    Page<Product_menu_detail> getProMenuByMenuPagi(int menu_id,Pageable pageable);

    @Query("SELECT p FROM Product_menu_detail p WHERE  p.menu_detail.menu_detail_id = ?1 ")
    Page<Product_menu_detail> getProMenuByMenuPaginate(int menu_id, Pageable pageable);

//    @Query("SELECT p FROM Product_menu_detail p WHERE p.status= 1")
//    Page<Product_menu_detail> getProMenuDetailPaginateShow(Pageable pageable);

    @Query("SELECT p FROM Product_menu_detail p WHERE  menu_detail_id = ?1 AND product_id = ?2 ")
    Product_menu_detail getProMDByMenuByPro(int menu_id, int pro_id);
}
