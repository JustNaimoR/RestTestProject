# RestTestProject
< Заввисимости: >

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
    
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
    
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
    
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

< Предметная область и общее описание: >
Проект состоит из сущностей университет, факультет, студент. Связь один ко многим (1 университет, много факультетов; 1 факультет, много студентов). Из реализованных функций - 1. вывод списков всех университетов, факультетов, студентов 2. вывод всех факлуьтетов университета, всех студентов факультета 3. добавление новых университетов, факультетов, студентов 4. возможность изменять данные студентов (изменение данных других по сути строится по аналогии). 

Университет содержит:
уникальный id, название, город, наличие аккредитации, год основания
Факультет содержит:
уникальный id, название, год основания, количество бюджетных мест, независимость, id университета, в котором находится
Студент содержит:
уникальный id, имя, фамилию, возраст, год поступления, учится ли, id факультета, на котором находится

При добавлении и обновлении данных используются валдаторы для недопустимости добавления студента с несуществущим факлуьтетом (то же и с университетом и факультетом), повторения данных или их некорректность.

< Инструкция работы: >
1. Для работы требуется БД. Я использоват PostgreSQL. Создается БД с названием "University" и паролем "159637482" (либо другое название с паролем, но тогда требуется в файле конфигурации src/main/resources/application.properties поменять значения полей spring.datasource.url и spring.datasource.password) 
2. Через Postman открывается файл Postman_config.json с готовыми папками запросов.
3. Репозиторий клонируется к себе и скачивается на локальный компьютер. В среде разработки (а моём случае Intellij idea) подключить БД через Database и запустить проект через RestTestProjectApplication класс
4. Через Postman производятся команды для работы
