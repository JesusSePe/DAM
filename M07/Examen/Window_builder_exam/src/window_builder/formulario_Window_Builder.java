package window_builder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class formulario_Window_Builder {

	private JFrame frame;
	private static JTextField nombre;
	private JComboBox ciudad;
	
	static String name, city;
    static ArrayList<String> hobbies;

    // Setters and Getters
    public static String getName() {
        return nombre.getText();
    }

    public void setName(String name) {
        nombre.setText(name);
    }

    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formulario_Window_Builder window = new formulario_Window_Builder();
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
	public formulario_Window_Builder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel_nombre_ciudad = new JPanel();
		frame.getContentPane().add(panel_nombre_ciudad, BorderLayout.NORTH);
		panel_nombre_ciudad.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_nombre = new JPanel();
		FlowLayout fl_panel_nombre = (FlowLayout) panel_nombre.getLayout();
		fl_panel_nombre.setAlignment(FlowLayout.LEFT);
		panel_nombre_ciudad.add(panel_nombre, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre:");
		panel_nombre.add(lblNombre);
		
		nombre = new JTextField();
		panel_nombre.add(nombre);
		nombre.setColumns(10);
		
		JPanel panel_ciudad = new JPanel();
		FlowLayout fl_panel_ciudad = (FlowLayout) panel_ciudad.getLayout();
		fl_panel_ciudad.setAlignment(FlowLayout.LEFT);
		panel_nombre_ciudad.add(panel_ciudad, BorderLayout.SOUTH);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		panel_ciudad.add(lblCiudad);
		
		String [] cities = {"Escoja una ciudad", "Barcelona", "Berlin", "Madrid", "Paris"};
		ciudad = new JComboBox(cities);
		panel_ciudad.add(ciudad);
		
		JPanel panel_aficiones = new JPanel();
		frame.getContentPane().add(panel_aficiones, BorderLayout.WEST);
		panel_aficiones.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_aficiones_label = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_aficiones_label.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_aficiones.add(panel_aficiones_label, BorderLayout.NORTH);
		
		JLabel lblAficiones = new JLabel("Aficiones");
		panel_aficiones_label.add(lblAficiones);
		
		JPanel panel_aficiones_checkboxes = new JPanel();
		panel_aficiones.add(panel_aficiones_checkboxes, BorderLayout.WEST);
		
		JCheckBox chckbxGolf = new JCheckBox("Golf");
		panel_aficiones_checkboxes.setLayout(new GridLayout(0, 2, 0, 0));
		panel_aficiones_checkboxes.add(chckbxGolf);
		
		JCheckBox chckbxPadel = new JCheckBox("Padel");
		panel_aficiones_checkboxes.add(chckbxPadel);
		
		JCheckBox chckbxTenis = new JCheckBox("Tenis");
		panel_aficiones_checkboxes.add(chckbxTenis);
		
		JCheckBox chckbxPetanca = new JCheckBox("Petanca");
		panel_aficiones_checkboxes.add(chckbxPetanca);
		
		JLabel label = new JLabel("");
		panel_aficiones_checkboxes.add(label);
		
		JLabel label_1 = new JLabel("");
		panel_aficiones_checkboxes.add(label_1);
		
		JPanel panel_genero = new JPanel();
		frame.getContentPane().add(panel_genero, BorderLayout.CENTER);
		panel_genero.setLayout(new BorderLayout(0, 0));
		
		JPanel genero_label = new JPanel();
		panel_genero.add(genero_label, BorderLayout.NORTH);
		
		JLabel lblGenero = new JLabel("Género:");
		genero_label.add(lblGenero);
		
		JPanel genero_radios = new JPanel();
		FlowLayout fl_genero_radios = (FlowLayout) genero_radios.getLayout();
		fl_genero_radios.setAlignment(FlowLayout.RIGHT);
		panel_genero.add(genero_radios, BorderLayout.CENTER);
		
		// Defining radiobuttons
        ButtonGroup bg = new ButtonGroup(); // Creating a new button group

        JRadioButton male = new JRadioButton("Hombre");
        male.setActionCommand("male");
        bg.add(male);

        JRadioButton female = new JRadioButton("Mujer");
        female.setActionCommand("female");
        bg.add(female);
        
        //genero_radios.add(bg);
        
        
        JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(e -> {

            ArrayList<String> checkHobbies = new ArrayList<>();
            int check = 0;
            // Get which checkboxes are selected
            if (chckbxGolf.isSelected()) {
                checkHobbies.add("GOLF");
            } if (chckbxPadel.isSelected()) {
                checkHobbies.add("PADEL");
            } if (chckbxTenis.isSelected()) {
                checkHobbies.add("TENIS");
            } if (chckbxPetanca.isSelected()) {
                checkHobbies.add("PETANCA");
            }
            // Check if user name is defined
            if (nombre.getText().length() != 0){
                check++;
            }
            // Check if user city is defined
            if (ciudad.getSelectedIndex() != 0){
                check++;
            }

            // Control in case not all fields are
            if (check != 2) {
                JOptionPane.showConfirmDialog(null, "Faltan campos por rellenar", "Aviso", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            } else {
                // Set all properties
                setName(nombre.getText());
                setCity(ciudad.getSelectedItem().toString());
                //setGender(bg.getSelection().toString());
                //setGender(bg.getElements().nextElement().getText());
                setHobbies(checkHobbies);
                // Open the new window
                //this.dispose();
                JFrame frame2 = new JFrame("Resumen de datos");
                new dataPanel2(frame2);
            }
        });
		
		frame.getContentPane().add(btnEnviar, BorderLayout.SOUTH);
        
	}
}

class dataPanel2 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JFrame frame2;
	// Creating window
    public dataPanel2(JFrame frame2) {
        frame2.setSize(300, 200);
        JPanel panel = new JPanel();
        frame2.add(panel);
        // Properties
        JTextArea info;

        info = new JTextArea("Nombre: " + formulario_Window_Builder.getName() +"\n", 20, 20);
        info.append("Ciudad: " + formulario_Window_Builder.getCity() +"\n");
        info.append("Aficiones: ");
        for (String hobby:formulario_Window_Builder.getHobbies()) {
            info.append(hobby + ", ");
        }
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


