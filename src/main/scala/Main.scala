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
def setter = (x : Int, y : Int) => if choice.isInstanceOf[Cell.Sand] then brush(x, y, 10, .2)
def clearer = (z : Boolean) => grid = Array.tabulate(wn, hn)((x, y) => Cell.Empty)
def sandChoice = (z : Boolean) => {choice = if z then BasicSandCell else Cell.Empty ; println(s"sand-choice: $choice")}
def woodChoice = (z : Boolean) => {choice = if z then BasicWoodCell else Cell.Empty ; println(s"wood-choice: $choice")}

def update =
  for
    // -1 instead of -2, instead put the condition in the updateCell function
    yi <- hn - 1 to 0 by -1
    xi <- 0 until wn
  do updateCell(xi, yi, grid(xi)(yi))