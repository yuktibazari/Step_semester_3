
def mergeTokens(counterA, counterB):
    i = 0
    j = 0
    result = []

    while i < len(counterA) and j < len(counterB):
        if counterA[i] <= counterB[j]:
            result.append(counterA[i])
            i += 1
        else:
            result.append(counterB[j])
            j += 1

    while i < len(counterA):
        result.append(counterA[i])
        i += 1

    while j < len(counterB):
        result.append(counterB[j])
        j += 1

    return result


print(mergeTokens([3, 8, 15, 20], [5, 8, 12]))
print(mergeTokens([], [4, 9]))