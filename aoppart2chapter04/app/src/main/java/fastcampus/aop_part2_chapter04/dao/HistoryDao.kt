package fastcampus.aop_part2_chapter04.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fastcampus.aop_part2_chapter04.model.History

@Dao
interface HistoryDao {

    // history 조회
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    // Save
    @Insert
    fun insertHistory(history: History)

    // Delete All
    @Query("DELETE FROM history")
    fun deleteAll()

    // Delete one
    @Delete
    fun delete(history: History)

    /*// Select option
    @Query("SELECT * FROM history WHERE result LIKE :result")
    fun findByResult(result: String): List<History>

    // Select option
    @Query("SELECT * FROM history WHERE result LIKE :result LIMIT 1")
    fun findByResult(result: String): History*/
}