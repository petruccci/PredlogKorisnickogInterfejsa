package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PredlogKorisnickogInterfejsa extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton buttonSave;
    private JButton buttonOpen;
    private JButton topNewButton;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;

    private JButton buttonGetSelection;
    String directory; // The default directory to display in the FileDialog
    String selection;
    private JButton buttonOpen1;
    private JButton bottomNewButton;

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        buttonOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonOpen();
            }
        });
        buttonOpen1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonOpen1();
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonSave();
            }
        });
        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });
        topNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonGetSelection();
            }
        });
        bottomNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonGetSelection();
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void saveFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileWriter out = null;
        try {
            file = new File(directory, filename); // Create a file object
            out = new FileWriter(file); // And a char stream to write it
            textAreaNew.getLineCount(); // Get text from the text area
            String s = textAreaNew.getText();
            out.write(s);
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaNew.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadAndDisplayFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaTop.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaTop.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaTop.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaTop.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!


        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadAndDisplayFileBottom(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaBottom.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaBottom.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaBottom.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaBottom.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!


        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }

    private void onButtonOpen() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFile(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    private void onButtonOpen1() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFileBottom(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    private void onButtonSave() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.SAVE);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        saveFile(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    private void onButtonGetSelection() {
        selection = textAreaTop.getSelectedText();

    }

    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
