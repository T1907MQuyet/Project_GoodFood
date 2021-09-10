package tomato_th.project_tomato.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.Feedbacks;

import java.util.List;

public interface FeedbacksRepository extends JpaRepository<Feedbacks,Integer> {

    @Query("SELECT f FROM Feedbacks f ORDER BY created DESC ")
    List<Feedbacks> getFeedbackCreatedDESC();

    @Query("SELECT f FROM Feedbacks f ORDER BY created DESC ")
    Page<Feedbacks> findPaginateCreatedDesc(Pageable pageable);
}
