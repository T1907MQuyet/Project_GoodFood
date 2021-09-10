package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.News;

import java.util.List;

public interface NewsService {
    List<News> listNews();
    News getNewById(int news_id);
    List<News> listNewsByTypeActiveHome(int type_id);
    List<News> listNewsByActiveHome(int type_id);
    Page<News> listNewsShow(int pageNo, int pageSize);
    Page<News> listPgNewsByType(int type_id, int pageNo, int pageSize);
    boolean saveNew(News news);
    News saveNew2(News news);
    boolean updateNew(News news);
    boolean deleteNew(int news_id);
    Page<News> findPaginated(int pageNo, int pageSize);
}
