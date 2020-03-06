package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.Suppliers;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SUPPLIERS".
*/
public class SuppliersDao extends AbstractDao<Suppliers, Void> {

    public static final String TABLENAME = "SUPPLIERS";

    /**
     * Properties of entity Suppliers.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FItemID = new Property(0, String.class, "FItemID", false, "FITEM_ID");
        public final static Property FName = new Property(1, String.class, "FName", false, "FNAME");
        public final static Property FNumber = new Property(2, String.class, "FNumber", false, "FNUMBER");
        public final static Property FOrg = new Property(3, String.class, "FOrg", false, "FORG");
        public final static Property FNote = new Property(4, String.class, "FNote", false, "FNOTE");
        public final static Property FMASTERID = new Property(5, String.class, "FMASTERID", false, "FMASTERID");
        public final static Property FSupplyClassIfy = new Property(6, String.class, "FSupplyClassIfy", false, "FSUPPLY_CLASS_IFY");
        public final static Property FItemClassID = new Property(7, String.class, "FItemClassID", false, "FITEM_CLASS_ID");
        public final static Property FParentID = new Property(8, String.class, "FParentID", false, "FPARENT_ID");
        public final static Property FLevel = new Property(9, String.class, "FLevel", false, "FLEVEL");
        public final static Property FDetail = new Property(10, String.class, "FDetail", false, "FDETAIL");
        public final static Property FAddress = new Property(11, String.class, "FAddress", false, "FADDRESS");
        public final static Property FPhone = new Property(12, String.class, "FPhone", false, "FPHONE");
        public final static Property FEmail = new Property(13, String.class, "FEmail", false, "FEMAIL");
    }


    public SuppliersDao(DaoConfig config) {
        super(config);
    }
    
    public SuppliersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SUPPLIERS\" (" + //
                "\"FITEM_ID\" TEXT," + // 0: FItemID
                "\"FNAME\" TEXT," + // 1: FName
                "\"FNUMBER\" TEXT," + // 2: FNumber
                "\"FORG\" TEXT," + // 3: FOrg
                "\"FNOTE\" TEXT," + // 4: FNote
                "\"FMASTERID\" TEXT," + // 5: FMASTERID
                "\"FSUPPLY_CLASS_IFY\" TEXT," + // 6: FSupplyClassIfy
                "\"FITEM_CLASS_ID\" TEXT," + // 7: FItemClassID
                "\"FPARENT_ID\" TEXT," + // 8: FParentID
                "\"FLEVEL\" TEXT," + // 9: FLevel
                "\"FDETAIL\" TEXT," + // 10: FDetail
                "\"FADDRESS\" TEXT," + // 11: FAddress
                "\"FPHONE\" TEXT," + // 12: FPhone
                "\"FEMAIL\" TEXT);"); // 13: FEmail
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SUPPLIERS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Suppliers entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(4, FOrg);
        }
 
        String FNote = entity.getFNote();
        if (FNote != null) {
            stmt.bindString(5, FNote);
        }
 
        String FMASTERID = entity.getFMASTERID();
        if (FMASTERID != null) {
            stmt.bindString(6, FMASTERID);
        }
 
        String FSupplyClassIfy = entity.getFSupplyClassIfy();
        if (FSupplyClassIfy != null) {
            stmt.bindString(7, FSupplyClassIfy);
        }
 
        String FItemClassID = entity.getFItemClassID();
        if (FItemClassID != null) {
            stmt.bindString(8, FItemClassID);
        }
 
        String FParentID = entity.getFParentID();
        if (FParentID != null) {
            stmt.bindString(9, FParentID);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(10, FLevel);
        }
 
        String FDetail = entity.getFDetail();
        if (FDetail != null) {
            stmt.bindString(11, FDetail);
        }
 
        String FAddress = entity.getFAddress();
        if (FAddress != null) {
            stmt.bindString(12, FAddress);
        }
 
        String FPhone = entity.getFPhone();
        if (FPhone != null) {
            stmt.bindString(13, FPhone);
        }
 
        String FEmail = entity.getFEmail();
        if (FEmail != null) {
            stmt.bindString(14, FEmail);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Suppliers entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(4, FOrg);
        }
 
        String FNote = entity.getFNote();
        if (FNote != null) {
            stmt.bindString(5, FNote);
        }
 
        String FMASTERID = entity.getFMASTERID();
        if (FMASTERID != null) {
            stmt.bindString(6, FMASTERID);
        }
 
        String FSupplyClassIfy = entity.getFSupplyClassIfy();
        if (FSupplyClassIfy != null) {
            stmt.bindString(7, FSupplyClassIfy);
        }
 
        String FItemClassID = entity.getFItemClassID();
        if (FItemClassID != null) {
            stmt.bindString(8, FItemClassID);
        }
 
        String FParentID = entity.getFParentID();
        if (FParentID != null) {
            stmt.bindString(9, FParentID);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(10, FLevel);
        }
 
        String FDetail = entity.getFDetail();
        if (FDetail != null) {
            stmt.bindString(11, FDetail);
        }
 
        String FAddress = entity.getFAddress();
        if (FAddress != null) {
            stmt.bindString(12, FAddress);
        }
 
        String FPhone = entity.getFPhone();
        if (FPhone != null) {
            stmt.bindString(13, FPhone);
        }
 
        String FEmail = entity.getFEmail();
        if (FEmail != null) {
            stmt.bindString(14, FEmail);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Suppliers readEntity(Cursor cursor, int offset) {
        Suppliers entity = new Suppliers( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FItemID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FOrg
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FNote
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // FMASTERID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // FSupplyClassIfy
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // FItemClassID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // FParentID
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // FLevel
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // FDetail
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // FAddress
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // FPhone
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // FEmail
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Suppliers entity, int offset) {
        entity.setFItemID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFOrg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFNote(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFMASTERID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFSupplyClassIfy(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFItemClassID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFParentID(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFLevel(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFDetail(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setFAddress(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setFPhone(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setFEmail(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Suppliers entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Suppliers entity) {
        return null;
    }

    @Override
    public boolean hasKey(Suppliers entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
