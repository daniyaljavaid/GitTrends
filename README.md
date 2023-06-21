# GitTrends

Android application which fetches & renders trending Github repositories from github API. This app is built using Android's 
new Architectural guidelines referred as **MAD (Modern Android Development)**. 

This project follows following architectural/design principles:

#### 1. Clean Architecture
Architectural guidelines which leads to different layers i.e **data, domain & presentation** in app application. 
Each layer having its responsibilities encapsulated.

#### 2. Reactive Architecture
Each layer in application provides a stream of data. So whenever changes happen in any layer, it's changes reflects directly on other layers
without pulling data again n again. eg if changes happen in data layer let's say in Database, the UI layer only observes the stream, rather than
it pulls data again and again to update it's UI.

This app use **Kotlin Flows** for this purpose.

#### 3. Repository Pattern
Repository pattern leads to a seperate layer called data layer whose responsibility is to handle app's data & business logic. It exposes API to
other layers of your app to access data. This layer is responsible to manage both all data sources. 

In our app, we have **Remote** & **Local** Data source. Which are not directly exposed to other layers but our Repository is responsible to expose data
from these sources. To avoid network calls, we have added cache mechanism using **Room Db**. So if data already exists, we show data from database.

#### 4. Test Driven Development
A development practice which focuses on writing tests and production code together rather than writing tests after completing production code.
It is an iterative approach in which we write tests, production code & refactor that code in a cycle.

#### 5. Jetpack Compose
This project uses jetpack compose, Android's recommended modern toolkit for building native UI.

### Github Endpoint
```
https://api.github.com/search/repositories?q=language=+sort:stars
```
### What's left:
1. Shimmer
2. Pull to refresh
3. UI tests
