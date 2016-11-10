import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReviewMealPlan {

    public ReviewMealPlan(final Plans newMealPlan) {
        initGUI(newMealPlan);
    }

    private void initGUI(final Plans newMealPlan) {
        JFrame mainFrame = new JFrame("Review Meal Plans");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JLabel lblPlans = new JLabel("Plans : ");
        JTextArea txtPlans = new JTextArea();

        txtPlans.setEditable(false);
        String plans = new String("");

        plans += "Daily Plans \n";
        plans += "----------- \n \n";
        for (mealPlan m: newMealPlan.mealplans) {
            plans = plans + "Date : " + m.date;
            plans += "\n Time : ";
            if (m.time == 0)
                plans += "Breakfast \n";
            else if (m.time == 1)
                plans += "Lunch \n";
            else if (m.time == 2)
                plans += "Dinner \n";

            plans += "Recipe Index : ";
            plans += Integer.toString(m.recipeIndex);
            plans += "\n \n";
        }

        txtPlans.setText(plans);

        mainPanel.add(lblPlans);
        mainPanel.add(txtPlans);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}