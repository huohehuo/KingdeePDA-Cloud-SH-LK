package com.fangzuo.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fangzuo.assist.cloud.Beans.AuxSignSecCheckBean;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.BarCode;
import com.fangzuo.assist.cloud.Dao.Bibie;
import com.fangzuo.assist.cloud.Dao.BibieTable;
import com.fangzuo.assist.cloud.Dao.Buyer;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.CountOffBean;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.DryingGetData;
import com.fangzuo.assist.cloud.Dao.Employee;
import com.fangzuo.assist.cloud.Dao.GetGoodsDepartment;
import com.fangzuo.assist.cloud.Dao.InStorageNum;
import com.fangzuo.assist.cloud.Dao.InStoreType;
import com.fangzuo.assist.cloud.Dao.NumberBean;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.PayType;
import com.fangzuo.assist.cloud.Dao.PDMain;
import com.fangzuo.assist.cloud.Dao.PDSub;
import com.fangzuo.assist.cloud.Dao.PGetData;
import com.fangzuo.assist.cloud.Dao.PriceMethod;
import com.fangzuo.assist.cloud.Dao.PrintErrorHistory;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PurchaseMethod;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.ReBoxBean;
import com.fangzuo.assist.cloud.Dao.RemarkData;
import com.fangzuo.assist.cloud.Dao.SaleMan;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.StoreMan;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.User;
import com.fangzuo.assist.cloud.Dao.Wanglaikemu;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.Dao.YuandanType;
import com.fangzuo.assist.cloud.Dao.TempDetil;

