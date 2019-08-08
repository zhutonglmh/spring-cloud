//package studyserverone.many_data_source;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * @author zhutong
// * @date 2019/8/6 11:06
// */
//public class DynamicDataSource extends AbstractRoutingDataSource {
//    
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return DatabaseContextHolder.getDatabaseType();
//    }
//}
