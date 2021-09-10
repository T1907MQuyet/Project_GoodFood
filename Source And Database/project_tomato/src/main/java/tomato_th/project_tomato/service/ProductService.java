package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Product;


import java.util.List;

public interface ProductService {
    List<Product> listProduct();

    Product getProById(int pro_id);
    boolean saveProduct(Product product);
    boolean deleteProduct(int pro_id);
    boolean updateProduct(Product product);
    List<Product> lisProByCateDetail(int cate_id);
    Page<Product> findPaginated(int pageNo, int pageSize);
    Page<Product> findPaginatedShow(int pageNo, int pageSize);
    Page<Product> findPaginatedHidden(int pageNo, int pageSize);
    int countProductByStatus(int status);
    List<Product> listProByStatus(int status);
    List<Product> listProByPriority();
    boolean checkProName(String pro_name, int cate_detail_id);

    List<Product> listProByProName(String product_name);

}