import com.fangzuo.greendao.gen.AuxSignSecCheckBeanDao;
import com.fangzuo.greendao.gen.PrintHistoryDao;
import com.fangzuo.greendao.gen.WortPrintDataDao;
import com.fangzuo.greendao.gen.BarCodeDao;
import com.fangzuo.greendao.gen.BibieDao;
import com.fangzuo.greendao.gen.BibieTableDao;
import com.fangzuo.greendao.gen.BuyerDao;
import com.fangzuo.greendao.gen.ClientDao;
import com.fangzuo.greendao.gen.CountOffBeanDao;
import com.fangzuo.greendao.gen.DepartmentDao;
import com.fangzuo.greendao.gen.DryingGetDataDao;
import com.fangzuo.greendao.gen.EmployeeDao;
import com.fangzuo.greendao.gen.GetGoodsDepartmentDao;
import com.fangzuo.greendao.gen.InStorageNumDao;
import com.fangzuo.greendao.gen.InStoreTypeDao;
import com.fangzuo.greendao.gen.NumberBeanDao;
import com.fangzuo.greendao.gen.OrgDao;
import com.fangzuo.greendao.gen.PayTypeDao;
import com.fangzuo.greendao.gen.PDMainDao;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.PGetDataDao;
import com.fangzuo.greendao.gen.PriceMethodDao;
import com.fangzuo.greendao.gen.PrintErrorHistoryDao;
import com.fangzuo.greendao.gen.ProductDao;
import com.fangzuo.greendao.gen.PurchaseMethodDao;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.ReBoxBeanDao;
import com.fangzuo.greendao.gen.RemarkDataDao;
import com.fangzuo.greendao.gen.SaleManDao;
import com.fangzuo.greendao.gen.StorageDao;
import com.fangzuo.greendao.gen.StoreManDao;
import com.fangzuo.greendao.gen.SuppliersDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.fangzuo.greendao.gen.UnitDao;
import com.fangzuo.greendao.gen.UserDao;
import com.fangzuo.greendao.gen.WanglaikemuDao;
import com.fangzuo.greendao.gen.WaveHouseDao;
import com.fangzuo.greendao.gen.YuandanTypeDao;
import com.fangzuo.greendao.gen.TempDetilDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig auxSignSecCheckBeanDaoConfig;
    private final DaoConfig printHistoryDaoConfig;
    private final DaoConfig wortPrintDataDaoConfig;
    private final DaoConfig barCodeDaoConfig;
    private final DaoConfig bibieDaoConfig;
    private final DaoConfig bibieTableDaoConfig;
    private final DaoConfig buyerDaoConfig;
    private final DaoConfig clientDaoConfig;
    private final DaoConfig countOffBeanDaoConfig;
    private final DaoConfig departmentDaoConfig;
    private final DaoConfig dryingGetDataDaoConfig;
    private final DaoConfig employeeDaoConfig;
    private final DaoConfig getGoodsDepartmentDaoConfig;
    private final DaoConfig inStorageNumDaoConfig;
    private final DaoConfig inStoreTypeDaoConfig;
    private final DaoConfig numberBeanDaoConfig;
    private final DaoConfig orgDaoConfig;
    private final DaoConfig payTypeDaoConfig;
    private final DaoConfig pDMainDaoConfig;
    private final DaoConfig pDSubDaoConfig;
    private final DaoConfig pGetDataDaoConfig;
    private final DaoConfig priceMethodDaoConfig;
    private final DaoConfig printErrorHistoryDaoConfig;
    private final DaoConfig productDaoConfig;
    private final DaoConfig purchaseMethodDaoConfig;
    private final DaoConfig pushDownMainDaoConfig;
    private final DaoConfig pushDownSubDaoConfig;
    private final DaoConfig reBoxBeanDaoConfig;
    private final DaoConfig remarkDataDaoConfig;
    private final DaoConfig saleManDaoConfig;
    private final DaoConfig storageDaoConfig;
    private final DaoConfig storeManDaoConfig;
    private final DaoConfig suppliersDaoConfig;
    private final DaoConfig t_DetailDaoConfig;
    private final DaoConfig t_mainDaoConfig;
    private final DaoConfig unitDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig wanglaikemuDaoConfig;
    private final DaoConfig waveHouseDaoConfig;
    private final DaoConfig yuandanTypeDaoConfig;
    private final DaoConfig tempDetilDaoConfig;

    private final AuxSignSecCheckBeanDao auxSignSecCheckBeanDao;
    private final PrintHistoryDao printHistoryDao;
    private final WortPrintDataDao wortPrintDataDao;
    private final BarCodeDao barCodeDao;
    private final BibieDao bibieDao;
    private final BibieTableDao bibieTableDao;
    private final BuyerDao buyerDao;
    private final ClientDao clientDao;
    private final CountOffBeanDao countOffBeanDao;
    private final DepartmentDao departmentDao;
    private final DryingGetDataDao dryingGetDataDao;
    private final EmployeeDao employeeDao;
    private final GetGoodsDepartmentDao getGoodsDepartmentDao;
    private final InStorageNumDao inStorageNumDao;
    private final InStoreTypeDao inStoreTypeDao;
    private final NumberBeanDao numberBeanDao;
    private final OrgDao orgDao;
    private final PayTypeDao payTypeDao;
    private final PDMainDao pDMainDao;
    private final PDSubDao pDSubDao;
    private final PGetDataDao pGetDataDao;
    private final PriceMethodDao priceMethodDao;
    private final PrintErrorHistoryDao printErrorHistoryDao;
    private final ProductDao productDao;
    private final PurchaseMethodDao purchaseMethodDao;
    private final PushDownMainDao pushDownMainDao;
    private final PushDownSubDao pushDownSubDao;
    private final ReBoxBeanDao reBoxBeanDao;
    private final RemarkDataDao remarkDataDao;
    private final SaleManDao saleManDao;
    private final StorageDao storageDao;
    private final StoreManDao storeManDao;
    private final SuppliersDao suppliersDao;
    private final T_DetailDao t_DetailDao;
    private final T_mainDao t_mainDao;
    private final UnitDao unitDao;
    private final UserDao userDao;
    private final WanglaikemuDao wanglaikemuDao;
    private final WaveHouseDao waveHouseDao;
    private final YuandanTypeDao yuandanTypeDao;
    private final TempDetilDao tempDetilDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        auxSignSecCheckBeanDaoConfig = daoConfigMap.get(AuxSignSecCheckBeanDao.class).clone();
        auxSignSecCheckBeanDaoConfig.initIdentityScope(type);

        printHistoryDaoConfig = daoConfigMap.get(PrintHistoryDao.class).clone();
        printHistoryDaoConfig.initIdentityScope(type);

        wortPrintDataDaoConfig = daoConfigMap.get(WortPrintDataDao.class).clone();
        wortPrintDataDaoConfig.initIdentityScope(type);

        barCodeDaoConfig = daoConfigMap.get(BarCodeDao.class).clone();
        barCodeDaoConfig.initIdentityScope(type);

        bibieDaoConfig = daoConfigMap.get(BibieDao.class).clone();
        bibieDaoConfig.initIdentityScope(type);

        bibieTableDaoConfig = daoConfigMap.get(BibieTableDao.class).clone();
        bibieTableDaoConfig.initIdentityScope(type);

        buyerDaoConfig = daoConfigMap.get(BuyerDao.class).clone();
        buyerDaoConfig.initIdentityScope(type);

        clientDaoConfig = daoConfigMap.get(ClientDao.class).clone();
        clientDaoConfig.initIdentityScope(type);

        countOffBeanDaoConfig = daoConfigMap.get(CountOffBeanDao.class).clone();
        countOffBeanDaoConfig.initIdentityScope(type);

        departmentDaoConfig = daoConfigMap.get(DepartmentDao.class).clone();
        departmentDaoConfig.initIdentityScope(type);

        dryingGetDataDaoConfig = daoConfigMap.get(DryingGetDataDao.class).clone();
        dryingGetDataDaoConfig.initIdentityScope(type);

        employeeDaoConfig = daoConfigMap.get(EmployeeDao.class).clone();
        employeeDaoConfig.initIdentityScope(type);

        getGoodsDepartmentDaoConfig = daoConfigMap.get(GetGoodsDepartmentDao.class).clone();
        getGoodsDepartmentDaoConfig.initIdentityScope(type);

        inStorageNumDaoConfig = daoConfigMap.get(InStorageNumDao.class).clone();
        inStorageNumDaoConfig.initIdentityScope(type);

        inStoreTypeDaoConfig = daoConfigMap.get(InStoreTypeDao.class).clone();
        inStoreTypeDaoConfig.initIdentityScope(type);

        numberBeanDaoConfig = daoConfigMap.get(NumberBeanDao.class).clone();
        numberBeanDaoConfig.initIdentityScope(type);

        orgDaoConfig = daoConfigMap.get(OrgDao.class).clone();
        orgDaoConfig.initIdentityScope(type);

        payTypeDaoConfig = daoConfigMap.get(PayTypeDao.class).clone();
        payTypeDaoConfig.initIdentityScope(type);

        pDMainDaoConfig = daoConfigMap.get(PDMainDao.class).clone();
        pDMainDaoConfig.initIdentityScope(type);

        pDSubDaoConfig = daoConfigMap.get(PDSubDao.class).clone();
        pDSubDaoConfig.initIdentityScope(type);

        pGetDataDaoConfig = daoConfigMap.get(PGetDataDao.class).clone();
        pGetDataDaoConfig.initIdentityScope(type);

        priceMethodDaoConfig = daoConfigMap.get(PriceMethodDao.class).clone();
        priceMethodDaoConfig.initIdentityScope(type);

        printErrorHistoryDaoConfig = daoConfigMap.get(PrintErrorHistoryDao.class).clone();
        printErrorHistoryDaoConfig.initIdentityScope(type);

        productDaoConfig = daoConfigMap.get(ProductDao.class).clone();
        productDaoConfig.initIdentityScope(type);

        purchaseMethodDaoConfig = daoConfigMap.get(PurchaseMethodDao.class).clone();
        purchaseMethodDaoConfig.initIdentityScope(type);

        pushDownMainDaoConfig = daoConfigMap.get(PushDownMainDao.class).clone();
        pushDownMainDaoConfig.initIdentityScope(type);

        pushDownSubDaoConfig = daoConfigMap.get(PushDownSubDao.class).clone();
        pushDownSubDaoConfig.initIdentityScope(type);

        reBoxBeanDaoConfig = daoConfigMap.get(ReBoxBeanDao.class).clone();
        reBoxBeanDaoConfig.initIdentityScope(type);

        remarkDataDaoConfig = daoConfigMap.get(RemarkDataDao.class).clone();
        remarkDataDaoConfig.initIdentityScope(type);

        saleManDaoConfig = daoConfigMap.get(SaleManDao.class).clone();
        saleManDaoConfig.initIdentityScope(type);

        storageDaoConfig = daoConfigMap.get(StorageDao.class).clone();
        storageDaoConfig.initIdentityScope(type);

        storeManDaoConfig = daoConfigMap.get(StoreManDao.class).clone();
        storeManDaoConfig.initIdentityScope(type);

        suppliersDaoConfig = daoConfigMap.get(SuppliersDao.class).clone();
        suppliersDaoConfig.initIdentityScope(type);

        t_DetailDaoConfig = daoConfigMap.get(T_DetailDao.class).clone();
        t_DetailDaoConfig.initIdentityScope(type);

        t_mainDaoConfig = daoConfigMap.get(T_mainDao.class).clone();
        t_mainDaoConfig.initIdentityScope(type);

        unitDaoConfig = daoConfigMap.get(UnitDao.class).clone();
        unitDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        wanglaikemuDaoConfig = daoConfigMap.get(WanglaikemuDao.class).clone();
        wanglaikemuDaoConfig.initIdentityScope(type);

        waveHouseDaoConfig = daoConfigMap.get(WaveHouseDao.class).clone();
        waveHouseDaoConfig.initIdentityScope(type);

        yuandanTypeDaoConfig = daoConfigMap.get(YuandanTypeDao.class).clone();
        yuandanTypeDaoConfig.initIdentityScope(type);

        tempDetilDaoConfig = daoConfigMap.get(TempDetilDao.class).clone();
        tempDetilDaoConfig.initIdentityScope(type);

        auxSignSecCheckBeanDao = new AuxSignSecCheckBeanDao(auxSignSecCheckBeanDaoConfig, this);
        printHistoryDao = new PrintHistoryDao(printHistoryDaoConfig, this);
        wortPrintDataDao = new WortPrintDataDao(wortPrintDataDaoConfig, this);
        barCodeDao = new BarCodeDao(barCodeDaoConfig, this);
        bibieDao = new BibieDao(bibieDaoConfig, this);
        bibieTableDao = new BibieTableDao(bibieTableDaoConfig, this);
        buyerDao = new BuyerDao(buyerDaoConfig, this);
        clientDao = new ClientDao(clientDaoConfig, this);
        countOffBeanDao = new CountOffBeanDao(countOffBeanDaoConfig, this);
        departmentDao = new DepartmentDao(departmentDaoConfig, this);
        dryingGetDataDao = new DryingGetDataDao(dryingGetDataDaoConfig, this);
        employeeDao = new EmployeeDao(employeeDaoConfig, this);
        getGoodsDepartmentDao = new GetGoodsDepartmentDao(getGoodsDepartmentDaoConfig, this);
        inStorageNumDao = new InStorageNumDao(inStorageNumDaoConfig, this);
        inStoreTypeDao = new InStoreTypeDao(inStoreTypeDaoConfig, this);
        numberBeanDao = new NumberBeanDao(numberBeanDaoConfig, this);
        orgDao = new OrgDao(orgDaoConfig, this);
        payTypeDao = new PayTypeDao(payTypeDaoConfig, this);
        pDMainDao = new PDMainDao(pDMainDaoConfig, this);
        pDSubDao = new PDSubDao(pDSubDaoConfig, this);
        pGetDataDao = new PGetDataDao(pGetDataDaoConfig, this);
        priceMethodDao = new PriceMethodDao(priceMethodDaoConfig, this);
        printErrorHistoryDao = new PrintErrorHistoryDao(printErrorHistoryDaoConfig, this);
        productDao = new ProductDao(productDaoConfig, this);
        purchaseMethodDao = new PurchaseMethodDao(purchaseMethodDaoConfig, this);
        pushDownMainDao = new PushDownMainDao(pushDownMainDaoConfig, this);
        pushDownSubDao = new PushDownSubDao(pushDownSubDaoConfig, this);
        reBoxBeanDao = new ReBoxBeanDao(reBoxBeanDaoConfig, this);
        remarkDataDao = new RemarkDataDao(remarkDataDaoConfig, this);
        saleManDao = new SaleManDao(saleManDaoConfig, this);
        storageDao = new StorageDao(storageDaoConfig, this);
        storeManDao = new StoreManDao(storeManDaoConfig, this);
        suppliersDao = new SuppliersDao(suppliersDaoConfig, this);
        t_DetailDao = new T_DetailDao(t_DetailDaoConfig, this);
        t_mainDao = new T_mainDao(t_mainDaoConfig, this);
        unitDao = new UnitDao(unitDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        wanglaikemuDao = new WanglaikemuDao(wanglaikemuDaoConfig, this);
        waveHouseDao = new WaveHouseDao(waveHouseDaoConfig, this);
        yuandanTypeDao = new YuandanTypeDao(yuandanTypeDaoConfig, this);
        tempDetilDao = new TempDetilDao(tempDetilDaoConfig, this);

        registerDao(AuxSignSecCheckBean.class, auxSignSecCheckBeanDao);
        registerDao(PrintHistory.class, printHistoryDao);
        registerDao(WortPrintData.class, wortPrintDataDao);
        registerDao(BarCode.class, barCodeDao);
        registerDao(Bibie.class, bibieDao);
        registerDao(BibieTable.class, bibieTableDao);
        registerDao(Buyer.class, buyerDao);
        registerDao(Client.class, clientDao);
        registerDao(CountOffBean.class, countOffBeanDao);
        registerDao(Department.class, departmentDao);
        registerDao(DryingGetData.class, dryingGetDataDao);
        registerDao(Employee.class, employeeDao);
        registerDao(GetGoodsDepartment.class, getGoodsDepartmentDao);
        registerDao(InStorageNum.class, inStorageNumDao);
        registerDao(InStoreType.class, inStoreTypeDao);
        registerDao(NumberBean.class, numberBeanDao);
        registerDao(Org.class, orgDao);
        registerDao(PayType.class, payTypeDao);
        registerDao(PDMain.class, pDMainDao);
        registerDao(PDSub.class, pDSubDao);
        registerDao(PGetData.class, pGetDataDao);
        registerDao(PriceMethod.class, priceMethodDao);
        registerDao(PrintErrorHistory.class, printErrorHistoryDao);
        registerDao(Product.class, productDao);
        registerDao(PurchaseMethod.class, purchaseMethodDao);
        registerDao(PushDownMain.class, pushDownMainDao);
        registerDao(PushDownSub.class, pushDownSubDao);
        registerDao(ReBoxBean.class, reBoxBeanDao);
        registerDao(RemarkData.class, remarkDataDao);
        registerDao(SaleMan.class, saleManDao);
        registerDao(Storage.class, storageDao);
        registerDao(StoreMan.class, storeManDao);
        registerDao(Suppliers.class, suppliersDao);
        registerDao(T_Detail.class, t_DetailDao);
        registerDao(T_main.class, t_mainDao);
        registerDao(Unit.class, unitDao);
        registerDao(User.class, userDao);
        registerDao(Wanglaikemu.class, wanglaikemuDao);
        registerDao(WaveHouse.class, waveHouseDao);
        registerDao(YuandanType.class, yuandanTypeDao);
        registerDao(TempDetil.class, tempDetilDao);
    }
    
    public void clear() {
        auxSignSecCheckBeanDaoConfig.clearIdentityScope();
        printHistoryDaoConfig.clearIdentityScope();
        wortPrintDataDaoConfig.clearIdentityScope();
        barCodeDaoConfig.clearIdentityScope();
        bibieDaoConfig.clearIdentityScope();
        bibieTableDaoConfig.clearIdentityScope();
        buyerDaoConfig.clearIdentityScope();
        clientDaoConfig.clearIdentityScope();
        countOffBeanDaoConfig.clearIdentityScope();
        departmentDaoConfig.clearIdentityScope();
        dryingGetDataDaoConfig.clearIdentityScope();
        employeeDaoConfig.clearIdentityScope();
        getGoodsDepartmentDaoConfig.clearIdentityScope();
        inStorageNumDaoConfig.clearIdentityScope();
        inStoreTypeDaoConfig.clearIdentityScope();
        numberBeanDaoConfig.clearIdentityScope();
        orgDaoConfig.clearIdentityScope();
        payTypeDaoConfig.clearIdentityScope();
        pDMainDaoConfig.clearIdentityScope();
        pDSubDaoConfig.clearIdentityScope();
        pGetDataDaoConfig.clearIdentityScope();
        priceMethodDaoConfig.clearIdentityScope();
        printErrorHistoryDaoConfig.clearIdentityScope();
        productDaoConfig.clearIdentityScope();
        purchaseMethodDaoConfig.clearIdentityScope();
        pushDownMainDaoConfig.clearIdentityScope();
        pushDownSubDaoConfig.clearIdentityScope();
        reBoxBeanDaoConfig.clearIdentityScope();
        remarkDataDaoConfig.clearIdentityScope();
        saleManDaoConfig.clearIdentityScope();
        storageDaoConfig.clearIdentityScope();
        storeManDaoConfig.clearIdentityScope();
        suppliersDaoConfig.clearIdentityScope();
        t_DetailDaoConfig.clearIdentityScope();
        t_mainDaoConfig.clearIdentityScope();
        unitDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        wanglaikemuDaoConfig.clearIdentityScope();
        waveHouseDaoConfig.clearIdentityScope();
        yuandanTypeDaoConfig.clearIdentityScope();
        tempDetilDaoConfig.clearIdentityScope();
    }

    public AuxSignSecCheckBeanDao getAuxSignSecCheckBeanDao() {
        return auxSignSecCheckBeanDao;
    }

    public PrintHistoryDao getPrintHistoryDao() {
        return printHistoryDao;
    }

    public WortPrintDataDao getWortPrintDataDao() {
        return wortPrintDataDao;
    }

    public BarCodeDao getBarCodeDao() {
        return barCodeDao;
    }

    public BibieDao getBibieDao() {
        return bibieDao;
    }

    public BibieTableDao getBibieTableDao() {
        return bibieTableDao;
    }

    public BuyerDao getBuyerDao() {
        return buyerDao;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public CountOffBeanDao getCountOffBeanDao() {
        return countOffBeanDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public DryingGetDataDao getDryingGetDataDao() {
        return dryingGetDataDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public GetGoodsDepartmentDao getGetGoodsDepartmentDao() {
        return getGoodsDepartmentDao;
    }

    public InStorageNumDao getInStorageNumDao() {
        return inStorageNumDao;
    }

    public InStoreTypeDao getInStoreTypeDao() {
        return inStoreTypeDao;
    }

    public NumberBeanDao getNumberBeanDao() {
        return numberBeanDao;
    }

    public OrgDao getOrgDao() {
        return orgDao;
    }

    public PayTypeDao getPayTypeDao() {
        return payTypeDao;
    }

    public PDMainDao getPDMainDao() {
        return pDMainDao;
    }

    public PDSubDao getPDSubDao() {
        return pDSubDao;
    }

    public PGetDataDao getPGetDataDao() {
        return pGetDataDao;
    }

    public PriceMethodDao getPriceMethodDao() {
        return priceMethodDao;
    }

    public PrintErrorHistoryDao getPrintErrorHistoryDao() {
        return printErrorHistoryDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public PurchaseMethodDao getPurchaseMethodDao() {
        return purchaseMethodDao;
    }

    public PushDownMainDao getPushDownMainDao() {
        return pushDownMainDao;
    }

    public PushDownSubDao getPushDownSubDao() {
        return pushDownSubDao;
    }

    public ReBoxBeanDao getReBoxBeanDao() {
        return reBoxBeanDao;
    }

    public RemarkDataDao getRemarkDataDao() {
        return remarkDataDao;
    }

    public SaleManDao getSaleManDao() {
        return saleManDao;
    }

    public StorageDao getStorageDao() {
        return storageDao;
    }

    public StoreManDao getStoreManDao() {
        return storeManDao;
    }

    public SuppliersDao getSuppliersDao() {
        return suppliersDao;
    }

    public T_DetailDao getT_DetailDao() {
        return t_DetailDao;
    }

    public T_mainDao getT_mainDao() {
        return t_mainDao;
    }

    public UnitDao getUnitDao() {
        return unitDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public WanglaikemuDao getWanglaikemuDao() {
        return wanglaikemuDao;
    }

    public WaveHouseDao getWaveHouseDao() {
        return waveHouseDao;
    }

    public YuandanTypeDao getYuandanTypeDao() {
        return yuandanTypeDao;
    }

    public TempDetilDao getTempDetilDao() {
        return tempDetilDao;
    }

}
