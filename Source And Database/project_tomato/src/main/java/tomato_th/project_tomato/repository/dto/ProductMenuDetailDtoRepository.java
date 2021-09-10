package tomato_th.project_tomato.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.dto.Product_menu_detailDto;


import java.util.List;

public interface ProductMenuDetailDtoRepository extends JpaRepository<Product_menu_detailDto,Integer> {
    @Query("SELECT p FROM Product_menu_detailDto p WHERE  menu_detail_id = ?1 ")
    List<Product_menu_detailDto> getProMenuByMenu(int menuId);
}
