import java.util.function.{BiFunction, BiConsumer, Consumer}
import System.{nanoTime}
import java.awt.Color
import scala.util.Random

def SandColorHue : Int = Color.decode("#dcb159").getRGB
def BasicSandCell : Cell = Cell.Sand(SandColorHue, 0, 0)
def WoodColorHue : Int = Color.decode("#8b4513").getRGB
def BasicWoodCell : Cell = Cell.Wood(WoodColorHue, 0, 0, false, Random.between(-6, 10), Random.between(100, 300))

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

def brush(x : Int, y : Int, r : Int, p : Double) =
  for
    i <- x - r to x + r
    j <- y - r to y + r
    if i >= 0 && i < wn && j >= 0 && j < hn
    if (i - x) * (i - x) + (j - y) * (j - y) <= r * r // this is the equation of a circle
    if Random.nextDouble < p
  do grid(i)(j) = BasicSandCell.vary

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