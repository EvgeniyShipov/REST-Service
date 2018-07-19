REST service
==============

Проект представляет из себя REST сервис, обрабатывающий get запросы.
Сервис принимает идентификатор контакта, и отправляет в ответе последную заявку контакта.

Запуск
========

Чтобы скомпилировать проект выполните команду "mvn install".

Для запуска сервиса выполните команду "mvn clean package".
После запуска подключится H2(in-memory bd), и заполнится случайными значениями.

Сервис обрабатывает запросы на следуюшие адреса

- http://localhost:8080/json/{contactId}  (Ответ вернется в формате json)
- http://localhost:8080/xml/{contactId}  (Ответ вернется в формате xml)

contactId - CONTACT_ID, уникальный идентификатор контакта. 
Валидные значения - от 1 до 10.

База данных
-------------------------

В проекте используется in-memory bd - H2

Для подключения к ней достаточно пройти по адресу

http://localhost:8080/h2-console

 - Driver Class: org.h2.Driver
 - JDBC URL: jdbc:h2:mem:testdb
 - User Name: admin
 - Password: admin

Документация
-------------------------

Документирование сервиса осуществляется с помощью Swagger.
Так же подключен web UI, который доступен по адресу,

 - http://localhost:8080/swagger-ui.html
 
Автор проекта
-------------------------

Шипов Евгений Викторович
 - shipov.foto@mail.ru