package tomato_th.project_tomato.service.Impl.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.repository.core.ProductRepository;
import tomato_th.project_tomato.service.ProductService;


import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProduct() {
        try{
            List<Product> list = productRepository.getAllProductStatus();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProById(int pro_id) {
        try{
            Product product = productRepository.findById(pro_id).get();
            return product;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            product.setCreated(new Date());
            product.setUpdated(new Date());
            productRepository.save(product);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int pro_id) {
        try{
            Product product = productRepository.findById(pro_id).get();
            product.setStatus(3);
            product.setUpdated(new Date());
            productRepository.save(product);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try{
            product.setUpdated(new Date());
            productRepository.save(product);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> lisProByCateDetail(int cate_id) {
        try{
            List<Product> list = productRepository.findAllByCategory_detail(cate_id);

            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProductStatus(pageable);
    }

    @Override
    public Page<Product> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProStatusShow(pageable);
    }

    @Override
    public Page<Product> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProStatusHidden(pageable);
    }

    @Override
    public int countProductByStatus(int status) {
        try{
            List<Product> list = productRepository.findAllByStatus(status);
            return list.size();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Product> listProByStatus(int status) {
        try{
            List<Product> list = productRepository.findAllByStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> listProByPriority() {
        try{
            List<Product> list =productRepository.getProByPriority();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkProName(String pro_name, int cate_detail_id) {
        Product product = productRepository.findByProName(pro_name,cate_detail_id);
        if (product==null)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Product> listProByProName(String product_name) {
        try{
            List<Product> list = productRepository.getListProByName(product_name);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
