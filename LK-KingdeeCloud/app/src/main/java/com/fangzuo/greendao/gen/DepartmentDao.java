package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.Department;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DEPARTMENT".
*/
public class DepartmentDao extends AbstractDao<Department, Void> {

    public static final String TABLENAME = "DEPARTMENT";

    /**
     * Properties of entity Department.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FItemID = new Property(0, String.class, "FItemID", false, "FITEM_ID");
        public final static Property FNumber = new Property(1, String.class, "FNumber", false, "FNUMBER");
        public final static Property FName = new Property(2, String.class, "FName", false, "FNAME");
        public final static Property FISSTOCK = new Property(3, String.class, "FISSTOCK", false, "FISSTOCK");
        public final static Property FOrg = new Property(4, String.class, "FOrg", false, "FORG");
    }


    public DepartmentDao(DaoConfig config) {
        super(config);
    }
    
    public DepartmentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEPARTMENT\" (" + //
                "\"FITEM_ID\" TEXT," + // 0: FItemID
                "\"FNUMBER\" TEXT," + // 1: FNumber
                "\"FNAME\" TEXT," + // 2: FName
                "\"FISSTOCK\" TEXT," + // 3: FISSTOCK
                "\"FORG\" TEXT);"); // 4: FOrg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEPARTMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Department entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(2, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(3, FName);
        }
 
        String FISSTOCK = entity.getFISSTOCK();
        if (FISSTOCK != null) {
            stmt.bindString(4, FISSTOCK);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(5, FOrg);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Department entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(2, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(3, FName);
        }
 
        String FISSTOCK = entity.getFISSTOCK();
        if (FISSTOCK != null) {
            stmt.bindString(4, FISSTOCK);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(5, FOrg);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Department readEntity(Cursor cursor, int offset) {
        Department entity = new Department( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FItemID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FNumber
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FISSTOCK
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // FOrg
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Department entity, int offset) {
        entity.setFItemID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFISSTOCK(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFOrg(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Department entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Department entity) {
        return null;
    }

    @Override
    public boolean hasKey(Department entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}