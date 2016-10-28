package cz.fi.muni.pa165.tasks;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.testng.Assert.*;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task01 extends AbstractTransactionalTestNGSpringContextTests
{

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void categoryTest()
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Category cat = new Category();
        cat.setName("Test");
        em.persist(cat);
        em.getTransaction().commit();
        em.close();

        //TODO under this line: create a second entity manager in categoryTest, use find method to find the category and assert its name.
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Category foundCategory = em2.find(Category.class, cat.getId());
        assertEquals("Test", foundCategory.getName());
        em2.getTransaction().commit();
        em2.close();
    }

}
