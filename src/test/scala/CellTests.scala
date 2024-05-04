import java.awt.Color

class CellTests extends munit.FunSuite {
  test("Empty cell toColor should return DARK_GRAY") {
    assertEquals(Cell.Empty.toColor, Color.DARK_GRAY)
  }

  test("Sand cell toColor should return color based on HSB") {
    val sandCell = Cell.Sand(0, 1, 1)
    val expectedColor = new Color(Color.HSBtoRGB(0, 1, 1))
    assertEquals(sandCell.toColor, expectedColor)
  }

  test("Empty cell vary should return Empty") {
    assertEquals(Cell.Empty.vary, Cell.Empty)
  }

  test("Sand cell vary should return new Sand cell with varied saturation and brightness") {
    val sandCell = Cell.Sand(0, 50, 50)
    val variedCell = sandCell.vary
    assert(variedCell.isInstanceOf[Cell.Sand])
    assert(variedCell.asInstanceOf[Cell.Sand].hue == 0)
    assert(variedCell.asInstanceOf[Cell.Sand].saturation != 50 || variedCell.asInstanceOf[Cell.Sand].brightness != 50)
  }
}
