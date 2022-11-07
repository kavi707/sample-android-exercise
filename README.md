# Sample test application

## Issues
- **Network API request**</br>
  - I was struggling to retrieve the data from your end-point. I can see using browser window that data fetch, but while using retrofit
    or when I was trying via Postman, getting the same 503 error and error page ask to enable cookies
  - While I check the difference in browser and my code, I was missing to process the cookies
  - For this reason, I have introduce CookieInterceptor that have two interceptors which read cookies from initial request (failure request)
    and store them in share-preference to use in immediate next request.
  - But even if I see header as `cookie` in browser inspect window, I don't receive it in the app.
  - Because if this blocker, I kept it a side and continue with other tasks
  - Handle the error and if we have error, then its show as error in the text-view used to show bit-coin value.

## Things can improve
- **Make the groovy build scripts to kotlin and introduce `buildSrc` to the project**</br>
  When the app grows up, we will have multi modules in the app. Therefore each module will differnt gradle files with same kind of configurations. But with the `buildSrc` we can have all gradle base configurations in one place for all the modules, and if we have plugin plugins, repository configurations also in that `buildSrc`.
- **Use MVVM instead of MVP**</br>
  Reason to convert app to MVVM is we can easily do the view-binding + data-bingin with MVVM, and when we use kotlin-coroutines we have the viewModelScope to scope some view related task done in view-model(What we discussed in the interivew).
- **Improve ApiManager**</br>
  In this code base the `ApiManager` is just an object that contain helps to get the Api interface. But we can improve this and related part to handle the errors, handle the response and send only the requested parameters to the view. Also we can modify it to accept custom headers, custom parameters as well as custom API interfaces.
- **Make a parent-module**</br>
  Idea to have this parent-module is to keep all the abstraction & common functionalities related to app in one place. As an example we can have `BaseActivity` or `BaseFragment` that responsible to keep all navigating methods, initiate databinding or viewbinding objects. This will help in the future developments.
- **Unit Test**</br>
  Create unit test for the code.
  
## Algorithm question
- ** Compaire two arrays **
To reduce the complexity for that instead of interage two arrays, merge those two arrays into one list iterate that single list and compair each eligment start from 0 index in new list. 
