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


public class practice1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        PrintStream output = new PrintStream(outputFile(console));
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<>();


        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        String a = "y";
        int j = 0;
        int z = 0;

        while (a.equals("y")){
            System.out.print("Student ID: ");
            int t = console.nextInt();
            if (id.contains(t)) {
                j = id.indexOf(t);
                System.out.print("Book: ");
                String value = console.next();
                outer.get(j).add(value);   //previous booklist
            } else {
                id.add(t);
                System.out.print("Student name: ");
                name.add(console.next());
                System.out.print("Checkout Book: ");
                ArrayList<String> nw = new ArrayList<>();
                nw.add(console.next());
                outer.add(nw);
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime ret = now.plusDays(30);
            String fornow = now.format(dtf);
            String forret = ret.format(dtf);
            n.add(fornow);
            r.add(forret);
            System.out.print("Continue(y/n): ");
            a = console.next();
        }

        System.setOut(output);
        outFile(console, name, id, outer, inner, r, n);
    }

    public static File outputFile(Scanner console) {
        System.out.print("Output file name: ");
        File output = new File(console.next());
        return output;
    }

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
        System.out.print(sb.toString());

    }
}
