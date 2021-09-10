package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.core.Product_menu_detail;
import tomato_th.project_tomato.repository.core.ProductMenuDetailRepository;
import tomato_th.project_tomato.service.ProductMenuDetailService;


import java.util.List;

@Service
public class ProductMenuDetailServiceImpl implements ProductMenuDetailService {
    @Autowired
    private ProductMenuDetailRepository productMenuDetailRepository;

    @Override
    public List<Product_menu_detail> lisProMenuDetailByMenu(int menuDetail_id) {
        try{
            List<Product_menu_detail> list = productMenuDetailRepository.getProMenuByMenu(menuDetail_id);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product_menu_detail> listProMenuDetailAll() {
        try{
            List<Product_menu_detail> list = productMenuDetailRepository.findAll();
            return list;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product_menu_detail getProMenuDById(int id) {
        try{
            Product_menu_detail product_menu_detail = productMenuDetailRepository.findById(id).get();
            return product_menu_detail;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveProMenuDetail(Product_menu_detail product_menu_detail) {
        try{
            productMenuDetailRepository.save(product_menu_detail);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProMenuDetail(Product_menu_detail product_menu_detail) {
        try{
            productMenuDetailRepository.save(product_menu_detail);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProMenuDetail(int id) {
        try{
            productMenuDetailRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getProMDByMDIdbyProId(int menu_detail_id, int product_id) {
        try{
            Product_menu_detail product_menu_detail = productMenuDetailRepository.getProMDByMenuByPro(menu_detail_id,product_id);
            if (product_menu_detail==null)
            {
                return false;
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Product_menu_detail> findPaginated(int pageNo, int pageSize, int menuDetail_id) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        return this.productMenuDetailRepository.getProMenuByMenuPaginate(menuDetail_id,pageable);
    }

    @Override
    public Page<Product_menu_detail> findAllPaginated(int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Product_menu_detail> list =productMenuDetailRepository.getProMenuByMenuPagi(1,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
