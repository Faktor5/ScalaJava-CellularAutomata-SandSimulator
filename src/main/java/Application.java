import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

public class Application {

	private final JFrame frame = new JFrame();

	public void next() {
		this.frame.repaint();
	}

	public Application(int w, int h, int wn, int hn, BiFunction<Integer, Integer, Color> getter,
			BiConsumer<Integer, Integer> setter, Consumer<Boolean> clearer, Consumer<Boolean> sand,
			Consumer<Boolean> wood ) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w + 16, h + 39 + 50);
		frame.setVisible(true);
		frame.setTitle("Sand-Spiel");
		frame.setResizable(false);

		JSplitPane layout = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		layout.setDividerLocation(h);
		layout.setEnabled(false);
		layout.setDividerSize(0);
		layout.setSize(w, h + 50);

		JPanel buttons = new Buttons(w, 50, clearer, sand, wood);

		JPanel c = new Canvas(w, h, wn, hn, getter);
		c.setSize(w, h);

		MouseListener listener = new OnClick(w, h, wn, hn, setter);

		frame.add(layout);
		layout.setBottomComponent(buttons);
		layout.setTopComponent(c);
		c.addMouseListener(listener);

		System.out.println(String.format("Insets: %s\nSize: %s", frame.getInsets(), frame.getSize()));
	}
}