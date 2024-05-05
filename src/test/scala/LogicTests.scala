import munit.FunSuite

class LogicTests extends FunSuite {
  test("updateCell should correctly update a cell") {
    val x = 1
    val y = 2
    grid(x)(y) = Cell.Sand(SandColorHue, 0, 0)
    grid(x)(y+1) = Cell.Empty
    updateCell(x, y, grid(x)(y))
    assertEquals(grid(x)(y), Cell.Empty)
    assert(grid(x)(y+1).isInstanceOf[Cell.Sand])
  }
}