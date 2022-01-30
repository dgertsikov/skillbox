public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль
        int fromIndex = 0;
        int prevIndex = 0;
        boolean isTrue = true;
        double earn = 0;

        while (isTrue)
        {
            fromIndex = text.indexOf(" рубл", fromIndex);
            if (fromIndex > -1) {
                prevIndex = text.lastIndexOf(" ", fromIndex - 1);
                earn = earn + Double.parseDouble(text.substring(prevIndex, fromIndex));
                fromIndex++;
            }
            else{
                isTrue = false;
            }

        }
        System.out.println("Сумма заработка всех друзей " + earn + " руб.");
    }

}