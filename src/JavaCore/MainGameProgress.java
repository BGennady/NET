package JavaCore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainGameProgress {
    public static void main(String[] args) {

        //создали три объекта класса `GameProgress`;
        GameProgress gameProgress1 = new GameProgress(1200, 45, 27, 39.125);
        GameProgress gameProgress2 = new GameProgress(1450, 64, 32, 15.79);
        GameProgress gameProgress3 = new GameProgress(1870, 72, 44, 141.37);

        //cписок файлов и архив для их хранения;
        List<File> archive = new ArrayList<>();
        File save1 = new File("D://Games/savegames/save1.dat");
        File save2 = new File("D://Games/savegames/save2.dat");
        File save3 = new File("D://Games/savegames/save3.dat");
        archive.add(save1);
        archive.add(save2);
        archive.add(save3);

        try {
            saveGameProgress(gameProgress1, save1);
            saveGameProgress(gameProgress2, save2);
            saveGameProgress(gameProgress3, save3);
        } catch (IOException ex) {
            System.out.println("ошибка при сохранении объектов: " + ex.getMessage());
        }

        //cоздали архив `gameProgress.zip`, куда будем архивировать файлы `save`;
        File zipFile = new File("D://Games/savegames/gameProgress.zip");

        //создаем архив, куда будем добавлять сериализованные объекты;
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (File save : archive) {
                //читаем файл 'save';
                try (FileInputStream fis = new FileInputStream(save)) {
                    //cоздаем 'entry';
                    ZipEntry entry = new ZipEntry(save.getName());
                    //помещаем его в архив;
                    zos.putNextEntry(entry);

                    //побайтово кладем файл в масив и записываем его в архив;
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    //закрываем запись для текущей entry;
                    zos.closeEntry();
                } catch (IOException ex) {
                    System.out.println("ошибка при добавлении файла в архив: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("ошибка при создании архива: " + ex.getMessage());
        }

        //удаляем исходные файлы после успешного архивирования
        for (File save : archive) {
            if (save.delete()) {
                System.out.println("файл успешно удалён: " + save.getName());
            } else {
                System.out.println("файл не был удалён: " + save.getName());
            }
        }
    }

    private static void saveGameProgress(GameProgress gameProgress, File save) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(save);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            //записываем объект 'gameProgress' в файл 'save.dat';
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println("ошибка при записи объекта: " + ex.getMessage());
        }
    }
}
