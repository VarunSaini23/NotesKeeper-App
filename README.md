# NotesKeeper-App
In Progress
An android application to Save Text Notes, Voice Notes, Photo Notes etc. with lot of Text Customization Features. Build to use Android Jetpack Components. During Google IO18 the Android team released some new libraries along with existing Android Architecture Components. This app aims to use most of AAC libraries in MVVM architecture.

A simple app which saves all you notes in sqlite using Page Library. Show these notes in a Recyclerview using LiveData. Customize the notes like never before - A lot of text formatting features available . Some are :-
- Bold
- Italics
- Font Size
- Font Color
- Image
- Bullets
- Checkbox
- Alignment
- Quoted Text
- Undo/Redo

You can connect your Google Account with this App which will Save all your notes on Firebase so that you never loses your Notes.
Just restore them whenever you lost your Sqlite saved Notes (In Case of Uninstalling and Reinstalling App). Video Notes feature to be added soon.

## Components used
- Lifecycle - Used by LiveData.
- LiveData - For observing upon data in ViewModel.
- ViewModel - For managing UI data.
- Room - For persisting data and storing it in database
- Paging - For loading long list or infinite data in RecyclerView

## To-Do
- Restore Feature
- Video Notes
- Integrate DataBinding
- Create widget
- Write tests

## Built With
- Android Studio
- Java
- Android JetPack
- Gradle


