public class Main {

    public static void main(String[] args) {

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        int startInd = 0;
        int endInd = 0;
        boolean isTrue = true;

        while (isTrue){

            startInd = text.indexOf("<");
            endInd = text.indexOf(">");

            if (startInd > -1 && endInd > -1){

                text = text.substring(0, startInd) + placeholder + text.substring(endInd+1);

            }
            else {
                isTrue = false;
            }
        }

        return text;
    }

}