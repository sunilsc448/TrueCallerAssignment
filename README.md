# Assignment contents
1. Dagger2
2. Retrofit
3. MVVM
4. Data Binding
5. Navigation graph
6. Coroutines
7. File Storage
8. Network State Broadcaster
9. Unit Testing

#The app should define and run 3 requests SIMULTANEOUSLY, each request is defined below:
 * The app uses Retrofit Service to make 3 API calls simultaneously and perform respective actions on the response of all these 3 API's
   1. Truecaller10thCharacterRequest - Fetch the 10th element from the response
   2. TruecallerEvery10thCharacterRequest - Fetch all the characters every 10th position and made a list of it.
      Displays only a brief of the items with comma separated string in the first page and displays all of the items on "View More" in the next page
   3. TruecallerWordCounterRequest - Made a list of words splitting them by space.
    Displays only a brief of the items with comma separated string in the first page and displays all of the items on "View More" in the next page

#The app saves the response in File and uses it as response in case of No Network means the app has offline support.
#The app uses the MVVM architecture
#The business layer is separated using Repository layer
#The app uses data binding
#Few unit test cases are also covered




