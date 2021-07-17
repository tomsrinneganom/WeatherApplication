# WeatherApplication

 Weather forecast app that changes the theme of the app in different weather conditions
  <div align="center">
  <img src="https://user-images.githubusercontent.com/44583642/125308681-c86a9000-e339-11eb-9f88-b633405e6988.jpg" width="200" />
  <img src="https://user-images.githubusercontent.com/44583642/125308683-c86a9000-e339-11eb-8e80-d6cef07b3cec.jpg" width="200" />
  <img src="https://user-images.githubusercontent.com/44583642/125308671-c7396300-e339-11eb-8435-29b8eba903ba.jpg" width="200" />
</div>

<h2> Tech Stack</h2>
Kotlin, Coroutines, Flow, StateFlow, SharedFlow, Ktor, Room, Preferences DataStore, Hilt, MVVM
<br/>
<h2>How the application works</h2>
<br/>
The MainActivity creates a ForecastFragment that calls a method from the ForecastViewmodel that returns a flow with the CurrentForecast and ForecastForDaysOfTheWeek. ForecastViewmodel transfers flow with location to repository. The repository retrieves data from the local database, and as soon as it receives the location from the flow, it makes a request to get the current forecast using the OpenWeather Api. The location is recorded in DataStore due to this, if the application does not have permission to use the location or the geolocation is turned off on the device, the location can be retrieved from the dataStore. If the application does not have access to the location and the last location is not stored in the Datastore, then an ErrorFragment will be displayed with a request to enable geolocation, or an empty ForecastFragment screen.
