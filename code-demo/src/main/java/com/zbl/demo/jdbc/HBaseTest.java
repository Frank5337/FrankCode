package com.zbl.demo.jdbc;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/26 14:00
 * @Description:
 * @Version: $
 */
public class HBaseTest {
    //    public static void main(String args[]) throws Exception {
////        Configuration conf = HBaseConfiguration.create();
////        conf.set("hbase.zookeeper.quorum", "zkproxy02");
////        conf.set("hbase.zookeeper.property.clientPort", "21810");
////        Connection connection = (Connection)ConnectionFactory.createConnection(conf);
////        Statement statement = connection.createStatement();
////        ResultSet resultSet = statement.executeQuery("scan 'hbase:Student1'");
////        while (resultSet.next()) {
////            System.out.println(resultSet.getObject(1));
////        }
//
////        String kr5Conf = "E:\\software\\qiyewxFiles\\WXWork\\1688851943938006\\Cache\\File\\2021-04\\hbase_krb5.conf";
//        String kr5Conf = "E:\\software\\qiyewxFiles\\WXWork\\1688851943938006\\Cache\\File\\2021-04\\KERBEROS_CLIENT-configs\\krb5.conf";
//        String keyTab = "E:\\software\\qiyewxFiles\\WXWork\\1688851943938006\\Cache\\File\\2021-04\\hbase_service_server.keytab";
//        System.setProperty("java.security.krb5.conf", kr5Conf);
//        System.setProperty("sun.security.krb5.debug", "true");
//
//        final Configuration conf = HBaseConfiguration.create();
////        conf.set("hbase.zookeeper.quorum", "hadoop1");
//        conf.set("hbase.zookeeper.quorum", "192.168.238.232");
//        conf.set("hbase.zookeeper.property.clientPort", "2181");
////        conf.set("hbase.zookeeper.property.clientPort", "16002");
//        conf.set("hadoop.security.authentication", "kerberos");
//        conf.set("hbase.security.authentication", "kerberos");
//        conf.set("zookeeper.znode.parent", "/hbase-secure");
//        conf.set("hbase.master.kerberos.principal", "hbase/hadoop2@MC.COM");
//        UserGroupInformation.setConfiguration(conf);
//        UserGroupInformation ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI("hbase/hadoop2@MC.COM", keyTab);
//        UserGroupInformation.setLoginUser(ugi);
//        HBaseAdmin.available(conf);
//        org.apache.hadoop.hbase.client.Connection connection = ConnectionFactory.createConnection(conf);
//        System.out.println("==============================");
//        System.out.println(connection.getAdmin());
//
//        NamespaceDescriptor[] nameSpaces = connection.getAdmin().listNamespaceDescriptors();
//        for (NamespaceDescriptor namespaceDescriptor : nameSpaces) {
//            System.out.println(namespaceDescriptor.getName());
//        }
//
//
////        System.out.println(connection.getAdmin());
////        TableName tableName = TableName.valueOf("testzbl2");
////        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
////        tableDescriptor.addFamily(new HColumnDescriptor("column1"));
////        tableDescriptor.addFamily(new HColumnDescriptor("column2"));
////        tableDescriptor.addFamily(new HColumnDescriptor("column3"));
////        connection.getAdmin().createTable(tableDescriptor);
////        System.out.println("nameSpaces==============================");
////        System.out.println("table=========================");
////        TableName[] tables = connection.getAdmin().listTableNamesByNamespace("test_ddl_aaa");
////        for (TableName tt : tables) {
////            System.out.print("--");
////            System.out.println(tt.getQualifierAsString());
////            String tName = "test_ddl" + ":" + tt.getQualifierAsString();
////            TableName ttt = TableName.valueOf(tName);
////            HTableDescriptor descriptors = connection.getAdmin().getTableDescriptor(ttt);
////            Set<byte[]> bs = descriptors.getColumnFamilyNames();
////            for (byte[] bs2 : bs) {
////                System.out.print("-----");
////                System.out.println(new String(bs2));
////            }
////        }
//
//
//
//    }

    public static void main(String[] args) {
        String s = "";
        System.out.println(Integer.valueOf(s));
    }
}
