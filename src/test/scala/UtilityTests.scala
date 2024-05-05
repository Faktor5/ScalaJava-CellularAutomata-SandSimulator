import java.awt.Color
import java.util.function.{Consumer, BiConsumer, BiFunction}

class UtilityTests extends munit.FunSuite {
  test("toConsumer should correctly convert Scala function to Java Consumer") {
    val scalaFunction = (b: Boolean) => println(b)
    val javaConsumer = toConsumer(scalaFunction)
    assert(javaConsumer.isInstanceOf[Consumer[java.lang.Boolean]])
  }

  test("toBiConsumer should correctly convert Scala function to Java BiConsumer") {
    val scalaFunction = (i: Int, j: Int) => println(i + j)
    val javaBiConsumer = toBiConsumer(scalaFunction)
    assert(javaBiConsumer.isInstanceOf[BiConsumer[Integer, Integer]])
  }

  test("toBiFunction should correctly convert Scala function to Java BiFunction") {
    val scalaFunction = (i: Int, j: Int) => Cell.Sand(i, j, 0)
    val javaBiFunction = toBiFunction(scalaFunction)
    assert(javaBiFunction.isInstanceOf[BiFunction[Integer, Integer, Color]])
  }

  // Note: Testing loop60fps might be challenging due to its infinite loop and dependency on system time.

  test("brush should set cells within radius to non-empty") {
    grid = Array.fill(wn, hn)(Cell.Empty)
    val x = 1
    val y = 2
    val r = 10
    val p = 1.0 // set probability to 1 for deterministic behavior
    brush(x, y, r, p, BasicSandCell)
    val cellsInBrush = for {
      i <- x - r to x + r
      j <- y - r to y + r
      if i >= 0 && i < wn && j >= 0 && j < hn
      if (i - x) * (i - x) + (j - y) * (j - y) <= r * r
    } yield grid(i)(j)
    assert(cellsInBrush.forall(_ != Cell.Empty))
  }


  test("moveCell should correctly move cell from one position to another") {
    val fromX = 1
    val fromY = 1
    val toX = 2
    val toY = 2
    grid(fromX)(fromY) = BasicSandCell
    grid(toX)(toY) = Cell.Empty
    moveCell(fromX, fromY, toX, toY)
    assert(grid(fromX)(fromY) == Cell.Empty)
    assert(grid(toX)(toY) == BasicSandCell)
  }

  test("swapCell should correctly swap cells at two positions") {
    val x1 = 1
    val y1 = 1
    val x2 = 2
    val y2 = 2
    grid(x1)(y1) = BasicSandCell
    grid(x2)(y2) = Cell.Empty
    swapCell(x1, y1, x2, y2)
    assert(grid(x1)(y1) == Cell.Empty)
    assert(grid(x2)(y2) == BasicSandCell)
  }

  test("clamp should correctly limit value within min and max") {
    assert(clamp(5, 1, 10) == 5) // value within range
    assert(clamp(0, 1, 10) == 1) // value below range
    assert(clamp(15, 1, 10) == 10) // value above range
  }
}
