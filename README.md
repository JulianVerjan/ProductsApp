### Code style

To maintain the style and quality of the code, I used the bellow static analysis tools.

| Tools                                                    Check command              | Fix command               |
|---------------------------------------------------------|--------------------------------------------------------
| [detekt](https://github.com/arturbosch/detekt)          | `./gradlew detekt`        | -                         |
| [spotless](https://github.com/diffplug/spotless)        | `./gradlew spotlessCheck` | `./gradlew spotlessApply` |
| [lint](https://developer.android.com/studio/write/lint) | `./gradlew lint`          | -                         |


## Design

App: the content has been adapted to fit for mobile devices. To do that, it has been created flexible layouts using one or more of the following concepts:

-   [Use constraintLayout](https://developer.android.com/training/multiscreen/screensizes#ConstraintLayout)
-   [Avoid hard-coded layout sizes](https://developer.android.com/training/multiscreen/screensizes#TaskUseWrapMatchPar)

## Architecture

The architecture of the application is based on the following points:

-   A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
-   Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitating a [separation](https://en.wikipedia.org/wiki/Separation_of_concerns) of development of the graphical user interface.
-   [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) design principles intended to make software designs more understandable, flexible and maintainable.
-   [Modular app architecture](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82) allows to be developed features in isolation, independently from other features.

### Modules

The above graph shows the app modularisation:
-   `:app` depends on `:commons:resources` and indirectly depends on `:features`.
-   `:features` modules depends on `:commons`, `:lib:network`, `:lib:data` and `:lib:model`.
-   `:commons` only depends for possible utils on `:lib`.
-   `:lib` don’t have any dependency.

#### App module

The `:app` module is the main entry of the app which is needed to create the app bundle.  It is also responsible for initiating the [dependency graph]

#### Core module

The `:lib:network` module is an android library  for serving network requests. Providing the data source for the catalog information.

#### Core module

The `:lib:data` module is an android library  and contains the use cases that fetch the data for the view models.

#### Features modules

In the `:features` module you will find the feature that fetch and show the related data about a catalog 


#### Commons modules

The `:commons` module only contains code and resources which are shared between feature modules. Reusing this way resources, 
layouts, views, and components in the different features modules, without the need to duplicate code.


#### Libraries modules

The `:lib` modules basically contains different utilities that can be used by the different modules.

#### Technology

Some of the main libraries used for this app are:

1. Coil library for handling image urls request
2. Dagger Hilt for handling the dependency injection
3. Fragment navigation for handling the navigation inside the app.

For more information related to the libraries used, please check the Dependencies.kt class on the buildSrc project.