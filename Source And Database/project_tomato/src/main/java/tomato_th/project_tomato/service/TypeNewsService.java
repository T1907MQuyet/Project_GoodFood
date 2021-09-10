package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.TypeNews;


import java.util.List;

public interface TypeNewsService {
    List<TypeNews> listTypeNews();
    TypeNews listTypeNews(int type_new_id);
    boolean saveTypeNews(TypeNews typeNews);
    boolean updateTypeNews(TypeNews typeNews);
    boolean deleteTypeNews(int type_new_id);
    Page<TypeNews> findPaginated(int pageNo, int pageSize);

    List<TypeNews> listByStatusShow();
}
