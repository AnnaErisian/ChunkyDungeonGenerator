package blue.thejester.cdg.generator

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Tile(val isRoom: Boolean, val flags: MutableSet<TileOpenings>) {
    var hallNorth: Boolean by OpeningFlag(TileOpenings.HALL_NORTH)
    var hallEast: Boolean by OpeningFlag(TileOpenings.HALL_EAST)
    var hallSouth: Boolean by OpeningFlag(TileOpenings.HALL_SOUTH)
    var hallWest: Boolean by OpeningFlag(TileOpenings.HALL_WEST)

    var roomNorth: Boolean by OpeningFlag(TileOpenings.ROOM_NORTH)
    var roomEast: Boolean by OpeningFlag(TileOpenings.ROOM_EAST)
    var roomSouth: Boolean by OpeningFlag(TileOpenings.ROOM_SOUTH)
    var roomWest: Boolean by OpeningFlag(TileOpenings.ROOM_WEST)
}

class OpeningFlag(val type: TileOpenings) : ReadWriteProperty<Tile, Boolean> {
    override fun getValue(thisRef: Tile, property: KProperty<*>): Boolean {
        return thisRef.flags.contains(type)
    }

    override fun setValue(thisRef: Tile, property: KProperty<*>, value: Boolean) {
        if(value) {
            if(! thisRef.flags.contains(type)) {
                thisRef.flags.add(type)
            }
        } else {
            if(thisRef.flags.contains(type)) {
                thisRef.flags.remove(type)
            }
        }
    }

}

enum class TileOpenings {
    HALL_NORTH,
    HALL_EAST,
    HALL_SOUTH,
    HALL_WEST,
    ROOM_NORTH,
    ROOM_EAST,
    ROOM_SOUTH,
    ROOM_WEST
}