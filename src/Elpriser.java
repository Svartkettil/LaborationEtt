import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Elpriser {
    public static void main(String[] args) {
        int[] hourPrices = new int[24];
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            startMenu();
            choice = sc.nextLine().toLowerCase();
            switch (choice) {
                case "1" -> input(sc, hourPrices);
                case "2" -> minMaxAverage(hourPrices);
                case "3" -> sortPrices(hourPrices);
                case "4" -> optimalHours(hourPrices);
                case "e" -> System.out.println("Thank you for using the machine");
                default -> System.out.println("errorInput");
            }
        }
        while (!choice.equals("e"));

    }

    public static void startMenu() {
        System.out.println("Elpriser");
        System.out.println("========");
        System.out.println("1. Inmatning");
        System.out.println("2. Min, Max och Medel");
        System.out.println("3. Sortera");
        System.out.println("4. Bästa laddningstid");
        System.out.println("e. Avsluta");
    }

    public static void input(Scanner sc, int[] hourPrices) {

        System.out.println("Enter price for each hour please :)");
        try {
            for (int i = 0; i < hourPrices.length; i++) {
                if (i < 9)
                    System.out.println("Enter time for hour: 0" + i + " - 0" + (i + 1));
                else if (i == 9)
                    System.out.println("Enter time for hour: 0" + i + " - " + (i + 1));
                else System.out.println("Enter time for hour: " + i + " - " + (i + 1));
               hourPrices[i] = sc.nextInt();
 //               hourPrices[i] = (int) (Math.random() * 400) + 1;
                System.out.println(hourPrices[i]);
            }
        } catch (Exception e) {
            System.out.println("There was an error in your input :" + e);
        }

    }

    public static void minMaxAverage(int[] hourPrices) {
        int minTime = getMinPrice(hourPrices);
        System.out.println("klockan " + minTime);
        int maxTime = getMaxPrice(hourPrices);
        System.out.println("klockan " + maxTime);
        int average = getAveragePrice(hourPrices);
        System.out.println("Medelpriset på EL är: " + average + " öre");

    }

    public static int getMinPrice(int[] hourPrices) {
        int minTime = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < hourPrices.length; i++) {
            if (min > hourPrices[i]) {
                min = hourPrices[i];
                minTime = i;
            }
        }
        System.out.print("Minpriset är " + min + " öre ");
        return minTime;
    }

    public static int getMaxPrice(int[] hourPrices) {
        int maxTime = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < hourPrices.length; i++) {
            if (max < hourPrices[i]) {
                max = hourPrices[i];
                maxTime = i;
            }
        }
        System.out.print("Maxpriset är " + max + " öre ");
        return maxTime;
    }

    public static int getAveragePrice(int[] hourPrices) {
        double average = 0;
        double sum = 0;
        int averageInt = 0;
        for (int i : hourPrices) {
            sum += i;
            average = sum / hourPrices.length;
            average = Math.round(average);
            averageInt = (int) average;

        }
        return averageInt;
    }

    public static void sortPrices(int[] hourPrices) {
        int[] hourPricesCopy = Arrays.copyOf(hourPrices, hourPrices.length);
        int[] hourPriceTime = new int[24];
        for (int i = 0; i < 24; i++) {
            hourPriceTime[i] = i;
        }

        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 0; i < hourPricesCopy.length - 1; i++) {
                if (hourPricesCopy[i] > hourPricesCopy[i + 1]) {
                    int tempPrice = hourPricesCopy[i + 1];
                    hourPricesCopy[i + 1] = hourPricesCopy[i];
                    hourPricesCopy[i] = tempPrice;

                    int tempTime = hourPriceTime[i + 1];
                    hourPriceTime[i + 1] = hourPriceTime[i];
                    hourPriceTime[i] = tempTime;
                    hasChanged = true;
                }


            }
            }
        for (int i = 0; i < hourPricesCopy.length; i++) {
            if (hourPriceTime[i] <= 9)
                System.out.println("Klockan 0" + hourPriceTime[i] + " är priset " + hourPricesCopy[i]);
            else
                System.out.println("Klockan " + hourPriceTime[i] + " är priset " + hourPricesCopy[i]);
        }


    }
    public static void optimalHours (int[] hourPrices){
        int sum = 0;
        int[] fourOptimalHours = new int[24];
        for (int i = 0; i <= 20; i++) {
            sum = 0;
            for (int j = i; j < i+4; j++) {
                sum += hourPrices[j];
            }
            fourOptimalHours[i] = sum;
        }
        int min = fourOptimalHours[0];
        int timeOptimalHours = 0;
        boolean isChanging = true;
        while (isChanging) {
            isChanging = false;
            for (int i = 0; i < 21; i++) {
                if (fourOptimalHours[i] < min) {
                    min = fourOptimalHours[i];
                    timeOptimalHours = i;
                    isChanging = true;
                }
            }
        }
        System.out.println("Klockan " + timeOptimalHours + " till och med " + (timeOptimalHours + 4) + "\när det billgast med det totala priset \n" + min + "öre");
    }

}


