package com.script;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CreateScript
 * @Description: 生成odps用到的 建表和dataX脚本
 * @Author honghuaao
 * @Date 2019/12/11
 **/
public class CreateScript {


    private static String PREFIX = "boh_base_";
    public static Map<String, String[]> tableName2ColumnMap = new HashMap<String,String[]>();
    static {
        tableName2ColumnMap.put("t_order",getColumnArray(getOrderColumn()));
        //tableName2ColumnMap.put("t_order_detail",getColumnArray(getOrderDetailColumn()));
        //tableName2ColumnMap.put("t_order_pay",getColumnArray(getOrderPayColumn()));
        //tableName2ColumnMap.put("t_order_delproducts",getColumnArray(getOrderDelproductsColumn()));
        //tableName2ColumnMap.put("t_order_itemsexsales",getColumnArray(getOrderItemsexsalesColumn()));
        //tableName2ColumnMap.put("acl_store",getColumnArray(getAclStoreColumn()));
        //tableName2ColumnMap.put("t_food",getColumnArray(getFoodColumn()));
        //tableName2ColumnMap.put("t_order_detail",getColumnArray(ColumnSource.vDetail()));
        //tableName2ColumnMap.put("t_order_pay",getColumnArray(ColumnSource.orderPayColumn()));
        //tableName2ColumnMap.put("t_order_delproducts",getColumnArray(ColumnSource.returnColumn()));
        //tableName2ColumnMap.put("store_operation_data",getColumnArray(ColumnSource.t()));
        //tableName2ColumnMap.put("acl_tenant",getColumnArray(ColumnSource.aclTenantColumn()));
        //tableName2ColumnMap.put("bas_daily_amt_estimate",getColumnArray(ColumnSource.basDaily()));

    }

    public static void main(String[] args) {
       for(Map.Entry<String, String[]> entry:tableName2ColumnMap.entrySet()){
           String tableName = entry.getKey();
           String columnArray[] = entry.getValue();
           String createSqlScript = getCreateSqlScript(tableName,columnArray);
           System.out.println(createSqlScript);
           String createDataXScript = getCreateDataXScript(tableName,columnArray);
           System.out.println(createDataXScript);
       }
    }

    /**
     * split字符串 去掉空格
     * 得到表的所有字段放入数组
     * @param clumnStr
     * @return
     */
    public static String[] getColumnArray(String clumnStr){
        String [] clumnArray = clumnStr.split("\n") ;
        for(int i=0;i<clumnArray.length;i++){
            clumnArray[i]=clumnArray[i].trim();
        }
        return clumnArray;
    }


