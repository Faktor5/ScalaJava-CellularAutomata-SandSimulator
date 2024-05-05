import scala.util.Random

def factor = 50
def wn = 4 * factor
def hn = 3 * factor

var choice : Cell = BasicSandCell

@main def app(): Unit =
  val engine = Application(800, 600, wn, hn, _getter, _setter, _clearer, _sandChoice, _woodChoice)
  loop60fps(() => {engine.next ; update})

//Grid of Cells
var grid = Array.tabulate(wn, hn)((x, y) => Cell.Empty)
def getter = (x : Int, y : Int) => grid(x)(y)
def setter = (x : Int, y : Int) => drawCell(x, y)
def clearer = (z : Boolean) => grid = Array.fill(wn, hn)(Cell.Empty)
def sandChoice = (z : Boolean) => choice = if z then BasicSandCell else Cell.Empty
def woodChoice = (z : Boolean) => choice = if z then BasicWoodCell else Cell.Empty

def update =
  for
    // -1 instead of -2, instead put the condition in the updateCell function
    yi <- hn - 1 to 0 by -1
    xi <- 0 until wn
  do updateCell(xi, yi, grid(xi)(yi))