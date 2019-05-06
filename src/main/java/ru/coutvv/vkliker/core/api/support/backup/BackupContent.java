package ru.coutvv.vkliker.core.api.support.backup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.Set;

public class BackupContent {

    private static final String IMG_PREFIX = "photo_";

    private final String path; // slash on the end
    private final Set<String> checkedLinks;
    private final String notFoundUrlsFile;

    public BackupContent(Set<String> checkedLinks, String notFoundUrlsFile) {
        this("", checkedLinks, notFoundUrlsFile);
    }

    public BackupContent(String path, Set<String> checkedLinks, String notFoundUrlsFile) {
        if (!path.equals("") && !path.endsWith("/")) {
            throw new IllegalArgumentException("The path must to ending with slash!");
        }
        this.path = path;
        this.checkedLinks = checkedLinks;
        this.notFoundUrlsFile = notFoundUrlsFile;
    }

    public void recursiveBackup(JsonObject post) throws IOException {
        int quality = post.keySet().stream().filter(s -> s.startsWith(IMG_PREFIX))
                .mapToInt(name -> Integer.parseInt(name.split("_")[1]))
                .max().orElse(-1);
        if (quality != -1) {
            save(post.get(IMG_PREFIX + quality).getAsString());
        }
        if (post.keySet().contains("ext") && post.keySet().contains("url")) {
            save(post);
            return;
        }
        for(String key: post.keySet()) {
            JsonElement jsonElement = post.get(key);
            if (jsonElement.isJsonObject()) {
                recursiveBackup(jsonElement.getAsJsonObject());
            } else if (jsonElement.isJsonArray()) {
                JsonArray array = jsonElement.getAsJsonArray();
                for (int i = 0; i < array.size(); i++) {
                    JsonElement arrayItem = array.get(i);
                    if (arrayItem.isJsonObject()) {
                        recursiveBackup(arrayItem.getAsJsonObject());
                    } else if (arrayItem.isJsonPrimitive()) {
                        save(arrayItem.getAsString());
                    }
                }
            }
        }
    }

    private static String nameFrom(String url) {
        return new String(Base64.getEncoder().encode(url.getBytes()))
                .replaceAll("=", "")
                .replaceAll("/", "");
    }

    private void save(JsonObject attachment) throws IOException {
        String link = attachment.get("url").getAsString();
        String extension = attachment.get("ext").getAsString();
        String name = nameFrom(link);

        String filename = name + "." + extension;
        String filePath = path + extension + "/" + filename;
        save(link, filePath);
    }

    private void save(String link, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists() || checkedLinks.contains(link)) {
            return;
        }
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        URL url = new URL(link);
        try {
            FileUtils.copyURLToFile(url, file);
        } catch (IOException e) {
            appendNonFoundUrl(link);
        }
    }

    private void save(String link) throws IOException {
        if (link.startsWith("http") && (link.endsWith("jpg") || link.endsWith("png"))) {
            String[] linkTrim = link.split("/");
            String filename = linkTrim[linkTrim.length - 1];
            String filePath = path + "img/" + filename;
            save(link, filePath);
        }
    }

    private void appendNonFoundUrl(String url) throws IOException {
        File file = new File(path + notFoundUrlsFile);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(url);
        pr.close();
        br.close();
        fr.close();
    }
}
