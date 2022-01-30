public class Main {
    public static void main(String[] args) {
        String[][] array = new String[7][7];

        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = " ";
            }
            array[i][i] = "X";
            array[i][array[i].length-1-i] = "X";
        }
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
    }
}
