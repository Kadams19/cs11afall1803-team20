import java.util.Scanner;
public class OddTable{
  public static void main(String[] args){
    int[][] data = readTable();
    int oddsum = sumOdds(data);
    System.out.printf("There are %d odd numbers in the table%n",oddsum);
}
  public static int[][] readTable(){
    Scanner in = new Scanner(System.in);
    System.out.println("How many rows? ");
    int rows = in.nextInt();
    System.out.println("How many cols? ");
    int cols = in.nextInt();
    int[][] data = new int[rows][cols];
    for(int i=0; i<rows; i++){
      System.out.println("Enter row "+i);
      for(int j=0; j<cols; j++){
        data[i][j] = in.nextInt();
      }
      in.nextLine();
    }
    return data;
  }
  public int countOdds (int[] values) {
  int countOdd =0;
  for (int i=0; i<values.length; i++) {
    if (values[i] %2 != 0) {
      countOdd++;
      return countOdd;
    }
  }
}
