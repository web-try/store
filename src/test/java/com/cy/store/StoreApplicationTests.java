package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
//        String currentDir = System.getProperty("user.dir");
//        currentDir = currentDir + File.separator +"resources"+ File.separator+"web";
//        System.out.println(currentDir);
//        String resourcesPath = "src/main/resources/myNewFolder";
//        Path path = Paths.get(resourcesPath);
//        try {
//            // 创建文件夹
//            Files.createDirectories(path);
//            System.out.println("文件夹创建成功: " + path.toAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("创建文件夹失败: " + e.getMessage());
//        }

    }
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
