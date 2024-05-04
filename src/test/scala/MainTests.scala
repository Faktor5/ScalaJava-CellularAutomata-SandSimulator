class MainTests extends munit.FunSuite {
  test("getter should return correct cell from grid") {
    grid(1)(1) = Cell.Sand(0, 1, 1)
    assertEquals(getter(1, 1), Cell.Sand(0, 1, 1))
  }
  
  test("setter should correctly set a cell in grid") {
    setter(1, 1)
    assert(grid(1)(1).isInstanceOf[Cell.Sand])
  }
  
  test("clearer should correctly reset the grid to Cell.Empty") {
    grid(1)(1) = Cell.Sand(0, 1, 1)
    clearer(true)
    assertEquals(grid(1)(1), Cell.Empty)
  }
  
  test("update should correctly update the grid based on cell's state") {
    grid(1)(1) = Cell.Sand(0, 1, 1)
    update
    assertEquals(grid(1)(2), Cell.Sand(0, 1, 1))
    assertEquals(grid(1)(1), Cell.Empty)
  }
  
  test("updateCell should correctly update a cell's state based on its neighbors") {
    grid(1)(1) = Cell.Sand(0, 1, 1)
    updateCell(1, 1)
    assertEquals(grid(1)(2), Cell.Sand(0, 1, 1))
    assertEquals(grid(1)(1), Cell.Empty)
  }
}
