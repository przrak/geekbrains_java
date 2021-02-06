package client;

import javafx.scene.control.TextArea;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalHistoryService {

    private File file;
    private FileWriter fw;
    private TextArea textArea;

    public LocalHistoryService(final TextArea textArea) {
        this.textArea = textArea;
    }

    public void initUserHistoryFile(final String nickname) {
        if (!Files.exists(Paths.get("history_" + nickname + ".txt"))) {
            file = new File("history_" + nickname + ".txt");
        } else {
            file = new File("history_" + nickname + ".txt");
            try {
                int n_lines = 5;
                int counter = 0;
                List<String> lines = new ArrayList<>();
                ReversedLinesFileReader object = new ReversedLinesFileReader(file,
                        StandardCharsets.UTF_8);
                while (counter < n_lines) {
                    String line = object.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(line);
                    counter++;
                }
                for (int i = lines.size() - 1; i >= 0; i--) {
                    this.textArea.appendText(lines.get(i) + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void appendTextToHistory(final String msg) {
        try {
            fw = new FileWriter(file, true);
            fw.write(msg + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
