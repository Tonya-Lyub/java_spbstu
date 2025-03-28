# Task Management System

## Описание
Система управления задачами, разработанная на Spring Boot. Проект демонстрирует различные аспекты разработки современного Java-приложения, включая:
- REST API
- Различные способы хранения данных (in-memory, H2, PostgreSQL)
- Кэширование
- Асинхронную обработку
- Микросервисную архитектуру
- Мониторинг

## Требования
- Java 17
- Gradle 8.x
- Docker (опционально)

## Запуск проекта
1. Клонируйте репозиторий
2. Выполните сборку проекта:
```bash
./gradlew build
```
3. Запустите приложение:
```bash
./gradlew bootRun
```

## API Endpoints
### Tasks
- GET /api/tasks - получить все задачи
- GET /api/tasks/{id} - получить задачу по ID
- POST /api/tasks - создать новую задачу
- DELETE /api/tasks/{id} - удалить задачу

### Users
- GET /api/users - получить всех пользователей
- GET /api/users/{id} - получить пользователя по ID
- POST /api/users - создать нового пользователя

### Notifications
- GET /api/notifications/unread/{userId} - получить непрочитанные уведомления
- GET /api/notifications/{userId} - получить все уведомления пользователя

## Тестирование
Для запуска тестов выполните:
```bash
./gradlew test
```

## Структура проекта
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── Application.java
│   │           ├── controller/
│   │           ├── model/
│   │           ├── service/
│   │           └── repository/
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── example/
``` 