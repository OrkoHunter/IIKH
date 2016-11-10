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

    public void amealPlan(String date, int time, int recipeIndex) {

        mealPlan meal = new mealPlan();
        meal.date = date;
        meal.time = time;
        meal.recipeIndex = recipeIndex;

        mealplans.add(meal);
    }

    public void adailyPlan() {
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