package cz.fi.muni.pa165.tasks;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.ProductDao;
import cz.fi.muni.pa165.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task03 extends AbstractTransactionalTestNGSpringContextTests
{

    @Autowired
    private ProductDao productDao;

    @Test
    public void createFindDeleteTest()
    {
        Product p1 = new Product();
        p1.setName("TestProduct1");
        Product p2 = new Product();
        p2.setName("TestProduct2");

        productDao.create(p1);
        productDao.create(p2);

        List<Product> products = productDao.findAll();
        assertEquals(2, products.size());
        assertTrue(products.stream().filter(p -> p.getName().equals("TestProduct1")).count() == 1);
        assertTrue(products.stream().filter(p -> p.getName().equals("TestProduct2")).count() == 1);

        assertEquals("TestProduct1", productDao.findById(p1.getId()).getName());
        assertEquals("TestProduct2", productDao.findById(p2.getId()).getName());

        productDao.delete(p2);
        assertEquals(1, productDao.findAll().size());
        assertNull(productDao.findById(p2.getId()));
    }

}
