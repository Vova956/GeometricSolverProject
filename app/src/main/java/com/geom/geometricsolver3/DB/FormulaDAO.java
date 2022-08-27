package com.geom.geometricsolver3.DB;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FormulaDAO {

    @Insert(onConflict = REPLACE)
    void insert(FormulaData formulaData);

    @Delete
    void delete(FormulaData formulaData);

    @Delete
    void reset(List<FormulaData> formulaData);

    @Query("SELECT * FROM Formulas")
    List<FormulaData> getAll();

    @Query("DELETE FROM Formulas")
    void nukeTable();

}
