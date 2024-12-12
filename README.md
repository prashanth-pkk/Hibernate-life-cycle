#Hibernate life cycle

It is a simple hibernate application to understand the life cycle of hibernate.
The Hibernate ORM will automatically create the Employee table, and you will see the output at each stage of the life cycle:
1. Transient state: Employee is created but not persisted.
2. Persistent state: Employee is saved to the database.
3. Detached state: Employee is fetched and session is closed.
4. Removed state: Employee is deleted from the database.

note - change the database name while testing the application
