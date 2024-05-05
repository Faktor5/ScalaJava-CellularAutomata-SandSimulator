import scala.util.Random

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
