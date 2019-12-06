package studyserverone.okhttp3;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.date.DateUtil.offsetDay;
import static cn.hutool.core.date.DateUtil.offsetWeek;

/**
 * @author zhutong
 * @date 2019/9/24 18:59
 */
public class Main {
    
    
    static String weeklySql = "select count(*)   from  scm_stock_flow  where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and is_in_stock = 1\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_stock_flow   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and is_in_stock = 0\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_apply_order   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_dispatch   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_direct   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_procurement   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_order   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_check   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select sum(t.cnt) from (\n" +
                                      "select count(*) as cnt  from  scm_sts_transfer   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "union all\n" +
                                      "select count(*) as cnt  from  scm_transfer   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' ) t\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_sales_order   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n" +
                                      "\n" +
                                      "union all select count(*)   from  scm_store    where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00'\n";
    
    static String allSql = "select count(*)   from  scm_stock_flow  where  create_time<'endDate  00:00:00' and is_in_stock = 1\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_stock_flow   where  create_time<'endDate  00:00:00' and is_in_stock = 0\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_apply_order   where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_dispatch   where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_direct   where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_procurement   where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_order   where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_check   where create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select sum(t.cnt) from (\n" +
                                   "select count(*) as cnt  from  scm_sts_transfer   where  create_time<'endDate  00:00:00'\n" +
                                   "union all\n" +
                                   "select count(*) as cnt  from  scm_transfer   where  create_time<'endDate  00:00:00' ) t\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_sales_order   where create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(*)   from  scm_store    where  create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(distinct tenant_id)   from  scm_store where create_time<'endDate  00:00:00'\n" +
                                   "\n" +
                                   "union all select count(distinct tenant_id)   from  scm_store where create_time<'startDate 00:00:00'\n" +
                                   "\n" +
                                   "union all select count(distinct tenant_id)   from  scm_store where create_time<'lastWeekDate 00:00:00';";
    static String weichaosql = "SELECT tenant_id, store_id,( CASE org_type WHEN 1 THEN '门店' WHEN 2 THEN '总部' WHEN 3 THEN '中央厨房' END ) org_type, ( CASE bill_type WHEN '912' THEN '采购入库' WHEN '913' THEN '盘盈入库'  WHEN '966' THEN '调拨冲销入库(调拨差异入库)' WHEN '914' THEN '其他入库' WHEN '921' THEN '调拨入库' WHEN '923' THEN '反结账入库' WHEN '924' THEN '取消菜品入库' WHEN '925' THEN '退菜入库' WHEN '937' THEN '配送入库' WHEN '938' THEN '直运入库' WHEN '942' THEN '退货入库' WHEN '950' THEN '配送冲销入库' WHEN '963' THEN '配送差异入库' WHEN '951' THEN '赠品入库' WHEN '953' THEN '期初入库' WHEN '954' THEN '代销入库' WHEN '955' THEN '产品入库' WHEN '956' THEN '冲销入库(手动)' WHEN '988' THEN '销售退货入库' WHEN '992' THEN '领料入库' WHEN '968' THEN '生产退料入库' WHEN '999' THEN '直送入库' ELSe bill_type END ) as bill_type, count(id) FROM scm_in WHERE create_time >= 'startDate 00:00:00' AND create_time < 'endDate  00:00:00' AND tenant_id NOT IN ( '5abf001796d190000d07a59a','5da67ab396d1900001938606', 'c5275c0472c44780bae3ddef9297459f', '8707910a997b4b08962ae8490722b267', 'de8548daa7fb4ba7837fb2ca4480decf', '7368e535f270443eb7f6b56005a81f30' ) GROUP BY tenant_id, store_id, org_type, bill_type\n" +
                                       "\n" +
                                       "UNION all \n" +
                                       "\n" +
                                       "SELECT tenant_id, store_id,( CASE org_type WHEN '1' THEN '门店' WHEN '2' THEN '总部' WHEN '3' THEN '中央厨房' END ),( CASE bill_type WHEN '915' THEN '盘亏出库' WHEN '917' THEN '报损出库(手动)' WHEN '919' THEN '其他出库' WHEN '922' THEN '调拨出库' WHEN '939' THEN '配送出库' WHEN '940' THEN '退货出库(手动)' WHEN '945' THEN '差异报损出库' WHEN '960' THEN '采购退货出库' WHEN '961' THEN '直运退货出库' WHEN '962' THEN '配送退货出库' WHEN '967' THEN '配送差异出库' WHEN '987' THEN '销售出库' WHEN '980' THEN '赠品出库' WHEN '981' THEN '代销出库' WHEN '982' THEN '冲消出库' WHEN '983' THEN '批次出库' WHEN '984' THEN '员工餐出库' WHEN '985' THEN '领料出库' WHEN '986' THEN '产品出库' WHEN '993' THEN '消耗出库' WHEN '990' THEN '消耗出库(核减)' END ) bill_type , count(id)FROM scm_out WHERE create_time >= 'startDate 00:00:00' AND create_time < 'endDate  00:00:00' AND tenant_id NOT IN ( '5abf001796d190000d07a59a','5da67ab396d1900001938606', 'c5275c0472c44780bae3ddef9297459f', '8707910a997b4b08962ae8490722b267', 'de8548daa7fb4ba7837fb2ca4480decf', '7368e535f270443eb7f6b56005a81f30' ) GROUP BY tenant_id, store_id, bill_type, org_type\n" +
                                       "\n" +
                                       "UNION all \n" +
                                       "\n" +
                                       "select tenant_id,store_id,'门店' as org_type,'门店请购' as bill_type,count(id)   from  scm_apply_order   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,store_id\n" +
                                       "\n" +
                                       "UNION all \n" +
                                       "\n" +
                                       "select tenant_id,store_id,'总部' as org_type,'配送订单' as bill_type,count(id)   from  scm_dispatch   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,store_id\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,store_id,'门店' as org_type,'直运订单' as bill_type,count(id)  from  scm_direct   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,store_id\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,store_id,'门店' as org_type,'自采订单' as bill_type,count(id) from  scm_procurement   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,store_id\n" +
                                       "\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,org_info_id as store_id,'总部' as org_type,'采购订单' as bill_type,count(id) from  scm_order   where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,org_info_id\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,store_id,(CASE org_type WHEN 1 THEN '门店' WHEN 2 THEN '总部' WHEN 3 THEN '中央厨房' END )as org_type,'盘点单' as bill_type,count(id)   from  scm_check  where  create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') GROUP BY tenant_id,store_id,org_type\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,out_store_id,'门店' as org_type,'门店调拨出' as bill_type,count(id) from scm_sts_transfer where create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') group BY tenant_id,out_store_id\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id,in_store_id,'门店' as org_type,'门店调拨入' as bill_type,count(id) from scm_sts_transfer where create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') group BY tenant_id,in_store_id\n" +
                                       "\n" +
                                       "UNION ALL\n" +
                                       "\n" +
                                       "select tenant_id, store_id, ( CASE org_type WHEN 1 THEN '门店' WHEN 2 THEN '总部' WHEN 3 THEN '中央厨房' END ) org_type,'机构内调拨' as bill_type,count(id) from scm_transfer where create_time>='startDate 00:00:00' and create_time<'endDate  00:00:00' and tenant_id not in ('5abf001796d190000d07a59a','c5275c0472c44780bae3ddef9297459f','8707910a997b4b08962ae8490722b267','de8548daa7fb4ba7837fb2ca4480decf','5d07bc9decc50f0001c1f45b','7368e535f270443eb7f6b56005a81f30','5da67ab396d1900001938606') group BY tenant_id,store_id,org_type;\n";
    
