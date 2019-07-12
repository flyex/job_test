package cn.itcast.shop.index.action;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class IndexAction  extends ActionSupport {
    //注入一级分类service
    private CategoryService categoryService;

    //注入商品service
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String execute(){
        //查询所有一级分类
        List<Category> cList = categoryService.findAll();
        //将一级分类存到session范围中
        ActionContext.getContext().getSession().put("cList",cList);

        //查询热门商品
        List<Product> hList = productService.findHot();
        //保存到值栈中  set中有key
        ActionContext.getContext().getValueStack().set("hList",hList);

        //查询最新商品
        List<Product> nList = productService.findNew();
        ActionContext.getContext().getValueStack().set("nList",nList);

        return "index";
    }
}
