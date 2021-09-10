package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tomato_th.project_tomato.model.Banners;

import java.util.List;

public interface BannersService {
    Page<Banners> getAllBanners(int pageNo,int pageSize);
    Banners getBannersById(int id);
    Banners getBannersByBannerName(String banner_name);
    boolean saveBanners(Banners banners);
    boolean updateBanners(Banners banners);
    boolean deleteBanners(int id);
}
