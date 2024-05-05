import munit._

class MainTests extends FunSuite {

  test("getter should return the correct cell") {
    grid = Array.fill(wn, hn)(Cell.Empty)
    val x = 1
    val y = 2
    grid(x)(y) = Cell.Sand(SandColorHue, 0, 0)
    assert(getter(x, y).isInstanceOf[Cell.Sand])
  }

  test("setter should set the correct cell") {
    grid = Array.fill(wn, hn)(Cell.Empty)
    val x = 1
    val y = 2
    setter(x, y)
    assertNotEquals(grid(x)(y), Cell.Empty)
  }

  test("clearer should clear the grid") {
    grid = Array.fill(wn, hn)(Cell.Sand(SandColorHue, 0, 0))
    clearer(true)
    assert(grid.forall(_.forall(_ == Cell.Empty)))
  }
}