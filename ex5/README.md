# This is the predicate logic reasoning task, using a language similar to PROLOG
% facts
east(MORDOR, GONDOR).
north(ROHAN, GONDOR).
near(ROHAN, GONDOR).
near(ROHAN, LOTHLORIEN).
south(ROHAN, LOTHLORIEN).
near(MORIA, LOTHLORIEN).
east(LOTHLORIEN, MORIA).
near(MORIA, HIGHPASS).
south(MORIA, HIGHPASS).
near(RIVENDELL, HIGHPASS).
west(RIVENDELL, HIGHPASS).
near(RIVENDELL, BREE).
east(RIVENDELL, BREE).
east(BREE, SHIRE).
near(SHIRE, BREE).

% rules
% symmetry
north(X, Y) => south(Y, X).
south(X, Y) => north(Y, X).
east(X, Y) => west(Y, X).
west(X, Y) => east(Y, X).
near(X, Y) => near(Y, X).
% transitivity
(north(X, Y) & north(Y, Z)) => north(X, Z).
(south(X, Y) & south(Y, Z)) => south(X, Z).
(east(X, Y) & east(Y, Z)) => east(X, Z).
(west(X, Y) & west(Y, Z)) => west(X, Z).
(near(X, Y) & near(Y, Z)) => near(X, Z).

% tests
-east(MORDOR, SHIRE).

This unfortunately has conflicts in it that I could not solve. 
I will show symmetry and transistivity in smaller implementations.
### Symmetry
east(Gondor, Mordor).

east(X, Y) => west(Y, X).
west(X, Y) => east(Y, X).

% tests
-west(Mordor, Gondor)

### Transistivity
east(MORDOR, GONDOR).
east(GONDOR, SHIRE).

(east(X, Y) & east(Y, Z)) => east(X, Z).

% tests
-east(MORDOR, SHIRE).
