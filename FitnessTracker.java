import com.sun.jdi.request.DuplicateRequestException;

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
    String dayOfWeek2;
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
    //tab 2:
    private final String[] choices2 = {"Select Day Of Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private JComboBox<String> comboBox2 = new JComboBox<>(choices2);
    private JButton deleteData;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter1) {
                InputDailyNutrition();
            }
            if (e.getSource() == deleteData) {
                DeleteDailyNutrition();
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

            //throws exception about duplicate day of week
            for (int i = 0; i < dailyInformation.size(); i++) {
                if (dailyInformation.get(i).toString().contains(dayOfWeek)) {
                    throw new DuplicateRequestException();
                }
            }
            dailyInformation.add(new FitnessTracker(dayOfWeek, calories, protein, carbs));
            System.out.println(dailyInformation.toString());

            JOptionPane.showMessageDialog(null, "Data added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DuplicateRequestException e) {
            JOptionPane.showMessageDialog(null, "Nutritional information for this day of the week is already full.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DeleteDailyNutrition() {
        try {
            dayOfWeek2 = (String) comboBox2.getSelectedItem();
            if (dayOfWeek2.equals("Select Day Of Week")) {
                throw new IllegalArgumentException();
            }

            if (!dailyInformation.toString().contains(dayOfWeek2)) {
                throw new Exception();
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nutritional data for this day of the week is empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        for (int i = 0; i < dailyInformation.size(); i++) {
            if (dailyInformation.get(i).toString().contains(dayOfWeek2)) {
                dailyInformation.remove(dailyInformation.get(i));
            }
        }

        JOptionPane.showMessageDialog(null, "Data removed successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public FitnessTracker() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();

        ///////////
        JComponent InputDailyNutrition = new JPanel();
        InputDailyNutrition.setLayout(null);
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

        ///////////
        JComponent DeleteDailyNutrition = new JPanel();
        DeleteDailyNutrition.setLayout(null);
        comboBox2.setBounds(225, 25, 140, 25);
        DeleteDailyNutrition.add(comboBox2);
        deleteData = new JButton("Delete Data");
        deleteData.addActionListener(actionListener);
        deleteData.setBounds(225, 75, 140, 25);
        DeleteDailyNutrition.add(deleteData);
        tabbedPane.addTab("Delete Daily Nutrition", DeleteDailyNutrition);


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
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

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
