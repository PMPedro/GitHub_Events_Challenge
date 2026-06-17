# eCabs Android Challenge — Pedro Martins

An Android application built for the eCabs Android Developer challenge. The app consumes the public GitHub Events API and displays a live list of events, with a dedicated detail screen for each one.

---

## Features

- Live list of recent GitHub public events
- Detail screen for each selected event
- Automatic background refresh every 10 seconds, merging new events into the existing list
- Reactive UI built entirely with Jetpack Compose

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM |
| State Management | ViewModel + StateFlow |
| Async | Kotlin Coroutines |
| Networking | Retrofit |
| Dependency Injection | Dagger 2 |

---

## How to Run

1. Unzip the project (or clone the repository)
2. Open it in Android Studio
3. Let Gradle sync finish
4. Run on an emulator or device (`minSdk 24`)

> No extra configuration, API keys, or manual steps are required.

---

## What's Left / Possible Improvements

- [ ] Unit tests for ViewModels and Repository
- [ ] Offline caching of events
- [ ] More granular error and empty state handling

---

## A Note on AI Usage

AI assistance was used for research, generating minor secondary files such as the color scheme, and reviewing the final implementation. All code was written, understood, and validated by me.
