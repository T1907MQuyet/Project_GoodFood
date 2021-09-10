package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.core.Category;

import java.util.List;

public interface CategoryService {
    List<Category> lisCategories();
    List<Category> listCateByStatus(int status);
    Category getCateById(int cate_id);
    boolean saveCate(Category category);
    boolean updateCate(Category category);
    boolean deleteCate(int cate_id);
    boolean checkCateName(String cate_name);
    int countCategoryByStatus(int status);
    Page<Category> findPaginated(int pageNo, int pageSize);

    List<Category> listCateByCateName(String cate_name);
}
