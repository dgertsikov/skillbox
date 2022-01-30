import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class MainForm {

    private JPanel mainPanel;
    private JButton button1;
    private JFormattedTextField formattedTextField1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel jpanel1;
    private JPanel jpanel2;

    public MainForm(){
        jpanel2.setVisible(false);
        button1.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] valueNames;
                if(button1.getText().equals("Collapse")){
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty()){
                        JOptionPane.showMessageDialog(
                                mainPanel, "Заполните Фамилию или Имя", "Сообщение", JOptionPane.PLAIN_MESSAGE);

                        return;
                    }
                    button1.setText("Expand");
                    textField4.setText(textField1.getText() + " " + textField2.getText() + " " + textField3.getText());
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    jpanel1.setVisible(false);
                    jpanel2.setVisible(true);
                }
                else{
                    valueNames = textField4.getText().split(" ");
                    if (valueNames.length < 2){
                        JOptionPane.showMessageDialog(
                                mainPanel, "Заполните Фамилию, Имя и Отчество", "Сообщение", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    textField1.setText(valueNames[0]);
                    textField2.setText(valueNames[1]);
                    textField3.setText(valueNames.length >=3 ? valueNames[2] : "");
                    textField4.setText("");
                    jpanel1.setVisible(true);
                    jpanel2.setVisible(false);
                    button1.setText("Collapse");

                }

            }
        });
    }
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
