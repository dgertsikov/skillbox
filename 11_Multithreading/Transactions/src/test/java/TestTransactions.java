import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestTransactions
{

    private Bank bank = new Bank();
    private long sumMoney = 0;

    @Before
    public void setBankAccount()
    {
        long money = 0;
        for(int i = 0; i < 10; i++){
            money = (long)(100000 + Math.random() * 60000);
            Account account = new Account(money, String.valueOf(i));
            bank.addAccount(account.getAccNumber(), account);
            sumMoney = sumMoney + money;
        }

    }
    
    @Test
    public void testTransfer()
    {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            int finalI = i;
            threads.add(new Thread(()->{
                bank.transfer(String.valueOf(finalI),String.valueOf(finalI+5),(long)(10000 + Math.random() * 60000));
            }));
        }

        threads.forEach(t-> t.start());
        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long sumSetMoney = 0;
        for (String key : bank.getAccounts().keySet()){
            sumSetMoney = sumSetMoney + bank.getAccounts().get(key).getMoney();

        }
        assertEquals(sumMoney, sumSetMoney);

    }
}
