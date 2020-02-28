# KIdenticon

A simple Kotlin (JVM) identicon generator

## Use

(Using jar from builds)

`java -jar kidenticon.jar <salt> <filename>`

e.g. `java -jar kidenticon.jar test output` will generate the following output.png

![output](./output.png)

## Code use

Colour and bubble size are configurable through passing Palette and Config options to the `Kidenticon` class

### Palette
Provides configuration for colour palette (20 colours) and background colour

### Config
Provides configuration for image size and min bubble size (max bubble size is always half)
