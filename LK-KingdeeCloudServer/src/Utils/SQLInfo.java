package Utils;

public class SQLInfo {
	public static final String GETDATABASE = "SELECT * FROM (SELECT t0.FNUMBER 账套编码, t0_L.FNAME 账套名称, t0.FDATABASENAME as 数据库,  t0.FVISION as 版本, t0.FDATACENTERID as 账套ID, ROW_NUMBER() OVER( ORDER BY t0.FNUMBER ASC) fidentityid FROM T_BAS_DATACENTER t0 LEFT OUTER JOIN T_BAS_DATACENTER_L t0_L ON (t0.FDATACENTERID = t0_L.FDATACENTERID AND t0_L.FLocaleId = 2052) WHERE t0.FISCLOUDDB = '1') tlist WHERE ((fidentityid >= 1) AND (fidentityid <= 200)) OPTION ( MAXDOP 0)";
}
