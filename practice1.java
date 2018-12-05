import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/*
ask user for student ID
check if Id exists in the libarary system
if yes, add current borrowing book to the the Id account
if not, create a new id account for the student, with name and book
system automatically generates the checkout and return date
the system uses 30 day book return period
*/


public class pp {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        //create a output file
        PrintStream output = new PrintStream(outputFile(console));
        //define some arraylists for different output items
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<>();
        //create a scanner to read from the file
        Scanner f = new Scanner(new File("Lib_Databases.txt"));

        //create arraylists for borrow time & return time
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        String a = "y";
        String line = "";
        int j = 0;
        int z = 0;
        //when you want to continue check & borrow books
        while (a.equals("y")) {
            System.out.print("Student ID: ");
            int t = console.nextInt();      //get a student ID
            if (id.contains(t)) {           //check previous records
                j = id.indexOf(t);
                System.out.print("Book: ");
                String value = console.next();
                int count = 0;
                while (f.hasNextLine()) {  //if the borrowed book is in stock, count it
                    String[] books = f.nextLine().split(",");
                    for (String b : books) {
                        if (b.contains(value))
                            count++;
                    }
                }
                if (count > 0) {          //if the database has the book, output the file
                    outer.get(j).add(value);
                }
                if (count == 0) {         //if the database doesn't have the book, showing "Book not Found" and restart check and borrow.
                    System.out.println("Book Not Found");
                    System.exit(0);
                }
            } else {

                id.add(t);                //add student ID into the system
                System.out.print("Student name: "); //get a student name
                name.add(console.next());
                System.out.print("Checkout Book: ");//get a checkout book name
                String vv = console.next();
                int ct = 0;
                while (f.hasNextLine()) {
                    String[] bks = f.nextLine().split(",");
                    for (String bk : bks) {
                        if (bk.contains(vv)) {      //check book name in database
                            ct++;
                        }
                    }
                }
                if (ct > 0) {
                    ArrayList<String> nw = new ArrayList<>();  //create array for borrowed book and ouput
                    nw.add(vv);
                    outer.add(nw);
                }
                if (ct == 0) {                                //if not in the database, then print "Book Not Found"
                    System.out.println("Book Not Found");
                    System.exit(0);
                }

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  //create data and time for now and return
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime ret = now.plusDays(30);
                String fornow = now.format(dtf);
                String forret = ret.format(dtf);
                n.add(fornow);
                r.add(forret);
                System.out.print("Continue(y/n): "); //continue borrow books or not
                a = console.next();                  // create a loop
            }
          }
        }
        if (ct > 0) {
          ArrayList<String> nw = new ArrayList<>();
          nw.add(vv);
          outer.add(nw);
        }
        if (ct == 0) {
          System.out.println("Book Not Found");
          System.exit(0);
        }
        System.setOut(output);
        outFile(console, name, id, outer, inner, r, n);  //output file arraylists
    }

    public static File outputFile(Scanner console) {
        System.out.print("Output file name: ");  //create an output file name
        File output = new File(console.next());
        return output;
    }
    System.setOut(output);
    outFile(console, name, id, outer, inner, r, n);
  }

    public static void outFile(Scanner console, ArrayList<String> name, ArrayList<Integer> id,   //outFile display items
                               ArrayList<ArrayList<String>> outer, ArrayList<String> inner,
                               ArrayList<String> r, ArrayList<String> n ) {
        StringBuilder sb = new StringBuilder();  //use StringBuilder function to "+" items
        for (int i = 0; i < id.size(); i++) {
            sb.append("Student Name: ").append(name.get(i)).append("\n");
            sb.append("Student ID: ").append(id.get(i)).append("\n");
            sb.append("Checkout Book: ").append(outer.get(i).get(0)).append("\n");
            sb.append("Checkout Date: ").append(n.get(i)).append("\n");
            sb.append("Return Date: ").append(r.get(i)).append("\n");
            sb.append("Book List: ").append(outer.get(i)).append("\n\n");
        }
        System.out.print(sb);

  public static void outFile(Scanner console, ArrayList<String> name, ArrayList<Integer> id,
  ArrayList<ArrayList<String>> outer, ArrayList<String> inner,
  ArrayList<String> r, ArrayList<String> n ) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < id.size(); i++) {
      sb.append("Student Name: ").append(name.get(i)).append("\n");
      sb.append("Student ID: ").append(id.get(i)).append("\n");
      sb.append("Checkout Book: ").append(outer.get(i).get(0)).append("\n");
      sb.append("Checkout Date: ").append(n.get(i)).append("\n");
      sb.append("Return Date: ").append(r.get(i)).append("\n");
      sb.append("Book List: ").append(outer.get(i)).append("\n\n");
    }
    System.out.print(sb);

  }
}
