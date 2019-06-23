package com.yemreak;

import javafx.scene.image.Image;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Utility {

    static void putClipboard(String clipboardString) {
        StringSelection stringSelection = new StringSelection(clipboardString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    static String getClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    static String makeLinkDirect(String link) {
        return link.replace("open?", "uc?export=download&");
    }

    static ArrayList<String> executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

        final ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = stdInput.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    static Image getImageFromFile(String filepath) {
        System.out.println(filepath);
        File file = new File(filepath);
        return new Image(file.toURI().toString());
    }

    static void deleteFile(String filepath) throws IOException {
        executeCommand("del \"" + filepath + "\"");
    }
}
