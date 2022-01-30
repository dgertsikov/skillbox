import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class DepositAccount extends BankAccount {

    public DepositAccount(double amount) {
        super(amount);
    }

    @Override
    public boolean minusMoney(double amount) {

        if (LocalDate.now().isBefore(getDepositDate().with(lastDayOfMonth()))){
            System.out.println("Нельзя снимать деньги в течение месяца после последнего внесения");
            return false;
        } else {
            super.minusMoney(amount);
            return true;
        }
    }


}
