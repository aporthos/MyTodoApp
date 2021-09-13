My Todo
======================

The app contains an IPC module, Top Ten's and a configuration module, you can share the graph as well as a pdf file with the list of Top Ten's in the market.
An MVVM architecture was used, using Clean Architecture, along with the repository pattern

Two keys were configured in RemoteConfig, one belongs to the IPC information url, and the other is the polling time for the TopTen part.

## Building and Running
In order to run the project, it is necessary to add the `MyTodoConfig` folder to `Documents`, following the path of the file `KeyHelper -> PRIVATE_PROPERTIES`, it is also necessary to update the path of the `keyStore`, updating the `keystore.properties`

![Sep-13-2021 4-29-15 PM](https://user-images.githubusercontent.com/8774947/133159294-2fe36f67-9af1-4207-b0a2-a92595f7d07f.gif)


## The project features:
- [x] Kotlin
- [x] Android Biometric
- [x] MPAndroidChart
- [x] Epoxy
- [x] Itextpdf
- [x] Firebase Auth
- [x] Firebase Remote Config
- [x] Dagger Hilt
- [x] Coroutines
- [x] Navigation
- [x] DataBinding
- [x] ViewModel
- [x] LiveData
- [x] Retrofit2
- [x] OkHttp
- [x] JUnit4
- [x] Mockito
- [x] Moshi
- [x] Lottie

## Modules:
1. ipc : Show a line graph, where you can share the graph
2. login : Displays the login screen, validating whether or not the fingerprint can be used
3. topten : It shows the list of Top Te's in the market and generates a PDF of it
4. shared : Contains method extensions, constants, base classes