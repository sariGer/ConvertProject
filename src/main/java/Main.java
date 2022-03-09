import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //First screen
        System.out.println("Welcome to currency converter");
        ArrayList<Double> result = new ArrayList<Double>();
        result = extracted(result);
        //Fourth Screen
        System.out.println("Thanks for using our currency converter");
        System.out.println("the value history:");
        System.out.print("[");
        for (int i = 0; i < result.size() - 1; i++)
            System.out.print(result.get(i) + " , ");
        System.out.print(result.get(result.size() - 1));
        System.out.print("]");
        //write all results to results.txt (file)
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        FileWriter writer = new FileWriter(dateFormat.format(date) + "results.txt");
        for (double str : result) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    private static ArrayList<Double> extracted(ArrayList<Double> result) throws IOException {
        int choice;
        double input;
        //First screen
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        System.out.println("Please choose an option (1/2)");
        System.out.println("1. Dollars to Shekels");
        System.out.println("2. Shekels to Dollars");

        //Second screen

        try {
            choice = s.nextInt();
            if (choice == 1) {
                System.out.println("Please enter an amount to convert");
                input = s1.nextInt();
                Coin ilsCoin = CoinFactory.getCoinInstance(Coins.ILS);
                double value = ilsCoin.calculate(input);
                System.out.println("the result: " + value);
                result.add(value);
            } else if (choice == 2) {
                System.out.println("Please enter an amount to convert");
                input = s1.nextInt();
                Coin usdCoin = CoinFactory.getCoinInstance(Coins.USD);
                double value = usdCoin.calculate(input);
                System.out.println("the result: " + value);
                result.add(value);
            } else if (choice != 1 && choice != 2) {
                System.out.println(" Invalid choice please enter only 1/2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Invalid choice please enter only int");
        }
        //Third screen
        String again;
        if (!extracted1(result, s2)){
            return result;
        }
        return result;
    }
    private static boolean extracted1(ArrayList<Double> result, Scanner s2) {
        String again;
        System.out.println("Do start over Y / N");
        again = s2.next();
        try {
            if (again.equalsIgnoreCase("Y")) {
                extracted(result);
            } else if (again.equalsIgnoreCase("N")) {
                return true;
            } else if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("n")){
                System.out.println("Invalid input please enter only y/n");
                extracted1(result, s2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid input please try again");
            extracted1(result, s2);
        }
        return false;
    }
}
