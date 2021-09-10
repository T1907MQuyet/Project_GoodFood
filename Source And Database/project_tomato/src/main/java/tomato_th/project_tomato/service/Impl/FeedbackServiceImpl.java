package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.Feedbacks;
import tomato_th.project_tomato.repository.FeedbacksRepository;
import tomato_th.project_tomato.service.FeedbackService;


import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbacksRepository feedbacksRepository;

    @Override
    public List<Feedbacks> listFeedback() {
        try{
            List<Feedbacks> list = feedbacksRepository.getFeedbackCreatedDESC();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Feedbacks getFbById(int id) {
        try {
            Feedbacks feedbacks = feedbacksRepository.findById(id).get();
            return feedbacks;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean save(Feedbacks feedbacks) {
        try{
            feedbacks.setCreated(new Date());
            feedbacksRepository.save(feedbacks);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Feedbacks> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.feedbacksRepository.findPaginateCreatedDesc(pageable);
    }

    @Override
    public boolean update(int id) {
        try{
            Feedbacks fb = feedbacksRepository.findById(id).get();
            fb.setStatus(2);
            feedbacksRepository.save(fb);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;

    }
}
