## 2. SQL: Ініціалізація бази

**Файл:** `sql/create_accounts_db.sql`

```sql
-- 1) Створення схеми (якщо її немає)
CREATE DATABASE IF NOT EXISTS accounts_db;

-- 2) Перемикання контексту на нашу БД
USE accounts_db;

-- 3) Визначення структури таблиці account
CREATE TABLE IF NOT EXISTS account (
  id INT AUTO_INCREMENT PRIMARY KEY,       -- Унікальний ключ, автоінкремент
  first_name VARCHAR(255),                -- Ім’я користувача
  last_name VARCHAR(255),                 -- Прізвище користувача
  username VARCHAR(255) NOT NULL UNIQUE,  -- Логін (унікальний)
  email VARCHAR(255) NOT NULL UNIQUE,     -- Email (унікальний)
  password VARCHAR(255) NOT NULL,         -- Хеш пароля
  role_id INT                             -- Логічне посилання на роль (без FK)
);
```

> **Пояснення**  
> - `AUTO_INCREMENT`: автоматичне збільшення `id` при вставці.  
> - `UNIQUE`: гарантує відсутність повторів для логіна та email.  
> - Таблиця **автономна**, без зовнішніх ключів — зручно для демонстрацій і тестів.
