[![Build Status](https://travis-ci.org/PURUS6233/contact_book.svg?branch=master)](https://travis-ci.org/PURUS6233/contact_book)

contact_book
Веб-приложение. Принимает запросы для поиска контактов в базе данных.
Примеры запросов:
1) /hello/contacts - возвращает все контакты в json формате в виде:
contacts: [ Contact, ... ];
2) /hello/contacts?nameFilter=^.*[A].*$ - возвращает контакты, которые НЕ содержат букв A в json формате в виде:
contacts: [ Contact, ... ];
3) /hello/contacts/{id} - возвращает контакт с заданной id в json формате в виде:
Contact
{
  “id”: integer,
  “name”: string,
  "links": [
	{
      "rel": "contact",
      "href": "http://localhost:8080/hello/contacts/{id}"
    }
  ]
};

База данных
Результаты поиска считываются с БД PostgreSQL.

1. Перед запуском приложения необходимо локально установить БД PostgreSQL;

2. Если есть существующая база данных данный пункт пропускаем и переходим пункту 3. 
Если необходимо создать БД, можно выполнить установочные sql скрипты из файлов:
- (sql/create.txt) - скрипты для создания базы данных, таблицы и схемы;
- (sql/data.txt) - скрипты для заполнения схемы данными для проверки;

3. Выполнить настройки конфигурационного файла (src/main/resources/application.properties):
spring.datasource.driver-class-name=org.postgresql.Driver //драйвер БД
spring.datasource.url=jdbc:postgresql://localhost:5432/<data_base_name> //Поменять 5432 на порт на котором БД запущена локально; изменить имя базы данных <data_base_name> на установленную локально
spring.datasource.username= //Логин доступа к БД
spring.datasource.password= //Пароль доступа к БД;

4. Проверить соответствие sql скриптов с настройками базы данных в файле (src/main/resources/sql.properties);

5. Проверить соответствие sql скриптов интеграционных тестов с настройками базы данных в файлах:
- (src/it/resources/beforeTestRun.sql),
- (src/it/resources/afterTestRun.sql);

Запуск тестов

Чтобы запустить интеграционные и Unit тесты необходимо выполнить фазы сборки maven выше verify. 
Пример: mvn clean verify;

Для запуска только Unit тестов необходимо выполнить фазу сборки maven test. 
Пример: mvn clean test;
