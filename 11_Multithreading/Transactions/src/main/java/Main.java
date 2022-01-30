import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        for(int i = 0; i < 2; i++){
            Account account = new Account(100000, String.valueOf(i));
            bank.addAccount(account.getAccNumber(), account);
        }

        ArrayList<Thread> threads = new ArrayList<>();
        threads.add(new Thread(()->{
            bank.transfer(String.valueOf(0),String.valueOf(1),(long)(10000 + Math.random() * 60000));
        }));
        threads.add(new Thread(()->{
            bank.transfer(String.valueOf(1),String.valueOf(0),(long)(10000 + Math.random() * 60000));
        }));
        
        threads.forEach(t-> t.start());

    }
}
