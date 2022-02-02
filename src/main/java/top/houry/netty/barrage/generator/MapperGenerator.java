package top.houry.netty.barrage.generator;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MapperGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("houry");
        gc.setOpen(false);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/barrage?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&&useSSL=false&serverTimezone=Hongkong");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("");
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig gc, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("date") || t.contains("time") || t.contains("year")) {
                    return DbColumnType.DATE;
                } else {
                    return super.processTypeConvert(gc, fieldType);
                }
            }
        });
        mpg.setDataSource(dsc);
        PackageConfig pc = new PackageConfig();
        pc.setParent("top.houry.netty.barrage");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setXml("mybatis.business");
        mpg.setPackageInfo(pc);
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        mpg.setTemplate(tc);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("t_barrage_color_configure");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }


}

