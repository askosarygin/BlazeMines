package com.example.data.db

import androidx.room.*

private const val TABLE_NAME_LEVELS = "levels"
private const val NUMBER = "number"
private const val NUMBER_OF_STARS = "number_of_stars"
private const val NUMBER_OF_BOMBS = "number_of_bombs"
private const val NUMBER_OF_FIRES = "number_of_fires"
private const val NUMBER_OF_CELLS = "number_of_cells"
private const val ACTIVATED = "activated"

@Entity(tableName = TABLE_NAME_LEVELS)
data class LevelDatabaseClass(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = NUMBER) val number: Int,
    @ColumnInfo(name = NUMBER_OF_STARS) val numberOfStars: String,
    @ColumnInfo(name = NUMBER_OF_BOMBS) val numberOfBombs: Int,
    @ColumnInfo(name = NUMBER_OF_FIRES) val numberOfFires: Int,
    @ColumnInfo(name = NUMBER_OF_CELLS) val numberOfCells: Int,
    @ColumnInfo(name = ACTIVATED) val activated: Boolean,
)

@Dao
interface LevelsDAO {

    @Insert
    fun add(levelDatabaseClass: LevelDatabaseClass)

    @Query("SELECT * FROM $TABLE_NAME_LEVELS WHERE id=(:id)")
    fun getLevelById(id: Long): LevelDatabaseClass

    @Query("SELECT * FROM $TABLE_NAME_LEVELS")
    fun getAll(): List<LevelDatabaseClass>

    @Update
    fun update(levelDatabaseClass: LevelDatabaseClass)
}

@Database(entities = [LevelDatabaseClass::class], version = 1, exportSchema = false)
abstract class LevelsInfoDatabase : RoomDatabase() {
    abstract fun levelsDAO(): LevelsDAO
}