# TranslateSqlQueryToMongoDB
This program translates SQL select queries into MongoDB

SQL for MongoDB
Задача 1

На Java или Kotlin напишите мини-версию транслятора из SQL в команды MongoDB shell. ​

Транслятор на вход получает строку с SQL запросом и отдает строку с командой MongoDB. Пример SELECT * FROM sales LIMIT 10 -> db.sales.find({}).limit(10) ​

    Достаточно поддержать только SELECT-запросы
    Список колонок в SELECT должен быть транслирован в projection команды find: SELECT name, surname FROM collection -> db.collection.find({}, {name: 1, surname: 1})
    OFFSET и LIMIT транслируются в соответствующие функции MongoDB курсоров: SELECT * FROM collection OFFSET 5 LIMIT 10 -> db.collection.find({}).skip(5).limit(10)
    Предикаты в WHERE превращаются в предикаты команды find: SELECT * FROM customers WHERE age > 22 AND name = 'Vasya' -> db.customers.find({age: {$gt: 22}, name: 'Vasya'}). Реализуйте только операции сравнения: = <> < >. Если предикатов несколько, то они соединены операцией AND ​

Не забудьте написать тесты.

​ В качестве ответа пришлите, пожалуйста, ссылку на репозиторий (например на github). ​

Edit 1: ключевое слово SKIP было заменено на общепринятое OFFSET. Я зачту оба варианта.

​ Edit 2: уточнено задание для WHERE: предикатов может быть несколько, они соединены операцией AND. Если вы учитывали только один предикат, это тоже будет засчитано.

TO START THE PROGRAMM, GO TO THE ROOT FOLFER OF THE PROJECT AND EXECUTE THE COMMANDS:
     
javac -d classes -cp src src/by/sergel/intership/Main.java

java -cp classes by.sergel.intership.Main "SQL query"

replace "SQL query" with your SQL query!

FOR EXAMPLE:
        java -cp classes by.sergel.intership.Main "SELECT * FROM user"
    

  
