import scala.util.Random

def factor = 50
def wn = 4 * factor
def hn = 3 * factor

@main def app(): Unit =
  val engine = Application(800, 600, wn, hn, _getter, _setter, _clearer)
  loop60fps(() => {engine.next ; update})

//Grid of Cells
var grid = Array.tabulate(wn, hn)((x, y) => Cell.Empty)
def getter = (x : Int, y : Int) => grid(x)(y)
def setter = (x : Int, y : Int) => brush(x, y, 10, .2)
def clearer = (z : Boolean) => grid = Array.tabulate(wn, hn)((x, y) => Cell.Empty)

def update =
  for
    yi <- hn - 2 to 0 by -1
    xi <- 0 until wn
    if grid(xi)(yi) != Cell.Empty
  do updateCell(xi, yi)

def updateCell (x : Int, y : Int) =
  if grid(x)(y+1) == Cell.Empty then
    grid(x)(y+1) = grid(x)(y)
    grid(x)(y) = Cell.Empty
  else
    val dx = Random.between(-1, 2)
    if x + dx >= 0 && x + dx < wn && grid(x + dx)(y+1) == Cell.Empty then
      grid(x + dx)(y+1) = grid(x)(y)
      grid(x)(y) = Cell.Empty