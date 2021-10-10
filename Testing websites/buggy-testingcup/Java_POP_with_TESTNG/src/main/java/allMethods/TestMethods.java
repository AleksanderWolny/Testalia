package allMethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TestMethods {
    Iterator<Object[]> parseCsvData(String pathToFile) {
        List<Object[]> newList = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(pathToFile))) {
            String holdingVariableString;
            while ((holdingVariableString = buffer.readLine()) != null) {
                Object[] values = holdingVariableString.split(",");
                newList.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newList.iterator();
    }

    public void cycleThroughFolderFiles(String pathToFolder, String pathToListOfFiles) {
        try {
            List<Path> files = Files.list(Paths.get(pathToFolder))
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
            List<String> changePathToString = files
                    .stream()
                    .map(Path::toString)
                    .collect(Collectors.toList());
            Files.write(Paths.get(pathToListOfFiles), changePathToString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFiles(String fileFolder, String fileName) {
        File file = new File(System.getProperty("user.home"), fileFolder + fileName);
        if (file.exists()) {
            file.delete();
            System.out.println("File " + fileName + " successfully deleted.");
        } else {
            System.out.println("There is no such file in this folder.");
        }
    }

    public File fileInFolder(String fileFolder, String fileName) {
        return new File(System.getProperty("user.home"), fileFolder + fileName);
    }

    public void clickElementMultipleTimes(int amount, WebElement product, Keys key){
        for (int i = 0; i < amount; i++) {
            product.sendKeys(key);
        }
    }
}
