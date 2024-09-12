package JavaCore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main_Installation {
    public static void main(String[] args) {


        File dirGames = new File("D://Games");
        StringBuilder sb = new StringBuilder();
        FileWriter writer;

        if (dirGames.mkdir())
            System.out.println("создан новый каталог Games");

        String[] directories = {"src", "res", "savegames", "temp", "src/main",
                "src/test", "res/drawables", "res/vectors", "res/icons", "temp"};

        for (String dir : directories) {
            File newDir = new File("D://Games/" + dir);
            if (newDir.mkdir())
                System.out.println(sb.append("создан новый каталог " + dir + " "));

        }
        String report = sb.toString();

        File myMain = new File("D://Games/src/main/Main.java");
        File myUtils = new File("D://Games/src/main/Utils.java");
        File myTemp = new File("D://Games/temp/temp.txt");

        try {
            if (myMain.createNewFile())
                System.out.println(sb.append("файл myMain был создан"));
            if (myUtils.createNewFile())
                System.out.println(sb.append("файл myUtils был создан"));
            if (myTemp.createNewFile())
                System.out.println(sb.append("файл myTemp был создан"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

        try {
            writer = new FileWriter("D://Games/temp/temp.txt", true);
            writer.write(report);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
