package com.hillel.finalWork;

import com.hillel.finalWork.model.Category;
import com.hillel.finalWork.model.Product;
import com.hillel.finalWork.service.ProductService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProduct {

    @Autowired
    private ProductService productService;

    private static Product product;

    @BeforeClass
    public static void preCondition(){
        product = new Product();
        product.setName("Test1");
        product.setCategory(Category.FOOD);
        product.setPrice(BigDecimal.valueOf(99.99));
    }

    @Test
    public void t1CreateProduct() {
        Product newProduct = productService.createProduct(product);
        product.setId(newProduct.getId());
        Assert.assertEquals(product, newProduct);
    }
    @Test
    public void t2UpdateProduct() {
        product.setPrice(BigDecimal.valueOf(55.55));
        product.setName("Test555");
        Assert.assertTrue(productService.updateProduct(product));
    }
    @Test
    public void t3FindProduct() {
        Assert.assertSame(product.getId(), productService.findById(product.getId()).getId());
    }

    @Test
    public void t4DeleteProduct() {
        productService.deleteProduct(product);
        Assert.assertNull(productService.findById(product.getId()));
    }

    @Test
    public void t5FindAll() {
        List<Product> products = productService.findAll();
        product.setName("Teat777");
        product.setId(0);
        productService.createProduct(product);
        int size = productService.findAll().size();
        Assert.assertEquals(products.size() + 1, size);
        productService.deleteProduct(product);
    }

    @AfterClass
    public static void postCondition(){
        product = null;
    }
}
