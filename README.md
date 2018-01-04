# ORM
Compassion of popular ORMs for Android


The program is dedicated to the comparing of currently popular ways to access database.
The following ways were analysed: GreenDao, DBFlow and classic way to access.

In order to compare them, a model with several children was created.
Parent model named "Kanji" has, aside from own description, children: list of readings and list of meanings.
Each of them is saved in own table.

Process of comparing consists of four steps.
First step is generation of N entities. Then inserting and loading is held.
Loading is conducted in two ways: shallow one, which only loads parent entity, without loading children, and the deep one, which loads all children of parent.
