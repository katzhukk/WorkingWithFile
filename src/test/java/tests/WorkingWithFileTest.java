package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class WorkingWithFileTest {

    private static final ClassLoader cl = WorkingWithFileTest.class.getClassLoader();

    @DisplayName("Тест на чтение и проверку Locator.pdf файла из архива zipArchive.zip")
    @Test
    void pdfFileParsingTest() throws Exception {
        PDF pdf = null;

        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("zipArchive.zip")
        )){
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if(entry.getName().equals("Locator.pdf")){
                    pdf = new PDF(zip);
                    assertThat(pdf.text).contains("Локаторы");
                    assertThat(pdf.text).contains("Цель изучения HTML и локаторов");
                    assertThat(pdf.numberOfPages).isEqualTo(9);
                    break;
                }
            }
        }
    }

    @DisplayName("Тест на чтение и проверку experiment.xlsx файла из архива zipArchive.zip")
    @Test
    void xlsxFileParsingTest() throws Exception {
        XLS xls = null;
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("zipArchive.zip")
        )){
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {

                if (entry.getName().equals("experiment.xlsx")) {
                    xls = new XLS(zip);

                    String firstCheck = xls.excel.getSheetAt(0).getRow(0).getCell(3).getStringCellValue();
                    assertThat(firstCheck).isEqualTo("Вид труб Вентури");

                    String secondCheck = xls.excel.getSheetAt(0).getRow(13).getCell(1).getStringCellValue();
                    assertThat(secondCheck).isEqualTo("Эффект");
                    break;
                }
            }
        }
    }

    @DisplayName("Тест на чтение и проверку export.csv файла из архива zipArchive.zip")
    @Test
    void csvFileParsingTest() throws Exception {
        CSVReader csv = null;
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("zipArchive.zip")
        )){
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {

                if (entry.getName().equals("export.csv")) {
                    csv = new CSVReader(new InputStreamReader(zip));

                    List<String[]> csvData = csv.readAll();

                    assertThat(csvData.get(0)).contains("Name,Job Title,Address,State,City");
                    assertThat(csvData.get(1)).contains("John Doe,Designer,325 Pine Street,,Seattle");

                    Assertions.assertArrayEquals(
                            new String[]{"Edward Green,Developer,110 Pike Street,WA,Seattle"},
                            csvData.get(4)
                    );
                    break;
                }
            }
        }
    }
}

