# Demo


### What
This is a simple CRUD aimed at property tenants. It allows tenants to rate the properties they used to lease and leave additional feedback.

### Why
To prove that I am not a watermelon, but indeed have written some code in my life.

### How
Webapp based on spring boot, with spring mvc and hibernate.

### What's left to do?
Pretty much everything, this is a demo project. If I were to develop this application, obviously the first thing to do would be rethinking the model, possibly adding new fields and surely adding some user authentication.

### Why is it so incoherent?
Where possible I aimed to show some different approaches / design patterns, which may look odd in such a small project. Factory methods in the entities are a good example - they seem to make no sense, as the additional validation logic in the factory method is minimal.

### What do I not like?
The mapping logic in service layer. It should be done in the presentation layer, this was done for simplicity, to perform the mapping within session without worrying about initialization.

### Anything else?
Take a look at *\src\test\resources\coderslabdemo.postman_collection.json*.\
It contains a postman collection allowing to quickly test the application.\
Also packaging, but it's hard to think about good design with 10 classes..