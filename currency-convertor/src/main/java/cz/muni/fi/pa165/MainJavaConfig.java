package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Filip Prochazka <filip@prochazka.su>
 */
@Configuration
public class MainJavaConfig
{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainJavaConfig.class);

        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);
        BigDecimal converted = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.valueOf(100));

        System.out.println("100 EUR to CZK is " + converted.toString());
    }

    @Bean
    public CurrencyConvertor getCurrencyConvertor(ExchangeRateTable exchangeRateTable) {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Bean
    public ExchangeRateTable getExchangeRateTable() {
        return new ExchangeRateTableImpl();
    }

}
