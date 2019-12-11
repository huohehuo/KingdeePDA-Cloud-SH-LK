package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.InStorageNum;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IN_STORAGE_NUM".
*/
public class InStorageNumDao extends AbstractDao<InStorageNum, Long> {

    public static final String TABLENAME = "IN_STORAGE_NUM";

    /**
     * Properties of entity InStorageNum.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property FItemID = new Property(1, String.class, "FItemID", false, "FITEM_ID");
        public final static Property FStockID = new Property(2, String.class, "FStockID", false, "FSTOCK_ID");
        public final static Property FQty = new Property(3, String.class, "FQty", false, "FQTY");
        public final static Property FStoreState = new Property(4, String.class, "FStoreState", false, "FSTORE_STATE");
        public final static Property FStoreOrgID = new Property(5, String.class, "FStoreOrgID", false, "FSTORE_ORG_ID");
        public final static Property FBatchNo = new Property(6, String.class, "FBatchNo", false, "FBATCH_NO");
        public final static Property FUnitID = new Property(7, String.class, "FUnitID", false, "FUNIT_ID");
    }


    public InStorageNumDao(DaoConfig config) {
        super(config);
    }
    
    public InStorageNumDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IN_STORAGE_NUM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"FITEM_ID\" TEXT," + // 1: FItemID
                "\"FSTOCK_ID\" TEXT," + // 2: FStockID
                "\"FQTY\" TEXT," + // 3: FQty
                "\"FSTORE_STATE\" TEXT," + // 4: FStoreState
                "\"FSTORE_ORG_ID\" TEXT," + // 5: FStoreOrgID
                "\"FBATCH_NO\" TEXT," + // 6: FBatchNo
                "\"FUNIT_ID\" TEXT);"); // 7: FUnitID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IN_STORAGE_NUM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InStorageNum entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(2, FItemID);
        }
 
        String FStockID = entity.getFStockID();
        if (FStockID != null) {
            stmt.bindString(3, FStockID);
        }
 
        String FQty = entity.getFQty();
        if (FQty != null) {
            stmt.bindString(4, FQty);
        }
 
        String FStoreState = entity.getFStoreState();
        if (FStoreState != null) {
            stmt.bindString(5, FStoreState);
        }
 
        String FStoreOrgID = entity.getFStoreOrgID();
        if (FStoreOrgID != null) {
            stmt.bindString(6, FStoreOrgID);
        }
 
        String FBatchNo = entity.getFBatchNo();
        if (FBatchNo != null) {
            stmt.bindString(7, FBatchNo);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(8, FUnitID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InStorageNum entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(2, FItemID);
        }
 
        String FStockID = entity.getFStockID();
        if (FStockID != null) {
            stmt.bindString(3, FStockID);
        }
 
        String FQty = entity.getFQty();
        if (FQty != null) {
            stmt.bindString(4, FQty);
        }
 
        String FStoreState = entity.getFStoreState();
        if (FStoreState != null) {
            stmt.bindString(5, FStoreState);
        }
 
        String FStoreOrgID = entity.getFStoreOrgID();
        if (FStoreOrgID != null) {
            stmt.bindString(6, FStoreOrgID);
        }
 
        String FBatchNo = entity.getFBatchNo();
        if (FBatchNo != null) {
            stmt.bindString(7, FBatchNo);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(8, FUnitID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public InStorageNum readEntity(Cursor cursor, int offset) {
        InStorageNum entity = new InStorageNum( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FItemID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FStockID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FQty
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FStoreState
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // FStoreOrgID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // FBatchNo
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // FUnitID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InStorageNum entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFItemID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFStockID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFQty(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFStoreState(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFStoreOrgID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFBatchNo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFUnitID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(InStorageNum entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(InStorageNum entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(InStorageNum entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
