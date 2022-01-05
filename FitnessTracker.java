import com.sun.jdi.request.DuplicateRequestException;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class FitnessTracker extends JPanel {
    private static JFrame frame;
    String dayOfWeek;
    String dayOfWeek2;
    String dayOfWeek3;
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
    //tab 3:
    private final String[] choices3 = {"Select Day Of Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private JComboBox<String> comboBox3 = new JComboBox<>(choices3);
    private JButton viewData;
    private JButton setWeeklyGoal;
    int caloriesGoal = 0;
    int proteinGoal = 0;
    int carbsGoal = 0;
    private JButton showWeeklyStatistics;
    //tab 4:
    private final String[] choicesMET = {"Select MET (difficulty of task)",
            "1 MET (sitting idly)", "2 MET (light-intensity)", "3 MET (moderate-intensity)",
            "4 MET (moderate-intensity)", "5 MET (moderate-intensity)", "6 MET (moderate-intensity)",
            "7 MET (vigorous-intensity)", "8 MET (vigorous-intensity)", "9 MET (vigorous-intensity)"};
    private JComboBox<String> selectMET = new JComboBox<>(choicesMET);
    private JTextField enterDuration;
    private JTextField enterBodyWeight;
    private JButton enter2;
    private JTextField enterActivity;


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter1) {
                InputDailyNutrition();
            }
            if (e.getSource() == deleteData) {
                DeleteDailyNutrition();
            }
            if (e.getSource() == setWeeklyGoal) {
                SetWeeklyGoal();
            }
            if (e.getSource() == showWeeklyStatistics) {
                ShowWeeklyStatistics();
            }
            if (e.getSource() == viewData) {
                ViewData();
            }
            if (e.getSource() == enter2) {
                CaloriesBurnedCalculator();
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

            for (int i = 0; i < dailyInformation.size(); i++) {
                if (dailyInformation.get(i).toString().contains(dayOfWeek2)) {
                    dailyInformation.remove(dailyInformation.get(i));
                }
            }

            JOptionPane.showMessageDialog(null, "Data removed successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nutritional data for this day of the week is already empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public void SetWeeklyGoal() {
        try {
            String stringCaloriesGoal = (JOptionPane.showInputDialog(null, "What is your daily calorie goal? (kcal)", "Calorie Goal", JOptionPane.QUESTION_MESSAGE));
            if (stringCaloriesGoal == null) {
                throw new Exception();
            }
            caloriesGoal = Integer.parseInt(stringCaloriesGoal);
            if (caloriesGoal == 0) {
                throw new NumberFormatException();
            }
            String stringProteinGoal = (JOptionPane.showInputDialog(null, "What is your daily protein goal? (g)", "Protein Goal", JOptionPane.QUESTION_MESSAGE));
            if (stringProteinGoal == null) {
                throw new Exception();
            }
            proteinGoal = Integer.parseInt(stringProteinGoal);
            if (proteinGoal == 0) {
                throw new NumberFormatException();
            }
            String stringCarbsGoal = (JOptionPane.showInputDialog(null, "What is your daily carbohydrate goal? (g)", "Carbohydrate Goal", JOptionPane.QUESTION_MESSAGE));
            if (stringCarbsGoal == null) {
                throw new Exception();
            }
            carbsGoal = Integer.parseInt(stringCarbsGoal);
            if (carbsGoal == 0) {
                throw new NumberFormatException();
            }

            JOptionPane.showMessageDialog(null, "Weekly goal set successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.out.println("it worked");
        }
        System.out.println(caloriesGoal + " " + proteinGoal + " " + carbsGoal);
    }

    public void ShowWeeklyStatistics() {
        int totalCalories = 0;
        int totalProtein = 0;
        int totalCarbs = 0;
        int avgCalories = 0;
        int avgProtein = 0;
        int avgCarbs = 0;
        String statisticsString = "";

        try {
            //average calories, protein, carbs
            if (dailyInformation.isEmpty()) {
                throw new Exception();
            }
            for (int i = 0; i < dailyInformation.size(); i++) {
                totalCalories += dailyInformation.get(i).calories;
                totalProtein += dailyInformation.get(i).protein;
                totalCarbs += dailyInformation.get(i).carbs;
            }
            avgCalories = totalCalories / dailyInformation.size();
            avgProtein = totalProtein / dailyInformation.size();
            avgCarbs = totalCarbs / dailyInformation.size();

            // if they inputted their weekly goals, say whether this is over or under their goal, otherwise tell them to input their weekly goals
            if (caloriesGoal == 0 || proteinGoal == 0 || carbsGoal == 0) {
                statisticsString = "Here are your weekly statistics:\n" +
                        "Average Calories: " + avgCalories +
                        "\nAverage Protein: " + avgProtein +
                        "\nAverage Carbohydrates: " + avgCarbs +
                        "\nEnter your weekly goals to see if you were near them!";
            } else {
                String calorieStr = "";
                String proteinStr = "";
                String carbsStr = "";

                if (avgCalories > caloriesGoal) {
                    calorieStr = "You were over your weekly calorie goal by " + (avgCalories - caloriesGoal) + " calorie(s).";
                } else if (avgCalories < caloriesGoal) {
                    calorieStr = "You were under your weekly calorie goal by " + (caloriesGoal - avgCalories) + " calorie(s).";
                } else {
                    calorieStr = "Your weekly calorie goal was met.";
                }

                if (avgProtein > proteinGoal) {
                    proteinStr = "You were over your weekly protein goal by " + (avgProtein - proteinGoal) + " grams(s).";
                } else if (avgProtein < proteinGoal) {
                    proteinStr = "You were under your weekly protein goal by " + (proteinGoal - avgProtein) + " gram(s).";
                } else {
                    proteinStr = "Your weekly protein goal was met.";
                }

                if (avgCarbs > carbsGoal) {
                    carbsStr = "You were over your weekly carbohydrate goal by " + (avgCarbs - carbsGoal) + " gram(s).";
                } else if (avgCarbs < carbsGoal) {
                    carbsStr = "You were under your weekly carbohydrate goal by " + (carbsGoal - avgCarbs) + " gram(s).";
                } else {
                    carbsStr = "Your weekly carbohydrate goal was met.";
                }

                statisticsString = "Here are your weekly statistics:\n" +
                        "Average Calories: " + avgCalories + "\n" + calorieStr +
                        "\nAverage Protein: " + avgProtein + "\n" + proteinStr +
                        "\nAverage Carbohydrates: " + avgCarbs + "\n" + carbsStr;
            }
            JOptionPane.showMessageDialog(null, statisticsString, "Weekly Statistics", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You have not inputted any nutritional data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ViewData() {
        String viewData = "";
        int retrievedCalories = 0;
        int retrievedProtein = 0;
        int retrievedCarbs = 0;

        try {
            dayOfWeek3 = (String) comboBox3.getSelectedItem();
            if (dayOfWeek3.equals("Select Day Of Week")) {
                throw new IllegalArgumentException();
            }

            if (!dailyInformation.toString().contains(dayOfWeek3)) {
                throw new Exception();
            }

            for (int i = 0; i < dailyInformation.size(); i++) {
                if (dailyInformation.get(i).toString().contains(dayOfWeek3)) {
                    retrievedCalories = dailyInformation.get(i).calories;
                    retrievedProtein = dailyInformation.get(i).protein;
                    retrievedCarbs = dailyInformation.get(i).carbs;
                }
            }

            viewData = "On " + dayOfWeek3 + ", you consumed:\n" + retrievedCalories + " calories\n" +
                    retrievedProtein + " grams of protein\n" +
                    retrievedCarbs + " grams of carbohydrates";

            JOptionPane.showMessageDialog(null, viewData, "View Day-Specific Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nutritional data for this day of the week is empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CaloriesBurnedCalculator() {
        try {
            String activity = enterActivity.getText();
            System.out.println(activity);
            if (activity.isBlank()) {
                throw new IOException();   //i need to change this and make my own exception lol
            }

            int duration = Integer.parseInt(enterDuration.getText());
            int bodyWeight = Integer.parseInt(enterBodyWeight.getText());

            String choiceMET = (String) selectMET.getSelectedItem();
            if (choiceMET.equals("Select MET (difficulty of task)")) {
                throw new Exception();
            }
            char num = choiceMET.charAt(0);
            int intMET = Integer.parseInt(String.valueOf(num));
            System.out.println(intMET);

            double caloriesBurned = duration * (intMET * 3.5 * bodyWeight) / 200;
            System.out.println(caloriesBurned);

            String displayCaloriesBurned = "You burned " + caloriesBurned + " calories while " + activity + ".";
            JOptionPane.showMessageDialog(null, displayCaloriesBurned, "Calories Burned", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException g) {
            JOptionPane.showMessageDialog(null, "Please enter the activity's name and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a valid option and try again.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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

        ///////////
        JComponent ViewWeeklyData = new JPanel();
        ViewWeeklyData.setLayout(null);
        comboBox3.setBounds(225, 125, 140, 25);
        ViewWeeklyData.add(comboBox3);
        viewData = new JButton("View Data");
        viewData.addActionListener(actionListener);
        viewData.setBounds(225, 175, 140, 25);
        ViewWeeklyData.add(viewData);
        setWeeklyGoal = new JButton("Set Weekly Goals");
        setWeeklyGoal.addActionListener(actionListener);
        setWeeklyGoal.setBounds(225, 25, 140, 25);
        ViewWeeklyData.add(setWeeklyGoal);
        showWeeklyStatistics = new JButton("View Statistics");
        showWeeklyStatistics.addActionListener(actionListener);
        showWeeklyStatistics.setBounds(225, 75, 140, 25);
        ViewWeeklyData.add(showWeeklyStatistics);
        tabbedPane.addTab("View Weekly Data", ViewWeeklyData);

        ///////////
        JComponent CaloriesBurned = new JPanel();
        CaloriesBurned.setLayout(null);
        selectMET.setBounds(200, 125, 200, 25);
        CaloriesBurned.add(selectMET);
        enterDuration = new JTextField("Enter Duration (minutes)", 15);
        enterDuration.addActionListener(actionListener);
        enterDuration.setBounds(225, 75, 140, 25);
        CaloriesBurned.add(enterDuration);
        enterBodyWeight = new JTextField("Enter Body Weight (kg)", 15);
        enterBodyWeight.addActionListener(actionListener);
        enterBodyWeight.setBounds(225, 175, 140, 25);
        CaloriesBurned.add(enterBodyWeight);
        enter2 = new JButton("Enter");
        enter2.addActionListener(actionListener);
        enter2.setBounds(225, 225, 140, 25);
        CaloriesBurned.add(enter2);
        enterActivity = new JTextField("Activity Name");
        enterActivity.addActionListener(actionListener);
        enterActivity.setBounds(225, 25, 140, 25);
        CaloriesBurned.add(enterActivity);
        tabbedPane.addTab("Calories Burned Calculator", CaloriesBurned);

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
