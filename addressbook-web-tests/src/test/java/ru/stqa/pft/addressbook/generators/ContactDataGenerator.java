package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Генератор тестовых данных по Контактам с записью в файл.
 */
public class ContactDataGenerator {
    /**
     * Количество групп (Например, "-c 2").
     */
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    /**
     * Путь к файлу (Например, "-f src/main/resources/contacts.json")
     */
    @Parameter(names = "-f", description = "Target file")
    public String file;
    /**
     * Формат документа (Например, "-d json")
     */
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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
        List<ContactData> contacts = generateContact(count);
        switch (format) {
            case "csv":
                saveAsCsv(contacts, new File(file));
                break;
            case "xml":
                saveAsXml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts, new File(file));
                break;
            default:
                System.out.println("Unrecognized format" + format);
                break;
        }
    }

    /**
     * Генерация списка контактов.
     *
     * @param count количество контактов
     * @return список контактов
     */
    private static List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("Firstname%s", i))
                    .withMiddlename(String.format("Middlename%s", i))
                    .withLastname(String.format("Lastname%s", i))
                    .withAddress(String.format("Cityname str.Lenina house %s", i))
                    .withPhoneHome(String.format("+7(499)654321%s", i))
                    .withPhoneWork(String.format("+7(495)123456%s", i))
                    .withPhoneMobile(String.format("+7(925)123456%s", i))
                    .withEmail(String.format("testmail%s@email.ru", i))
                    .withEmail2(String.format("testmail%s@email2.com", i))
                    .withEmail3(String.format("testmail%s@email3.org", i)));
        }
        return contacts;
    }

    /**
     * Сохранить csv-файл.
     *
     * @param contacts список контактов
     * @param file     путь к файлу
     * @throws IOException
     */
    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstname(),
                        contact.getMiddlename(),
                        contact.getLastname(),
                        contact.getAddress(),
                        contact.getPhoneHome(),
                        contact.getPhoneWork(),
                        contact.getPhoneMobile(),
                        contact.getEmail(),
                        contact.getEmail2(),
                        contact.getEmail3()));
            }
        }
    }

    /**
     * Сохранить xml-файл.
     *
     * @param contacts список контактов
     * @param file   путь к файлу
     * @throws IOException
     */
    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    /**
     * Сохранить json-файл.
     *
     * @param contacts список контактов
     * @param file   путь к файлу
     * @throws IOException
     */
    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }
}
