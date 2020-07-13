package blue.thejester.cdg.generator

import kotlin.random.Random

object ChunkyDungeonGenerator {
    fun getTile(x: Int, y: Int): Tile {
        val opens = mutableSetOf<TileOpenings>()

        if(Random.nextInt(2) == 1) opens.add(TileOpenings.HALL_EAST)
        if(Random.nextInt(2) == 1) opens.add(TileOpenings.HALL_WEST)
        if(Random.nextInt(2) == 1) opens.add(TileOpenings.HALL_SOUTH)
        if(Random.nextInt(2) == 1) opens.add(TileOpenings.HALL_NORTH)
        return Tile(Random.nextInt(5) == 0, opens)
    }
}
