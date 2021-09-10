package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.Banners;

import tomato_th.project_tomato.repository.BannersRepository;
import tomato_th.project_tomato.service.BannersService;

import java.util.Date;
import java.util.List;

@Service
public class BannersServiceImpl implements BannersService {

    @Autowired
    private BannersRepository bannersRepository;

    @Override
    public Page<Banners> getAllBanners(int pageNo,int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Banners> bannersPage = bannersRepository.getAllBannerPaginated(pageable);
            return bannersPage;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Banners getBannersById(int id) {
        try{
            Banners banners = bannersRepository.findById(id).get();
            return banners;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Banners getBannersByBannerName(String banner_name) {
        try{
            List<Banners> bannersList = bannersRepository.getBannerByBannerName(banner_name);
            Banners banners = bannersList.get(0);
            return banners;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveBanners(Banners banners) {
        try{
            banners.setCreated(new Date());
            banners.setUpdated(new Date());
            bannersRepository.save(banners);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBanners(Banners banners) {
        try{
            banners.setUpdated(new Date());
            bannersRepository.save(banners);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBanners(int id) {
        try{
            Banners banners = bannersRepository.findById(id).get();
            banners.setUpdated(new Date());
            banners.setStatus(3);
            bannersRepository.save(banners);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
