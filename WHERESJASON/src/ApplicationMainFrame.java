import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ApplicationMainFrame extends JFrame implements ActionListener, MouseListener {

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JPopupMenu rightClickMenu;
    JMenuItem newElementItem;
    JMenuItem editElementItem;
    JMenuItem removeElementItem;
    JTree jsonTree;
    JsonElement jsonElement;

    ApplicationMainFrame(){
        // Init files
        this.setTitle("WheresJASON");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(720, 720);
        this.setLayout(new FlowLayout());

        // Code for Menu

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Open Files
        this.setJMenuBar(menuBar);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("No JSON Loaded");
        jsonTree = new JTree(new DefaultTreeModel(root));
        jsonTree.setPreferredSize(new Dimension(700,600));
        this.add(new JScrollPane(jsonTree));
        this.setVisible(true);

        //Right-Click Menu
        rightClickMenu = new JPopupMenu();

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem){
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                // Load JSON File
                try {
                    JsonElement jsonElement = readJSONFile(file);
                    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
                    createTreeNodes(root, jsonElement);
                    // Update the JTree root
                    jsonTree.setModel(new DefaultTreeModel(root));
                } catch (FileNotFoundException err){
                    JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
                // createTreeNodes(root, jsonElement);
                // jsonTree = new JTree(new DefaultTreeModel(root));
                // jsonTree.setPreferredSize(new Dimension(700,600));
                // this.add(new JScrollPane(jsonTree));
                
            }
        }
        if (e.getSource() == saveItem){
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }
        if (e.getSource() == exitItem){
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    public JsonElement readJSONFile(File filePath) throws FileNotFoundException {
        FileReader reader = new FileReader(filePath);
        return JsonParser.parseReader(reader);
    }

    public void createTreeNodes(DefaultMutableTreeNode parent, JsonElement element){
        if (element.isJsonObject()){
            JsonObject jsonObject = element.getAsJsonObject();
            for (String key: jsonObject.keySet()){
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(key);
                parent.add(child);
                createTreeNodes(child, jsonObject.get(key));
            }
        }else if (element.isJsonArray()) {
            JsonArray jsonArray = element.getAsJsonArray();
            int index = 0;
            for (JsonElement arrayElement : jsonArray) {
                DefaultMutableTreeNode arrayNode = new DefaultMutableTreeNode("[" + index + "]");
                parent.add(arrayNode);
                createTreeNodes(arrayNode, arrayElement);
                index++;
            }
        } else {
            // If it's a primitive type (string, number, boolean, etc.), display the value
            parent.add(new DefaultMutableTreeNode(element.getAsString()));
        }
    }
    
}
