package com.meida.backend.generatebackend;

import com.meida.backend.generatebackend.config.ConstVal;
import com.meida.backend.generatebackend.config.DataSourceConfig;
import com.meida.backend.generatebackend.config.GlobalConfig;
import com.meida.backend.generatebackend.config.PackageConfig;
import com.meida.backend.generatebackend.config.StrategyConfig;
import com.meida.backend.generatebackend.config.rules.DbType;
import com.meida.backend.generatebackend.config.rules.NamingStrategy;

public class BackendSqlGenerator {
	
	private static String auth = "BING";  //作者

	private static String dbDriverName = "com.mysql.cj.jdbc.Driver";  //数据库连接驱动
	
	private static String dbUsername = "root";  //数据库用户名
	
	private static String dbPassword = "123456";  //数据库密码
	
	//数据库连接地址
	private static String dbUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";

    private static String basePackage = "com.meida.backend.pay";  //包名  示例：com.meida.backend.userManager
    //private static String tableName = "tb_account_amount";  //数据库表名
    private static String[] tableNames =new String[]{
            "tb_member",
            "tb_alipay_return",
            "tb_alipay_notify",
            "tb_account_refund",
            "tb_account_recharge",
            "tb_account_receivable",
            "tb_account_payable",
            "tb_account_amount",
            "tb_account_history"
    } ;  //数据库表名
    private static String baseUrl="/backend/pay";

    /**
     *  生成演示
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(ConstVal.javaPath(""));
        gc.setOutputJspDir(ConstVal.projectPath());
        gc.setFileOverride(true);//是否覆盖现有文件
        gc.setAuthor(auth);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);
        //pc.setModuleName("WJQ");
        pc.setBaseUrl(baseUrl);  //url
        //pc.setParentJsp("WebContent\\WEB-INF\\jsp"); //jsp输出父路径
        //pc.setModuleJspName("backend\\shenxiao"); //子路径，这也是controller跳转页面的路径
        mpg.setPackageInfo(pc);
     
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        //gc.setMapperName("%sDao");
        //gc.setDaoName("%sDao");
        //gc.setDaoMappingName("%sDao");//xml和dao名一般保存一致
        //gc.setServiceName("%sService");
        //gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        // gc.setManagerListName("");
        // gc.setAddOrUpdateName("");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(dbDriverName);
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        dsc.setUrl(dbUrl);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("tb_");// 此处可以修改为您的表前缀,去掉表前缀
        strategy.setTableBehind("");// 此处可以修改为您的表后缀,去掉表后缀
        strategy.setNaming(NamingStrategy.remove_all_and_camel);// 表名生成策略
        //strategy.setInclude(new String[] { tableName }); // 需要生成的表,注释掉就生成全部
        strategy.setInclude(tableNames); // 需要生成的表,注释掉就生成全部
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
//        // 自定义实体po父类
//        strategy.setSuperModelClass("com.meida.base.BasePo");
//        // 自定义实体vo父类
//        strategy.setSuperModelVoClass("com.meida.base.BaseVo");
//        // 自定义 action 父类
//        strategy.setSuperControllerClass("com.meida.base.BaseController");

//        // 自定义 service 父类
//        strategy.setSuperServiceClass("com.meida.base.service.inter.BaseService");
//        // 自定义 service 实现类父类
//        strategy.setSuperServiceImplClass("com.meida.base.service.impl.BaseServiceImpl");
        // 自定义 Dao 类父类
        strategy.setSuperDaoClass("com.meida.backend.base.dao.inter.BaseDao");
//        // 自定义 Dao 实现类父类
//        strategy.setSuperDaoImplClass("com.meida.base.dao.BaseDaoImpl");

        // 自定义实体，公共字段
        //strategy.setSuperModelColumns(new String[]{"id"});
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.demo.TestMapper");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        // 执行生成
        mpg.execute();
    }

}
