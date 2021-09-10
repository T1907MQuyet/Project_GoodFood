package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.Feedbacks;

import java.util.List;

public interface FeedbackService {
    List<Feedbacks> listFeedback();
    Feedbacks getFbById(int id);
    Boolean save(Feedbacks feedbacks);
    Page<Feedbacks> findPaginated(int pageNo, int pageSize);
    boolean update(int id);

}
