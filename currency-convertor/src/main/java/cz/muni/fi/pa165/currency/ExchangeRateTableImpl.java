package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Filip Prochazka <filip@prochazka.su>
 */
public class ExchangeRateTableImpl implements ExchangeRateTable
{

    public ExchangeRateTableImpl()
    {
    }

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException
    {
        if (sourceCurrency.getCurrencyCode().equals("EUR") && targetCurrency.getCurrencyCode().equals("CZK")) {
            return BigDecimal.valueOf(27);
        }

        return null;
    }

}
