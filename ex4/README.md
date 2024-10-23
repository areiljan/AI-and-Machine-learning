# This is the raport for the Programming exercise of the forward chaining
This program lets the user insert Horn clauses and uses propositional logic to find if the query is entailed from these clauses.


### This is the egg problem
kb = [
    (("Spoiled", "Break"), "Smell"),
    (("Break", "Liquid"), "Mess"),
    (("Fragile", "Falls"), "Break"),
    ((), "Liquid"),
    ((), "Falls"),
    ((), "Fragile"),
    ((), "Egg")
]
q = "Break"
q = "Mess"
q = "Smell"

The query 'Break' is entailed by the knowledge base.
The query 'Mess' is entailed by the knowledge base.
The query 'Smell' is not entailed by the knowledge base.
