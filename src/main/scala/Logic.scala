import scala.util.Random

def drawCell(x: Int, y: Int) = choice match
  case Cell.Empty => ()
  case Cell.Sand(_, _, _) => brush(x, y, 10, .2, BasicSandCell)
  case Cell.Wood(_,_,_,_,_,_) => brush(x, y, 5, .8, BasicWoodCell)

def updateCell(x: Int, y: Int, z: Cell) = z match
  case Cell.Empty => ()
  case Cell.Sand(_, _, _) =>
    if (y < hn - 1)
        if grid(x)(y + 1) == Cell.Empty then
          grid(x)(y + 1) = grid(x)(y)
          grid(x)(y) = Cell.Empty
        else
          val dx = Random.between(-1, 2)
          if x + dx >= 0 && x + dx < wn && grid(x + dx)(y + 1) == Cell.Empty
          then
            grid(x + dx)(y + 1) = grid(x)(y)
            grid(x)(y) = Cell.Empty
  case Cell.Wood(_,_,_,_,_,_) => ()
