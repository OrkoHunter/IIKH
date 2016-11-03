import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.*;

public class CreatePlan {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    JLabel lblIndex;
    JLabel lblName;
    JLabel lblIngredients;
    JLabel lblProcedure;
    JTextField txtIndex;
    JTextField txtName;
    JTextField txtIngredients;
    JTextArea txtProcedure;
    JButton btnAdd;
    JButton btnReset;
    JButton btnClose;

    public CreatePlan(Recipes newRecipes, Plans newPlans) {
        initGUI(newRecipes, newPlans);
        addActionListeners(newRecipes, newPlans);
    }

    private void initGUI(Plans newPlans) {
        mainFrame = new JFrame("Add a new recipe");
        topPanel = new JPanel();

        lblIndex = new JLabel("Index : ");
        lblName = new JLabel("Name : ");
        lblIngredients = new JLabel("Ingredients : ");
        lblProcedure = new JLabel("Procedure : ");

        txtIndex = new JTextField(Integer.toString(newRecipes.size() + 1));
        txtIndex.setEditable(false);
        txtName = new JTextField(15);
        txtIngredients = new JTextField(15);
        txtProcedure = new JTextArea(5, 15);
        JScrollPane scroll = new JScrollPane(txtProcedure);

        topPanel.add(lblIndex);
        topPanel.add(txtIndex);
        topPanel.add(lblName);
        topPanel.add(txtName);
        topPanel.add(lblIngredients);
        topPanel.add(txtIngredients);
        topPanel.add(lblProcedure);
        topPanel.add(scroll);

        SpringLayout layout = new SpringLayout();
        topPanel.setLayout(layout);
        SpringUtilities.makeCompactGrid(topPanel, 4, 2, 5, 5, 5, 5);

        btnAdd = new JButton("Add");
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        FlowLayout flowLayout = new FlowLayout();
        bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnReset);
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

    private void add(Recipes newRecipes, Plans newPlans) {
        String name = txtName.getText();
        String ingredients = txtIngredients.getText();
        String procedure = txtProcedure.getText();
        newRecipes.add(name, ingredients, procedure);
        try {
            FileOutputStream fileOut = new FileOutputStream("recipes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newRecipes);
            out.close();
            fileOut.close();
            System.out.println("New Recipe " + name + " data saved in recipes.ser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        reset();
        txtIndex.setText(Integer.toString(newRecipes.size() + 1));
    }

    private void reset() {
        txtName.setText("");
        txtIngredients.setText("");
        txtProcedure.setText("");
    }

    private void close() {
        reset();
        mainFrame.setVisible(false);
    }

    private void addActionListeners(Recipes newRecipes, Plans newPlans) {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(newRecipes);
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


}