    static JSONObject sqlAddr = JSONObject.parseObject("{\"sql\":\"explain select * from scm_stock_goods_detail where tenant_id = '5b308129360170000172afa6'  and store_id = \\\"6e6f11213dcb415b98713776bdc8bfba\\\" and (buss_date between \\\"2019-08-25\\\" and \\\"2019-09-25\\\")\",\"basename\":\"online_cloud_scm\",\"source\":\"online_scm_select\"}");
    static JSONObject sqlAddr2zhongtai1 = JSONObject.parseObject("{\"sql\":\"select count(*) as total from \\r\\n(select DISTINCT t.tenant_id from bas_rel_application_shopid t where t.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' and t.delete_flag = '1' and t.status = 1) a\\r\\nleft join acl_tenant te on te.id = a.tenant_id\\r\\nwhere \\r\\nte.status = 1 and te.delete_flag = 1\\r\\nand te.id not in (\\r\\n'5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d',\\r\\n'5abf001796d190000d07a59a',\\r\\n'5c89e7c63601700001c461f3',\\r\\n'5d0735d4ecc50f0001c1f454',\\r\\n'5d07bc9decc50f0001c1f45b',\\r\\n'5d07c1827c59920001a7dc61',\\r\\n'5d08f50fecc50f0001c1f4e7',\\r\\n'5d2fea6aecc50f0001c202af',\\r\\n'5d3fe3d796d190000153b109',\\r\\n'5d4c396decc50f0001c20893',\\r\\n'7368e535f270443eb7f6b56005a81f30',\\r\\n'b81c52b170bc4c1eb9fb559131cdef36',\\r\\n'c5275c0472c44780bae3ddef9297459f',\\r\\n'd7a33b1f879c4cc5830d24f8e054ef97');\",\"basename\":\"choiceaccount\",\"source\":\"online_choiceaccount_select\"}");
    static String jwtToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzU2MzcxMjcsIm5hbWUiOiLmnLHlkIwiLCJyb2xlIjoiZ3Vlc3QifQ.YrdO-uLIBK3fk_B0yYCQewXYMMVICB2Bp1P0s3dbAi8";
    static String url = "http://47.98.58.111:18080/api/v2/query";
    
    
    public static void main(String[] args) throws Exception {
    
//        String startDate = DateUtil.formatDate(DateUtil.parseDate("2019-10-04"));
//        String endDate = DateUtil.formatDate(DateUtil.parseDate("2019-10-11"));
//        String endDatew = DateUtil.formatDate(DateUtil.parseDate("2019-10-10"));
        
        
        String startDate = DateUtil.formatDate(DateUtil.lastWeek());
        String startDatew = DateUtil.formatDate(offsetDay(DateUtil.lastWeek(), -1));
        String endDate = DateUtil.today();
        String endDatew = DateUtil.formatDate(offsetDay(new DateTime(), -1));
        String lastweekDate = DateUtil.formatDate(offsetWeek(new DateTime(), -2));
        
        Map<String, Object> resultMap = new HashMap<>();

        //1、求合计
        String allSqls = allSql.replace("endDate", endDate).replace("startDate", startDate).replace("lastWeekDate",lastweekDate);
        total(allSqls,resultMap);

        //求本周
        String weeklySqls = weeklySql.replace("endDate", endDate).replace("startDate", startDate);
        week(weeklySqls,resultMap);

        //
        //求上周
        String lastWeekSql = weeklySql.replace("endDate", startDate).replace("startDate", lastweekDate);
        lastWeek(lastWeekSql,resultMap);

        resultMap.put("week",(startDate + "~" + endDatew).replace("-",""));
        resultMap.put("lastWeek", (lastweekDate+ "~" + startDatew).replace("-",""));

        //处理商户总数本周总计
        totalTenant(endDate,resultMap);
        totalTenantLastWeek(startDate,resultMap);
        totalTenantLastLastWeek(lastweekDate,resultMap);
        resultMap.put("week11",Integer.valueOf(String.valueOf(resultMap.get("total11"))) - Integer.valueOf(String.valueOf(resultMap.get("total13"))));
        resultMap.put("lastWeek11",Integer.valueOf(String.valueOf(resultMap.get("total13"))) - Integer.valueOf(String.valueOf(resultMap.get("total14"))));
        //处理门店总数
        totalStore(endDate,resultMap);
        totalStoreLastWeek(startDate,resultMap);
        totalStoreLastLastWeek(lastweekDate,resultMap);
        resultMap.put("week12",Integer.valueOf(String.valueOf(resultMap.get("total12"))) - Integer.valueOf(String.valueOf(resultMap.get("total15"))));
        resultMap.put("lastWeek12",Integer.valueOf(String.valueOf(resultMap.get("total15"))) - Integer.valueOf(String.valueOf(resultMap.get("total16"))));


        //本周活跃门店
        activeStore(startDate,endDate,resultMap);
        activeTenantId(startDate,endDate,resultMap);
        //上周活跃门店
        activeStorelast(lastweekDate,startDate,resultMap);
        activeTenantIdlast(lastweekDate,startDate,resultMap);

        Workbook workbook = ExcelExportUtil.exportExcel(new TemplateExportParams("templates/total.xlsx", true), resultMap);
        String fileName = "供应链产品业务运营数据("+startDate+"~"+endDatew+")";
        fileName = fileName.replace("-","");
        File file = new File("D:\\辰森世纪-工作-2019\\数据统计\\程序生成统计数据\\"+fileName+".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        
        weichaosql = weichaosql.replace("endDate", endDate).replace("startDate", startDate);
        String ss = weichao(weichaosql);
        Workbook workbook2 = ExcelExportUtil.exportExcel(new TemplateExportParams("templates/demo.xlsx", true), JSONObject.parseObject(ss));
        String fileName2 = "供应链产品业务运营详细数据("+startDate+"~"+endDatew+")";
        fileName2 = fileName2.replace("-","");
        File file2 = new File("D:\\辰森世纪-工作-2019\\数据统计\\程序生成统计数据\\"+fileName2+".xlsx");
        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
        workbook2.write(fileOutputStream2);
        fileOutputStream2.close();
        System.out.println("生成成功!!!!!!!!!!!!!!!!!!!!!!!!!");
        addStore(startDate,endDate);
    }
    
    private static void total(String sql,Map<String,Object> resultMap) throws Exception{
        sqlAddr.put("sql", sql);
        System.out.println(sql);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
    
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();

        response = client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total1", maps.get(0).get("count(*)"));
            resultMap.put("total2", maps.get(1).get("count(*)"));
            resultMap.put("total3", maps.get(2).get("count(*)"));
            resultMap.put("total4", maps.get(3).get("count(*)"));
            resultMap.put("total5", maps.get(4).get("count(*)"));
            resultMap.put("total6", maps.get(5).get("count(*)"));
            resultMap.put("total7", maps.get(6).get("count(*)"));
            resultMap.put("total8", maps.get(7).get("count(*)"));
            resultMap.put("total9", maps.get(8).get("count(*)"));
            resultMap.put("total10", maps.get(9).get("count(*)"));
            resultMap.put("total12", maps.get(10).get("count(*)"));
            resultMap.put("total11", maps.get(11).get("count(*)"));
            resultMap.put("total13", maps.get(12).get("count(*)"));
            resultMap.put("total14", maps.get(13).get("count(*)"));
            System.out.println(resultMap);
        }
        
        
        
        
        
    }
    
