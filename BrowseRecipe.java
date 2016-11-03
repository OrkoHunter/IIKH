import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.*;

public class BrowseRecipe {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    JComboBox<String> nameList;
    JLabel lblIndex;
    JLabel lblName;
    JLabel lblIngredients;
    JLabel lblProcedure;
    JTextField txtIndex;
    JTextField txtName;
    JTextField txtIngredients;
    JTextArea txtProcedure;

    JButton btnReset;
    JButton btnClose;

    public BrowseRecipe(final Recipes newRecipes) {
        initGUI(newRecipes);
        addActionListeners(newRecipes);
    }

    private void initGUI(final Recipes newRecipes) {
        mainFrame = new JFrame("Browse existing recipes");
        topPanel = new JPanel();

        String[] names = new String[newRecipes.size()];
        int i = 0;
        for (Recipe r: newRecipes.list) {
            names[i] = r.name;
            i++;
        }
        nameList = new JComboBox<String>(names);

        lblIndex = new JLabel("Index : ");
        lblName = new JLabel("Name : ");
        lblIngredients = new JLabel("Ingredients : ");
        lblProcedure = new JLabel("Procedure : ");

        txtIndex = new JTextField();
        txtIndex.setEditable(false);
        txtName = new JTextField(10);
        txtName.setEditable(false);
        txtIngredients = new JTextField(10);
        txtIngredients.setEditable(false);
        txtProcedure = new JTextArea(5, 15);
        txtProcedure.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtProcedure);
        setDisplay(newRecipes, 0);

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

        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        FlowLayout flowLayout = new FlowLayout();
        bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnClose);

        mainPanel = new JPanel();
        BoxLayout yBoxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(yBoxLayout);
        mainPanel.add(nameList);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        mainFrame.add(mainPanel);

        mainFrame.setLocationRelativeTo(null);

        mainFrame.pack();
        mainFrame.setVisible(true);
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

    private void setDisplay(final Recipes newRecipes, int index) {
        txtIndex.setText(Integer.toString(index + 1));
        txtName.setText(newRecipes.list.get(index).name);
        txtIngredients.setText(newRecipes.list.get(index).ingredients);
        txtProcedure.setText(newRecipes.list.get(index).procedure);
    }

    private void addActionListeners(final Recipes newRecipes) {
        nameList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int index = (int) cb.getSelectedIndex();
                setDisplay(newRecipes, index);
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