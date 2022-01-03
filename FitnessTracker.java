import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FitnessTracker extends JPanel {
    private static JFrame frame;
    //tab 1:
    private JTextField enterCalories;
    private JTextField enterProtein;
    private JTextField enterCarbohydrates;
    private JButton enter1;


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == homepage1 || e.getSource() == homepage2 || e.getSource() == homepage3 || e.getSource() == homepage4 || e.getSource() == homepage5) {
//
//            }
        }
    };

    public FitnessTracker() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent InputDailyNutrition = new JPanel();
        InputDailyNutrition.setLayout(null);

        String[] choices = {"Select Day Of Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        final JComboBox<String> comboBox = new JComboBox<>(choices);
        InputDailyNutrition.add(comboBox);
        enterCalories = new JTextField("Enter Calories (kcal)", 15);
        enterCalories.addActionListener(actionListener);
        enterCalories.setBounds(100, 100, 140, 25); //THIS IS WHAT I WAS LOOKING FOR
        InputDailyNutrition.add(enterCalories);
        enterProtein = new JTextField("Enter Protein (g)", 15);
        enterProtein.addActionListener(actionListener);
        InputDailyNutrition.add(enterProtein);
        enterCarbohydrates = new JTextField("Enter Carbohydrates (g)", 15);
        enterCarbohydrates.addActionListener(actionListener);
        InputDailyNutrition.add(enterCarbohydrates);
        enter1 = new JButton("Enter");
        enter1.addActionListener(actionListener);
        InputDailyNutrition.add(enter1);
        tabbedPane.addTab("Input Daily Nutrition", InputDailyNutrition);

        JComponent test = new JPanel();
        tabbedPane.addTab("test tab", test);



        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
