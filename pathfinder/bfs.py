import time
from queue import Queue, PriorityQueue

def bfs_search(map_data, start):
    # start can be a tuple (row, column)

    frontier = Queue()
    frontier.put(start)
    came_from = {}
    came_from[start] = None
    max_row = len(map_data) - 1
    max_col = len(map_data[0]) - 1
    path = None


    while not frontier.empty():
        current = frontier.get()
        print(current)

        # we don't care about all paths, so we should check if current is a diamond.
        # If it is, we store the coordinates of the diamond and stop the search
        # (implement this yourself)

        if current[0] < 0 or current[0] > max_row:
            continue

        if current[1] < 0 or current[1] > max_col:
            continue

        if map_data[current[0]][current[1] - 1] == "*":
            continue

        if map_data[current[0]][current[1]] == "D":
            print("FOUND")
            track = []
            point = current
            while point != start:
                track.append(point)
                point = came_from[point]
            track.append(point)
            path = track[::-1]
            break

        for neighbour in neighbors(current):  # lots of changes needed here

            if neighbour[0] < 0 or neighbour[0] > max_row:
                continue

            if neighbour[1] < 0 or neighbour[1] > max_col:
                continue

            if map_data[neighbour[0]][neighbour[1] - 1] == "*":
                continue

            if neighbour not in came_from:
                frontier.put(neighbour)
                came_from[neighbour] = current

    # Once we found the diamond, reconstruct the path
    # Find an appropriate data structure to represent the path, a list is a natural choice
    return path


def neighbors(node):
    up = (node[0], node[1] + 1)
    down = (node[0], node[1] - 1)
    right = (node[0] + 1, node[1])
    left = (node[0] - 1, node[1])
    return [up, down, right, left]


def greedy_search(map_data, start, goal):
    # start can be a tuple (row, column)

    frontier = PriorityQueue()
    frontier.put((0, start))

    came_from = {}
    came_from[start] = None
    max_row = len(map_data) - 1
    max_col = len(map_data[0]) - 1
    path = None


    while not frontier.empty():
        _, current = frontier.get()
        print(current)

        # we don't care about all paths, so we should check if current is a diamond.
        # If it is, we store the coordinates of the diamond and stop the search
        # (implement this yourself)

        if current[0] < 0 or current[0] > max_row:
            continue

        if current[1] < 0 or current[1] > max_col:
            continue

        if map_data[current[0]][current[1] - 1] == "*":
            continue

        if map_data[current[0]][current[1]] == "D":
            print("FOUND")
            track = []
            point = current
            while point != start:
                track.append(point)
                point = came_from[point]
            track.append(point)
            path = track[::-1]
            break

        for neighbour in neighbors(current):  # lots of changes needed here

            if neighbour[0] < 0 or neighbour[0] > max_row:
                continue

            if neighbour[1] < 0 or neighbour[1] > max_col:
                continue

            if map_data[neighbour[0]][neighbour[1] - 1] == "*":
                continue

            if neighbour not in came_from:
                priority = h(neighbour, goal)
                frontier.put((priority, neighbour))
                came_from[neighbour] = current
    # Once we found the diamond, reconstruct the path
    # Find an appropriate data structure to represent the path, a list is a natural choice
    return path

def astar_search(map_data, start, goal):
    # start can be a tuple (row, column)

    frontier = PriorityQueue()
    frontier.put((0, start))

    came_from = {}
    came_from[start] = None
    max_row = len(map_data) - 1
    max_col = len(map_data[0]) - 1
    path = None

    cost_so_far = {}
    cost_so_far[start] = 0


    while not frontier.empty():
        _, current = frontier.get()
        print(current)

        # we don't care about all paths, so we should check if current is a diamond.
        # If it is, we store the coordinates of the diamond and stop the search
        # (implement this yourself)

        if current[0] < 0 or current[0] > max_row:
            continue

        if current[1] < 0 or current[1] > max_col:
            continue

        if map_data[current[0]][current[1] - 1] == "*":
            continue

        if map_data[current[0]][current[1]] == "D":
            print("FOUND")
            track = []
            point = current
            while point != start:
                track.append(point)
                point = came_from[point]
            track.append(point)
            path = track[::-1]
            break

        for neighbour in neighbors(current):  # lots of changes needed here

            if neighbour[0] < 0 or neighbour[0] > max_row:
                continue

            if neighbour[1] < 0 or neighbour[1] > max_col:
                continue

            if map_data[neighbour[0]][neighbour[1] - 1] == "*":
                continue

            new_cost = cost_so_far[current] + 1
            if neighbour not in cost_so_far or new_cost < cost_so_far[neighbour]:
                cost_so_far[neighbour] = new_cost
                priority = new_cost + h(neighbour, goal)    # g(n) + h(n)
                frontier.put((priority, neighbour))
                came_from[neighbour] = current
    # Once we found the diamond, reconstruct the path
    # Find an appropriate data structure to represent the path, a list is a natural choice
    return path

def h(node, goal):
    return abs(node[0] - goal[0]) + abs(node[1] - goal[1])