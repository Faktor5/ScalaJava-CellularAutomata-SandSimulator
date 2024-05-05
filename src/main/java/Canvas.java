import java.awt.Color;
import java.awt.Graphics;
import java.util.function.BiFunction;

import javax.swing.JPanel;

public class Canvas extends JPanel {
  private int wn;
  private int hn;
  private BiFunction<Integer, Integer, Color> getter;
  private int wx;
  private int hx;

  public Canvas(int w, int h, int wn, int hn, BiFunction<Integer, Integer, Color> getter) {
    this.wn = wn;
    this.hn = hn;
    this.getter = getter;
    this.wx = w / wn;
    this.hx = h / hn;
	}

	@Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int xi = 0; xi < wn; xi++)
    for (int yi = 0; yi < hn; yi++)
    {
      g.setColor(getter.apply(xi, yi));
      g.fillRect(xi * wx, yi * hx, wx, hx);
    }
  }
}
