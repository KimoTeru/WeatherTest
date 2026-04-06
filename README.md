# Weather Test

Android приложение для отслеживания погоды, построенное на современных технологиях: **Decompose**, **Koin**, **Retrofit**, **Room** и **Jetpack Compose**.

## Запуск проекта

Для корректной работы приложения необходимо получить API ключ от сервиса **OpenWeatherMap**.

1. Зарегистрируйтесь на [OpenWeatherMap](https://openweathermap.org/api).
2. Создайте новый API Key в личном кабинете.
3. Откройте файл `build.gradle.kts` (в модуле `:app`) и вставьте ваш ключ в соответствующее поле (ищите строку с `API_KEY`).

```kotlin
// Пример в build.gradle.kts
buildTypes {
    debug {
        buildConfigField("String", "API_KEY", "\"ВАШ_КЛЮЧ_ЗДЕСЬ\"")
    }
}
```

## Стек технологий
- **Decompose**: для управления навигацией и жизненным циклом компонентов.
- **Koin**: Dependency Injection.
- **Retrofit**: сетевые запросы.
- **Room**: локальное хранилище истории поисков.
- **Jetpack Compose**: декларативный UI.
- **Kotlin Coroutines & Flow**: асинхронная работа.