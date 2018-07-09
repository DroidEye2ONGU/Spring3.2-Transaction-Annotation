import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import Service.BookShopService;
import Service.Cashier;

public class TransactionalAnnotationTest {
    ApplicationContext applicationContext;
    BookShopService bookShopService;
    Cashier cashier;

    {
        applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        bookShopService = (BookShopService) applicationContext.getBean("bookShopService");
        cashier = (Cashier) applicationContext.getBean("cashier");
    }

    @Test
    public void cashierTest() {
        cashier.checkout("AA",Arrays.asList("1001","1002"));
    }
}
