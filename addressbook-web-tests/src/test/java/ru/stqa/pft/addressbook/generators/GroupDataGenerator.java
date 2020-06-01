package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Генератор тестовых данных по группам с записью в файл.
 */
public class GroupDataGenerator {
    /**
     * Количество групп (Например, "-c 2").
     */
    @Parameter(names = "-c", description = "Group count")
    public int count;
    /**
     * Путь к файлу (Например, "-f src/main/resources/groups.json")
     */
    @Parameter(names = "-f", description = "Target file")
    public String file;
    /**
     * Формат документа (Например, "-d json")
     */
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    /**
     * Запуск генерации файла.
     *
     * @throws IOException
     */
    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        switch (format) {
            case "csv":
                saveAsCsv(groups, new File(file));
                break;
            case "xml":
                saveAsXml(groups, new File(file));
                break;
            case "json":
                saveAsJson(groups, new File(file));
                break;
            default:
                System.out.println("Unrecognized format " + format);
                break;
        }
    }

    /**
     * Генерация списка групп.
     *
     * @param count количество групп
     * @return список групп
     */
    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }

    /**
     * Сохранить csv-файл.
     *
     * @param groups список групп
     * @param file   путь к файлу
     * @throws IOException
     */
    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n",
                    group.getName(),
                    group.getHeader(),
                    group.getFooter()));
        }
        writer.close();
    }

    /**
     * Сохранить xml-файл.
     *
     * @param groups список групп
     * @param file   путь к файлу
     * @throws IOException
     */
    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    /**
     * Сохранить json-файл.
     *
     * @param groups список групп
     * @param file   путь к файлу
     * @throws IOException
     */
    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }
}