    public static String getCreateSqlScript(String tableName,String[] clumnArray){

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<clumnArray.length;i++){
            sb.append("  `").append(clumnArray[i]).append("` string");
            if(i<clumnArray.length-1){
                //sb.append(",\n");
                sb.append(",");
            }
        }
        String clumnStr = sb.toString();
        String createSqlScript = templateCreateSql.replace("&targetTableName",PREFIX+tableName);
        createSqlScript = createSqlScript.replace("&clumn",clumnStr);
      return createSqlScript;
    }

    public static String getCreateDataXScript(String tableName,String[] clumnArray){

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<clumnArray.length;i++){
            sb.append("                     \"").append(clumnArray[i]).append("\" ");
            if(i<clumnArray.length-1){
                sb.append(",\n");
            }
        }
        String clumnStr = sb.toString();
        String createDataXScript = templateDataX.replace("&sourceTableName",tableName);
        createDataXScript = createDataXScript.replace("&targetTableName",PREFIX+tableName);
        createDataXScript = createDataXScript.replace("&clumn",clumnStr);
        return createDataXScript;
    }


    public static String getFoodColumn(){

        String temp = "id\n" +
                "dishCode\n" +
                "dishName\n" +
                "phoneticCode\n" +
                "sortId\n" +
                "sellPrice\n" +
                "tenantId\n" +
                "dishImg\n" +
                "dishSort\n" +
                "membPrice\n" +
                "updateTime\n" +
                "theoMargin\n" +
                "dishHeat\n" +
                "unitName\n" +
                "assistUnitName\n" +
                "status\n" +
                "dishIngredients\n" +
                "outBizId\n" +
                "source\n" +
                "ruleIds\n" +
                "cookeryIds\n" +
                "createTime\n" +
                "box_price\n" +
                "prices\n" +
                "discateid\n" +
                "cookery_info\n" +
                "is_show\n" +
                "is_take\n" +
                "is_book\n" +
                "is_featured\n" +
                "is_gum\n" +
                "is_new\n" +
                "is_spicy\n" +
                "description\n" +
                "image_md5\n" +
                "cost\n" +
                "max_count\n" +
                "sale_count_times\n" +
                "is_visrec2\n" +
                "visrec2_price\n" +
                "max_order_number";
        return temp;
    }

    public static String getAclStoreColumn(){
        String temp = "id\n" +
                "tenant_id\n" +
                "code\n" +
                "name\n" +
                "parent_id\n" +
                "is_leaf\n" +
                "country\n" +
                "province\n" +
                "province_name\n" +
                "city\n" +
                "city_name\n" +
                "district\n" +
                "address\n" +
                "status\n" +
                "contact_man\n" +
                "contact_teln\n" +
                "create_user\n" +
                "create_time\n" +
                "update_user\n" +
                "update_time\n" +
                "delete_flag\n" +
                "boh_code\n" +
                "shopid\n" +
                "pid\n" +
                "org_type\n" +
                "source\n" +
                "source_id\n" +
                "parent_source_id\n" +
                "store_desc\n" +
                "brand_name\n" +
                "business_type\n" +
                "area\n" +
                "area_name\n" +
                "email\n" +
                "post_num\n" +
                "park_info\n" +
                "store_short_name\n" +
                "third_code\n" +
                "store_type_name\n" +
                "store_mgr\n" +
                "governor\n" +
                "street\n" +
                "stime\n" +
                "etime\n" +
                "open_time\n" +
                "close_time\n" +
                "breakfast_status\n" +
                "breakfast_stime\n" +
                "breakfast_etime\n" +
                "breakfast_weekday_stime\n" +
                "breakfast_weekday_etime\n" +
                "breakfast_cncode\n" +
                "lunch_status\n" +
                "lunch_stime\n" +
                "lunch_etime\n" +
                "lunch_weekday_stime\n" +
                "lunch_weekday_etime\n" +
                "lunch_cncode\n" +
                "dinner_status\n" +
                "dinner_stime\n" +
                "dinner_etime\n" +
                "dinner_weekday_stime\n" +
                "dinner_weekday_etime\n" +
                "dinner_cncode\n" +
                "morning_tea_status\n" +
                "morning_tea_stime\n" +
                "morning_tea_etime\n" +
                "morning_tea_weekday_stime\n" +
                "morning_tea_weekday_etime\n" +
                "morning_tea_cncode\n" +
                "afternoon_tea_status\n" +
                "afternoon_tea_stime\n" +
                "afternoon_tea_etime\n" +
                "afternoon_tea_weekday_stime\n" +
                "afternoon_tea_weekday_etime\n" +
                "afternoon_tea_cncode\n" +
                "night_status\n" +
                "night_stime\n" +
                "night_etime\n" +
                "night_weekday_stime\n" +
                "night_weekday_etime\n" +
                "night_cncode\n" +
                "spaces\n" +
                "install_time\n" +
                "lobby_size\n" +
                "kitchen_size\n" +
                "table_count\n" +
                "front_num\n" +
                "kitchen_num\n" +
                "style\n" +
                "position\n" +
                "is_storearea_pubitem\n" +
                "paytaxtyp\n" +
                "taxrate\n" +
                "brand_tag_id";
        return temp;
    }
    public static String getOrderItemsexsalesColumn(){
        String temp ="id\n" +
                "tenant_id\n" +
                "vscode\n" +
                "dworkdate\n" +
                "vbcode\n" +
                "vorclass\n" +
                "vposid\n" +
                "vpcode\n" +
                "istate\n" +
                "nfcount\n" +
                "nfmoney\n" +
                "nfymoney\n" +
                "nfzmoney\n" +
                "nservicefee\n" +
                "nyservicefee\n" +
                "nzservicefee\n" +
                "vfcode\n" +
                "vfname\n" +
                "visproduct\n" +
                "vitemid\n" +
                "istc\n" +
                "create_time";
        return temp;
    }
    public static String getOrderDelproductsColumn(){

        String temp = "ID_NO\n" +
                "id\n" +
                "vgroupcode\n" +
                "dworkdate\n" +
                "vbcode\n" +
                "vscode\n" +
                "icount\n" +
                "vpcode\n" +
                "vpname\n" +
                "nprice\n" +
                "vdataged\n" +
                "verrorname\n" +
                "npriceeatin\n" +
                "verrorstring\n" +
                "vdeletetype\n" +
                "vunit\n" +
                "measdoc\n" +
                "pubitem\n" +
                "practice\n" +
                "vorclass\n" +
                "createtime\n" +
                "grptypcode\n" +
                "grptypname\n" +
                "grpcode\n" +
                "grpname\n" +
                "vtypcode\n" +
                "vtypname";
        return temp;
    }
    public static String getOrderPayColumn(){

        String temp = "oid\n" +
                "id\n" +
                "vgroupcode\n" +
                "dworkdate\n" +
                "vbcode\n" +
                "vscode\n" +
                "vypayflag\n" +
                "voperate\n" +
                "vpaymentdes\n" +
                "vactcode\n" +
                "acttypcode\n" +
                "acttypname\n" +
                "acttypmincode\n" +
                "acttypminname\n" +
                "vtypcode\n" +
                "vtypname\n" +
                "isno\n" +
                "VORDERS\n" +
                "vecode\n" +
                "tctime\n" +
                "igroupid\n" +
                "isshow\n" +
                "nrefundamt\n" +
                "npayamt\n" +
                "nmoney\n" +
                "noveramt\n" +
                "nserviceamt\n" +
                "vorclass\n" +
                "createtime\n" +
                "vhungacountno\n" +
                "vhungacountname";
        return temp;

    }
    public static String getOrderDetailColumn(){

        String temp="oid\n" +
                "id\n" +
                "vgroupcode\n" +
                "dworkdate\n" +
                "vbcode\n" +
                "vscode\n" +
                "ordertype\n" +
                "nrestprice\n" +
                "npackcnt\n" +
                "nzmoney\n" +
                "ndiscount\n" +
                "nrefund\n" +
                "nprice\n" +
                "ncount\n" +
                "nfamt\n" +
                "namt\n" +
                "njamt\n" +
                "nymamt\n" +
                "npricestd\n" +
                "ntax\n" +
                "nymoney\n" +
                "nsvcchg\n" +
                "nservicefee\n" +
                "nroomamt\n" +
                "npackdisc\n" +
                "nactymamt\n" +
                "nbzeroamt\n" +
                "vrecode\n" +
                "vpkgtag\n" +
                "vpackcode\n" +
                "vpackno\n" +
                "vpackname\n" +
                "packagetypecode\n" +
                "packagetypename\n" +
                "vvoidrsn\n" +
                "vvoidrsndes\n" +
                "vdataged\n" +
                "vquick\n" +
                "vspecialprice\n" +
                "vhighprice\n" +
                "vdese\n" +
                "grptypcode\n" +
                "grptypname\n" +
                "grpcode\n" +
                "grpname\n" +
                "deptcode\n" +
                "deptname\n" +
                "vpcode\n" +
                "vpname\n" +
                "vunit\n" +
                "measdoc\n" +
                "pubitem\n" +
                "practice\n" +
                "vistemp\n" +
                "vorclass\n" +
                "nzsmoney\n" +
                "nzcount\n" +
                "vgiveaccredit\n" +
                "vzsreason\n" +
                "vtypcode\n" +
                "vtypname\n" +
                "createtime\n" +
                "pubpackattr\n" +
                "vitemid";
        return temp;

    }
    public static String getOrderColumn() {

        String temp = "oid\n" +
                "id\n" +
                "vgroupcode\n" +
                "dworkdate\n" +
                "vbcode\n" +
                "vscode\n" +
                "pk_store\n" +
                "vsname\n" +
                "tstime\n" +
                "brandcode\n" +
                "brandname\n" +
                "marketcode\n" +
                "marketname\n" +
                "bohcode\n" +
                "bohname\n" +
                "governorcode\n" +
                "governorname\n" +
                "sftcode\n" +
                "sftname\n" +
                "ilclass\n" +
                "ilclassname\n" +
                "vorclass\n" +
                "dbrtime\n" +
                "dertime\n" +
                "VTABLENUM\n" +
                "vtbldes\n" +
                "sitetypecode\n" +
                "sitetypename\n" +
                "varea\n" +
                "iclasses\n" +
                "vmemo\n" +
                "vsetupby\n" +
                "dbtabletime\n" +
                "vlastemp\n" +
                "detabletime\n" +
                "verrorstring\n" +
                "vresvtype\n" +
                "vresvtypename\n" +
                "vinputname\n" +
                "icclass\n" +
                "source\n" +
                "voldorderid\n" +
                "vorderid\n" +
                "weeks\n" +
                "isholidy\n" +
                "itblcnt\n" +
                "maxpeople\n" +
                "ipeolenum\n" +
                "tc\n" +
                "namt\n" +
                "nfamt\n" +
                "nzamt\n" +
                "nactymamt\n" +
                "nbzero\n" +
                "nroomamt\n" +
                "nsvcchg\n" +
                "nsvcitem\n" +
                "ntaxitem\n" +
                "ncomp\n" +
                "ntmptsfr\n" +
                "ninvoicemoney\n" +
                "nmoney\n" +
                "nymoney\n" +
                "nactrefundamt\n" +
                "nzmoney\n" +
                "ndiscitem\n" +
                "nrefund\n" +
                "vposid\n" +
                "vjecode\n" +
                "vjename\n" +
                "createtime\n" +
                "outtradeno\n" +
                "ityp\n" +
                "cloud_serialnum\n" +
                "verrorname\n" +
                "icclassname\n" +
                "VTOADDRESS\n" +
                "dtoreceivetime\n" +
                "dtodeliverytime\n" +
                "dtorequiretime\n" +
                "VTOCUSTOMERNAME\n" +
                "vtocustomertel\n" +
                "vopenid\n" +
                "cardno\n" +
                "vcardid\n" +
                "alipayid\n" +
                "vpassengertypecode\n" +
                "vpassengertypename\n" +
                "newservicefee\n" +
                "boxfee\n" +
                "teefee\n" +
                "bottolefee";
        return temp;
    }


    /** 生成建表语句的模板*/
    public static String templateCreateSql = "create table IF NOT EXISTS &targetTableName\n" +
            "(\n" +
            "&clumn\n" +
            ")\n" +
            "PARTITIONED BY (ds string)\n" +
            "LIFECYCLE 7;";

    /** 生成导数据的脚本模板*/
    public static  String templateDataX = "{\n" +
            "    \"type\": \"job\",\n" +
            "    \"steps\": [\n" +
            "        {\n" +
            "            \"stepType\": \"mysql\",\n" +
            "            \"parameter\": {\n" +
            "                \"column\": [\n" +
            "&clumn\n" +
            "                ],\n" +
            "                \"connection\": [\n" +
            "                    {\n" +
            "                        \"datasource\": \"choiceaccount_213\",\n" +
            "                        \"table\": [\n" +
            "                            \"&sourceTableName\"\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"where\": \"\",\n" +
            "                \"splitPk\": \"id\",\n" +
            "                \"encoding\": \"UTF-8\"\n" +
            "            },\n" +
            "            \"name\": \"Reader\",\n" +
            "            \"category\": \"reader\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"stepType\": \"odps\",\n" +
            "            \"parameter\": {\n" +
            "                \"partition\": \"ds=${bizdate}\",\n" +
            "                \"truncate\": false,\n" +
            "                \"datasource\": \"odps_first\",\n" +
            "                \"column\": [\n" +
            "&clumn\n" +
            "                ],\n" +
            "                \"emptyAsNull\": false,\n" +
            "                \"table\": \"&targetTableName\"\n" +
            "            },\n" +
            "            \"name\": \"Writer\",\n" +
            "            \"category\": \"writer\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"version\": \"2.0\",\n" +
            "    \"order\": {\n" +
            "        \"hops\": [\n" +
            "            {\n" +
            "                \"from\": \"Reader\",\n" +
            "                \"to\": \"Writer\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"setting\": {\n" +
            "        \"errorLimit\": {\n" +
            "            \"record\": \"\"\n" +
            "        },\n" +
            "        \"speed\": {\n" +
            "            \"throttle\": true,\n" +
            "            \"concurrent\": 2,\n" +
            "            \"mbps\": \"20\"\n" +
            "        }\n" +
            "    }\n" +
            "}";



}
