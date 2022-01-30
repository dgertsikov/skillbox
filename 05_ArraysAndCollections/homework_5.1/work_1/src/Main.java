public class Main {
    public static void main(String[] args) {

        String text = "Каждый охотник желает знать, где сидит фазан b ckjy.";
        String[] array = text.split("\\,?\\s+");
        String word;
        for (int i = 0; i <= array.length/2; i++){
            word = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = word;
        }

        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