    private static void week(String sql,Map<String,Object> resultMap) throws Exception{
        sqlAddr.put("sql", sql);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("week1", maps.get(0).get("count(*)"));
            resultMap.put("week2", maps.get(1).get("count(*)"));
            resultMap.put("week3", maps.get(2).get("count(*)"));
            resultMap.put("week4", maps.get(3).get("count(*)"));
            resultMap.put("week5", maps.get(4).get("count(*)"));
            resultMap.put("week6", maps.get(5).get("count(*)"));
            resultMap.put("week7", maps.get(6).get("count(*)"));
            resultMap.put("week8", maps.get(7).get("count(*)"));
            resultMap.put("week9", maps.get(8).get("count(*)"));
            resultMap.put("week10", maps.get(9).get("count(*)"));
            resultMap.put("week12", maps.get(10).get("count(*)"));
            System.out.println(resultMap);
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void lastWeek(String sql,Map<String,Object> resultMap) throws Exception{
        sqlAddr.put("sql", sql);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response = client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("lastWeek1", maps.get(0).get("count(*)"));
            resultMap.put("lastWeek2", maps.get(1).get("count(*)"));
            resultMap.put("lastWeek3", maps.get(2).get("count(*)"));
            resultMap.put("lastWeek4", maps.get(3).get("count(*)"));
            resultMap.put("lastWeek5", maps.get(4).get("count(*)"));
            resultMap.put("lastWeek6", maps.get(5).get("count(*)"));
            resultMap.put("lastWeek7", maps.get(6).get("count(*)"));
            resultMap.put("lastWeek8", maps.get(7).get("count(*)"));
            resultMap.put("lastWeek9", maps.get(8).get("count(*)"));
            resultMap.put("lastWeek10", maps.get(9).get("count(*)"));
            resultMap.put("lastWeek12", maps.get(10).get("count(*)"));
            System.out.println(resultMap);
        }
    }
    
