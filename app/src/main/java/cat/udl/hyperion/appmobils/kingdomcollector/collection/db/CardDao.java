package cat.udl.hyperion.appmobils.kingdomcollector.collection.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM CardEntity")
    List<CardEntity> getAll();

    @Insert
    void insertAll(CardEntity... cards);
}
