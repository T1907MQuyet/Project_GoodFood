package tomato_th.project_tomato.controller.frontend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tomato_th.project_tomato.model.Cart;
import tomato_th.project_tomato.model.core.*;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/food/cart")
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDetailService categoryDetailService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDetailService menuDetailService;
    @Autowired
    private ProductMenuDetailService productMenuDetailService;

    public void menu(Model model)
    {
        List<Category> listCate = categoryService.listCateByStatus(1);
        List<Category_detail> listCateDetail = categoryDetailService.listCateDetailStatus(1);
        model.addAttribute("listCate",listCate);
        model.addAttribute("listCateDetail",listCateDetail);

        List<Menu> listMenu = menuService.listMenuByStatus(1);
        List<Menu_detail> listMenuDetail = menuDetailService.listMenuDerailByStatus(1);
        model.addAttribute("listMenu",listMenu);
        model.addAttribute("listMenuDetail",listMenuDetail);
    }
    @RequestMapping("/addCart")
    public String addToCart(@RequestParam("productId")Integer product_id, HttpSession session, Model model)
    {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");

        Product product = productService.getProById(product_id);
        if (listCart==null)
        {
            listCart = new ArrayList<>();
            Cart cart = new Cart(product,1);
            listCart.add(cart);
        }else{
            boolean checkExit = false;
            for (Cart cart : listCart)
            {
                if (cart.getProduct().getProduct_id()==product_id)
                {
                    checkExit = true;
                    break;
                }
            }

            if (checkExit)
            {
                for (int i=0; i<listCart.size();i++)
                {
                    if (listCart.get(i).getProduct().getProduct_id()==product_id)
                    {
                        listCart.get(i).setQuantity(listCart.get(i).getQuantity()+1);
                        break;
                    }
                }
            }
            else
            {
                Cart cart = new Cart(product,1);
                listCart.add(cart);
            }
        }
        session.setAttribute("listCart",listCart);
        session.setAttribute("tongtien",sumAmount(listCart));
        model.addAttribute("listCart",listCart);
        menu(model);
        return "frontend/ajax/shoppingCart";
    }

    public float sumAmount(List<Cart> listCart)
    {
        float price = 0;
        float sum = 0;
        float phantram=0;
        for(Cart cart:listCart)
        {
            phantram = (float) ((cart.getProduct().getPrice()*cart.getProduct().getDiscount())/100);
            price = (float) (cart.getProduct().getPrice()-phantram);
            sum +=cart.getQuantity()*price;
        }
        return sum;
    }

    @RequestMapping(value = "/listCart")
    public String listCart(HttpSession session, Model model)
    {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        model.addAttribute("listCart",listCart);
        List<Services> services = servicesService.listServiceShow();
        model.addAttribute("listServices",services);

        menu(model);
        return "frontend/shoppingCart";
    }

    @RequestMapping(value = "/deleteCart")
    public String deleteCart(@RequestParam("product_id")Integer product_id, HttpSession session)
    {
        List<Cart> listcart = (List<Cart>)session.getAttribute("listCart");
        for (int i =0; i<listcart.size();i++)
        {
            if (listcart.get(i).getProduct().getProduct_id() == product_id)
            {
                listcart.remove(i).getProduct().getProduct_id();
                break;
            }
        }

        session.setAttribute("listCart",listcart);
        session.setAttribute("tongtien",sumAmount(listcart));
        return "redirect:/food/cart/listCart?success=Deleted product successfully";

    }




    @RequestMapping(path = "/updateQuantity")
    public String updateQty(@RequestParam("quantity")Integer[] qty, HttpSession session)
    {
        List<Cart> listCart =(List<Cart>)session.getAttribute("listCart");
        for (int i=0;i<listCart.size();i++)
        {
            if (qty[i]>0)
            {
                listCart.get(i).setQuantity(qty[i]);
            }
        }

        session.setAttribute("listCart",listCart);
        session.setAttribute("tongtien",sumAmount(listCart));
        return "redirect:/food/cart/listCart?success=update quantity product successfully";
    }
    @RequestMapping(value = "/deleteCartAjax")
    public String deleteCartAjax(@RequestParam("product_id")Integer product_id, HttpSession session)
    {
        List<Cart> listcart = (List<Cart>)session.getAttribute("listCart");
        for (int i =0; i<listcart.size();i++){
            if (listcart.get(i).getProduct().getProduct_id() == product_id)
            {
                listcart.remove(i).getProduct().getProduct_id();
                break;
            }
        }

        session.setAttribute("listCart",listcart);
        session.setAttribute("tongtien",sumAmount(listcart));
        return "frontend/ajax/shoppingCart";

    }



}
