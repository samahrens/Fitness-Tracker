import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FitnessTracker extends JPanel {
    private static JFrame frame;
    String dayOfWeek;
    int calories;
    int protein;
    int carbs;
    private static ArrayList<FitnessTracker> dailyInformation = new ArrayList<>();
    //tab 1:
    private final String[] choices = {"Select Day Of Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private JComboBox<String> comboBox = new JComboBox<>(choices);
    private JTextField enterCalories;
    private JTextField enterProtein;
    private JTextField enterCarbohydrates;
    private JButton enter1;


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter1) {
                InputDailyNutrition();
            }
        }
    };

    public void InputDailyNutrition() {
        try {
            //throws Exception
            dayOfWeek = (String) comboBox.getSelectedItem();
            if (dayOfWeek.equals("Select Day Of Week")) {
                throw new Exception();
            }
            //throws NumberFormatException
            calories = Integer.parseInt(enterCalories.getText());
            protein = Integer.parseInt(enterProtein.getText());
            carbs = Integer.parseInt(enterCarbohydrates.getText());
            dailyInformation.add(new FitnessTracker(dayOfWeek, calories, protein, carbs));
            System.out.println(dailyInformation.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
//        try {
//            calories = Integer.parseInt(enterCalories.getText());
//            protein = Integer.parseInt(enterProtein.getText());
//            carbs = Integer.parseInt(enterCarbohydrates.getText());
//            dailyInformation.add(new FitnessTracker(dayOfWeek, calories, protein, carbs));
//            System.out.println(dailyInformation.toString());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Please enter a number and try again.", "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }

    public FitnessTracker() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent InputDailyNutrition = new JPanel();
        InputDailyNutrition.setLayout(null);

        String[] choices = {"Select Day Of Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//        final JComboBox<String> comboBox = new JComboBox<>(choices);
        comboBox.setBounds(225, 25, 140, 25);
        InputDailyNutrition.add(comboBox);
        enterCalories = new JTextField("Enter Calories (kcal)", 15);
        enterCalories.addActionListener(actionListener);
        enterCalories.setBounds(225, 75, 140, 25);
        InputDailyNutrition.add(enterCalories);
        enterProtein = new JTextField("Enter Protein (g)", 15);
        enterProtein.addActionListener(actionListener);
        enterProtein.setBounds(225, 125, 140, 25);
        InputDailyNutrition.add(enterProtein);
        enterCarbohydrates = new JTextField("Enter Carbohydrates (g)", 15);
        enterCarbohydrates.addActionListener(actionListener);
        enterCarbohydrates.setBounds(225, 175, 140, 25);
        InputDailyNutrition.add(enterCarbohydrates);
        enter1 = new JButton("Enter");
        enter1.addActionListener(actionListener);
        enter1.setBounds(225, 225, 140, 25);
        InputDailyNutrition.add(enter1);
        tabbedPane.addTab("Input Daily Nutrition", InputDailyNutrition);

        JComponent test = new JPanel();
        tabbedPane.addTab("test tab", test);



        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public FitnessTracker(String dayOfWeek, int calories, int protein, int carbs) {
        this.dayOfWeek = dayOfWeek;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return dayOfWeek + ", " + calories + ", " + protein + ", " + carbs;
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Fitness Tracker");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                JFrame frame = (JFrame)e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "test",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
//                    choice = "quit";
                    JOptionPane.showMessageDialog(null, "test", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        frame.add(new FitnessTracker(), BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

}
