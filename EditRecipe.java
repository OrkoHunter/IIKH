import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.*;

public class EditRecipe {
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
    JButton btnModify;
    JButton btnClose;

    public EditRecipe(final Recipes newRecipes) {
        initGUI(newRecipes);
        addActionListeners(newRecipes);
    }

    private void initGUI(final Recipes newRecipes) {
        mainFrame = new JFrame("Edit existing recipes");
        topPanel = new JPanel();

        String[] names = new String[newRecipes.size() + 1];
        names[0] = "";
        int i = 1;
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

        btnModify = new JButton("Modify");
        btnClose = new JButton("Close");

        FlowLayout flowLayout = new FlowLayout();
        bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
        bottomPanel.add(btnModify);
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

    private void edit(final Recipes newRecipes) {
        int i = Integer.parseInt(txtIndex.getText()) - 1;
        String name = txtName.getText();
        String ingredients = txtIngredients.getText();
        String procedure = txtProcedure.getText();
        newRecipes.edit(i, name, ingredients, procedure);
        try {
            FileOutputStream fileOut = new FileOutputStream("recipes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newRecipes);
            out.close();
            fileOut.close();
            System.out.println("Updated Recipe " + name + " data saved in recipes.ser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(mainFrame, "Recipe for " + name + " modified!");
        close();
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
                if (index == 0) {
                    reset();
                    txtIndex.setText("");
                }
                else
                    setDisplay(newRecipes, index - 1);
            }
        });

        btnModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Name cannot be empty.",
                        "Null error", JOptionPane.ERROR_MESSAGE);
                } else if (txtIngredients.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Ingredients cannot be empty.",
                        "Null error", JOptionPane.ERROR_MESSAGE);
                } else if (txtProcedure.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "Procedure cannot be empty.",
                        "Null error", JOptionPane.ERROR_MESSAGE);
                } else {
                    edit(newRecipes);
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }


}
