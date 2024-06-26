import java.awt.Color
import java.awt.Color.{HSBtoRGB}
import scala.util.Random

enum Cell:
  case Empty extends Cell
  case Sand(hue: Float, saturation: Float, brightness: Float) extends Cell
  case Wood(hue: Float, saturation: Float, brightness: Float, isLit : Boolean, glow: Float, fuel : Int) extends Cell
  
  def toColor: Color = this match
    case Empty => Color.DARK_GRAY
    case Sand(hue, saturation, brightness) => Color(HSBtoRGB(hue, saturation, brightness))
    case Wood(hue, saturation, brightness, isLit, glow, fuel) =>
      if isLit then Color(HSBtoRGB(hue, saturation, brightness + glow))
      else Color(HSBtoRGB(hue, saturation, brightness))
  
  def vary : Cell = this match
    case Empty => Empty
    case Sand(hue, saturation, brightness) => Sand(
      hue,
      clamp(Random.between(saturation - 0.2f, saturation), 0.0f, 1.0f),
      clamp(Random.between(brightness - 0.1f, brightness), 0.0f, 1.0f)
    )
    case Wood(hue, saturation, brightness, isLit, glow, fuel) => Wood(
      hue,
      clamp(Random.between(saturation - 0.2f, saturation), 0.0f, 1.0f),
      clamp(Random.between(brightness - 0.2f, brightness + 0.1f), 0.0f, 1.0f),
      isLit,
      Random.between(-6, 10),
      Random.between(100, 300)
    )

/* Sketch
* <h1> In Java <h1>
* <p>
* The {@code saturation} and {@code brightness} components
* should be floating-point values between zero and one
* (numbers in the range 0.0-1.0).  The {@code hue} component
* can be any floating-point number.  The floor of this number is
* subtracted from it to create a fraction between 0 and 1.  This
* fractional number is then multiplied by 360 to produce the hue
* angle in the HSB color model.
* <p>

From https://jason.today/falling-sand #Drawing pixels to the screen

```js
function varyColor(p, color) {
  let hue = p.floor(p.hue(color));
  let saturation = p.saturation(color) + p.floor(p.random(-20, 0));
  saturation = p.constrain(saturation, 0, 100);
  let lightness = p.lightness(color) + p.floor(p.random(-10, 10));
  lightness = p.constrain(lightness, 0, 100);
  return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
}
```
*/
