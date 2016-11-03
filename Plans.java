import java.io.Serializable;
import java.util.*;
import java.text.*;

public class Plans implements Serializable {

    public void showMeals() {
        for (dailyPlan d: dailyplans) {
            System.out.println("----------------------------------");
            System.out.println("For date : " + d.date);
            System.out.println("Recipe Index (Breakfast) : " + Integer.toString(d.recipeIndices[0]));
            System.out.println("Recipe Index (Lunch) : " + Integer.toString(d.recipeIndices[1]));
            System.out.println("Recipe Index (Dinner) : " + Integer.toString(d.recipeIndices[2]));
        }

        for (mealPlan m : mealplans) {
            System.out.println("----------------------------------");
            System.out.println("For date : " + m.date);
            if (m.time == 0)
                System.out.print("For Breakfast, ");
            else if (m.time == 1)
                System.out.print("For Lunch, ");
            else if (m.time == 2)
                System.out.print("For Dinner, ");
            System.out.println("Recipe index is " + Integer.toString(m.recipeIndex));
        }
    }
    public void createNew() {
        this.printOptions();
        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();

        if (opt == 1)
            this.amealPlan();
        if (opt == 2)
            this.adailyPlan();
    }

    private void amealPlan() {
        Scanner scanner = new Scanner(System.in);
        mealPlan meal = new mealPlan();
        while(true) {
            System.out.print("Enter date (yyyy-mm-dd) : ");
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            String input = scanner.nextLine();
            try {
                meal.date = input;
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("Enter 0 for Breakfast, 1 for Lunch and 2 for Dinner : ");
        meal.time = scanner.nextInt();
        System.out.print("Enter the recipe index : ");
        meal.recipeIndex = scanner.nextInt();

        mealplans.add(meal);
    }

    private void adailyPlan() {
        Scanner scanner = new Scanner(System.in);
        dailyPlan meal = new dailyPlan();
        while(true) {
            System.out.print("Enter date (yyyy-mm-dd) : ");
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            String input = scanner.nextLine();
            try {
                meal.date = input;
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("Enter recipe indices for Breakfast, Lunch and Dinner : ");
        meal.recipeIndices[0] = scanner.nextInt();
        meal.recipeIndices[1] = scanner.nextInt();
        meal.recipeIndices[2] = scanner.nextInt();

        dailyplans.add(meal);
    }

    private void printOptions() {
        System.out.println("1. Plan for a meal");
        System.out.println("2. Plan for a day");
    }

    public ArrayList<dailyPlan> dailyplans = new ArrayList<dailyPlan>();
    public ArrayList<mealPlan> mealplans = new ArrayList<mealPlan>();
}

class dailyPlan implements Serializable {
    public String date;
    public int[] recipeIndices = new int[3];
}

class mealPlan implements Serializable {
    public String date;
    public int time;  // 0, 1, 2 for Breakfast, Lunch, Dinner
    public int recipeIndex;
}