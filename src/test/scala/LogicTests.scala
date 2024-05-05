import munit.FunSuite

class LogicTests extends FunSuite {
  test("updateCell should correctly update a cell") {
    grid = Array.tabulate(5, 5)((x, y) => Cell.Empty)
    val x = 1
    val y = 2
    grid(x)(y) = BasicSandCell
    grid(x)(y+1) = Cell.Empty
    updateCell(x, y, grid(x)(y))
    assertEquals(grid(x)(y), Cell.Empty)
    assert(grid(x)(y+1).isInstanceOf[Cell.Sand])
  }
}
