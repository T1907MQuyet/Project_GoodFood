package tomato_th.project_tomato.service;

import tomato_th.project_tomato.model.Type_Banner;

import java.util.List;

public interface TypeBannerService {
    List<Type_Banner> getAllTypeBanner();
    List<Type_Banner> getTypeBannerByStatus(int status);
    Type_Banner getById(int type_banner_id);
    boolean save_type_banner(Type_Banner type_banner);
    boolean update_type_banner(Type_Banner type_banner);
    boolean delete_type_banner(int id);
}
