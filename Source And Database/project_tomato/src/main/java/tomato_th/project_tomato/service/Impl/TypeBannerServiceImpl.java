package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.Type_Banner;
import tomato_th.project_tomato.repository.TypeBannerRepository;
import tomato_th.project_tomato.service.TypeBannerService;

import java.util.Date;
import java.util.List;

@Service
public class TypeBannerServiceImpl implements TypeBannerService {
    @Autowired
    private TypeBannerRepository typeBannerRepository;

    @Override
    public List<Type_Banner> getAllTypeBanner() {
        try{
            List<Type_Banner> list = typeBannerRepository.getListTypeBannerShowAndHidden();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Type_Banner> getTypeBannerByStatus(int status) {
        try{
            List<Type_Banner> list = typeBannerRepository.getTypeBannerStatus(status);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Type_Banner getById(int type_banner_id) {
        try{
            Type_Banner type_banner = typeBannerRepository.findById(type_banner_id).get();
            return type_banner;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save_type_banner(Type_Banner type_banner) {
        try{
            type_banner.setCreated(new Date());
            type_banner.setUpdated(new Date());
            typeBannerRepository.save(type_banner);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update_type_banner(Type_Banner type_banner) {
        try {
            typeBannerRepository.findById(type_banner.getType_banner_id()).get();
            type_banner.setUpdated(new Date());
            typeBannerRepository.save(type_banner);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete_type_banner(int id) {
        try{
            Type_Banner type_banner = typeBannerRepository.findById(id).get();
            type_banner.setStatus(3);
            type_banner.setUpdated(new Date());
            typeBannerRepository.save(type_banner);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
