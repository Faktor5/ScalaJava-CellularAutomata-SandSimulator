import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;

// make it a virtual thread
public class Buttons extends JPanel {

  private JToggleButton sandBtn = new JToggleButton("Sand");
  private JToggleButton woodBtn = new JToggleButton("Wood");

  public Buttons(int w, int h, Consumer<Boolean> clearer, Consumer<Boolean> sand, Consumer<Boolean> wood ) {
    setLayout(new GridLayout(1, 3)); // Set layout

    sandBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    sandBtn.setBackground(new Color(194, 178, 128)); // Set deselected background color
    sandBtn.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        woodBtn.setSelected(false);
        sand.accept(true);
      } else {
        sand.accept(false);
      }
    });

    add(sandBtn);

    woodBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    woodBtn.setBackground(new Color(139, 69, 19)); // Set background color to brown
    woodBtn.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        sandBtn.setSelected(false);
        wood.accept(true);
      } else {
        wood.accept(false);
      }
    });
    add(woodBtn);

    // Create Clear button
    JButton clearBtn = new JButton("Clear");
    clearBtn.setBackground(new Color(192, 192, 192));
    woodBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    clearBtn.addActionListener(e -> clearer.accept(e.getActionCommand().equals("Clear")));
    add(clearBtn);

    setPreferredSize(new Dimension(w, 50)); // Set preferred size
    sandBtn.setSelected(true); // Set selected
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
  }
}