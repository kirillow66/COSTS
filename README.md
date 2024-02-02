# Система учёта расходов

## Работа с приложением

```plantuml
@startuml
:Админ: as Admin

(Вход в приложение) as (Login)
(Список пользователей) as (Users)
(Список расходов) as (Costs)
(Карточка пользователя) as (User)
(Карточка расхода) as (Cost)
(Расходы по категориям) as (Categories)

Пользователь --> (Login)

Admin ---> (Login)

Login ---> (Users) : Если пароль верный\nи роль ADMIN
Login ---> (Costs) : Если пароль верный\nи роль USER
Login ---> (Login) : Если пароль неверный

Users ---> (User) : Создать\nнового\nпользователя
Users ---> (User) : Изменить\nсуществующего\nпользователя

Costs ---> (Cost) : Создать\nновый\nрасход
Costs ---> (Cost) : Изменить\nсуществующий\nрасход

User ---> (Users) : Сохранить\nпользователя
User ---> (Users) : Удалить\nпользователя
User ---> (Users) : Вернуться\nбез сохранения

Cost ---> (Costs) : Сохранить\nрасход
Cost ---> (Costs) : Удалить\nрасход
Cost ---> (Costs) : Вернуться\nбез сохранения

Costs ---> (Categories) : Перейти (по умолчанию\nпериод: текущий месяц)
Categories ---> (Categories) : Просмотреть расходы за выбранный период
Categories ---> (Costs) : Вернуться

note left of Login : Далее переход в зависимости от роли

@enduml
```

## Роли приложения

Роли:

- Админ
- Рядовой пользователь

## Функции приложения

Функции админа:

1. Логин к списку всех пользователей
1. Просмотр списка пользователей
1. Ввод данных нового пользователя
1. Просмотр/изменение существующего пользователя
1. Сохранение пользователя
1. Удаление пользователя

Функции рядового пользователя:

1. Логин к списку своих расходов
1. Просмотр списка расходов
1. Ввод данных нового расхода
1. Просмотр/изменение существующего расхода
1. Сохранение расхода
1. Удаление расхода
1. Просмотр расходов за период по категориям

## Экраны приложения

Экраны:

1. Экран логина
1. Список пользователей
1. Карточка пользователя
1. Список расходов
1. Карточка расходов
1. Просмотр расходов за период по категориям

### Экран 1 "Логина"

```plantuml
@startsalt
{
  Login    | "MyName   "
  Password | "****     "
  [Cancel] | [  OK   ]
}
@endsalt
```

### Экран 2 "Список пользователей"

```plantuml
@startsalt
{
    {SI
        vasya | vv@mail.ru
        peter | ppetrov@mail.ru
        lmasha | lmaria@mail.ru
        suser | admin@mail.ru
        .
    }
    {
        [Создать]
    }
}
@endsalt
```

### Экран 3 "Карточка пользователя"

```plantuml
@startsalt
{
  Логин | "MyName   "
  Почта | "name@domain.ru     "
  [Сохранить] | [ Отмена  ]
}
@endsalt
```

### Экран 4 "Список расходов"

```plantuml
@startsalt
{
    {SI
        Бензин | Транспорт | 1499.05
        Зубная щетка | Хозтовары | 147.50
        Молоко | Питание | 99.99
        Огурцы | Питание | 255.00
        Туалетная бумага | Хозтовары | 10.99
        .
    }
    {
        [Создать] | [Просмотр по категориям]
    }
}
@endsalt
```

### Экран 5 "Карточка расхода"

```plantuml
@startsalt
{
  Расход    | "Наименование расхода  "
  Категория | ^Категория расходов^
  Сумма     | "999.99     "
  [Сохранить] | [ Отмена  ]
}
@endsalt
```

### Экран 6 "Список расходов за период"

```plantuml
@startsalt
{
    {SI
        Транспорт | 1499.05
        Хозтовары | 147.50
        Питание   | 99.99
        .
    }
    {
       Дата с | "01.02.2024"
       Дата по | "15.02.2024"
       [Просмотреть] | [ Вернуться ]
    }
}
@endsalt
```
