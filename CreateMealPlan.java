import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.*;

public class CreateMealPlan {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    JLabel lblDate;
    JTextField txtDate;
    JComboBox<String> txtTime;
    JLabel lblTime;

    public CreateMealPlan(final Recipes newRecipes, final Plans newPlans) {
        initGUI(newRecipes, newPlans);
        //addActionListeners(newRecipes, newPlans);
    }

    private void initGUI(final Recipes newRecipes, final Plans newPlans) {
        mainFrame = new JFrame("Create a Meal Plan");
        topPanel = new JPanel();

        lblDate = new JLabel("Select Date (dd/MM/yyyy) : ");
        txtDate = new JTextField(10);
        lblTime = new JLabel("Select Time of the day : ");
        String[] timeOptions = {"Breakfast", "Lunch", "Dinner"};
        txtTime = new JComboBox<String>(timeOptions);

        topPanel.add(lblDate);
        topPanel.add(txtDate);
        topPanel.add(lblTime);
        topPanel.add(txtTime);

        SpringLayout layout = new SpringLayout();
        topPanel.setLayout(layout);
        SpringUtilities.makeCompactGrid(topPanel,
                                        2, 2,  // row, cols
                                        5, 5,
                                        5, 5);

        FlowLayout flowLayout = new FlowLayout();
        bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);

        mainPanel = new JPanel();
        BoxLayout yBoxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(yBoxLayout);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        mainFrame.add(mainPanel);

        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
/*
    private void addActionListeners(final Recipes newRecipes, final Plans newPlans) {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(newRecipes, newPlans);
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }
*/

}
