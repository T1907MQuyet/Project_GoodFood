package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.News;
import tomato_th.project_tomato.repository.NewsRepository;
import tomato_th.project_tomato.service.NewsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;


    @Override
    public List<News> listNews() {
        try{
            List<News> list = newsRepository.ListNewsStatus();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public News getNewById(int news_id) {
        try{
            News news = newsRepository.findById(news_id).get();
            return news;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<News> listNewsByTypeActiveHome(int type_id) {
        try{
            Pageable pageable = PageRequest.of(0,3);
            List<News> list = newsRepository.listNewsByTypeActiveHome(type_id,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<News> listNewsByActiveHome(int type_id) {
        try{
//            List<News> listNews = new ArrayList<>();
//            List<News> newsList = newsRepository.listNewsByActiveHomeIndex();
//            for (News news:newsList) {
//                if (news.getType_news().getType_new_id()!=1)
//                {
//                    listNews.add(news);
//                }
//            }
//            List<News> listNewsHome = new ArrayList<>();
//            listNewsHome.add(listNews().get(0));
//            listNewsHome.add(listNews().get(1));
//            listNewsHome.add(listNews().get(2));
//            return listNewsHome;
            Pageable pageable = PageRequest.of(0,3);
            List<News> list = newsRepository.listNewsByTypeActiveHome(type_id,pageable);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<News> listNewsShow(int pageNo, int pageSize) {
        try{

            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            return this.newsRepository.findPaginateNewsShow(pageable);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<News> listPgNewsByType(int type_id, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            return this.newsRepository.findPagiListNewByType(type_id,pageable);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveNew(News news) {
        try{
            news.setCreated(new Date());
            news.setUpdated(new Date());
            newsRepository.save(news);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public News saveNew2(News news) {
        try{
            news.setCreated(new Date());
            news.setUpdated(new Date());
            newsRepository.save(news);
            return news;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateNew(News news) {
        try{
            news.setUpdated(new Date());
            newsRepository.save(news);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNew(int news_id) {
        try{
            News news = newsRepository.findById(news_id).get();
            news.setUpdated(new Date());
            news.setStatus(3);
            newsRepository.save(news);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<News> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.newsRepository.findPaginateNewsStatus(pageable);
    }
}
