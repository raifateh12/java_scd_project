import java.awt.EventQueue;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Convertor {

    private JFrame frame;
    private JTextField myText;
    private JLabel lblNewLabel_1;
    private final ButtonGroup group_from = new ButtonGroup();
    private final ButtonGroup group_to = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Convertor window = new Convertor();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Convertor() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 499, 333);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        myText = new JTextField();
        myText.setBounds(32, 27, 325, 19);
        frame.getContentPane().add(myText);
        myText.setColumns(10);

        JLabel lblNewLabel = new JLabel("From");
        lblNewLabel.setBounds(42, 60, 61, 26);
        frame.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("To");
        lblNewLabel_1.setBounds(246, 67, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JRadioButton rb_from_cm = new JRadioButton("cm");
        group_from.add(rb_from_cm);
        rb_from_cm.setBackground(new Color(240, 240, 240));
        rb_from_cm.setBounds(32, 92, 103, 21);
        frame.getContentPane().add(rb_from_cm);

        JRadioButton rb_from_inches = new JRadioButton("inches");
        group_from.add(rb_from_inches);
        rb_from_inches.setBounds(32, 115, 103, 21);
        frame.getContentPane().add(rb_from_inches);

        JRadioButton rb_from_foot = new JRadioButton("foot");
        group_from.add(rb_from_foot);
        rb_from_foot.setBounds(32, 138, 103, 21);
        frame.getContentPane().add(rb_from_foot);

        JRadioButton rb_to_cm = new JRadioButton("cm");
        group_to.add(rb_to_cm);
        rb_to_cm.setBackground(UIManager.getColor("Button.background"));
        rb_to_cm.setBounds(246, 92, 103, 21);
        frame.getContentPane().add(rb_to_cm);

        JRadioButton rb_to_inches = new JRadioButton("inches");
        group_to.add(rb_to_inches);
        rb_to_inches.setBounds(246, 115, 103, 21);
        frame.getContentPane().add(rb_to_inches);

        JRadioButton rb_to_foot = new JRadioButton("foot");
        group_to.add(rb_to_foot);
        rb_to_foot.setBounds(246, 138, 103, 21);
        frame.getContentPane().add(rb_to_foot);

        JLabel notice = new JLabel("Please insert a number and mark your choice");
        notice.setBounds(42, 254, 279, 13);
        frame.getContentPane().add(notice);

        // Add action listeners to handle the enabling/disabling of the "To" units based on the "From" selection
        rb_from_cm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "From" is cm
                rb_to_cm.setEnabled(false);  // Disable "To" cm
                rb_to_inches.setEnabled(true);  // Enable "To" inches
                rb_to_foot.setEnabled(true);   // Enable "To" foot
                group_to.clearSelection();     // Clear any previous "To" selection
            }
        });

        rb_from_inches.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "From" is inches
                rb_to_cm.setEnabled(true);     // Enable "To" cm
                rb_to_inches.setEnabled(false); // Disable "To" inches
                rb_to_foot.setEnabled(true);    // Enable "To" foot
                group_to.clearSelection();      // Clear any previous "To" selection
            }
        });

        rb_from_foot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "From" is foot
                rb_to_cm.setEnabled(true);      // Enable "To" cm
                rb_to_inches.setEnabled(true);  // Enable "To" inches
                rb_to_foot.setEnabled(false);   // Disable "To" foot
                group_to.clearSelection();      // Clear any previous "To" selection
            }
        });

        JButton bt_convert = new JButton("Convert");
        bt_convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float initial_length, converted_length;
                    initial_length = Float.parseFloat(myText.getText().toString());

                    String fromUnit = "", toUnit = "", resultMessage = "";

                    if (rb_from_cm.isSelected()) {
                        fromUnit = "cm";
                        if (rb_to_inches.isSelected()) {
                            // Convert cm to inches
                            converted_length = initial_length / 2.54f;  // 1 inch = 2.54 cm
                            toUnit = "inches";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else if (rb_to_foot.isSelected()) {
                            // Convert cm to foot
                            converted_length = initial_length / 30.48f; // 1 foot = 30.48 cm
                            toUnit = "foot";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else {
                            notice.setText("Please make your choice.");
                        }
                    } else if (rb_from_inches.isSelected()) {
                        fromUnit = "inches";
                        if (rb_to_cm.isSelected()) {
                            // Convert inches to cm
                            converted_length = initial_length * 2.54f;  // 1 inch = 2.54 cm
                            toUnit = "cm";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else if (rb_to_foot.isSelected()) {
                            // Convert inches to foot
                            converted_length = initial_length / 12f;   // 1 foot = 12 inches
                            toUnit = "foot";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else {
                            notice.setText("Please make your choice.");
                        }
                    } else if (rb_from_foot.isSelected()) {
                        fromUnit = "foot";
                        if (rb_to_cm.isSelected()) {
                            // Convert foot to cm
                            converted_length = initial_length * 30.48f; // 1 foot = 30.48 cm
                            toUnit = "cm";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else if (rb_to_inches.isSelected()) {
                            // Convert foot to inches
                            converted_length = initial_length * 12f;   // 1 foot = 12 inches
                            toUnit = "inches";
                            resultMessage = String.format("%.2f %s = %.2f %s", initial_length, fromUnit, converted_length, toUnit);
                            myText.setText(resultMessage);
                        } else {
                            notice.setText("Please make your choice.");
                        }
                    } else {
                        notice.setText("Please make your choice.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error during conversion");
                }
            }
        });
        bt_convert.setBounds(31, 186, 85, 21);
        frame.getContentPane().add(bt_convert);

        JButton bt_clear = new JButton("Clear");
        bt_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Clear all fields and selections
                    myText.setText("");
                    group_from.clearSelection();
                    group_to.clearSelection();
                    notice.setText("Please insert a number and mark your choice");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error during clearing");
                }
            }
        });
        bt_clear.setBounds(32, 217, 85, 21);
        frame.getContentPane().add(bt_clear);
    }
}
