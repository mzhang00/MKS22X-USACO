import java.util.*;
import java.io.*;

public class USACO{
  private static int R;
  private static int C;
  private static int E;
  private static int N;
  public static int bronze(String filename) throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String line = inf.nextLine();
    String[] values = line.split(" ", -2);
    R = Integer.parseInt(values[0]);
    C = Integer.parseInt(values[1]);
    E = Integer.parseInt(values[2]);
    N = Integer.parseInt(values[3]);
    return 1;
  }
}
