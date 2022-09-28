package cn.smilex.openvas.scan;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author smilex
 * @date 2022/9/29/16:07
 * @since 1.0
 */
public class MyBatisPlusAutoGenerator {

    @Test
    public void context() {
        final String path = System.getProperty("user.dir") +
                File.separator +
                "src" +
                File.separator +
                "main" +
                File.separator +
                "java";

        FastAutoGenerator autoGenerator = FastAutoGenerator.create(
                "jdbc:mysql://localhost:3333/guokai_platform",
                "root",
                "123123"
        );

        autoGenerator
                .globalConfig(
                        builder -> builder
                                .author("smilex")
                                .dateType(DateType.TIME_PACK)
                                .commentDate("yyyy/MM/dd HH:mm:ss")
                                .disableOpenDir()
                                .outputDir(path)
                )
                .packageConfig(
                        builder -> builder.parent("cn.smilex.guokai.guoKaiPlatform")
                                .entity("pojo")
                )
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .addTableFills(
                                    new Column("gmt_created", FieldFill.INSERT),
                                    new Column("gmt_modified", FieldFill.INSERT_UPDATE)
                            )
                            .enableChainModel()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .disableSerialVersionUID()
                            .versionColumnName("version")
                            .logicDeleteColumnName("is_deleted")
                            .idType(IdType.ASSIGN_ID)
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .mapperBuilder()
                            .formatMapperFileName("%sDao")
                            .enableFileOverride();
                })
                .execute();
    }
}
