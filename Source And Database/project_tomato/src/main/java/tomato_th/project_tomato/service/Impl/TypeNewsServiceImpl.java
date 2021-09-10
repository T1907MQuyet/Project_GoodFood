package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.TypeNews;
import tomato_th.project_tomato.repository.TypeNewsRepository;
import tomato_th.project_tomato.service.TypeNewsService;


import java.util.Date;
import java.util.List;

@Service
public class TypeNewsServiceImpl implements TypeNewsService {
    @Autowired
    private TypeNewsRepository typeNewsRepository;

    @Override
    public List<TypeNews> listTypeNews() {
        try{
            List<TypeNews> list = typeNewsRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TypeNews listTypeNews(int type_new_id) {
        try{
            TypeNews typeNews = typeNewsRepository.findById(type_new_id).get();
            return typeNews;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveTypeNews(TypeNews typeNews) {
        try{
            typeNews.setCreated(new Date());
            typeNewsRepository.save(typeNews);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTypeNews(TypeNews typeNews) {
        try{
            typeNewsRepository.save(typeNews);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTypeNews(int type_new_id) {
        try{
            TypeNews typeNews = typeNewsRepository.findById(type_new_id).get();
            typeNews.setStatus(3);
            typeNewsRepository.save(typeNews);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<TypeNews> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.typeNewsRepository.findPaginate(pageable);
    }

    @Override
    public List<TypeNews> listByStatusShow() {
        try{
            List<TypeNews> list = typeNewsRepository.findAllByStatus(1);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
