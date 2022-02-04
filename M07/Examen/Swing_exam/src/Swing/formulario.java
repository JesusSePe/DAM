package Swing;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class formulario {

    // Main execution
    public static void main(String[] args) {
        new form();
    }
}

class form extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Properties
    static String name, city, gender;
    static ArrayList<String> hobbies;

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        form.name = name;
    }

    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        form.city = city;
    }

    public static String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        form.gender = gender;
    }

    public static ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        form.hobbies = hobbies;
    }

    // Main panels
    public form(){
        this.setTitle("Pequeño formulario");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(formPane());
        this.setSize(400, 200);

    }
    public JPanel formPane() {
        JTextField uname;
        JComboBox<String> ucity;
        JCheckBox golf, padel, tenis, petanca;
        ButtonGroup bg;


        // Declaring objects
        JPanel panel = new JPanel();
        // Defining panel as a group layout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        // Group layout settings
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        // Defining objects
        // First, labels
        JLabel lname = new JLabel("Nombre: ");
        JLabel lcity = new JLabel("Ciudad: ");
        JLabel lhobbies = new JLabel("Aficiones");
        JLabel lgender = new JLabel("Género");

        // Defining username text field
        uname = new JTextField();

        // Defining Cities dropdown
        String [] cities = {"Escoja una ciudad", "Barcelona", "Berlin", "Madrid", "Paris"};
        ucity = new JComboBox<>(cities);

        // Defining checkboxes
        golf = new JCheckBox("Golf");
        padel = new JCheckBox("Padel");
        tenis = new JCheckBox("Tenis");
        petanca = new JCheckBox("Petanca");

        // Defining radiobuttons
        bg = new ButtonGroup(); // Creating a new button group

        JRadioButton male = new JRadioButton("Hombre");
        male.setActionCommand("male");
        bg.add(male);

        JRadioButton female = new JRadioButton("Mujer");
        female.setActionCommand("female");
        bg.add(female);


        // Defining send button
        JButton send = new JButton("Enviar");
        send.addActionListener(e -> {

            ArrayList<String> checkHobbies = new ArrayList<>();
            int check = 0;
            // Get which checkboxes are selected
            if (golf.isSelected()) {
                checkHobbies.add("GOLF");
            } if (padel.isSelected()) {
                checkHobbies.add("PADEL");
            } if (tenis.isSelected()) {
                checkHobbies.add("TENIS");
            } if (petanca.isSelected()) {
                checkHobbies.add("PETANCA");
            }
            // Check if user name is defined
            if (uname.getText().length() != 0){
                check++;
            }
            // Check if user city is defined
            if (ucity.getSelectedIndex() != 0){
                check++;
            }
            // Check if user gender is defined
            if (bg.getSelection() != null){
                check ++;
            }
            // Control in case not all fields are
            if (check != 3) {
                JOptionPane.showConfirmDialog(null, "Faltan campos por rellenar", "Aviso", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            } else {
                // Set all properties
                setName(uname.getText());
                setCity(ucity.getSelectedItem().toString());
                //setGender(bg.getSelection().toString());
                setGender(bg.getElements().nextElement().getText());
                setHobbies(checkHobbies);
                // Open the new window
                this.dispose();
                JFrame frame2 = new JFrame("Resumen de datos");
                new dataPanel(frame2);
            }
        });

        // Time for some structuration
        // First we define the horizontal group
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lname)
                        .addComponent(lcity)
                        .addComponent(lhobbies)
                        .addComponent(golf)
                        .addComponent(tenis)
                        .addComponent(send))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(uname)
                        .addComponent(ucity)
                        .addComponent(padel)
                        .addComponent(petanca))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lgender)
                        .addComponent(male)
                        .addComponent(female))
        );
        // Something
        layout.linkSize(SwingConstants.HORIZONTAL, uname, ucity);
        // Now, vertical group
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lname)
                        .addComponent(uname))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lcity)
                        .addComponent(ucity))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lhobbies)
                        .addComponent(lgender))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(golf)
                        .addComponent(padel)
                        .addComponent(male))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tenis)
                        .addComponent(petanca)
                        .addComponent(female))
                .addComponent(send));

        return panel;
    }

}
class dataPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JFrame frame2;
	// Creating window
    public dataPanel(JFrame frame2) {
        frame2.setSize(300, 200);
        JPanel panel = new JPanel();
        frame2.add(panel);
        // Properties
        JTextArea info;

        info = new JTextArea("Nombre: " + getName() +"\n", 20, 20);
        info.append("Ciudad: " + form.getCity() +"\n");
        info.append("Aficiones: ");
        for (String hobby:form.getHobbies()) {
            info.append(hobby + ", ");
        }
        info.append("\n" + "Género: " + form.getGender());
        // TextArea settings
        info.setEditable(false);
        info.setFont(new Font("Serif", Font.ITALIC, 16));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(info);
        panel.setVisible(true);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

}