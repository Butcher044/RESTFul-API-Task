# 🌟 Restful API Project

Этот проект представляет собой пример Restful API для управления задачами.

## 📖 Описание

Проект построен с использованием Java 17, Spring Boot, Hibernate (или Spring Data), и Rest Assured для тестирования веб-сервисов.

## ⚙️ Требования

- Java 17
- Maven
- Spring Boot

## 🚀 Установка

1. Клонируйте репозиторий: `git clone <URL репозитория>`
2. Перейдите в каталог проекта: `cd Restful-api-project`
3. Установите зависимости: `./mvnw install` (или `mvnw.cmd install` на Windows)
4. Запустите приложение: `./mvnw spring-boot:run` (или `mvnw.cmd spring-boot:run` на Windows)

## 📂 Структура проекта

- `com.example.restfulapiproject.controller`: Контроллеры для обработки HTTP-запросов.
- `com.example.restfulapiproject.facade`: Фасады для упрощения взаимодействия контроллеров и сервисов.
- `com.example.restfulapiproject.mapper`: Мапперы для конвертации между сущностями и DTO.
- `com.example.restfulapiproject.model.dto`: DTO (Data Transfer Objects) для передачи данных между компонентами.
- `com.example.restfulapiproject.model.entity`: Сущности, представляющие объекты в базе данных.
- `com.example.restfulapiproject.repository`: Репозитории для взаимодействия с базой данных.
- `com.example.restfulapiproject.service`: Сервисы, реализующие бизнес-логику.

## 🧪 Тестирование

Вы можете запустить тесты с использованием команды: `./mvnw test`

## 🔒 Аутентификация

Проект включает в себя механизм аутентификации с использованием Spring Security. Для тестирования доступны следующие учетные записи:

- **Администратор:**
  - Имя пользователя: admin
  - Пароль: 123
  - Роли: ADMIN, USER

- **Пользователь:**
  - Имя пользователя: user
  - Пароль: 123
  - Роли: USER

Пример запроса для аутентификации пользователя:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"username":"user","password":"123"}' http://localhost:8080/auth/login
```

## 📋 Использование

- **Получение задачи по ID:**
  - Endpoint: `/api/tasks/{id}`
  - HTTP-метод: GET
  - Пример запроса: 
  ```bash
  curl -X GET http://localhost:8080/api/tasks/1
  ```

- **Создание или обновление задачи:**
  - Endpoint: `/api/tasks`
  - HTTP-метод: POST
  - Пример запроса: 
  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"title":"New Task","description":"Task Description","completed":false}' http://localhost:8080/api/tasks
  ```

- **Удаление задачи по ID:**
  - Endpoint: `/api/tasks/{id}`
  - HTTP-метод: DELETE
  - Пример запроса: 
  ```bash
  curl -X DELETE http://localhost:8080/api/tasks/1
  ```

## 🌐 Дополнительные возможности

- Деплой на внешний сервер (опционально)
ы можете связаться со мной по электронной почте: [ваш_email@example.com](mailto:ваш_email@example.com)