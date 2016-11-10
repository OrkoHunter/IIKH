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
    JLabel lblRecipeIndex;
    JComboBox<String> txtRecipeIndex;
    JButton btnSave;

    public CreateMealPlan(final Recipes newRecipes, final Plans newPlans) {
        initGUI(newRecipes, newPlans);
    }

    private void initGUI(final Recipes newRecipes, final Plans newPlans) {
        mainFrame = new JFrame("Create a Meal Plan");
        topPanel = new JPanel();

        lblDate = new JLabel("Select Date (dd/MM/yyyy) : ");
        txtDate = new JTextField(10);
        lblTime = new JLabel("Select Time of the day : ");
        String[] timeOptions = {"Breakfast", "Lunch", "Dinner"};
        txtTime = new JComboBox<String>(timeOptions);

        lblRecipeIndex = new JLabel("Choose Recipe : ");
        String[] names = new String[newRecipes.size()];
        int i = 0;
        for (Recipe r: newRecipes.list) {
            names[i] = r.name;
            i++;
        }

        txtRecipeIndex = new JComboBox<String>(names);


        topPanel.add(lblDate);
        topPanel.add(txtDate);
        topPanel.add(lblTime);
        topPanel.add(txtTime);
        topPanel.add(lblRecipeIndex);
        topPanel.add(txtRecipeIndex);

        SpringLayout layout = new SpringLayout();
        topPanel.setLayout(layout);
        SpringUtilities.makeCompactGrid(topPanel,
                                        3, 2,  // row, cols
                                        5, 5,
                                        5, 5);

        FlowLayout flowLayout = new FlowLayout();
        bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);

        btnSave = new JButton("Save");

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newPlans.amealPlan(txtDate.getText(), txtTime.getSelectedIndex(), txtRecipeIndex.getSelectedIndex());
                try {
                    FileOutputStream fileOut = new FileOutputStream("plans.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(newPlans);
                    out.close();
                    fileOut.close();
                    System.out.println("New Plan for " + txtDate.getText() + " data saved in plans.ser");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
                txtDate.setText("");
            }
        });

        JButton btnClose = new JButton("Close");

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
            }
        });

        bottomPanel.add(btnSave);
        bottomPanel.add(btnClose);


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
}
