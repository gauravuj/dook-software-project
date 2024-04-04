---
layout: page
title: User Guide
---

## What is Dook?

![UiPreview](images/UserGuideFront.png)

An **elegant minimalistic desktop application** made especially for **NUS Computing students** to easily manage their academic consultations and contacts all-in-one place. This lightweight
platform enables users to quickly view and manage their consultations with people.

Dook blends the power of a nimble [**Command Line Interface (CLI)**](#glossary) together with the intuitive accessibility of a
[**Graphical User Interface (GUI)**](#glossary).

Designed for the dynamic needs of the NUS
School of Computing (SoC) community, Dook centralizes faculty information
in an innovative manner, allowing one to easily find their professors by taking advantage of the basic CLI familiarity of SoC students.

For the quick typist, Dook elevates your ability to swiftly organize your academic consultations with professors
and teaching assistants, outpacing conventional GUI-based applications, making management of consultations a breeze.

We hope that you will find this guide helpful in maximising your Dook experience! :)

--------------------------------------------------------------------------------------------------------------------

## Using the Guide
We hope that this guide will familiarise you with the [CLI](#glossary) commands and [GUI](#glossary) and interface. If you
are unfamiliar with some jargon here, do have a quick look at our [glossary](#glossary) below! :)

* For **first-time users**, please take a look at our [Quick Start](#quick-start) section to start an end-to-end tutorial for Dook.
* For **seasoned users** who know command line and/or have used _Dook_ before, do take a look at our [Command Summary](#command-summary) for a refresher!

Throughout this guide, we also use coloured boxes to provide any important or extra pointers that we hope you would find useful.

<div markdown="block" class="alert alert-info">
:information_source: **Information**

Content in blue boxes provides additional information and contextual knowledge you need to better understand Dook.
</div>

<div markdown="block" class="alert alert-success">
:bulb: **Tips**

Content in green boxes provides tips and good practices to help you use the application more efficiently.
</div>

<div markdown="block" class="alert alert-danger">
:exclamation: **Warnings** <br>

Content in red boxes draws your attention to potential pitfalls to avoid and alert you to possible errors.
</div>

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have [Java 11](#faq) or above installed in your Computer. If  If you have never downloaded it before,
   download from [here.](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)

2. Download the latest version of [Dook](https://github.com/AY2324S2-CS2103T-W11-3/tp/releases) in your computer.

3. Copy the file to an empty folder you want to use as the `home folder` for Dook.

4. From the home folder, open “Terminal” on macOS or “Command Prompt” in Windows, and type `java -jar Dook.jar` to run the application. <br>
   A GUI similar to the below should appear in a few seconds.

   ![Ui image](images/UserGuideApp.png)

<div markdown="block" class="alert alert-info">
:information_source: **Information**

If you are unfamiliar with the command terminal, follow this [guideline.](https://tutorials.codebar.io/command-line/introduction/tutorial.html)
</div>


5. Type a command in the command box and press Enter to execute it. <br>
   Some example commands you can try:

   * `help` : Opens the help window.

   * `list` : Lists all contacts.

   * `add -n Prof Damith -p 98765432 -e damithch@example.com -a COM1-02-57` : Adds a contact named `Prof Damith` to the Dook address book.

   * `book -n CS2103T Consultation -s 2024-04-21 14:00 -e 2024-04-21 16:00` : Adds a new booking for `CS2103T Consultation` from `2024-04-21 14:00` to `2024-04-21 16:00`.

   * `search CS2101 Consultation` : Finds the consultation(s) named `CS2101 Consultation` from the list.

   * `exit` : Exits the app.

6. Please refer to the [Features](#features) below for details of each command.

[Back To ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Familiarising yourself with Dook Interface
The main interface is divided into **two main panels** (Booking and Contact) and **4 small components** (listed below). There's a menu bar at the top with two buttons `File`
 and `Help`, and at the bottom there's a status bar displaying the location of the `addressbook.json` file.

<div markdown="block" class="alert alert-info">
:information_source: **Information**

A [JSON](#glossary) file is a text-based format for storing and exchanging data. JSON stands for JavaScript Object Notation.
</div>

![Main panel](images/MainPanel.png)

1. `Contact List`: Displays all contacts in the Dook address book.
2. `Booking List`: Shows all the bookings the user currently has.
3. `Result Box`: Displays the output after each command is executed.
4. `Command Box`: Allows users to enter commands.

### Contact Card
![Contact Card](images/ContactCard.png)

Each `Contact card` Includes the following information:
1. `Prof Damith Rajapakse` - Contact Name
2. `65164359` - Phone Number
3. `COM2-02-57` - Address
4. `damithch@comp.nus.edu.sg` - Email
5. `Professor` - Tag (it can be 0 or more)

<div markdown="block" class="alert alert-success">
:bulb: **Tips**

You can easily add a new contact using the [add](#adding-a-person-add) command or update the existing information with the [edit](#editing-a-person--edit) command.
</div>

### Booking Card
![Booking Card](images/BookingCard.png)

Each `Booking card` Includes the following information:
1. `CS2103T Consult` - Consultation Title
2. `2024-03-21 12:00` - Start Date and Time
3. `2024-03-21 14:00` - End Date and Time
4. `Attend as early as possible...` - Notes or Descriptions for the consultation

<div markdown="block" class="alert alert-success">
:bulb: **Tips**

You can easily update the following details with the [update](#editing-a-person--edit) command or [cancel](#cancelling-a-booking--cancel) any existing bookings immediately.
</div>

### Result Box

Displays two kinds of information/indication based on the command executed.

**Upon Success:**
![Result Box](images/ResultBox_Success.png)

**Upon Failure:**
![Result Box](images/ResultBox_Error.png)

### Help Window

Displays a window with `Command Box Panel` and `Take me to UG` link.
1. `Command Box Panel` - it consists of all the commands native to Dook.
2. `Take me to UG` - takes you directly to this User Guide for more details on each feature.

**Format:** `help`

**Instruction:**
1. Type `help` in the `Command Box` to open this window,
2. Or, press `Help` Button on the main interface.

It will open a window similar to this:

![help message](images/HelpWindow.png)

[Back To ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t friend`, `-t friend -t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE_NUMBER`, `-p PHONE_NUMBER -n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

[Back To ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Adding a contact: `add`

With every contact book, we need to be able to create contacts. You have just made a new friend in your tutorial and
decided to save his contact for future collaboration purposes.

This command adds a new person to the contact book!

Format: `add -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS -t TAG…​`

| Param            | Remarks                                                                                                        |
|------------------|----------------------------------------------------------------------------------------------------------------|
| **NAME**         | Must be non-null and unique                                                                                    |
| **PHONE_NUMBER** | Optional, only numbers and minimum 3 digits                                                                    |
| **EMAIL**        | Optional, follow standard email formats (i.e xxx@xxx)                                                          |
| **ADDRESS**      | Optional                                                                                                       |
| **TAG**          | Optional, each tag should start with -t (i.e. for two tags `friend`, `groupmate` use `-t friend -t groupmate`) |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add -n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01`
* `add -n Betsy Crowe -t friend -e betsycrowe@example.com -a Newgate Prison -p 1234567 -t criminal`

### Adding a Professor

You have just taken CS2040S and you now have a favorite Professor in SoC!

This command is specialized for adding a Professor to the contact book, allowing you to maintain their contact details at
your fingertips.

Format: `prof -n NAME` (Adds a professor individually)
Format: `prof -a` (Adds all staff in SoC to contact book)

| Param            | Remarks                                                                                                        |
|------------------|----------------------------------------------------------------------------------------------------------------|
| **NAME**         | Must be non-null and unique                                                                                    |

Examples:
* `prof -n seth`

<div markdown="block" class="alert alert-danger">
:exclamation: **Warnings** <br>
While adding all professors is included as an option, we generally advise adding professors individually unless you decide
having the whole SoC staff faculty in your contact book is necessary!
</div>

### Listing all contacts : `list`

You decided one day to touch base with all your course-mates and friends in NUS.

This command lets you easily view all the people in your contacts!

Format: `list`

### Editing a contacts : `edit`

You just had a catchup with an old friend and you realised he changed his primary contact number because he recently
migrated overseas.

The command allows you to update an existing contact's details in the contact book.

Format: `edit INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.

| Param            | Remarks                                                                                                        |
|------------------|----------------------------------------------------------------------------------------------------------------|
| **NAME**         | Must be non-null and unique                                                                                    |
| **PHONE_NUMBER** | Optional, only numbers and minimum 3 digits                                                                    |
| **EMAIL**        | Optional, follow standard email formats (i.e xxx@xxx)                                                          |
| **ADDRESS**      | Optional                                                                                                       |
| **TAG**          | Optional, each tag should start with -t (i.e. for two tags `friend`, `groupmate` use `-t friend -t groupmate`) |

Examples:
* `edit 1 -p 91234567 -e johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 -n Betsy Crower -t` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

<div markdown="block" class="alert alert-info">:information_source: **Information**
When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
</div>

<div markdown="span" class="alert alert-success">:bulb: **Tip:**
You can remove all the person’s tags by typing `-t` without specifying any tags after it.
</div>

### Locating contacts by name: `find`

Imagine you have just met a wonderful group of NUS students at a networking event, and you've added their details to the
contact book. A few days later, you want to send a follow-up email to one of them, but their name escapes you. You remember
it started with "John"

This command allows you to quickly retrieve all names that match, ensuring you can maintain that crucial connection without
a hitch.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

  ![result for 'find alex david'](images/findAlexDavidResult.png)

<div markdown="block" class="alert alert-info">:information_source: **Information**
This command will also fetch all existing Professors in the contact book if there is a match!
</div>

### Deleting a contact : `delete`

Your contact book is starting to look cluttered, and you realise you no longer certain contacts' details.

This command deletes the specified person from the contact book, ensuring your contacts remain relevant and up-to-date.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive number** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a booking : `book`

Adds the specified booking to the address book.

Format: `-n DESCRIPTION -s START_TIME -e END_TIME`

* Creates a booking with the specified parameters.
* Start and end time formats are in yyyy-mm-d hh:mm.

Examples:
* `book -n John's Birthday Party -s 2023-12-31 19:00 -e 2023-12-31 23:00`

### Cancelling a booking : `cancel`

Cancels the specified booking from the address book.

Format: `cancel INDEX`

* Deletes the booking at the specified `INDEX`.
* The index refers to the index number shown in the displayed booking list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `cancel 2` cancels the 2nd booking in the booking list.

### Searching for a booking : `search`

Searches for the specified booking from the address book.

Format: `search KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `john` will match `John`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John` will return `Johns Birthday Party`, `Dinner with John`

### Clearing all contact entries : `clear`

Clears all contact entries from the address book.

Format: `clear`

<div markdown="block" class="alert alert-danger">

**:exclamation: Destructive Command!**<br>

This command is **irreversible**, and all your data will be lost.
Please use this command with caution!

</div>


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I install Java 11?
**A**: Follow this [link](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) for steps to download Java 11.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Go to the application's home directory and copy the `data/addressbook.json` file containing your data into the empty
data folder created by Dook on the other computer.

**Q**: Oh no! I have accidentally closed Dook without using the `exit` command? Do I lose all my data?
**A**: Not to worry! Dook automatically saves all data after every change, so no data will be lost!

**Q**: Do I need Internet connection to use Dook?
**A**: Nope! Dook works fully offline and online!

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

<div markdown="block" class="alert alert-info">
:information_source: **Note:**

The list of valid commands accept **only lowercase letters**. For example, `Book` will not be accepted. Please use `book` instead.
</div>

### Address Book Command summary

| Action            | Format, Examples                                                                                                                                                                |
|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**           | `add -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS [-t TAG]…​` <br> e.g., `add -n John Doe -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -t friend t/colleague` |
| **Add Professor** | `prof -n NAME` or `prof -a`                                                                                                                                                     |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                             |
| **Edit**          | `edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…​`<br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`                                              |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                      |
| **List**          | `list`                                                                                                                                                                          |
| **Clear**         | `clear`                                                                                                                                                                         |
| **Help**          | `help`                                                                                                                                                                          |


### Booking List Command summary

| Action     | Format, Examples                                                                                                                     |
|------------|--------------------------------------------------------------------------------------------------------------------------------------|
| **Book**   | `book -n DESCRIPTION -s START_TIME -e END_TIME` <br> e.g., `book -n Consult with Prof Aaron -s 2024-03-01 19:00 -e 2024-03-01 23:00` |
| **View**   | `view`                                                                                                                               |
| **Update** | `update INDEX [-n DESCRIPTION] [-s START_TIME] [-e END_TIME]`<br> e.g.,`edit 2 -n Prof Aaron's Consultation -s 2024-03-01 14:30`     |
| **Search** | `search KEYWORD [MORE_KEYWORDS]`<br> e.g., `search Prof Lee's Consultation`                                                          |
| **Cancel** | `cancel INDEX` or `cancel -a`<br> e.g., `cancel 2`                                                                                   |


[Back To ToC](#table-of-contents)

## Glossary

| Term                                                                                                                                                                           | Meaning                                                                                                                                                                                                                                                                                      |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[CLI (Command Line Interface)](https://en.wikipedia.org/wiki/Command-line_interface)**                                                                                       | A text-based interface where users type in commands instead of interacting with the application's graphics.                                                                                                                                                                                  |
| **[CSV](https://en.wikipedia.org/wiki/Comma-separated_values)**                                                                                                                | A text file format that uses commas to separate values. It is supported by a wide range of software, including Microsoft Excel.                                                                                                                                                              |
| **[Command](https://en.wikipedia.org/wiki/Command_(computing))**                                                                                                               | A line of instructions that you input into the command box. Also see: Command Box                                                                                                                                                                                                            |
| **[Command Box](https://www.codecademy.com/articles/command-line-commands)** <a name="command-box"></a>                                                                        | A box for you to input commands.                                                                                                                                                                                                                                                             |
| **[Command Output Box](https://stackoverflow.com/questions/3038392/do-you-know-any-command-line-http-fetching-utility-like-wget)** <a name="command-output-box"></a>           | A box that displays the results of the command you keyed in. It will tell you whether the command you entered has successfully run, or if there is an error in your command that needs to be fixed. Refer to []() for a picture.                                                             |
| **[Contact List](https://support.microsoft.com/en-us/office/create-and-share-contact-groups-in-outlook-com-34a9c07e-9a4c-4ec4-a272-bc35a9c8c253)** <a name="contact-list"></a> | The Contact list is the list of contacts on the left side of the application.                                                                                                                                                                                                                |
| **[Booking List](https://www.smartsheet.com/free-excel-booking-templates)** <a name="booking-list"></a>                                                                        | The Booking list is the list of bookings on the right side of the application.                                                                                                                                                                                                               |
| **[GUI (Graphic User Interface)](https://en.wikipedia.org/wiki/Graphical_user_interface)**                                                                                     | A graphical based interface where users interact with the application's graphics like buttons or scroll panes. Also see: CLI                                                                                                                                                                 |
| **[JSON](https://www.json.org/json-en.html)**                                                                                                                                  | A JSON (JavaScript Object Notation) file is a lightweight data interchange format that's easy for humans to read and write and easy for machines to parse and generate. JSON files typically use the `.json` extension and consist of key/value pairs similar to JavaScript object literals. |
| **[Prefix](https://techterms.com/definition/prefix)**                                                                                                                          | A letter or phrase before an input.                                                                                                                                                                                                                                                          |
| **[Tag](https://en.wikipedia.org/wiki/Tag_(metadata))**                                                                                                                        | A text phrase used to categorize employees by. A tag must be either a Student, Professor, or Teaching Assistant (TA)                                                                                                                                                                         |
| **[Index](https://en.wikipedia.org/wiki/Index_(computer_science))**                                                                                                            | The number labeling each employee in the employee list.                                                                                                                                                                                                                                      |
| **[Web browser](https://www.mozilla.org/en-US/firefox/new/)**                                                                                                                  | An application to serve the web like Internet Explorer, Google Chrome, or Firefox. In fact, you are probably using one to access this guide right now!                                                                                                                                       |



[Back To ToC](#table-of-contents)
