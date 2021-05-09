package edu.iis.mto.testreactor.atm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
@ExtendWith({MockitoExtension.class})
class ATMachineTest {

    @Mock
    MoneyDeposit moneyDeposit;
    @Mock
    Bank bank;

    @BeforeEach
    void setUp() throws Exception {}

    @Test
    void withdrawWithIncorrectCurrencyShouldInvokeAtmException(){
        Currency currency = Currency.getInstance(Locale.UK);
        Money money = new Money(new BigDecimal("1234"), currency);
        ATMachine atmachine = new ATMachine(bank, currency);
        atmachine.setDeposit(moneyDeposit);
        when(moneyDeposit.getCurrency()).thenReturn(Currency.getInstance(Locale.PRC));
        try {
            atmachine.withdraw(PinCode.createPIN(1, 2, 3, 4), Card.create("1234569012"), money);
            fail("Should be exception");
        } catch (ATMOperationException e) {
            System.out.println("ok");

        }

    }
}
