import java.util.*;
import java.io.*;

public class USACO{
  private static int R;
  private static int C;
  private static int E;
  private static int N;
  private static int N2;
  private static int M;
  private static int T;
  private static String[][] data;
  private static int[][] newboard;
  private static int R1;
  private static int R2;
  private static int C1;
  private static int C2;

  public static int bronze(String filename) throws FileNotFoundException{
    int depth = 0;
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String line = inf.nextLine();
    String[] values = line.split(" ", -2);
    R = Integer.parseInt(values[0]);
    C = Integer.parseInt(values[1]);
    E = Integer.parseInt(values[2]);
    N = Integer.parseInt(values[3]);
    int[][] moves = new int[N][3];
    int[][] board = new int[R][C];
    for (int i = 0; i < R; i++){
      line = inf.nextLine();
      values = line.split(" ", -2);
      for (int c = 0; c < values.length; c++){
        board[i][c] = Integer.parseInt(values[c]);
      }
    }
    int counter = 0;
    while (inf.hasNextLine()){
      line = inf.nextLine();
      values = line.split(" ", -2);
      for (int i = 0; i < values.length; i++){
        moves[counter][i] = Integer.parseInt(values[i]);
      }
      counter++;
    }
    for (int i = 0; i < moves.length; i++){
      int row = moves[i][0];
      int col = moves[i][1];
      int max = 0;
      for (int r = row; r <= row + 2; r++){
        for (int c = col; c <= col + 2; c++){
          if (board[r - 1][c - 1] > max){
            max = board[r - 1][c - 1];
          }
        }
      }
      max = max - moves[i][2];
      for (int r = row; r <= row + 2; r++){
        for (int c = col; c <= col + 2; c++){
          if (board[r - 1][c - 1] > max){
            board[r - 1][c - 1] = max;
          }
        }
      }
    }
    for (int i = 0; i < board.length; i++){
      for (int c = 0; c < board[0].length; c++){
        int newval = E - board[i][c];
        if (newval > 0){
          board[i][c] = newval;
        }else{
          board[i][c] = 0;
        }
        depth += board[i][c];
      }
    }
    return 72 * 72 * depth;
  }
  
  public static int silver(String filename) throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String line = inf.nextLine();
    String[] values = line.split(" ", -2);
    N2 = Integer.parseInt(values[0]);
    M = Integer.parseInt(values[1]);
    T = Integer.parseInt(values[2]);
    data = new String[N2][M];
    for (int i = 0; i < N2; i++){
      line = inf.nextLine();
      for (int c = 0; c < line.length(); c++){
        data[i][c] = line.substring(c, c + 1);
      }
    }
    line = inf.nextLine();
    values = line.split(" ", -2);
    R1 = Integer.parseInt(values[0]) - 1;
    C1 = Integer.parseInt(values[1]) - 1;
    R2 = Integer.parseInt(values[2]) - 1;
    C2 = Integer.parseInt(values[3]) - 1;
    newboard = new int[data.length][data[0].length];
    for (int r = 0; r < newboard.length; r++){
      for (int c = 0; c < newboard[0].length; c++){
        if (data[r][c].equals(".")){
          newboard[r][c] = 0; 
        }else{
          newboard[r][c] = -99999999;
        }
      }
    }
    newboard[R1][C1] = 1;
    ArrayList<Integer> coords;
    int[][] moves = {
      {0,1},
      {0,-1},
      {1,0},
      {-1,0}
    };
    while(T > 0){
      coords = new ArrayList<>();
      for(int r = 0; r < data.length; r++){
        for (int c = 0; c < data[0].length; c++){
          if (newboard[r][c] > 0){
            coords.add(r);
            coords.add(c);
          }
        }
      }
      for (int a = 0; a < coords.size(); a += 2){
        for (int b = 0; b < moves.length; b++){
          if (inbounds(coords.get(a) + moves[b][0], coords.get(a+1) + moves[b][1], newboard)){
            newboard[coords.get(a) + moves[b][0]][coords.get(a+1) + moves[b][1]] += newboard[coords.get(a)][coords.get(a+1)];
          }
        }
        newboard[coords.get(a)][coords.get(a+1)] = 0;
      }
      T--;
    }
    return newboard[R2][C2];
  }

  private static boolean inbounds(int x, int y, int[][] board){
    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length){
      return false;
    }
    return true;
  }

  public static void main(String[] args){
    try{
      
      //for (int i = 1; i <= 5; i++){
        //System.out.println(silver("testCases/ctravel." + i + ".in"));
        //System.out.println("" + N2 + " " + M + " " + T);
      //}
      //System.out.println("" + R1 + " " + C1 + " " + R2 + " " + C2);
      
      /*

      for (int[] i : newboard){
        for (int c : i){
          System.out.print("" + c + " ");
        }
        System.out.println();
      }

      */

    }
    catch(FileNotFoundException e){
      System.out.println("file not found :(");
    }
  }
}
