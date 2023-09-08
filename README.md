# Kotlin Timer

## This project is a medule to practice Kotlin with MVI framework
### Compared with MVVM, MVI has a couple of advantages: 
![photo1](https://raw.githubusercontent.com/niuniu268/KotlinTimer/master/mvi2.png)

I use ViewModel to host the Model layer of MVI, and the overall structure is similar to MVVM. The main difference lies in the part where the Model interacts with the View layer.
The Model layer hosts UI states and exposes a ViewState for the View to subscribe to. ViewState is a data class that contains all the page states.
The View layer updates the ViewState through Actions, replacing the way MVVM interacts by calling ViewModel methods.

![MVVM](https://raw.githubusercontent.com/niuniu268/KotlinTimer/master/mvvm.png)
![MVII](https://raw.githubusercontent.com/niuniu268/KotlinTimer/master/mvii.png)
