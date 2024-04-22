package edu.uestc.imdds.config;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class HDFSConfig {
    @Value("${hdfs.ip}")
    private String hdfs_ip;
    @Value("${hdfs.username}")
    private String hdfs_name;
    @Value("${hdfs.path}")
    private String hdfs_path;
    @Bean
    public org.apache.hadoop.conf.Configuration  getConfiguration(){
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hdfs_ip);
        return configuration;
    }
    @Bean
    public FileSystem getFileSystem(){
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(hdfs_ip), getConfiguration(), hdfs_name);
            System.out.println("fileSystem created！");
            if(!fileSystem.exists(new Path(hdfs_path))){
                fileSystem.mkdirs(new Path(hdfs_path));
                System.out.println("文件夹初始化完成！");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            log.error(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
//            log.error(e.getMessage());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
//            log.error(e.getMessage());
        }
        return fileSystem;
    }

}
