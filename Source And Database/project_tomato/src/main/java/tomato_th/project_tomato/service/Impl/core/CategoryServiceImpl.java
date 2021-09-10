package tomato_th.project_tomato.service.Impl.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Category;
import tomato_th.project_tomato.repository.core.CategoryRepository;
import tomato_th.project_tomato.service.CategoryService;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> lisCategories() {
        try{
            List<Category> list = categoryRepository.findActiveCateStatus();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> listCateByStatus(int status) {
        try{
            List<Category> list = categoryRepository.findAllByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getCateById(int cate_id) {
        try{
            Category category = categoryRepository.findById(cate_id).get();
            return category;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCate(Category category) {
        try{
            categoryRepository.save(category);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCate(Category category) {
        try{
            categoryRepository.save(category);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCate(int cate_id) {
        try{
            Category category = categoryRepository.findById(cate_id).get();
            category.setStatus(3);
            categoryRepository.save(category);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkCateName(String cate_name) {
        Category category = categoryRepository.findByCate_name(cate_name);
        if (category==null)
        {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int countCategoryByStatus(int status) {
        try{
            List<Category> list = categoryRepository.findAllByStatus(status);
            return list.size();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<Category> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize, Sort.by("status"));
        return this.categoryRepository.findPaginateCateStatus(pageable);
    }
    @Override
    public List<Category> listCateByCateName(String cate_name) {
        try{
            List<Category> list = categoryRepository.getListCateByName(cate_name);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
