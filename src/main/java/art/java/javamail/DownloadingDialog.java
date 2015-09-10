package art.java.javamail;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Almaz on 06.06.2015.
 */
public class DownloadingDialog extends JDialog {
    public DownloadingDialog(Frame parent) {
        super(parent, true);
        setTitle("E-mail client");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel contenPane = new JPanel();
        contenPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contenPane.add(new JLabel("Downloading messages..."));
        setContentPane(contenPane);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }
}
