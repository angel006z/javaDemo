package com.meida.common.generate;

import com.meida.common.generate.config.ConstVal;
import com.meida.common.generate.config.DataSourceConfig;
import com.meida.common.generate.config.GlobalConfig;
import com.meida.common.generate.config.PackageConfig;
import com.meida.common.generate.config.StrategyConfig;
import com.meida.common.generate.config.rules.DbType;
import com.meida.common.generate.config.rules.NamingStrategy;

public class sqlGenerator {
	
	private static String auth = "";  //作者
	
	private static String basePackage = "com.demo.backend.ticketSaleManager";  //包名  示例：com.demo.backend.userManager
	
	private static String dbDriverName = "com.mysql.jdbc.Driver";  //数据库连接驱动
	
	private static String dbUsername = "root";  //数据库用户名
	
	private static String dbPassword = "123456";  //数据库密码
	
	//数据库连接地址
	private static String dbUrl = "jdbc:mysql://192.168.18.240:3306/baseticketsystemforjava_5_1?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	
	private static String tableName = "st_sale_info_ticket_order_t";  //数据库表名
	
	//继承公共po  示例：com.demo.base.po.BaseInfo或com.demo.base.po.BaseApplicationInfo
	private static String basePo = "com.demo.base.po.BaseApplicationInfo";  
	
	
	

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
        gc.setFileOverride(true);
        gc.setAuthor(auth);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);
        //pc.setModuleName("WJQ");
        //pc.setBaseUrl("/backstage/privateAuthority");  //url
        //pc.setParentJsp("WebContent\\WEB-INF\\jsp"); //jsp输出父路径
        //pc.setModuleJspName("backend\\shenxiao"); //子路径，这也是controller跳转页面的路径
        mpg.setPackageInfo(pc);
     
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
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
        //strategy.setTablePrefix("M_");// 此处可以修改为您的表前缀,去掉表前缀
        strategy.setTableBehind("_T");// 此处可以修改为您的表后缀,去掉表后缀
        strategy.setNaming(NamingStrategy.remove_all_and_camel);// 表名生成策略
        strategy.setInclude(new String[] { tableName }); // 需要生成的表,注释掉就生成全部
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        // 自定义实体po父类
        strategy.setSuperModelClass(basePo);
        // 自定义实体vo父类
        strategy.setSuperModelVoClass("com.demo.base.vo.BaseBusinessBean");
        // 自定义 action 父类
        strategy.setSuperControllerClass("com.demo.base.action.BaseAction");
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.demo.base.service.inter.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.demo.base.service.impl.BaseServiceImpl");
        // 自定义 Dao 类父类
        strategy.setSuperDaoClass("com.demo.base.dao.BaseDao");
        // 自定义 Dao 实现类父类
        strategy.setSuperDaoImplClass("com.demo.base.dao.BaseDaoImpl");
        // 自定义实体，公共字段
        strategy.setSuperModelColumns(new String[]{"id"});
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