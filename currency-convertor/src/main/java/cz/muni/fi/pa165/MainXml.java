package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.logging.Logger;

/**
 * @author Filip Prochazka <filip@prochazka.su>
 */
public class MainXml
{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        CurrencyConvertor currencyConvertor = (CurrencyConvertor) applicationContext.getBean("currencyConvertor");
        BigDecimal converted = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.valueOf(100));

        System.out.println("100 EUR to CZK is " + converted.toString());
    }

}
