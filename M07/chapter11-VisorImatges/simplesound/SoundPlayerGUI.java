import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.*;

/**
 * A simple sound player. To start, create an instance of this class.
 * 
 * The sound player can play sound clips in WAV, AU and AIFF formats
 * with standard sample rates.
 * 
 * @author Michael Kolling and David J Barnes 
 * @version 1.0
 */
public class SoundPlayerGUI extends JFrame
    implements ChangeListener, ActionListener
{
    private static final String VERSION = "Version 1.0";
    private static final String AUDIO_DIR = "audio";
    
    private JList fileList;
    private JSlider slider;
    private JLabel infoLabel;
    private SoundEngine player;

    /**
     * Main method for starting the player from a command line.
     */
    public static void main(String[] args)
    {
        SoundPlayerGUI gui = new SoundPlayerGUI();
    }
    
    /**
     * Create a SoundPlayer and display its GUI on screen.
     */
    public SoundPlayerGUI()
    {
        super("SoundPlayer");
        player = new SoundEngine();
        String[] audioFileNames = findFiles(AUDIO_DIR, null);
        makeFrame(audioFileNames);
    }

    /**
     * Play the sound file currently selected in the file list. If there is no
     * selection in the list, or if the selected file is not a sound file, 
     * do nothing.
     */
    private void play()
    {
        String filename = (String)fileList.getSelectedValue();
        if(filename == null) {  // nothing selected
            return;
        }
        slider.setValue(0);
        boolean successful = player.play(new File(AUDIO_DIR, filename));
        if(successful) {
            showInfo(filename + " (" + player.getDuration() + " seconds)");
        }
        else {
            showInfo("Could not play file - unknown format");
        }
    }

    /**
     * Display information about a selected sound file (name and clip length).
     * @param message The message to display.
     */
    private void showInfo(String message)
    {
        infoLabel.setText(message);
    }
    
    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void stop()
    {
        player.stop();
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void pause()
    {
        player.pause();
    }

    /**
     * Resume a previously suspended sound file.
     */
    private void resume()
    {
        player.resume();
    }

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    
    /**
     * About function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(this, 
                    "SoundPlayer\n" + VERSION,
                    "About SoundPlayer", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Load the file names of all files in the given directory.
     * @param dirName Directory (folder) name.
     * @param suffix File suffix of interest.
     * @return The names of files found.
     */
    private String[] findFiles(String dirName, String suffix)
    {
        File dir = new File(dirName);
        if(dir.isDirectory()) {
            String[] allFiles = dir.list();
            if(suffix == null) {
                return allFiles;
            }
            else {
                List<String> selected = new ArrayList<String>();
                for(String filename : allFiles) {
                    if(filename.endsWith(suffix)) {
                        selected.add(filename);
                    }
                }
                return selected.toArray(new String[selected.size()]);
            }
        }
        else {
            System.out.println("Error: " + dirName + " must be a directory");
            return null;
        }
    }

    // ------- ChangeListener interface (for Slider) -------

    /**
     * ChangeListener method for slider changes. This method is called
     * when the slider value is changed by the user.
     * @param evt The event details.
     */
    public void stateChanged(ChangeEvent evt)
    {
        player.seek(slider.getValue());
    }
    
    // ------- ActionListener interface (for ComboBox) -------

    /**
     * ActionListener method for changes of format combo box.
     * When this methosd is called, the user has made a new selection 
     * in the combo box.
     * @param evt The event details.
     */
    public void actionPerformed(ActionEvent evt) 
    {
        JComboBox cb = (JComboBox)evt.getSource();
        String format = (String)cb.getSelectedItem();
        if(format.equals("all formats")) {
            format = null;
        }
        fileList.setListData(findFiles(AUDIO_DIR, format));
    }

    // ---- Swing stuff to build the frame and all its components and menus ----
    
    /**
     * Create the complete application GUI.
     * @param audioFiles The file names to display.
     */
    private void makeFrame(String[] audioFiles)
    {
        // the following makes sure that our application exits when
        // the user closes its window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 10, 10, 10));

        makeMenuBar();
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the left side with combobox and scroll list
        JPanel leftPane = new JPanel();
        {
            leftPane.setLayout(new BorderLayout(8, 8));

            String[] formats = { "all formats", ".wav", ".au", ".aif" };
    
            // Create the combo box.
            JComboBox formatList = new JComboBox(formats);
            formatList.addActionListener(this);
            leftPane.add(formatList, BorderLayout.NORTH);
    
            // Create the scrolled list for file names
            fileList = new JList(audioFiles);
            fileList.setForeground(new Color(140,171,226));
            fileList.setBackground(new Color(0,0,0));
            fileList.setSelectionBackground(new Color(87,49,134));
            fileList.setSelectionForeground(new Color(140,171,226));
            JScrollPane scrollPane = new JScrollPane(fileList);
            scrollPane.setColumnHeaderView(new JLabel("Audio files"));
            leftPane.add(scrollPane, BorderLayout.CENTER);
        }
        contentPane.add(leftPane, BorderLayout.CENTER);

        // Create the center with image, text label, and slider
       JPanel centerPane = new JPanel();
        {
            centerPane.setLayout(new BorderLayout(8, 8));
    
            JLabel image = new JLabel(new ImageIcon("title.jpg"));
            centerPane.add(image, BorderLayout.NORTH);
            centerPane.setBackground(Color.BLACK);

            infoLabel = new JLabel("  ");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setForeground(new Color(140,171,226));
            centerPane.add(infoLabel, BorderLayout.CENTER);

            slider = new JSlider(0, 100, 0);
            TitledBorder border = new TitledBorder("Seek");
            border.setTitleColor(Color.white);
            slider.setBorder(new CompoundBorder(new EmptyBorder(6, 10, 10, 10), border));
            slider.addChangeListener(this);
            slider.setBackground(Color.BLACK);
            slider.setMajorTickSpacing(25);
            slider.setPaintTicks(true);
            centerPane.add(slider, BorderLayout.SOUTH);
        }
        contentPane.add(centerPane, BorderLayout.EAST);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        {
            toolbar.setLayout(new GridLayout(1, 0));
  
            JButton button = new JButton("Play");
            button.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) { play(); }
                               });
            toolbar.add(button);
            
            button = new JButton("Stop");
            button.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) { stop(); }
                               });
            toolbar.add(button);
    
            button = new JButton("Pause");
            button.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) { pause(); }
                               });
            toolbar.add(button);
            
            button = new JButton("Resume");
            button.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) { resume(); }
                               });
            toolbar.add(button);
        }
        
        contentPane.add(toolbar, BorderLayout.NORTH);

        // building is done - arrange the components      
        pack();
        
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About SoundPlayer...");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showAbout(); }
                           });
        menu.add(item);
    }
}
