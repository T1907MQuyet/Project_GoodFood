package tomato_th.project_tomato.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tomato_th.project_tomato.model.ClImage;
import tomato_th.project_tomato.model.core.Category_detail;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.service.CategoryDetailService;
import tomato_th.project_tomato.service.CdService;
import tomato_th.project_tomato.service.ProductService;


import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryDetailService categoryDetailService;
    @Autowired
    private CdService cdService;

    @InitBinder
    public void InitBinder(WebDataBinder data)
    {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getProducts(Model model)
    {
        return findPaginated(1,model);
    }

    @RequestMapping("show")
    public String getProShow(Model model)
    {
        return findPagShow(1,model);
    }

    @RequestMapping("hidden")
    public String getProHidden(Model model)
    {
        return findPagHidden(1,model);
    }

    @RequestMapping("/insertProduct")
    public String insertPro(Model model)
    {
        Product product = new Product();
        List<Category_detail> listCateDetail = categoryDetailService.listCateDetailStatus(1);
        model.addAttribute("proNew",product);
        model.addAttribute("listCateDetail",listCateDetail);
        return "admin/product/insertProduct";
    }

    @RequestMapping(value = "/savePro",method = RequestMethod.POST)
    public String savePro(@ModelAttribute("proNew")@Valid Product product, BindingResult result, Model model, @RequestParam("file_avatar") MultipartFile multipartFile)throws IOException
    {
        if (result.hasErrors())
        {
            List<Category_detail> listCateDetail = categoryDetailService.lisCategoryDetails();
            model.addAttribute("listCateDetail",listCateDetail);
            model.addAttribute("proNew",product);
            return "admin/product/insertProduct";
        }
        boolean checkProName = productService.checkProName(product.getProduct_name(),product.getCategory_detail().getCate_detail_id());
        if (checkProName==false)
        {
            return "redirect:/admin/product/insertProduct?errorcatename=Product Name is existed";
        }

        Map resultCd = cdService.upload(multipartFile);
        ClImage clImage = new ClImage((String)resultCd.get("secure_url"));
        product.setImage(clImage.getUrl());
        boolean bl = productService.saveProduct(product);
        if(bl)
        {
            return "redirect:/admin/product?success=Add New product success";
        }
        return "redirect:/admin/product?error=Add New product error";
    }

    @RequestMapping(value = "/editPro")
    public String editPro(@RequestParam("id")Integer id, Model model)
    {
        Product product = productService.getProById(id);
        List<Category_detail> listCateDetail = categoryDetailService.listCateDetailStatus(1);
        model.addAttribute("proEdit",product);
        model.addAttribute("listCateDetail",listCateDetail);
        return "admin/product/editProduct";
    }

    @RequestMapping(value = "/updatePro",method = RequestMethod.POST)
    public String updatePro(@ModelAttribute("proEdit")Product product, Model model, @RequestParam("file_avatar") MultipartFile multipartFile)throws IOException
    {
        boolean checkName = checkProNameEdit(product.getProduct_name(),product.getProduct_id(),product.getCategory_detail().getCate_detail_id());
        if (checkName==false)
        {
            return "redirect:/admin/product/editPro?id="+product.getProduct_id()+"&&errorcatename=Product name is existed";
        }


        Product proEdit = productService.getProById(product.getProduct_id());
        if (multipartFile.getSize()>0)
        {
            Map result = cdService.upload(multipartFile);
            ClImage clImage = new ClImage((String)result.get("secure_url"));
            product.setImage(clImage.getUrl());
        }else{
            product.setImage(proEdit.getImage());
        }
        boolean bl = productService.updateProduct(product);
        if (bl)
        {
            return "redirect:/admin/product?success=Add New product success";
        }
        return "redirect:/admin/product?error=Add New product error";
    }

    @RequestMapping(value = "/detailPro")
    public String detailProductById(@RequestParam("id")Integer id, Model model)
    {
        Product product = productService.getProById(id);
        model.addAttribute("proDetail",product);
        return "admin/product/detailProduct";
    }

    @RequestMapping(value = "/deleteProducts")
    public String deleteProduct(@RequestParam("id")Integer id)
    {
        boolean bl = productService.deleteProduct(id);
        if (bl)
        {
            return "redirect:/admin/product?success=Delete product success";
        }
        return "redirect:/admin/product?error=Delete product error";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model)
    {
        int pageSize = 10;
        List<Category_detail> listCateDetail = categoryDetailService.lisCategoryDetails();
        Page<Product> page = productService.findPaginated(pageNo,pageSize);
        List<Product> listProduct = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listProduct);
        model.addAttribute("listCateDetail",listCateDetail);


        return "admin/product/productList";
    }
    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo")int pageNo, Model model)
    {
        int pageSize = 10;
        List<Category_detail> listCateDetail = categoryDetailService.lisCategoryDetails();
        Page<Product> page = productService.findPaginatedShow(pageNo,pageSize);
        List<Product> listProduct = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listProduct);
        model.addAttribute("listCateDetail",listCateDetail);

        return "admin/product/productList";
    }
    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo")int pageNo, Model model)
    {
        int pageSize = 10;
        List<Category_detail> listCateDetail = categoryDetailService.lisCategoryDetails();
        Page<Product> page = productService.findPaginatedHidden(pageNo,pageSize);
        List<Product> listProduct = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listProduct);
        model.addAttribute("listCateDetail",listCateDetail);
        return "admin/product/productList";
    }

    @RequestMapping("searchProName")
    public String searchProName(@RequestParam("key_pro_name")String pro_name, Model model)
    {
        List<Product> list = productService.listProByProName(pro_name);
        model.addAttribute("list",list);
        return "admin/product/productList";
    }

    public boolean checkProNameEdit(String pro_name,int id,int cate_detail_id)
    {
        Product product = productService.getProById(id);
        boolean checkProName = productService.checkProName(pro_name,cate_detail_id);
        if (checkProName==false)
        {
            if (pro_name.equals(product.getProduct_name()) && product.getCategory_detail().getCate_detail_id()==cate_detail_id)
            {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }


}
