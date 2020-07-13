package blue.thejester.cdg.viewer.main

import blue.thejester.cdg.generator.ChunkyDungeonGenerator
import blue.thejester.cdg.viewer.main.artists.drawDungeon
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import tornadofx.*

class CDGView: View() {

    val image = WritableImage(800, 800)
    val dungeon = ChunkyDungeonGenerator

    init {
        for (i in 100..500) {
            for (j in 200..300) {
                image.pixelWriter.setColor(i, j, Color.ALICEBLUE)
            }
        }
    }

    override val root = vbox {
        style {
            backgroundColor += Color.BISQUE
            borderColor += box(
                top = Color.RED,
                right = Color.DARKGREEN,
                left = Color.ORANGE,
                bottom = Color.PURPLE
            )
        }
        hbox {
            paddingBottom = 30
            vbox {
                textfield()
                button {
                    text = "Render With Seed"
                    action {
                        print("uhhh")
                        drawDungeon(image.pixelWriter, dungeon, 0, 0, image.width.toInt(), image.height.toInt())
                    }
                }
                paddingRight = 30
            }
            gridpane {
                button {
                    text = "^"
                    action {
                        println("UP")
                    }
                    gridpaneConstraints { columnRowIndex(1,0) }
                }
                button {
                    text = "v"
                    action {
                        println("DOWN")
                    }
                    gridpaneConstraints { columnRowIndex(1,2) }
                }
                button {
                    text = "<"
                    action {
                        println("LEFT")
                    }
                    gridpaneConstraints { columnRowIndex(0,1) }
                }
                button {
                    text = ">"
                    action {
                        println("RIGHT")
                    }
                    gridpaneConstraints { columnRowIndex(2,1) }
                }
            }
        }
        imageview(image)
    }
}
