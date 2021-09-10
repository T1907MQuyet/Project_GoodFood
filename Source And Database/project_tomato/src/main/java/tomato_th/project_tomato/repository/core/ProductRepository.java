package tomato_th.project_tomato.repository.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tomato_th.project_tomato.model.core.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByStatus(int status);
    @Query("SELECT p FROM Product p WHERE  cate_detail_id = ?1 AND status=1 ORDER BY priority ASC")
    List<Product> getProByCate(int cate_id);

    @Query("SELECT p FROM Product p WHERE priority <= 3 order by priority ASC")
    List<Product> getProByPriority();

    @Query("SELECT p FROM Product p WHERE  cate_detail_id = ?1 AND status=1")
    List<Product> findAllByCategory_detail(int cate_id);

    @Query("SELECT p FROM Product p WHERE status = 1 OR status = 2")
    List<Product> getAllProductStatus();

    @Query("SELECT p FROM Product p WHERE p.product_name LIKE %:product_name% AND status = 1 OR status=2")
    List<Product> getListProByName(@Param("product_name") String product_name);

    @Query("SELECT p FROM Product p WHERE status = 1 OR status = 2")
    Page<Product> findPaginateProductStatus(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE status = 1 ")
    Page<Product> findPaginateProStatusShow(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE status = 2 ")
    Page<Product> findPaginateProStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE product_name = ?1 AND cate_detail_id = ?2")
    Product findByProName(String proName, int cateDetail_id);

}
