package src.database;

import src.data.Example;

import java.sql.SQLException;
import src.exceptions.EmptySetException;
import src.exceptions.MissingNumberException;

import java.util.List;

public class TableData {

    private DbAccess db;

    TableData(DbAccess db) {
        this.db = db;
    }

    List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException,
    MissingNumberException {
        
    } 
}
