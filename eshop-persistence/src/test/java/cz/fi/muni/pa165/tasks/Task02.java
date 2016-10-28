package cz.fi.muni.pa165.tasks;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Set;

import static org.testng.Assert.*;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests
{

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Product flashlight;
    private Product kitchenRobot;
    private Product plate;
    private Category electro;
    private Category kitchen;

    @BeforeClass
    public void prepareData()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        electro = new Category();
        electro.setName("Electro");
        kitchen = new Category();
        kitchen.setName("Kitchen");

        flashlight = new Product();
        flashlight.setName("Flashlight");
        kitchenRobot = new Product();
        kitchenRobot.setName("Kitchen robot");
        plate = new Product();
        plate.setName("Plate");

        flashlight.addCategory(electro);
        kitchenRobot.addCategory(kitchen);
        kitchenRobot.addCategory(electro);
        plate.addCategory(kitchen);

        em.persist(electro);
        em.persist(kitchen);
        em.persist(flashlight);
        em.persist(kitchenRobot);
        em.persist(plate);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void flashlightTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, flashlight.getId());
        assertEquals(flashlight.getName(), product.getName());
        assertContainsCategoryWithName(product.getCategories(), electro.getName());

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void kitchenRobotTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, kitchenRobot.getId());
        assertEquals(kitchenRobot.getName(), product.getName());
        assertContainsCategoryWithName(product.getCategories(), electro.getName());
        assertContainsCategoryWithName(product.getCategories(), kitchen.getName());

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void plateTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, plate.getId());
        assertEquals(plate.getName(), product.getName());
        assertContainsCategoryWithName(product.getCategories(), kitchen.getName());

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void electroTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Category category = em.find(Category.class, electro.getId());
        assertEquals(electro.getName(), category.getName());
        assertContainsProductWithName(category.getProducts(), flashlight.getName());
        assertContainsProductWithName(category.getProducts(), kitchenRobot.getName());

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void kitchenTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Category category = em.find(Category.class, kitchen.getId());
        assertEquals(kitchen.getName(), category.getName());
        assertContainsProductWithName(category.getProducts(), kitchenRobot.getName());
        assertContainsProductWithName(category.getProducts(), plate.getName());

        em.getTransaction().commit();
        em.close();
    }

    private void assertContainsCategoryWithName(
        Set<Category> categories,
        String expectedCategoryName
    )
    {
        for (Category cat : categories) {
            if (cat.getName().equals(expectedCategoryName)) {
                return;
            }
        }

        Assert.fail("Couldn't find category " + expectedCategoryName + " in collection " + categories);
    }

    private void assertContainsProductWithName(
        Set<Product> products,
        String expectedProductName
    )
    {
        for (Product prod : products) {
            if (prod.getName().equals(expectedProductName)) {
                return;
            }
        }

        Assert.fail("Couldn't find product " + expectedProductName + " in collection " + products);
    }


}
