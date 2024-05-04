import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.BiConsumer;

public class OnClick implements MouseListener {

  private int w;
  private int h;
  private int wn;
  private int hn;
  private BiConsumer<Integer, Integer> setter;

  public OnClick(int w, int h, int wn, int hn, BiConsumer<Integer, Integer> setter) {
    this.w = w;
    this.h = h;
    this.wn = wn;
    this.hn = hn;
    this.setter = setter;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    int wx = w / wn; // width of a cell
    int hx = h / hn; // height of a cell
    int ix = x / wx; // cell x
    int iy = y / hx; // cell y
    System.out.println(String.format("Pressed: %2d | %2d", ix, iy));
    setter.accept(ix, iy);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Clicked");
  }


  @Override
  public void mouseReleased(MouseEvent e) {
    System.out.println("Released");
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    System.out.println("Entered");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    System.out.println("Exited");
  }
}