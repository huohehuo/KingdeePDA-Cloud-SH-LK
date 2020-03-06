package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.PushDownSub;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PUSH_DOWN_SUB".
*/
public class PushDownSubDao extends AbstractDao<PushDownSub, Long> {

    public static final String TABLENAME = "PUSH_DOWN_SUB";

    /**
     * Properties of entity PushDownSub.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FSEQ = new Property(1, String.class, "FSEQ", false, "FSEQ");
        public final static Property FID = new Property(2, String.class, "FID", false, "FID");
        public final static Property FMaterialID = new Property(3, String.class, "FMaterialID", false, "FMATERIAL_ID");
        public final static Property FAccountID = new Property(4, String.class, "FAccountID", false, "FACCOUNT_ID");
        public final static Property FEntryID = new Property(5, String.class, "FEntryID", false, "FENTRY_ID");
        public final static Property FUnitID = new Property(6, String.class, "FUnitID", false, "FUNIT_ID");
        public final static Property FNumber = new Property(7, String.class, "FNumber", false, "FNUMBER");
        public final static Property FName = new Property(8, String.class, "FName", false, "FNAME");
        public final static Property FModel = new Property(9, String.class, "FModel", false, "FMODEL");
        public final static Property FBillNo = new Property(10, String.class, "FBillNo", false, "FBILL_NO");
        public final static Property FQty = new Property(11, String.class, "FQty", false, "FQTY");
        public final static Property FQtying = new Property(12, String.class, "FQtying", false, "FQTYING");
        public final static Property FTaxPrice = new Property(13, String.class, "FTaxPrice", false, "FTAX_PRICE");
        public final static Property FStockID = new Property(14, String.class, "FStockID", false, "FSTOCK_ID");
        public final static Property FBatchNo = new Property(15, String.class, "FBatchNo", false, "FBATCH_NO");
        public final static Property FBaseCanreturnQty = new Property(16, String.class, "FBaseCanreturnQty", false, "FBASE_CANRETURN_QTY");
        public final static Property FHuoZhuNumber = new Property(17, String.class, "FHuoZhuNumber", false, "FHUO_ZHU_NUMBER");
        public final static Property AuxSign = new Property(18, String.class, "AuxSign", false, "AUX_SIGN");
        public final static Property ActualModel = new Property(19, String.class, "ActualModel", false, "ACTUAL_MODEL");
        public final static Property FPriceUnitID = new Property(20, String.class, "FPriceUnitID", false, "FPRICE_UNIT_ID");
        public final static Property FTaxRate = new Property(21, String.class, "FTaxRate", false, "FTAX_RATE");
        public final static Property FIsGift = new Property(22, String.class, "FIsGift", false, "FIS_GIFT");
        public final static Property FStorageOutID = new Property(23, String.class, "FStorageOutID", false, "FSTORAGE_OUT_ID");
        public final static Property FStorageInID = new Property(24, String.class, "FStorageInID", false, "FSTORAGE_IN_ID");
        public final static Property FOrgOutID = new Property(25, String.class, "FOrgOutID", false, "FORG_OUT_ID");
        public final static Property FOrgInID = new Property(26, String.class, "FOrgInID", false, "FORG_IN_ID");
        public final static Property FHuozhuOutID = new Property(27, String.class, "FHuozhuOutID", false, "FHUOZHU_OUT_ID");
        public final static Property FHuozhuInID = new Property(28, String.class, "FHuozhuInID", false, "FHUOZHU_IN_ID");
        public final static Property FNeedOrgID = new Property(29, String.class, "FNeedOrgID", false, "FNEED_ORG_ID");
        public final static Property FLevel = new Property(30, String.class, "FLevel", false, "FLEVEL");
        public final static Property FYmLenght = new Property(31, String.class, "FYmLenght", false, "FYM_LENGHT");
        public final static Property FYmDiameter = new Property(32, String.class, "FYmDiameter", false, "FYM_DIAMETER");
        public final static Property FBLenght = new Property(33, String.class, "FBLenght", false, "FBLENGHT");
        public final static Property FBWide = new Property(34, String.class, "FBWide", false, "FBWIDE");
        public final static Property FBThick = new Property(35, String.class, "FBThick", false, "FBTHICK");
        public final static Property FIsPrint = new Property(36, String.class, "FIsPrint", false, "FIS_PRINT");
        public final static Property FWide = new Property(37, String.class, "FWide", false, "FWIDE");
        public final static Property FM3 = new Property(38, String.class, "FM3", false, "FM3");
        public final static Property FPCS = new Property(39, String.class, "FPCS", false, "FPCS");
        public final static Property FStr1 = new Property(40, String.class, "FStr1", false, "FSTR1");
        public final static Property FStr2 = new Property(41, String.class, "FStr2", false, "FSTR2");
        public final static Property FStr3 = new Property(42, String.class, "FStr3", false, "FSTR3");
        public final static Property FStr4 = new Property(43, String.class, "FStr4", false, "FSTR4");
        public final static Property FStr5 = new Property(44, String.class, "FStr5", false, "FSTR5");
        public final static Property FAuxQty = new Property(45, String.class, "FAuxQty", false, "FAUX_QTY");
        public final static Property FAuxQtying = new Property(46, String.class, "FAuxQtying", false, "FAUX_QTYING");
        public final static Property FCreateDate = new Property(47, String.class, "FCreateDate", false, "FCREATE_DATE");
    }


    public PushDownSubDao(DaoConfig config) {
        super(config);
    }
    
    public PushDownSubDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PUSH_DOWN_SUB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FSEQ\" TEXT," + // 1: FSEQ
                "\"FID\" TEXT," + // 2: FID
                "\"FMATERIAL_ID\" TEXT," + // 3: FMaterialID
                "\"FACCOUNT_ID\" TEXT," + // 4: FAccountID
                "\"FENTRY_ID\" TEXT," + // 5: FEntryID
                "\"FUNIT_ID\" TEXT," + // 6: FUnitID
                "\"FNUMBER\" TEXT," + // 7: FNumber
                "\"FNAME\" TEXT," + // 8: FName
                "\"FMODEL\" TEXT," + // 9: FModel
                "\"FBILL_NO\" TEXT," + // 10: FBillNo
                "\"FQTY\" TEXT," + // 11: FQty
                "\"FQTYING\" TEXT," + // 12: FQtying
                "\"FTAX_PRICE\" TEXT," + // 13: FTaxPrice
                "\"FSTOCK_ID\" TEXT," + // 14: FStockID
                "\"FBATCH_NO\" TEXT," + // 15: FBatchNo
                "\"FBASE_CANRETURN_QTY\" TEXT," + // 16: FBaseCanreturnQty
                "\"FHUO_ZHU_NUMBER\" TEXT," + // 17: FHuoZhuNumber
                "\"AUX_SIGN\" TEXT," + // 18: AuxSign
                "\"ACTUAL_MODEL\" TEXT," + // 19: ActualModel
                "\"FPRICE_UNIT_ID\" TEXT," + // 20: FPriceUnitID
                "\"FTAX_RATE\" TEXT," + // 21: FTaxRate
                "\"FIS_GIFT\" TEXT," + // 22: FIsGift
                "\"FSTORAGE_OUT_ID\" TEXT," + // 23: FStorageOutID
                "\"FSTORAGE_IN_ID\" TEXT," + // 24: FStorageInID
                "\"FORG_OUT_ID\" TEXT," + // 25: FOrgOutID
                "\"FORG_IN_ID\" TEXT," + // 26: FOrgInID
                "\"FHUOZHU_OUT_ID\" TEXT," + // 27: FHuozhuOutID
                "\"FHUOZHU_IN_ID\" TEXT," + // 28: FHuozhuInID
                "\"FNEED_ORG_ID\" TEXT," + // 29: FNeedOrgID
                "\"FLEVEL\" TEXT," + // 30: FLevel
                "\"FYM_LENGHT\" TEXT," + // 31: FYmLenght
                "\"FYM_DIAMETER\" TEXT," + // 32: FYmDiameter
                "\"FBLENGHT\" TEXT," + // 33: FBLenght
                "\"FBWIDE\" TEXT," + // 34: FBWide
                "\"FBTHICK\" TEXT," + // 35: FBThick
                "\"FIS_PRINT\" TEXT," + // 36: FIsPrint
                "\"FWIDE\" TEXT," + // 37: FWide
                "\"FM3\" TEXT," + // 38: FM3
                "\"FPCS\" TEXT," + // 39: FPCS
                "\"FSTR1\" TEXT," + // 40: FStr1
                "\"FSTR2\" TEXT," + // 41: FStr2
                "\"FSTR3\" TEXT," + // 42: FStr3
                "\"FSTR4\" TEXT," + // 43: FStr4
                "\"FSTR5\" TEXT," + // 44: FStr5
                "\"FAUX_QTY\" TEXT," + // 45: FAuxQty
                "\"FAUX_QTYING\" TEXT," + // 46: FAuxQtying
                "\"FCREATE_DATE\" TEXT);"); // 47: FCreateDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PUSH_DOWN_SUB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PushDownSub entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String FSEQ = entity.getFSEQ();
        if (FSEQ != null) {
            stmt.bindString(2, FSEQ);
        }
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(3, FID);
        }
 
        String FMaterialID = entity.getFMaterialID();
        if (FMaterialID != null) {
            stmt.bindString(4, FMaterialID);
        }
 
        String FAccountID = entity.getFAccountID();
        if (FAccountID != null) {
            stmt.bindString(5, FAccountID);
        }
 
        String FEntryID = entity.getFEntryID();
        if (FEntryID != null) {
            stmt.bindString(6, FEntryID);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(7, FUnitID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(8, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(9, FName);
        }
 
        String FModel = entity.getFModel();
        if (FModel != null) {
            stmt.bindString(10, FModel);
        }
 
        String FBillNo = entity.getFBillNo();
        if (FBillNo != null) {
            stmt.bindString(11, FBillNo);
        }
 
        String FQty = entity.getFQty();
        if (FQty != null) {
            stmt.bindString(12, FQty);
        }
 
        String FQtying = entity.getFQtying();
        if (FQtying != null) {
            stmt.bindString(13, FQtying);
        }
 
        String FTaxPrice = entity.getFTaxPrice();
        if (FTaxPrice != null) {
            stmt.bindString(14, FTaxPrice);
        }
 
        String FStockID = entity.getFStockID();
        if (FStockID != null) {
            stmt.bindString(15, FStockID);
        }
 
        String FBatchNo = entity.getFBatchNo();
        if (FBatchNo != null) {
            stmt.bindString(16, FBatchNo);
        }
 
        String FBaseCanreturnQty = entity.getFBaseCanreturnQty();
        if (FBaseCanreturnQty != null) {
            stmt.bindString(17, FBaseCanreturnQty);
        }
 
        String FHuoZhuNumber = entity.getFHuoZhuNumber();
        if (FHuoZhuNumber != null) {
            stmt.bindString(18, FHuoZhuNumber);
        }
 
        String AuxSign = entity.getAuxSign();
        if (AuxSign != null) {
            stmt.bindString(19, AuxSign);
        }
 
        String ActualModel = entity.getActualModel();
        if (ActualModel != null) {
            stmt.bindString(20, ActualModel);
        }
 
        String FPriceUnitID = entity.getFPriceUnitID();
        if (FPriceUnitID != null) {
            stmt.bindString(21, FPriceUnitID);
        }
 
        String FTaxRate = entity.getFTaxRate();
        if (FTaxRate != null) {
            stmt.bindString(22, FTaxRate);
        }
 
        String FIsGift = entity.getFIsGift();
        if (FIsGift != null) {
            stmt.bindString(23, FIsGift);
        }
 
        String FStorageOutID = entity.getFStorageOutID();
        if (FStorageOutID != null) {
            stmt.bindString(24, FStorageOutID);
        }
 
        String FStorageInID = entity.getFStorageInID();
        if (FStorageInID != null) {
            stmt.bindString(25, FStorageInID);
        }
 
        String FOrgOutID = entity.getFOrgOutID();
        if (FOrgOutID != null) {
            stmt.bindString(26, FOrgOutID);
        }
 
        String FOrgInID = entity.getFOrgInID();
        if (FOrgInID != null) {
            stmt.bindString(27, FOrgInID);
        }
 
        String FHuozhuOutID = entity.getFHuozhuOutID();
        if (FHuozhuOutID != null) {
            stmt.bindString(28, FHuozhuOutID);
        }
 
        String FHuozhuInID = entity.getFHuozhuInID();
        if (FHuozhuInID != null) {
            stmt.bindString(29, FHuozhuInID);
        }
 
        String FNeedOrgID = entity.getFNeedOrgID();
        if (FNeedOrgID != null) {
            stmt.bindString(30, FNeedOrgID);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(31, FLevel);
        }
 
        String FYmLenght = entity.getFYmLenght();
        if (FYmLenght != null) {
            stmt.bindString(32, FYmLenght);
        }
 
        String FYmDiameter = entity.getFYmDiameter();
        if (FYmDiameter != null) {
            stmt.bindString(33, FYmDiameter);
        }
 
        String FBLenght = entity.getFBLenght();
        if (FBLenght != null) {
            stmt.bindString(34, FBLenght);
        }
 
        String FBWide = entity.getFBWide();
        if (FBWide != null) {
            stmt.bindString(35, FBWide);
        }
 
        String FBThick = entity.getFBThick();
        if (FBThick != null) {
            stmt.bindString(36, FBThick);
        }
 
        String FIsPrint = entity.getFIsPrint();
        if (FIsPrint != null) {
            stmt.bindString(37, FIsPrint);
        }
 
        String FWide = entity.getFWide();
        if (FWide != null) {
            stmt.bindString(38, FWide);
        }
 
        String FM3 = entity.getFM3();
        if (FM3 != null) {
            stmt.bindString(39, FM3);
        }
 
        String FPCS = entity.getFPCS();
        if (FPCS != null) {
            stmt.bindString(40, FPCS);
        }
 
        String FStr1 = entity.getFStr1();
        if (FStr1 != null) {
            stmt.bindString(41, FStr1);
        }
 
        String FStr2 = entity.getFStr2();
        if (FStr2 != null) {
            stmt.bindString(42, FStr2);
        }
 
        String FStr3 = entity.getFStr3();
        if (FStr3 != null) {
            stmt.bindString(43, FStr3);
        }
 
        String FStr4 = entity.getFStr4();
        if (FStr4 != null) {
            stmt.bindString(44, FStr4);
        }
 
        String FStr5 = entity.getFStr5();
        if (FStr5 != null) {
            stmt.bindString(45, FStr5);
        }
 
        String FAuxQty = entity.getFAuxQty();
        if (FAuxQty != null) {
            stmt.bindString(46, FAuxQty);
        }
 
        String FAuxQtying = entity.getFAuxQtying();
        if (FAuxQtying != null) {
            stmt.bindString(47, FAuxQtying);
        }
 
        String FCreateDate = entity.getFCreateDate();
        if (FCreateDate != null) {
            stmt.bindString(48, FCreateDate);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PushDownSub entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String FSEQ = entity.getFSEQ();
        if (FSEQ != null) {
            stmt.bindString(2, FSEQ);
        }
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(3, FID);
        }
 
        String FMaterialID = entity.getFMaterialID();
        if (FMaterialID != null) {
            stmt.bindString(4, FMaterialID);
        }
 
        String FAccountID = entity.getFAccountID();
        if (FAccountID != null) {
            stmt.bindString(5, FAccountID);
        }
 
        String FEntryID = entity.getFEntryID();
        if (FEntryID != null) {
            stmt.bindString(6, FEntryID);
        }
 
        String FUnitID = entity.getFUnitID();
        if (FUnitID != null) {
            stmt.bindString(7, FUnitID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(8, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(9, FName);
        }
 
        String FModel = entity.getFModel();
        if (FModel != null) {
            stmt.bindString(10, FModel);
        }
 
        String FBillNo = entity.getFBillNo();
        if (FBillNo != null) {
            stmt.bindString(11, FBillNo);
        }
 
        String FQty = entity.getFQty();
        if (FQty != null) {
            stmt.bindString(12, FQty);
        }
 
        String FQtying = entity.getFQtying();
        if (FQtying != null) {
            stmt.bindString(13, FQtying);
        }
 
        String FTaxPrice = entity.getFTaxPrice();
        if (FTaxPrice != null) {
            stmt.bindString(14, FTaxPrice);
        }
 
        String FStockID = entity.getFStockID();
        if (FStockID != null) {
            stmt.bindString(15, FStockID);
        }
 
        String FBatchNo = entity.getFBatchNo();
        if (FBatchNo != null) {
            stmt.bindString(16, FBatchNo);
        }
 
        String FBaseCanreturnQty = entity.getFBaseCanreturnQty();
        if (FBaseCanreturnQty != null) {
            stmt.bindString(17, FBaseCanreturnQty);
        }
 
        String FHuoZhuNumber = entity.getFHuoZhuNumber();
        if (FHuoZhuNumber != null) {
            stmt.bindString(18, FHuoZhuNumber);
        }
 
        String AuxSign = entity.getAuxSign();
        if (AuxSign != null) {
            stmt.bindString(19, AuxSign);
        }
 
        String ActualModel = entity.getActualModel();
        if (ActualModel != null) {
            stmt.bindString(20, ActualModel);
        }
 
        String FPriceUnitID = entity.getFPriceUnitID();
        if (FPriceUnitID != null) {
            stmt.bindString(21, FPriceUnitID);
        }
 
        String FTaxRate = entity.getFTaxRate();
        if (FTaxRate != null) {
            stmt.bindString(22, FTaxRate);
        }
 
        String FIsGift = entity.getFIsGift();
        if (FIsGift != null) {
            stmt.bindString(23, FIsGift);
        }
 
        String FStorageOutID = entity.getFStorageOutID();
        if (FStorageOutID != null) {
            stmt.bindString(24, FStorageOutID);
        }
 
        String FStorageInID = entity.getFStorageInID();
        if (FStorageInID != null) {
            stmt.bindString(25, FStorageInID);
        }
 
        String FOrgOutID = entity.getFOrgOutID();
        if (FOrgOutID != null) {
            stmt.bindString(26, FOrgOutID);
        }
 
        String FOrgInID = entity.getFOrgInID();
        if (FOrgInID != null) {
            stmt.bindString(27, FOrgInID);
        }
 
        String FHuozhuOutID = entity.getFHuozhuOutID();
        if (FHuozhuOutID != null) {
            stmt.bindString(28, FHuozhuOutID);
        }
 
        String FHuozhuInID = entity.getFHuozhuInID();
        if (FHuozhuInID != null) {
            stmt.bindString(29, FHuozhuInID);
        }
 
        String FNeedOrgID = entity.getFNeedOrgID();
        if (FNeedOrgID != null) {
            stmt.bindString(30, FNeedOrgID);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(31, FLevel);
        }
 
        String FYmLenght = entity.getFYmLenght();
        if (FYmLenght != null) {
            stmt.bindString(32, FYmLenght);
        }
 
        String FYmDiameter = entity.getFYmDiameter();
        if (FYmDiameter != null) {
            stmt.bindString(33, FYmDiameter);
        }
 
        String FBLenght = entity.getFBLenght();
        if (FBLenght != null) {
            stmt.bindString(34, FBLenght);
        }
 
        String FBWide = entity.getFBWide();
        if (FBWide != null) {
            stmt.bindString(35, FBWide);
        }
 
        String FBThick = entity.getFBThick();
        if (FBThick != null) {
            stmt.bindString(36, FBThick);
        }
 
        String FIsPrint = entity.getFIsPrint();
        if (FIsPrint != null) {
            stmt.bindString(37, FIsPrint);
        }
 
        String FWide = entity.getFWide();
        if (FWide != null) {
            stmt.bindString(38, FWide);
        }
 
        String FM3 = entity.getFM3();
        if (FM3 != null) {
            stmt.bindString(39, FM3);
        }
 
        String FPCS = entity.getFPCS();
        if (FPCS != null) {
            stmt.bindString(40, FPCS);
        }
 
        String FStr1 = entity.getFStr1();
        if (FStr1 != null) {
            stmt.bindString(41, FStr1);
        }
 
        String FStr2 = entity.getFStr2();
        if (FStr2 != null) {
            stmt.bindString(42, FStr2);
        }
 
        String FStr3 = entity.getFStr3();
        if (FStr3 != null) {
            stmt.bindString(43, FStr3);
        }
 
        String FStr4 = entity.getFStr4();
        if (FStr4 != null) {
            stmt.bindString(44, FStr4);
        }
 
        String FStr5 = entity.getFStr5();
        if (FStr5 != null) {
            stmt.bindString(45, FStr5);
        }
 
        String FAuxQty = entity.getFAuxQty();
        if (FAuxQty != null) {
            stmt.bindString(46, FAuxQty);
        }
 
        String FAuxQtying = entity.getFAuxQtying();
        if (FAuxQtying != null) {
            stmt.bindString(47, FAuxQtying);
        }
 
        String FCreateDate = entity.getFCreateDate();
        if (FCreateDate != null) {
            stmt.bindString(48, FCreateDate);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PushDownSub readEntity(Cursor cursor, int offset) {
        PushDownSub entity = new PushDownSub( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FSEQ
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FMaterialID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FAccountID
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // FEntryID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // FUnitID
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // FNumber
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // FName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // FModel
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // FBillNo
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // FQty
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // FQtying
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // FTaxPrice
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // FStockID
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // FBatchNo
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // FBaseCanreturnQty
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // FHuoZhuNumber
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // AuxSign
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // ActualModel
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // FPriceUnitID
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // FTaxRate
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // FIsGift
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // FStorageOutID
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // FStorageInID
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // FOrgOutID
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // FOrgInID
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // FHuozhuOutID
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // FHuozhuInID
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // FNeedOrgID
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // FLevel
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // FYmLenght
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // FYmDiameter
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // FBLenght
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // FBWide
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // FBThick
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // FIsPrint
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // FWide
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // FM3
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // FPCS
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // FStr1
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // FStr2
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // FStr3
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // FStr4
            cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // FStr5
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45), // FAuxQty
            cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46), // FAuxQtying
            cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47) // FCreateDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PushDownSub entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFSEQ(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFMaterialID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFAccountID(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFEntryID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFUnitID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFModel(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFBillNo(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setFQty(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setFQtying(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setFTaxPrice(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setFStockID(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setFBatchNo(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setFBaseCanreturnQty(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setFHuoZhuNumber(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setAuxSign(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setActualModel(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setFPriceUnitID(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setFTaxRate(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setFIsGift(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setFStorageOutID(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setFStorageInID(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setFOrgOutID(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setFOrgInID(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setFHuozhuOutID(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setFHuozhuInID(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setFNeedOrgID(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setFLevel(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setFYmLenght(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setFYmDiameter(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setFBLenght(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setFBWide(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setFBThick(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setFIsPrint(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setFWide(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setFM3(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setFPCS(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setFStr1(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setFStr2(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setFStr3(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setFStr4(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setFStr5(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setFAuxQty(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
        entity.setFAuxQtying(cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46));
        entity.setFCreateDate(cursor.isNull(offset + 47) ? null : cursor.getString(offset + 47));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PushDownSub entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PushDownSub entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PushDownSub entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
