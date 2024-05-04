import java.awt.Color
import java.util.function.{Consumer, BiConsumer, BiFunction}

class UtilityTests extends munit.FunSuite {
  test("SandColorHue should return correct RGB value") {
    assertEquals(SandColorHue, Color.decode("#dcb159").getRGB)
  }

  test("BasicSandCell should return Sand cell with correct hue and zero saturation and brightness") {
    val sandCell = BasicSandCell
    assert(sandCell.isInstanceOf[Cell.Sand])
    assert(sandCell.asInstanceOf[Cell.Sand].hue == SandColorHue)
    assert(sandCell.asInstanceOf[Cell.Sand].saturation == 0)
    assert(sandCell.asInstanceOf[Cell.Sand].brightness == 0)
  }

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

  test("brush should correctly update the grid with BasicSandCell") {
    val x = 10
    val y = 10
    val r = 5
    val p = 1 // for 100% probability
    brush(x, y, r, p)
    for
      i <- x - r to x + r
      j <- y - r to y + r
      if i >= 0 && i < wn && j >= 0 && j < hn
      if (i - x) * (i - x) + (j - y) * (j - y) <= r * r
    do
      assert(grid(i)(j).isInstanceOf[Cell.Sand])
  }
}
