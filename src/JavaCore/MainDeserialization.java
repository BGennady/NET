package JavaCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainDeserialization {
    public static void main(String[] args) {

        //путь к архиву и папке для извлечения файлов:
        File zipFile = new File("D://Games/savegames/gameProgress.zip");
        File dirSave = new File("D://Games/savegames/");

        try //открываем поток для чтения из ZIP архива;
                (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            //читаем каждую запись (файл) из архива;
            while ((entry = zis.getNextEntry()) != null) {
                //получаем имя файла;
                String name = entry.getName();
                //создаем новый файл в директории для сохранения;
                File extractFile = new File(dirSave, name);

                try     //открываем поток для записи данных в новый файл;
                        (FileOutputStream fos = new FileOutputStream(extractFile)) {
                    //читаем данные из архива и записываем их в файл;
                    int buffer;
                    while ((buffer = zis.read()) != -1) {
                        fos.write(buffer);
                    }
                    //закрываем текущий файл и архивную запись;
                    zis.closeEntry();
                } catch (IOException ex) {
                    System.out.println("ошибка при записи файла: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("ошибка при разархивировании: " + ex.getMessage());
        }
    }

}
