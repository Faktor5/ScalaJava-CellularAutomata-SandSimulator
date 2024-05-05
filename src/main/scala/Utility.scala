import java.util.function.{BiFunction, BiConsumer, Consumer}
import System.{nanoTime}
import java.awt.Color
import scala.util.Random

// def SandColorHue : Int = Color.decode("#dcb159").getRGB
def SandColorHSB : Array[Float] = hexToHSB("#dcb159")
// def BasicSandCell : Cell = Cell.Sand(SandColorHue, 0, 0)
def BasicSandCell : Cell = Cell.Sand(SandColorHSB(0),SandColorHSB(1),SandColorHSB(2))
// def WoodColorHue : Int = Color.decode("#8b4513").getRGB
def WoodColorHSB : Array[Float] = hexToHSB("#8b4513")
def BasicWoodCell : Cell = Cell.Wood(WoodColorHSB(0), WoodColorHSB(1), WoodColorHSB(2), false, 0, 0)

// HEX to HSB
def hexToHSB(hex: String) : Array[Float] = {
  val rgb = Color.decode(hex).getRGB
  val r = (rgb >> 16) & 0xFF
  val g = (rgb >> 8) & 0xFF
  val b = rgb & 0xFF
  Color.RGBtoHSB(r, g, b, null)
}


def toConsumer (c : (Boolean) => Unit) : Consumer[java.lang.Boolean] = c(_)
def toBiConsumer (c : (Int, Int) => Unit) : BiConsumer[Integer, Integer] = c(_,_)
def toBiFunction (f : (Int, Int) => Cell) : BiFunction[Integer, Integer, Color] = f(_,_).toColor

def _setter = toBiConsumer(setter)
def _getter = toBiFunction(getter)
def _clearer = toConsumer(clearer)
def _sandChoice = toConsumer(sandChoice)
def _woodChoice = toConsumer(woodChoice)

def loop60fps = (action : () => Unit) =>
  var lastTime = nanoTime
  var currentTime = 0L
  var delta = currentTime - lastTime
  val rate = 1_000_000_000 / 120
  while true do
    currentTime = nanoTime
    delta = currentTime - lastTime
    if delta >= rate then
      action.apply
      lastTime = currentTime

def brush(x : Int, y : Int, r : Int, p : Double, z : Cell) =
  for
    i <- x - r to x + r
    j <- y - r to y + r
    if i >= 0 && i < wn && j >= 0 && j < hn
    if (i - x) * (i - x) + (j - y) * (j - y) <= r * r // this is the equation of a circle
    if Random.nextDouble < p
  do grid(i)(j) = z.vary

def clamp(value: Int, min: Int, max: Int): Int = {
  if (value < min) min
  else if (value > max) max
  else value
}

def moveCell(fromX: Int, fromY: Int, toX: Int, toY: Int) = {
  grid(toX)(toY) = grid(fromX)(fromY)
  grid(fromX)(fromY) = Cell.Empty
}

def swapCell(x1: Int, y1: Int, x2: Int, y2: Int) = {
  val temp = grid(x1)(y1)
  grid(x1)(y1) = grid(x2)(y2)
  grid(x2)(y2) = temp
}