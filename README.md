# Sprint_7

## Описание проекта
Проект **Sprint_7** представляет собой учебный проект для автоматизации тестирования с использованием следующих технологий:

- **JUnit 4**: для написания тестов.
- **RestAssured**: для тестирования REST API.
- **Allure**: для создания отчетов о тестировании.
- **Maven**: для управления зависимостями и автоматизации сборки.

## Структура проекта

- **src/test/java/**: содержит тестовые классы.
- **target/allure-results/**: временные файлы, генерируемые Allure для создания отчетов.
- **docs/**: папка, куда сохраняется отчет Allure.

## Требования для запуска

- Java 11 или выше
- Maven 3.6 или выше
- Установленный Allure (для команды `allure serve`)

## Как запустить тесты

1. **Через IntelliJ IDEA**:
    - Открой проект в IntelliJ IDEA.
    - Нажми правой кнопкой мыши на тестовый класс или метод и выбери "Run".

2. **Через командную строку**:
   ```bash
   mvn clean test
   ```

## Генерация Allure-отчета

1. Выполнить тесты:
   ```bash
   mvn clean test
   ```

2. Сгенерировать отчет Allure:
   ```bash
   mvn allure:report
   ```

3. Отчет будет сохранен в папке `target`. Чтобы открыть отчет локально:
   ```bash
   mvn allure:serve
   ```
