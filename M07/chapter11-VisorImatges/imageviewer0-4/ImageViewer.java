import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ImageViewerFirst is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kolling and David J Barnes 
 * @version 0.4
 */
public class ImageViewer
{
    private JFrame frame;
    private ImagePanel imagePanel;

    public JLabel status;

    public void setStatus(String newStatus) {
        this.status.setText(newStatus);
    }

    /**
     * Create an ImageViewerFirst show it on screen.
     */
    public ImageViewer()
    {
        makeFrame();
    }


    // ---- implementation of menu functions ----
    
    /**
     * Open function: open a file chooser to select a new image file.
     */
    private void openFile()
    {
        OFImage image = ImageFileManager.getImage();
        imagePanel.setImage(image);
        frame.pack();
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {


        frame = new JFrame("ImageViewerFirst");
        makeMenuBar(frame);
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        JLabel fileName = new JLabel();
        contentPane.add(fileName);

        JLabel lab = new JLabel();
        contentPane.add(lab);

        imagePanel = new ImagePanel();
        contentPane.add(imagePanel);

        status = new JLabel("Now with more b̶̟̝̺̪̺͖̖͉̹̊͊͑̃̃̆͒̔͛̚ủ̷̲̗̪̫͖̖͖͔̙̌̋́͆͂͑̂̄̌̾̏̈́̎͘̕͘g̵̣̲̝̖͖̜̖͌̉̀͂͌̀̓̄̀͑͂̀̕͜͠s̸̢̧̭͖̣͇͔̞̘̘̭̭̫̮̻͚̱͈̻̞͙̖̻̬̥̰̦̄̔̈́͋̇̐̈́͊̎̐̈͌̔ !");
        contentPane.add(status);

        /*imagePanel = new ImagePanel();
        contentPane.add(imagePanel);*/

        // building is done - arrange the components and show        
        frame.pack();
        frame.setVisible(true);

    }
    
    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File manu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        // create the Help menu
        JMenu helpMenu = new JMenu("Ayuda");
        menubar.add(helpMenu);

        // create the Recording menu
        JMenu recMenu = new JMenu("Grabar");
        menubar.add(recMenu);

        // create the filters menu
        JMenu filterMenu = new JMenu("Filtros");
        menubar.add(filterMenu);

        // File menu options
        JMenuItem openItem = new JMenuItem("Open");
            openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            abrirArchivo aa = new abrirArchivo();
            openItem.addActionListener(aa);
        fileMenu.add(openItem);

        JMenuItem quitItem = new JMenuItem("Quit");
            quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            salir sal = new salir();
            quitItem.addActionListener(sal);
        fileMenu.add(quitItem);

        // Help menu options
        JMenuItem aboutItem = new JMenuItem("Sobre el Visor de Imágenes");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mucha gente colaborando. Ayuda. Hjälp. Vesion Undefined.null (beta)");
            }
        });
        helpMenu.add(aboutItem);

        // Recording menu options
        JMenuItem recItem = new JMenuItem("Comenzar a grabar");
        grabarArchivo ga = new grabarArchivo();
        recItem.addActionListener(ga);
        recMenu.add(recItem);

        // Filters menu options
        // Oscuro
        JMenuItem umbreon = new JMenuItem("Aplicar Oscuro");
        aplicarOscuro ao = new aplicarOscuro();
        umbreon.addActionListener(ao);
        filterMenu.add(umbreon);
        // Claro
        JMenuItem light = new JMenuItem("Aplicar Claro");
        aplicarClaro ac = new aplicarClaro();
        light.addActionListener(ac);
        filterMenu.add(light);
        // Umbral
        JMenuItem umbral = new JMenuItem("Aplicar Umbral");
        aplicarUmbral au = new aplicarUmbral();
        umbral.addActionListener(au);
        filterMenu.add(umbral);
    }

    private class abrirArchivo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            openFile();
            System.out.println("Opening da file");
        }
    }
    private class grabarArchivo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("RECORDING");
        }
    }
    private class salir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Shutting down noises");
            quit();
        }
    }

    private class NewPane {
        public void setLabelText(JLabel label, String text){
            label.setText(text);
        }
    }

    private class aplicarOscuro implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(imagePanel != null){
                //imagePanel.dark();
                frame.repaint();
                setStatus("Everything, everything BLACK!");
            } else {
                setStatus("Error 404: Image not found");
            }
        }
    }

    private class aplicarClaro implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(imagePanel != null){
                //imagePanel.clear();
                frame.repaint();
                setStatus("HÁGASE LA LUZ!");
            } else {
                setStatus("Error 404: Image not found");
            }
            //System.out.println("HÁGASE LA LUZ!");
        }
    }

    private class aplicarUmbral implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(imagePanel != null){
                //imagePanel.umbral();
                frame.repaint();
                setStatus("Umbral aplicado");
            } else {
                setStatus("Error 404: Image not found");
            }
            //System.out.println("Umbral aplicado");
        }
    }
}
