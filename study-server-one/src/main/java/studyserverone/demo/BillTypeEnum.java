package studyserverone.demo;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public enum BillTypeEnum {
  //入库类型(4种),现有列:编码保持统一
  SCM_IN_TYPE_TRANSFER(921, "调拨入库", true), SCM_IN_TYPE_PROFIT(913, "盘盈入库", true),
  SCM_IN_TYPE_OTHER(914, "其他入库", true), SCM_IN_TYPE_PURCHASE(912, "采购入库", true),
  SCM_IN_TYPE_ZERO(0, "核减补充0入库", true),
  
  //出库类型(5种),现有列:编码保持统一
  SCM_OUT_TYPE_TRANSFER(922, "调拨出库", false), SCM_OUT_TYPE_RETURNM(917, "报损出库(手动)", false),
  SCM_OUT_TYPE_OTHER(919, "其他出库", false), SCM_OUT_TYPE_SALE(916, "销售出库", false),
  SCM_OUT_TYPE_INVENTORY_LOSS(915, "盘亏出库", false),
  
  //-----------------------json类型------------------------------------
  //json扩展类型
  //入库的(20种)
  SCM_IN_TYPE_BACK(942, "配送退货入库", "backInNum", "backInAmt", "dualBackInNum", true),
  SCM_IN_TYPE_DIS_WRITEOFF(950, "配送冲销入库(总部配送差异冲销入库)", "disWriteoffInNum", "disWriteoffInAmt", "dualDisWriteoffInNum", true),
  SCM_IN_TYPE_PRESENT(952, "赠品入库", "presentInNum", "presentInAmt", "dualPresentInNum", true),
  SCM_IN_TYPE_RECLAIM(991, "回收入库", "reclaimInNum", "reclaimInAmt", "dualReclaimInNum", true),
  SCM_IN_TYPE_SALEPROXY(954, "代销入库", "saleProxyInNum", "saleProxyInAmt", "dualSaleProxyInNum", true),
  SCM_IN_TYPE_PRODUCT(955, "产品入库", "productInNum", "productInAmt", "dualProductInNum", true),
  SCM_IN_TYPE_WRITEOFF(956, "冲销入库(手动)", "writeoffInNum", "writeoffInAmt", "dualWriteoffInNum", true),
  SCM_IN_TYPE_SALE_BACK(988, "销售退货", "saleBackNum", "saleBackAmt", "dualSaleBackNum", true),
  SCM_IN_TYPE_SALE(989, "销售入库", "saleInNum", "saleInAmt", "dualSaleInNum", true),
  SCM_IN_TYPE_STALL_SUPPLY(958, "档口报货", "stallSupplyNum", "stallSupplyAmt", "dualStallSupplyNum", true),
  SCM_IN_TYPE_STORE_SUPPLY(959, "门店报货", "storeSupplyNum", "storeSupplyAmt", "dualStoreSupplyNum", true),
  SCM_IN_TYPE_DIF(963, "差异入库(配送差异)", "difInNum", "difInAmt", "dualDifInNum", true),
  SCM_IN_TYPE_DIR(938, "直运入库", "dirInNum", "dirInAmt", "dualDirInNum", true),
  SCM_IN_TYPE_DIS(937, "配送入库", "disInNum", "disInAmt", "dualDisInNum", true),
  SCM_IN_TYPE_TRANS_WRITEOFF(966, "调拨冲销入库(调拨差异入库)", "transWriteoffInNum", "transWriteoffInAmt", "dualTransWriteoffInNum", true),
  SCM_IN_TYPE_INIT(953, "期初入库", "initInNum", "initInAmt", "dualInitInNum", true),
  SCM_IN_TYPE_COUNTER(923, "反结账入库", "counterInNum", "counterInAmt", "dualCounterInNum", true),
  SCM_IN_TYPE_CANCEL_DISHES(924, "取消菜品入库", "cancelDishesInNum", "cancelDishesInAmt", "dualCancelDishesInNum", true),
  SCM_IN_TYPE_RETURN_DISHES(925, "退菜入库", "returnDishesInNum", "returnDishesInAmt", "dualReturnDishesInNum", true),
  SCM_IN_TYPE_TAKE_MATERISLS(992, "领料入库", "takeMaterislsInNum", "takeMaterislsInAmt", "dualTakeMaterislsInNum", true),
  SCM_IN_TYPE_DIRECT_SEND(999, "直送入库", "directSendInNum", "directSendInAmt", "dualDirectSendInNum", true),
  SCM_IN_TYPE_TAKE_MATERISLS_BACK(994, "领料退库入库", "takeMaterislsBackInNum", "takeMaterislsBackInAmt", "dualTakeMaterislsBackInNum", true),
  SCM_IN_TYPE_DIRECT_SEND_BACK(970, "直送退货入库", "directSendBackInNum", "directSendBackInAmt", "dualDirectSendBackInNum", true),
  
  //出库的(17种)
  SCM_OUT_TYPE_BACK(940, "退货出库（手动）", "backOutNum", "backOutAmt", "dualBackOutNum", false),
  SCM_OUT_TYPE_DIF_LOSS(945, "差异报损出库", "difLossOutNum", "difLossOutAmt", "dualDifLossOutNum", false),
  SCM_OUT_TYPE_PRESENT(980, "赠品出库", "presentOutNum", "presentOutAmt", "dualPresentOutNum", false),
  SCM_OUT_TYPE_SALE_PROXY(981, "代销出库", "saleProxyOutNum", "saleProxyOutAmt", "dualSaleProxyOutNum", false),
  SCM_OUT_TYPE_WRITEOFF(982, "冲销出库", "writeoffOutNum", "writeoffOutAmt", "dualWriteoffOutNum", false),
  SCM_OUT_TYPE_BATCH(983, "批次出库", "batchOutNum", "batchOutAmt", "dualBatchOutNum", false),
  SCM_OUT_TYPE_STAFF_MEAL(984, "员工餐出库", "staffMealOutNum", "staffMealOutAmt", "dualStaffMealOutNum", false),
  SCM_OUT_TYPE_TAKE_MATERISLS(985, "领料出库", "takeMaterislsOutNum", "takeMaterislsOutAmt", "dualTakeMaterislsOutNum", false),
  SCM_OUT_TYPE_PRODUCT(986, "产品出库", "productOutNum", "productOutAmt", "dualProductOutNum", false),
  SCM_OUT_TYPE_SALE_ORDER(987, "销售出库", "saleOrderNum", "saleOrderAmt", "dualSaleOrderNum", false),
  SCM_OUT_TYPE_SELL_OUT(990, "消耗出库", "sellOutNum", "sellOutAmt", "dualSellOutNum", false),
  SCM_OUT_TYPE_PRODUCTION(993, "生产耗用", "productionOutNum", "productionOutAmt", "dualProductionOutNum", false),
  SCM_OUT_TYPE_DIS(939, "配送出库", "disOutNum", "disOutAmt", "dualDisOutNum", false),
  SCM_OUT_TYPE_PUR_RET(960, "采购退货出库", "purRetOutNum", "purRetOutAmt", "dualPurRetOutNum", false),
  SCM_OUT_TYPE_DIR_RET(961, "直运退货出库", "dirRetOutNum", "dirRetOutAmt", "dualDirRetOutNum", false),
  SCM_OUT_TYPE_DISDIF_RET(962, "配送退货出库", "disdifRetOutNum", "disdifRetOutAmt", "dualDisdifRetOutNum", false),
  SCM_OUT_TYPE_DIS_DIF(967, "配送差异出库", "disDifOutNum", "disDifOutAmt", "dualDisDifOutNum", false),
  //这个地方一开始弄错了，生产退料是入库，之前写成出库，属性也不对（暂时不改变，已参与业务逻辑），最后的值改为true 因为是入库
  SCM_OUT_TYPE_PROD_RETURN(968, "生产退料入库", "disProdReturnedOrderNum", "disProdReturnedOrderAmt", "dualProdReturnedOrderNum", true),
  SCM_OUT_TYPE_TAKE_MATERISLS_BACK(995, "退料出库", "takeMaterislsBackOutNum", "takeMaterislsBackOutAmt", "dualTakeMaterislsBackOutNum", false),
  SCM_OUT_TYPE_SALE_BACK(996, "销售退货报损出库", "saleBackOutNum", "saleBackOutAmt", "dualSaleBackOutNum", false),
  SCM_OUT_TYPE_DIRECT_SEND(971, "直送出库", "directSendOutNum", "directSendOutAmt", "dualDirectSendOutNum", false),
  // 直送退货出库 972：门店退总部 973：总部退供应商
  SCM_OUT_TYPE_DIRECT_SEND_BACK(972, "直送退货出库", "directSendBackOutApplyOrgNum", "directSendBackOutApplyOrgAmt", "dualDirectSendBackOutApplyOrgNum", false),
  SCM_OUT_TYPE_DIRECT_SEND_BACK_ZB(973, "直送退货出库", "directSendBackOutNum", "directSendBackOutAmt", "dualDirectSendBackOutNum", false);
  
  static Map<Integer, BillTypeEnum> enumMap = new HashMap<Integer, BillTypeEnum>();
  
  static {
    for (BillTypeEnum type : BillTypeEnum.values()) {
      enumMap.put(type.getCode(), type);
      System.out.println(type.name);
        System.out.println(type.name+"(补单)");
    }
  }
  
  /**
   * 单据类型code
   */
  private int code;
  /**
   * 单据类型名称
   */
  private String name;
  /**
   * 是否json格式
   */
  private boolean isJson;
  /**
   * 是json格式时，jsonKey,数量
   */
  private String jsonKeyForNum;
  /**
   * 是json格式时，jsonKey，金额
   */
  private String jsonKeyForAmt;
  /**
   * 是json格式时，jsonKey，辅助数量
   */
  private String jsonKeyForDual;
  /**
   * 是json格式时，出入库标志:入库为ture，出库为false
   * 用来计算日结表期末
   */
  private boolean inOutFlag;
  
  /**
   * 用来定义非json类型
   *
   * @param code
   * @param name
   * @param inOutFlag
   */
  BillTypeEnum(int code, String name, boolean inOutFlag) {
    this.code = code;
    this.name = name;
    this.inOutFlag = inOutFlag;
    this.isJson = false;
  }
  
  /**
   * 用来定义json类型
   *
   * @param code
   * @param name
   * @param jsonKeyForNum
   * @param jsonKeyForAmt
   */
  BillTypeEnum(int code, String name, String jsonKeyForNum, String jsonKeyForAmt, String jsonKeyForDual, boolean inOutFlag) {
    this.code = code;
    this.name = name;
    this.isJson = true;
    this.jsonKeyForNum = jsonKeyForNum;
    this.jsonKeyForAmt = jsonKeyForAmt;
    this.jsonKeyForDual = jsonKeyForDual;
    this.inOutFlag = inOutFlag;
  }
  
  /**
   * 转换器
   *
   * @param code
   * @return
   */
  public static BillTypeEnum getEnum(Integer code) {
    return enumMap.get(code);
  }
}