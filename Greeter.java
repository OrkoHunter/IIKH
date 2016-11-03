import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.*;

public class Greeter {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JButton btnAddRecipe;
    private JButton btnEditRecipe;
    private JButton btnBrowseRecipe;
    private JButton btnReviewPlan;
    private JButton btnCreatePlan;
    final DateFormat dateFormat = new SimpleDateFormat("E dd-MM-yyyy HH:mm:ss");

    public Greeter(Recipes newRecipes, Plans newPlans) {
        prepareGUI();
        addActionListeners(newRecipes);
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Interactive Intelligent Kitchen Helper");
        mainFrame.setSize(600, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        initTopPanel();
        initMidPanel();
        initBottomPanel();

        /* Main Panel */
        mainPanel.add(topPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        mainPanel.add(midPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(100, 80)));
        mainPanel.add(bottomPanel);
        mainFrame.add(mainPanel);

        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    private void initTopPanel() {
        /* Top Panel */
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Welcome");
        JLabel label2 = new JLabel("to");
        JLabel label3 = new JLabel("Interactive Intelligent Kitchen Helper");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createRigidArea(new Dimension(20, 20)));
        topPanel.add(label1);
        topPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        topPanel.add(label2);
        topPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        topPanel.add(label3);
    }

    private void initMidPanel() {
        /* Left Panel */
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        leftPanel.setBorder(BorderFactory.createTitledBorder(blackline, "Recipe Manager"));
        Border border = leftPanel.getBorder();
        Border margin = new EmptyBorder(10, 20, 70, 50);
        leftPanel.setBorder(new CompoundBorder(border, margin));
        btnReviewPlan = new JButton("Review Plan");
        btnAddRecipe = new JButton("Add Recipe");
        btnAddRecipe.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        leftPanel.add(btnAddRecipe);
        btnEditRecipe = new JButton("Edit Recipe");
        btnEditRecipe.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        leftPanel.add(btnEditRecipe);
        btnBrowseRecipe = new JButton("Browse Recipe");
        btnBrowseRecipe.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        leftPanel.add(btnBrowseRecipe);

        /* Right Panel */
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder(blackline, "Plan Manager"));
        border = rightPanel.getBorder();
        margin = new EmptyBorder(50, 10, 70, 25);
        rightPanel.setBorder(new CompoundBorder(border, margin));
        btnReviewPlan = new JButton("Review Plan");
        btnReviewPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(btnReviewPlan);
        btnCreatePlan = new JButton("Create Plan");
        btnCreatePlan.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        rightPanel.add(btnCreatePlan);

        /* Mid Panel */
        midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));
        midPanel.add(leftPanel);
        midPanel.add(Box.createRigidArea(new Dimension(200, 100)));
        midPanel.add(rightPanel);
    }

    private void initBottomPanel() {
        /* Bottom Panel */
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        Calendar now = Calendar.getInstance();
        JLabel time = new JLabel(dateFormat.format(now.getTime()));
        time.setBounds(100, 100, 125, 125);
        bottomPanel.add(time);

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                time.setText(dateFormat.format(now.getTime()));
            }
        }).start();
    }

    private void addActionListeners(Recipes newRecipes) {
        btnAddRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddRecipe addRecipe = new AddRecipe(newRecipes);
            }
        });

        btnBrowseRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BrowseRecipe browseRecipe = new BrowseRecipe(newRecipes);
            }
        });

        btnEditRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditRecipe editRecipe = new EditRecipe(newRecipes);
            }
        });
    }

    public static void main(String[] args) {

        Recipes recipes;
        Plans plans;

        /* Look for existing recipes */
        try {
            FileInputStream fileIn = new FileInputStream("recipes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recipes = (Recipes) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Existing recipes database found. Loading...");
        } catch (Exception e) {
            System.out.println("No recipes database found.");
            System.out.println("Creating new database...");
            recipes = new Recipes();
        }

        /* Look for existing plans */
        try {
            FileInputStream fileIn = new FileInputStream("plans.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            plans = (Plans) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Existing plan database found. Loading...");
        } catch (Exception e) {
            System.out.println("No plans database found.");
            System.out.println("Creating new database...");
            plans = new Plans();
        }


        Greeter greeter = new Greeter(recipes, plans);

/*
        try {
            FileOutputStream fileOut = new FileOutputStream("recipes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(recipes);
            out.close();
            fileOut.close();
            System.out.println("Recipes data saved in recipes.ser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("plans.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(plans);
            out.close();
            fileOut.close();
            System.out.println("Plans data saved in recipes.ser");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
*/
    }

    public void printOptions() {
        System.out.println("------------------------------------");
        System.out.println("Choose one of the following options : ");
        System.out.println("| 1. Browser the recipe database");
        System.out.println("| 2. Add a new recipe");
        System.out.println("| 3. Edit the existing recipe");
        System.out.println("| 4. Review the existing plan for meal");
        System.out.println("| 5. Create a new plan for meal");
        System.out.println("| 6. Exit");
        System.out.println("------------------------------------");
        System.out.print("Choice : ");
    }
}
