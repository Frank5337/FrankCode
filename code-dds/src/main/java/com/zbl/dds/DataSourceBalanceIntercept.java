package com.zbl.dds;

/**
 * @author zbl
 * @date 2020/11/9
 * @des sql 拦截器 读写分离负载均衡
 */
//@Intercepts(@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
//public class DataSourceBalanceIntercept implements Interceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger(DataSourceBalanceIntercept.class);
//
//    // mapper 名单
//    @Value("#{'${dds.mapperIds}'.split(',')}")
//    private Set<String> mapperIds ;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        try {
//            // 是否是用事务管理
//            boolean syschronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
//            if (!syschronizationActive && !CollectionUtils.isEmpty(mapperIds)) {
//                Object[] args = invocation.getArgs();
//                MappedStatement mappedStatement = (MappedStatement)args[0];
//                if ( mapperIds.contains(mappedStatement.getId()) ) {
//                    DynamicDataSourceHolder.setDbType(DBTypeEnum.SLAVE) ;
//                }
//            }
//            logger.info(" use datasource {} " , DynamicDataSourceHolder.getDbType());
//            //具体执行
//            return invocation.proceed();
//        } finally {
//            DynamicDataSourceHolder.clearDbType();
//        }
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return Plugin.wrap(o, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
