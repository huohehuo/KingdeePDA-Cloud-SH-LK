package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.BarCode;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BAR_CODE".
*/
public class BarCodeDao extends AbstractDao<BarCode, Void> {

    public static final String TABLENAME = "BAR_CODE";

    /**
     * Properties of entity BarCode.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FName = new Property(0, String.class, "FName", false, "FNAME");
        public final static Property FTypeID = new Property(1, String.class, "FTypeID", false, "FTYPE_ID");
        public final static Property FItemID = new Property(2, String.class, "FItemID", false, "FITEM_ID");
        public final static Property FBarCode = new Property(3, String.class, "FBarCode", false, "FBAR_CODE");
        public final static Property FNumber = new Property(4, String.class, "FNumber", false, "FNUMBER");
        public final static Property FUnitID = new Property(5, String.class, "FUnitID", false, "FUNIT_ID");
    }


    public BarCodeDao(DaoConfig config) {
        super(config);
    }
    
    public BarCodeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BAR_CODE\" (" + //
                "\"FNAME\" TEXT," + // 0: FName
                "\"FTYPE_ID\" TEXT," + // 1: FTypeID
                "\"FITEM_ID\" TEXT," + // 2: FItemID
                "\"FBAR_CODE\" TEXT," + // 3: FBarCode
                "\"FNUMBER\" TEXT," + // 4: FNumber
                "\"FUNIT_ID\" TEXT);"); // 5: FUnitID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BAR_CODE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BarCode entity) {
        stmt.clearBindings();
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(1, FName);
        }
 
        String FTypeID = entity.getFTypeID();
        if (FTypeID != null) {
            stmt.bindString(2, FTypeID);
        }
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(3, FItemID);
        }
 
        String FBarCode = entity.getFBarCode();
        if (FBarCode != null) {
            stmt.bindString(4, FBarCode);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(5, FNumber);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(6, FUnitID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BarCode entity) {
        stmt.clearBindings();
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(1, FName);
        }
 
        String FTypeID = entity.getFTypeID();
        if (FTypeID != null) {
            stmt.bindString(2, FTypeID);
        }
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(3, FItemID);
        }
 
        String FBarCode = entity.getFBarCode();
        if (FBarCode != null) {
            stmt.bindString(4, FBarCode);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(5, FNumber);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(6, FUnitID);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public BarCode readEntity(Cursor cursor, int offset) {
        BarCode entity = new BarCode( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FName
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FTypeID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FItemID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FBarCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FNumber
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // FUnitID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BarCode entity, int offset) {
        entity.setFName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFTypeID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFItemID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFBarCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFNumber(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFUnitID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(BarCode entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(BarCode entity) {
        return null;
    }

    @Override
    public boolean hasKey(BarCode entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}