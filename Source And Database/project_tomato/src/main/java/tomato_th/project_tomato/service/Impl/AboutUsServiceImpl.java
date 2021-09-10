package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.About_us;
import tomato_th.project_tomato.repository.About_usRepository;
import tomato_th.project_tomato.service.AboutUsService;

import java.util.Date;
import java.util.List;

@Service
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    private About_usRepository about_usRepository;

    @Override
    public List<About_us> listAboutUs() {
        try{
            List<About_us> list = about_usRepository.findActiveAboutStatus();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public About_us getAboutById(int id) {
        try{
            About_us about_us = about_usRepository.findById(id).get();
            return about_us;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveAbout(About_us about_us) {
        try{
            about_us.setCreated(new Date());
            about_us.setUpdated(new Date());
            about_usRepository.save(about_us);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAbout(About_us about_us) {
        try{
            about_us.setUpdated(new Date());
            about_usRepository.save(about_us);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAbout(int id) {
        try{
            About_us about_us = about_usRepository.findById(id).get();
            about_us.setStatus(3);
            about_usRepository.save(about_us);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<About_us> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.about_usRepository.findPaginateAboutStatus(pageable);
    }
}
