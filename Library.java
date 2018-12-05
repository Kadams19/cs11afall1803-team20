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


public class Library {

    private static PrintStream outputFileStream;

    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        outputFileStream = new PrintStream(outputFile(console));
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<ArrayList<String>> outer = new ArrayList<>();
        Scanner f = new Scanner(new File("databse.txt"));


        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        String a = "y";
        int j;

        ArrayList<String> all = new ArrayList<>();
        while (f.hasNextLine()) {
            all.add(f.nextLine());
        }
        System.out.println(all);

        while (a.contains("y")) {
            System.out.print("Student ID: ");
            int t = console.nextInt();
            console.nextLine();
            if (id.contains(t)) {
                j = id.indexOf(t);
                System.out.print("Book: ");
                String value = console.nextLine();
                System.out.println("\n\n$$$ " + value + " $$$\n\n");
                boolean ftt = bookExist(value, all);

                while (!ftt) {
                    System.out.println("****Book Not Found****");
                    System.out.print("Checkout Book: ");
                    value = console.nextLine();
                    ftt = bookExist(value, all);
                }
                outer.get(j).add(value);
            } else {
                id.add(t);
                System.out.print("Student name: ");
                name.add(console.nextLine());
                System.out.print("Checkout Book: ");
                String vv = console.nextLine();
                boolean tff = bookExist(vv, all);

                while (!tff) {
                    System.out.println("****Book Not Found****");
                    System.out.print("Checkout Book: ");
                    vv = console.nextLine();
                    tff = bookExist(vv, all);
                }
                ArrayList<String> nw = new ArrayList<>();
                nw.add(vv);
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
        System.setOut(outputFileStream);
        outFile(name, id, outer, r, n);
    }

    public static File outputFile(Scanner console) {
        System.out.print("Output file name: ");
        File output = new File(console.nextLine());
        return output;
    }

    public static boolean bookExist(String value, ArrayList<String> all) {
        boolean tf = false;
        for(String ss : all){
            if (ss.equals(value))
                tf = true;
        }
        return tf;
    }

    public static void outFile(ArrayList<String> name, ArrayList<Integer> id,
                               ArrayList<ArrayList<String>> outer,
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
        outputFileStream.print(sb);
    }
}
