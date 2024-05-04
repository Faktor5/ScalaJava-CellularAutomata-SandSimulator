import munit._

class MainTests extends FunSuite {

  test("getter should return the correct cell") {
    val x = 1
    val y = 2
    grid(x)(y) = Cell.Sand(SandColorHue, 0, 0)
    assert(getter(x, y).isInstanceOf[Cell.Sand])
  }

  test("setter should set the correct cell") {
    val x = 1
    val y = 2
    setter(x, y)
    assertNotEquals(grid(x)(y), Cell.Empty)
  }

  test("clearer should clear the grid") {
    clearer(true)
    assert(grid.forall(_.forall(_ == Cell.Empty)))
  }

  test("updateCell should correctly update a cell") {
    val x = 1
    val y = 2
    grid(x)(y) = Cell.Sand(SandColorHue, 0, 0)
    grid(x)(y+1) = Cell.Empty
    updateCell(x, y)
    assertEquals(grid(x)(y), Cell.Empty)
    assert(grid(x)(y+1).isInstanceOf[Cell.Sand])
  }

  test("brush should set cells within radius to non-empty") {
    val x = 1
    val y = 2
    val r = 10
    val p = 1.0 // set probability to 1 for deterministic behavior
    brush(x, y, r, p)
    val cellsInBrush = for {
      i <- x - r to x + r
      j <- y - r to y + r
      if i >= 0 && i < wn && j >= 0 && j < hn
      if (i - x) * (i - x) + (j - y) * (j - y) <= r * r
    } yield grid(i)(j)
    assert(cellsInBrush.forall(_ != Cell.Empty))
  }
}