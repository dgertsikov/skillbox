import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account
{
    private String type;
    private String number;
    private String currency;
    private Date date;
    private String reference;
    private String description;
    private double debet;
    private double credit;
    private String shortDesc;

    Pattern pattern = Pattern.compile("(.*\\+\\d{4,}\\s{4,}.*?)(.+?)\\s{4,}");

    public static class Builder{
        private Account newAccount;

        public Builder(){
            newAccount = new Account();
        }
        public Builder withType(String type){
            newAccount.type = type;
            return this;
        }
        public Builder withNumber(String number){
            newAccount.number = number;
            return this;
        }
        public Builder withCurrency(String currency){
            newAccount.currency = currency;
            return this;
        }
        public Builder withDate(Date date){
            newAccount.date = date;
            return this;
        }
        public Builder withReference(String reference){
            newAccount.reference = reference;
            return this;
        }
        public Builder withDescription(String description){
            newAccount.description = description;

            Matcher matcher = newAccount.pattern.matcher(description);
            if (matcher.find()){
                newAccount.shortDesc = matcher.group(2).isEmpty() ? description : matcher.group(2);
            }
            else{
                newAccount.shortDesc = description;
            }
            return this;
        }
        public Builder withDebet(double debet){
            newAccount.debet = debet;
            return this;
        }
        public Builder withCredit(double credit){
            newAccount.credit = credit;
            return this;
        }

        public Account build(){
            return newAccount;
        }
    }

    public double getDebet() {
        return debet;
    }

    public double getCredit() {
        return credit;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getCurrency() {
        return currency;
    }

    public String toString()
    {
        return getShortDesc() + "\t\t\t " + (getCredit() != 0 ? getCredit() : getDebet()) + " " + getCurrency();
    }
}
