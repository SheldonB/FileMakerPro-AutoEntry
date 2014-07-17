import javax.swing.*;
import java.awt.*;

public class FFAMainPanel extends JPanel {
    private JTextArea chapterNameArea;
    private JTextArea weekNumberArea;
    private JButton startButton;
    private FileChooserPanel chooserPanel;

    public FFAMainPanel() {
        this.setPreferredSize(new Dimension(400, 110));
        this.chapterNameArea = new JTextArea(1, 15);
        this.weekNumberArea = new JTextArea(1,1);
        this.startButton = new JButton("Start");
        this.chooserPanel = new FileChooserPanel();

        this.add(new JLabel("Chapter Name: "));
        this.add(this.chapterNameArea);
        this.add(new JLabel("Week: "));
        this.add(this.weekNumberArea);
        this.add(this.chooserPanel);
        this.add(this.startButton);

        this.startButton.addActionListener(e -> {
            if(e.getSource() == startButton) {
                Main.frameVisible(false);
                if(Main.run(new Chapter(chapterNameArea.getText(), Integer.parseInt(weekNumberArea.getText())),
                                           chooserPanel.getExcelDoc())) {
                    System.out.println("Run Finished");
                    Main.frameVisible(true);
                } else {
                    System.out.println("Run failed");
                }
            }
        });
    }
}
