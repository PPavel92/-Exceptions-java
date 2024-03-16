package Home_work;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(
                    "| Введите данные через пробел:|| Фамилия Имя Отчество || Дата рождения (dd.mm.yyyy) || Номер телефона 8... || Пол (f или m)|");
            System.out.println(
                    "|-----------------------------||----------------------||----------------------------||---------------------||--------------|");
            String input = scanner.nextLine();

            String[] data = input.split(" ");

            if (data.length < 6) {
                System.err.println("Ошибка: Введено недостаточное количество данных.");
                return;
            } else if (data.length > 6) {
                System.err.println("Ошибка: Введено избыточное количество данных.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            LocalDate dateOfBirth = LocalDate.parse(data[3],
                    java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            if (!lastName.matches("[a-zA-Z]+") || !firstName.matches("[a-zA-Z]+")
                    || !middleName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Фамилия, имя и отчество должны содержать только буквы.");
            }

            if (phoneNumber < 0 || !Pattern.matches("^8\\d{10}$", Long.toString(phoneNumber))) {
                throw new IllegalArgumentException(
                        "Неверный формат номера телефона. Вводите номер телефона начиная с 8 и состоящий из 10 чисел!");
            }

            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
            }
            LocalDate currentDate = LocalDate.now();
            LocalDate earliestDate = currentDate.minusYears(120);
            LocalDate latestDate = currentDate.minusYears(5);

            if (dateOfBirth.isBefore(earliestDate) || dateOfBirth.isAfter(latestDate)) {
                throw new IllegalArgumentException("Неверная дата рождения. Укажите вашу реальную дату рождения.");
            }

            Person person = new Person(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);

            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String formattedData = String.format("| %-20s | %-10s | %-10d | %-1c |\n",
                                                        person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName(),
                                                        person.getDateOfBirth(),
                                                        person.getPhoneNumber(),
                                                        person.getGender());
                String separatorLine = "+---------------------------------------------------------------+\n";
                writer.write(separatorLine);
                writer.write(formattedData);
                writer.close();
            }
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат номера телефона.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: Недостаточно данных.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ошибка: Неверный формат данных.");
            e.printStackTrace();
        }
    }
}
