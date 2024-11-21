# This is the raport for the Programming excercise of the search algorithms
## First: breadth first search
This algorithm simply looks at all tiles that are available and when finding the diamond stops and finds the way back.
|map    |time(ms)|iterations  |steps|
|-------|--------|-------|-----|
|300x300|207     |47222 |555|
|600x600|1111    |297802 |1246|     
|900x900|2164    |450369| 1844|

## Second: greedy search
This algorithm is almost the same as the last one but it has a heuristic and always moves first onto the tile that is closest to the end point that is provided to it.
The downside of this search algorithm is that the algorithm needs to know the end point before excecution.
|map    |time(ms)|iterations  |steps |
|-------|--------|-------|------|
|300x300|26     |3316  |983|
|600x600|45    |7024 |1904|
|900x900|192   |117960| 4122|   

## Third: a-star search
A* combines into itself Djikstras algorithm, which entails a constant monitoring of the distance of the start point from the agent and of the greedy search provided before. The algorithm now tries to guess the shortest distance to the target.
|map    |time(ms)|iterations  |steps|
|-------|--------|-------|-----|
|300x300|51     |8200  |555 | 
|600x600|424   |60452 | 1246 |
|900x900|725   |93988| 1844| 

# Verdict 
Something interesting happens with greedy search. I am not sure whether it happens because the ai is set up wrong or because of the map design, but the greedy bot clearly does not take the direct route to the diamond.
After taking a careful look at the map, yes there is an obstacle in the middle of the map, the algorithm works well.


