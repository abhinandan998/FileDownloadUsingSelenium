package org.Abhinandan_Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadFileToCustomFolder {
    public static void main(String[] args) throws InterruptedException {

        String downloadFolderPath= System.getProperty("user.dir")+ File.separator+"jenkinsDownload";
        File jenKinsDownloadDir = new File(downloadFolderPath);

        if(!jenKinsDownloadDir.exists()) {
            System.out.println("Jenkins Folder not present !!");
            if(jenKinsDownloadDir.mkdir()){
                System.out.println("Created Jenkins Download directory");
            }
        }
        Map<String, Object> pref = new HashMap<>();
        pref.put("download.default_directory", downloadFolderPath);
        pref.put("download.prompt_for_download", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", pref);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://get.jenkins.io/war-stable/2.541.2/jenkins.war");

        File jenkinsFile = new File(jenKinsDownloadDir, "jenkins.war");
        int timeOutSeconds = 30;
        int elapsedTime =0;
        while(elapsedTime < timeOutSeconds && !jenkinsFile.exists())
        {
            Thread.sleep(1000);
            elapsedTime++;
            System.out.println("Waiting for file to download");
        }
        if(jenkinsFile.exists())
        {
            System.out.println("File Downloaded Successfully");
        }
        else{
            System.out.println("File could not be downloaded");
        }
    }
}
