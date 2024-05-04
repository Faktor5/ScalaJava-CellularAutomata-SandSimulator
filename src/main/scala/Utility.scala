import java.util.function.{BiFunction, BiConsumer, Consumer}
import System.{nanoTime}
import java.awt.Color
import scala.util.Random

def SandColor = Color.decode("#dcb159")
def SandColorHue = SandColor.getRGB
def SandCell = Cell.Material(SandColorHue, 0, 0)

def toConsumer (c : (Boolean) => Unit) : Consumer[java.lang.Boolean] = c(_)
def toBiConsumer (c : (Int, Int) => Unit) : BiConsumer[Integer, Integer] = c(_,_)
def toBiFunction (f : (Int, Int) => Cell) : BiFunction[Integer, Integer, Color] = f(_,_).toColor

def _setter = toBiConsumer(setter)
def _getter = toBiFunction(getter)
def _clearer = toConsumer(clearer)

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
  do grid(i)(j) = SandCell.vary