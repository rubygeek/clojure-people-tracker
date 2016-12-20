# people-tracker

A small app to keep track of people, last contact date and history

## Usage

Start a repl in the project and use these functions to interact with it, most functions can take an atom as a first param so we can pass in anatom for testing. Otherwise it uses the globally defined `people` atom.

(display-list) = list all the people you are traking

(add-person {:name "Bob" :any-other "data})

(remove-person "bob")

(update-person "bob" {:fields-to-add "data"}) 

### Future Ideas

(top3) - list the top three people to contact by order of last contacted

(update "name") - finds the name and then ask for an update and a date to remind you 
* add spec to validate data
* create generated tests with spec
* add more interactive prompts when using repl 
* add datomic to store data
* export to edn and save to file
* load from edn file
* create a web interface 







