
def findTopper(marks):
    bestIndex = 0
    bestTotal = -1

    for i in range(len(marks)):
        total = 0
        for j in range(len(marks[i])):
            total += marks[i][j]

        if total > bestTotal:
            bestTotal = total
            bestIndex = i

    return (bestIndex, bestTotal)


marks = [[78, 85, 90], [88, 92, 79], [65, 70, 95]]
print(findTopper(marks))