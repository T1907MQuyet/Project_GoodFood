package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.About_us;

import java.util.List;

public interface AboutUsService {
    List<About_us> listAboutUs();
    About_us getAboutById(int id);
    boolean saveAbout(About_us about_us);
    boolean updateAbout(About_us about_us);
    boolean deleteAbout(int id);
    Page<About_us> findPaginated(int pageNo, int pageSize);
}
