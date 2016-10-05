package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Filip Prochazka <filip@prochazka.su>
 */
public class MainAnnotations
{

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("cz.muni.fi.pa165");
        applicationContext.refresh();

        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);
        BigDecimal converted = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.valueOf(100));

        System.out.println("100 EUR to CZK is " + converted.toString());
    }

}
