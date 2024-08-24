package src.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import src.data.Example;
import src.exceptions.DatabaseConnectionException;
import src.exceptions.EmptySetException;
import src.exceptions.MissingNumberException;

public class TableData {

    private DbAccess db;

    TableData(DbAccess db) {
        this.db = db;
    }

    public List<Example> getDistinctTransazioni(String table) 
            throws SQLException, EmptySetException, MissingNumberException {
        List<Example> examples = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT * FROM " + table);

            TableSchema schema = new TableSchema(db, table);

            if (!rs.next()) {
                throw new EmptySetException("La tabella " + table + " è vuota.");
            }

            do {
                Example e = new Example();
                for (int i = 0; i < schema.getNumberOfAttributes(); i++) {
                    TableSchema.Column column = schema.getColumn(i);
                    if (column.isNumber()) {
                        e.add(rs.getDouble(i+1));
                    } else {
                        throw new MissingNumberException("L'attributo " + column.getColumnName() + " non è numerico.");
                    }
                }
                examples.add(e);
            } while (rs.next());

            return examples;
        } catch (DatabaseConnectionException e) {
            System.out.println("Errore nella connessione al db");
            return examples;
        }  finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
