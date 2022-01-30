import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private HashSet<String> accountBlock = new HashSet<>();
    private final Random random = new Random();

    public void addAccount(String accNumber, Account account)
    {
        accounts.put(accNumber, account);

    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)
    {
        Account lowSyncAccount = fromAccountNum.compareTo(toAccountNum) > 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum);
        Account topSyncAccount = fromAccountNum.compareTo(toAccountNum) < 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum);
        synchronized (lowSyncAccount)
        {
            synchronized (topSyncAccount)
            {
                if (amount > 50000){
                    try {
                        if(isFraud(fromAccountNum,toAccountNum,amount))
                        {
                            accountBlock.add(fromAccountNum);
                            accountBlock.add(toAccountNum);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!accounts.containsKey(fromAccountNum)){
                    System.out.println("Не найден счет отправитель");
                    return;
                }
                if(!accounts.containsKey(toAccountNum)){
                    System.out.println("Не найден счет получатель");
                    return;
                }
                if (accounts.get(fromAccountNum).getMoney() < amount){
                    System.out.println("Нельзя перевести денег больше, чем есть на счете");
                    return;
                }
                if (accountBlock.contains(fromAccountNum)){
                    System.out.println("Счет заблокирован");
                    return;
                }
                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);

                long sum = getBalance(fromAccountNum) + getBalance(toAccountNum);
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

}
