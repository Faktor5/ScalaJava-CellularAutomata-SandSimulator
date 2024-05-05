## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

## Inspiration

- [Making a falling sand simulator](https://jason.today/falling-sand)

## naive color approach

```scala
def SandColorHue : Int = Color.decode("#dcb159").getRGB
def BasicSandCell : Cell = Cell.Sand(SandColorHue, 0, 0)
def WoodColorHue : Int = Color.decode("#8b4513").getRGB
def BasicWoodCell : Cell = Cell.Wood(WoodColorHue, 0, 0, false, 0, 0)
```