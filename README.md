# WeatherApp
## Тестовое задание выполнил Стрешнев Игорь

### Технологический стек:
1. Jetpack Compose
2. Kotlin Coroutines
3. Retrofit
4. Dagger-Hilt
5. ViewModel

Архитектура приложения построена в соответствии с CleanArchitecture. 
Приложение разделено на три слоя:
- Presentation - UI и ViewModel
- Domain - Entity-классы, Use-cases, интерфейсы Repository
- Data - DTO-классы, реализации Репозиториев, DataSource-ы

Для Dependecy Injection был использован Dagger-Hilt за счет его удобной интеграции с Compose

Приложение содержит два Compose-экрана: CitiesListScreen и WeatherScreen, одну Activity WeatherAppMainActivity. 
