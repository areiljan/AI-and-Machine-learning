
def forwardChainer(kb: list[tuple[tuple[str], str]], q: str):
    count_table = {}
    inferred_table = {}
    agenda_queue = []

    for element in kb:
        if len(element[0]) == 0:
            agenda_queue.append(element[1])
        count_table[element] = len(element[0])
        inferred_table[element[1]] = False

    while agenda_queue:
        p = agenda_queue.pop(0)
        if p == q:
            return True
        if not inferred_table[p]:
            inferred_table[p] = True
            for count_pair in count_table:
                if p in count_pair[0]: # if p is in the predicate elements
                    count_table[count_pair] -= 1
                    if count_table[count_pair] == 0:
                        agenda_queue.append(count_pair[1])

    return False

def main():
    kb = [
        (("P",), "Q"),
        (("L", "M"), "P"),
        (("B", "L"), "M"),
        (("A", "P"), "L"),
        (("A", "B"), "L"),
        ((), "A"),
        ((), "B")
    ]
    q = "Q"

    result = forwardChainer(kb, q)

    kb2 = [
        (("Spoiled", "Break"), "Smell"),
        (("Break", "Liquid"), "Mess"),
        (("Fragile", "Falls"), "Break"),
        ((), "Liquid"),
        ((), "Falls"),
        ((), "Fragile"),
        ((), "Egg")
    ]
    q = "Break"
    result = forwardChainer(kb2, q)


    if result:
        print(f"The query '{q}' is entailed by the knowledge base.")
    else:
        print(f"The query '{q}' is not entailed by the knowledge base.")
    q = "Mess"
    result = forwardChainer(kb2, q)


    if result:
        print(f"The query '{q}' is entailed by the knowledge base.")
    else:
        print(f"The query '{q}' is not entailed by the knowledge base.")
    q = "Smell"
    result = forwardChainer(kb2, q)

    if result:
        print(f"The query '{q}' is entailed by the knowledge base.")
    else:
        print(f"The query '{q}' is not entailed by the knowledge base.")

if __name__ == "__main__":
    main()







