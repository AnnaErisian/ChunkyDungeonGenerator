package blue.thejester.cdg.viewer.main.artists

import blue.thejester.cdg.generator.ChunkyDungeonGenerator
import blue.thejester.cdg.generator.Tile
import javafx.scene.image.PixelWriter
import javafx.scene.paint.Color
import java.lang.Exception

const val TILE_SIZE = 8;

/**
 * Draws the dungeon to the PixelWriter
 * offsets determine the top left corner's coordinates, can be mid-room
 */
fun drawDungeon(
    g: PixelWriter,
    dungeonGenerator: ChunkyDungeonGenerator,
    offsetX: Int,
    offsetY: Int,
    viewWidth: Int,
    viewHeight: Int
) {
    val tilesAcross = viewWidth / TILE_SIZE + 2 //We're doing a simple thing, just draw a small excess
    val tilesVertically = viewHeight / TILE_SIZE + 2
    val firstTileX = offsetX / TILE_SIZE
    val firstTileY = offsetY / TILE_SIZE
    for (i in 0 until tilesAcross) {
        for (j in 0 until tilesVertically) {
            val tileX = firstTileX + i
            val tileY = firstTileY + j
            val tilePixelX = i * TILE_SIZE - (offsetX % TILE_SIZE)
            val tilePixelY = j * TILE_SIZE - (offsetY % TILE_SIZE)
            drawRoom(g, dungeonGenerator.getTile(tileX, tileY), tilePixelX, tilePixelY, viewWidth, viewHeight)
        }
    }
}

fun drawRoom(
    g: PixelWriter,
    tile: Tile,
    roomPixelX: Int, roomPixelY: Int,
    viewWidth: Int, viewHeight: Int
) {
    fun setPx(x: Int, y: Int, c: Color) {
        if(x+roomPixelX in 1 until viewWidth && (y+roomPixelY in 1 until viewHeight))
            try {
                g.setColor(roomPixelX + x, roomPixelY + y, c)
            } catch (e: Exception) {
                println("coords: $x,$y | limits: $viewWidth, $viewHeight")
            }
    }

    //blank the room
    for (i in 0 until TILE_SIZE)
        for (j in 0 until TILE_SIZE)
            setPx(i, j, (if (roomPixelX/8%2==roomPixelY/8%2) Color.WHITE else Color.ROSYBROWN))
    if (tile.isRoom) {
        //fill the base room area
        for (i in 1 until TILE_SIZE - 1)
            for (j in 1 until TILE_SIZE - 1)
                setPx(i, j, Color.BLACK)
        if (tile.roomEast) {
            for (j in 1 until TILE_SIZE - 1)
                setPx(TILE_SIZE - 1, j, Color.BLACK)
        }
        if (tile.roomWest) {
            for (j in 1 until TILE_SIZE - 1)
                setPx(0, j, Color.BLACK)
        }
        if (tile.roomSouth) {
            for (i in 1 until TILE_SIZE - 1)
                setPx(i, TILE_SIZE - 1, Color.BLACK)
        }
        if (tile.roomNorth) {
            for (i in 1 until TILE_SIZE - 1)
                setPx(i, 0, Color.BLACK)
        }
    }

    //fill the base hall area
    for (i in 3 until TILE_SIZE - 3)
        for (j in 3 until TILE_SIZE - 3)
            setPx(i, j, Color.BLACK)
    if (tile.hallEast)
        for (i in 0..2)
            for (j in 3 until TILE_SIZE - 3)
                setPx(TILE_SIZE - 1 - i, j, Color.BLACK)
    if (tile.hallWest)
        for (i in 0..2)
            for (j in 3 until TILE_SIZE - 3)
                setPx(i, j, Color.BLACK)
    if (tile.hallSouth)
        for (i in 3 until TILE_SIZE - 3)
            for (j in 0..2)
                setPx(i, TILE_SIZE - 1 - j, Color.BLACK)
    if (tile.hallNorth)
        for (i in 3 until TILE_SIZE - 3)
            for (j in 0..2)
                setPx(i, j, Color.BLACK)

}
