
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordSuche {

    public static void main(String[] args) {
        String therapyText = getTherapyFile(Thread.currentThread()
                .getContextClassLoader()
                .getResource("\\Documents\\Enuresis ST.doc"));
        // System.out.println(StringEscapeUtils.escapeJava(therapyText));
        // System.out.println(therapyText);
        ArrayList<String> drugList = getDrugList();
        // System.out.println(drugList.toString());
        ArrayList<String> drugsInDocument = new ArrayList<>();
        ArrayList<Integer> drugsPositionInDocument = new ArrayList<>();

        for (String drug : drugList) {
            if (therapyText.contains(drug)) {
                System.out.print(drug);
                System.out.println(" " + therapyText.indexOf(drug));

                drugsInDocument.add(drug);

                drugsPositionInDocument.add(therapyText.indexOf(drug));
            }
        }
    }

    private static ArrayList<String> getDrugList() {
        ArrayList<String> drugList = new ArrayList<>();
        try (Scanner s = new Scanner((Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("DrugList")))) {
            while (s.hasNextLine()) {
                drugList.add(s.nextLine());
            }
        }
        return drugList;

    }

    // Gets the complete theraphy part as a String
    private static String getTherapyFile(final URL fileURL) {
        try {
            File file = new File(fileURL.toURI());
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument wordDocument = new HWPFDocument(fis);
            String[] fileData;
            try (WordExtractor extractor = new WordExtractor(wordDocument)) {
                fileData = extractor.getParagraphText();
            }

            Boolean fill = false;
            StringBuilder TheraphieText = new StringBuilder();
            for (int i = 0; i < fileData.length; i++) {
                if (fill == false && fileData[i].equals("Therapie\r\n")) {

                    fill = true;
                }
                if (fill == true && fileData[i].equals("Appendix\r\n")) {

                    fill = false;
                }
                if (fill && fileData[i] != null) {
                    TheraphieText.append(fileData[i]);
                }
            }
            return TheraphieText.toString();
        } catch (Exception exep) {
            exep.printStackTrace();
            return null;
        }
    }

    private static String[] getCompleteDocument(final URL fileURL) {
        File file = null;
        try {
            file = new File(fileURL.toURI());
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument document = new HWPFDocument(fis);
            WordExtractor extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            extractor.close();

            return fileData;
        } catch (Exception exep) {
            exep.printStackTrace();
            return null;
        }
    }

    private static String getMedicamentTherapyLines(final String FilePath) {
        File file = null;
        try {
            file = new File(FilePath);
            // System.out.println(FilePath);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument document = new HWPFDocument(fis);
            WordExtractor extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            extractor.close();

            Boolean fill = false;
            StringBuilder TheraphieText = new StringBuilder();
            for (int i = 0; i < fileData.length; i++) {
                if (fill == false && fileData[i].equals("Therapie\r\n")) {

                    fill = true;
                }
                if (fill == true && fileData[i].equals("Appendix\r\n")) {

                    fill = false;
                }
                if (fill && fileData[i] != null) {
                    TheraphieText.append(fileData[i]);
                }
            }
            return TheraphieText.toString();
        } catch (Exception exep) {
            exep.printStackTrace();
            return null;
        }
    }
}
// ALTE MAIN

// File file = null;
// WordExtractor extractor = null;
// try {
// System.out.println(1);
// System.out.println(file);
// file = new File(
// "c:\\Users\\pc\\Desktop\\Bachelorarbeit\\Neue Dokumente\\Enuresis ST.doc");
// System.out.println(file);
// FileInputStream fis = new FileInputStream(file.getAbsolutePath());
// System.out.println(fis);
// System.out.println(1);
//
// HWPFDocument document = new HWPFDocument(fis);
// extractor = new WordExtractor(document);
// String[] fileData = extractor.getParagraphText();
//
// Boolean print = false;
// for (int i = 0; i < fileData.length; i++) {
// if (print == false && fileData[i].equals("Therapie\r\n")){
//
// print = true;
// }
// if (print == true && fileData[i].equals("Appendix\r\n")){
//
// print = false;
// }
// if (print && fileData[i] != null)
// System.out.println(fileData[i]);
// }
// System.out.println(StringEscapeUtils.escapeJava(fileData[171]));
// if (fileData[142].length() == ("Therapie\n\n".length()))
// System.out.println("yay");
// } catch (Exception exep) {
// exep.printStackTrace();
// }

