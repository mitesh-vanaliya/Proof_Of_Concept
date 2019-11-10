# Proof Of Concept
Proof of concept is app which consumes a REST service and displays photos with headings and
descriptions.

# Task Specifications:

Created an Android app which contains:
- [x] Ingests a json feed from [https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json](https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json)

    • The feed contains a title and a list of rows.
     
    • Use of third party json parser to parse this if desired.

- [x] Display the content *(including image, title and description)* in a RecyclerView

    • The title in the ActionBar should be updated from the json data.
 
    • Each row should be dynamically sized to the right height to display its content - no clipping, no extraneous white-space etc. This means some rows will be larger than others.

- [x] Loads the images lazily *(does not download them all at once, but only as needed)*.

- [x] Implement a refresh function allowing the data & view to be updated

    • Use of pull down to refresh.

- [x] Does not block UI when loading the data from the json feed.


# Project Structure
• Kotlin for programming language

• MVVM Architecture with Databinding and  AndroidX 

• Repository pattern for handling business logic

• ROOM Persistence for local SQLite database connection

• Binder pattern used for all activities

• Kotlin DSL implementation

• Kotlin Extensions 



# App Screenshots

![](https://i.stack.imgur.com/Sn6gb.jpg)


# Output

Download Apk from **[here](https://github.com/mitesh-vanaliya/Proof_Of_Concept/tree/master/APK/Proof_Of_Concept.apk)**
