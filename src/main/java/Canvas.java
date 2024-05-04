import java.awt.Color;
import java.awt.Graphics;
import java.util.function.BiFunction;

import javax.swing.JPanel;

public class Canvas extends JPanel {
  private int w;
  private int h;
  private int wn;
  private int hn;
  private BiFunction<Integer, Integer, Color> getter;

  public Canvas(int w, int h, int wn, int hn, BiFunction<Integer, Integer, Color> getter) {
    this.w = w;
    this.h = h;
    this.wn = wn;
    this.hn = hn;
    this.getter = getter;
	}

	@Override
  public void paint(Graphics g) {
    super.paint(g);
    int wx = w / wn; // width of a cell
    int hx = h / hn; // height of a cell
    for (int xi = 0; xi < wn; xi++)
    for (int yi = 0; yi < hn; yi++)
    {
      g.setColor(getter.apply(xi, yi));
      g.fillRect(xi * wx, yi * hx, wx, hx);
    }
  }
}