    private static String weichao(String sql) throws Exception{
        sqlAddr.put("sql", sql);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response = client.newCall(requestOk).execute();
        return response.body().string();
        
    }
    
    private static String addStore(String startDate,String endDate) throws Exception{
    
        String sql = "SELECT b.ten_name, count(*) FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id LEFT JOIN acl_store c ON a.shopid = c.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time > 'startDate 00:00:00' AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL GROUP BY b.ten_name";
        sql = sql.replace("endDate", endDate).replace("startDate", startDate);
        System.out.println(sql);
        return null;
    }
    
    private static void totalTenant(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(tenant_id)) as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total11",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void totalTenantLastWeek(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(tenant_id)) as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total13",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void totalTenantLastLastWeek(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(tenant_id))  as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total14",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    
    
    
    
    private static void totalStore(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(*)  as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        System.out.println("门店本周:"+sqll);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            System.out.println(jsonString);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total12",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void totalStoreLastWeek(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(*) as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        System.out.println("门店上周:"+sqll);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total15",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void totalStoreLastLastWeek(String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(*) as total FROM bas_rel_application_shopid a LEFT JOIN acl_tenant b ON a.tenant_id = b.id WHERE a.application_id = 'dd54e0c1ce2c43dcad7cc3e3b8a2d3a6' AND a.delete_flag = '1' AND a. STATUS = 1 AND a.create_time < 'endDate 00:00:00' AND a.tenant_id NOT IN ( '5c89e7c63601700001c461f3','520829d8b47745d08b304c3dbe3fca9d', '5abf001796d190000d07a59a', '5c89e7c63601700001c461f3', '5d0735d4ecc50f0001c1f454', '5d07bc9decc50f0001c1f45b', '5d07c1827c59920001a7dc61', '5d08f50fecc50f0001c1f4e7', '5d2fea6aecc50f0001c202af', '5d3fe3d796d190000153b109', '5d4c396decc50f0001c20893', '7368e535f270443eb7f6b56005a81f30', 'b81c52b170bc4c1eb9fb559131cdef36', 'c5275c0472c44780bae3ddef9297459f', 'd7a33b1f879c4cc5830d24f8e054ef97' ) AND b.id IS NOT NULL;\n";
        sqlAddr2zhongtai1.put("sql",sqll.replace("endDate",endDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("total16",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void activeTenantId(String startDate,String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(tenant_id)) as total from scm_stock_flow where org_type = 1 and create_time > 'startDate 00:00:00' and create_time < 'endDate 00:00:00';\n";
        sqlAddr.put("sql",sqll.replace("endDate",endDate).replace("startDate",startDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("activeTenant",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void activeStore(String startDate,String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(store_id)) as total from scm_stock_flow where org_type = 1 and create_time > 'startDate 00:00:00' and create_time < 'endDate 00:00:00';\n";
        sqlAddr.put("sql",sqll.replace("endDate",endDate).replace("startDate",startDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("activeStore",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static void activeTenantIdlast(String startDate,String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(tenant_id)) as total from scm_stock_flow where org_type = 1 and create_time > 'startDate 00:00:00' and create_time < 'endDate 00:00:00';\n";
        sqlAddr.put("sql",sqll.replace("endDate",endDate).replace("startDate",startDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("activeTenantLast",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    private static void activeStorelast(String startDate,String endDate,Map<String, Object> resultMap) throws Exception{
        String sqll = "SELECT count(DISTINCT(store_id)) as total from scm_stock_flow  where create_time > 'startDate 00:00:00' and org_type = 1 and create_time < 'endDate 00:00:00';\n";
        sqlAddr.put("sql",sqll.replace("endDate",endDate).replace("startDate",startDate));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONObject.toJSONString(sqlAddr2zhongtai1));
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", jwtToken)
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(2000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response =client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
            resultMap.put("activeStoreLast",maps.get(0).get("total"));
        }else {
            System.out.println("token过期！！！！！！");
        }
    }
    
    
}
