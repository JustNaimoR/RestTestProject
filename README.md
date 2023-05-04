# RestTestProject
Заввисимости:

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

Инструкция работы:
1. Для работы требуется БД. Я использоват PostgreSQL. Создается БД с названием "University" и паролем "159637482" (либо другое название с паролем, но тогда требуется в файле конфигурации src/main/resources/application.properties поменять значения полей spring.datasource.url и spring.datasource.password) 
2. Через Postman открывается файл Postman_config.json с готовыми папками запросов.
3. Репозиторий клонируется к себе и скачивается на локальный компьютер. В среде разработки (а моём случае Intellij idea) подключить БД через Database и запустить проект через RestTestProjectApplication класс
4. Через Postman производятся команды для работы
