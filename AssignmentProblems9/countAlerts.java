
def countAlerts(readings, k, threshold):
    windowSum = sum(readings[:k])
    count = 0

    if windowSum >= k * threshold:
        count += 1

    for i in range(k, len(readings)):
        windowSum += readings[i] - readings[i - k]

        if windowSum >= k * threshold:
            count += 1

    return count


print(countAlerts([2, 2, 2, 2, 5, 5, 5, 8], 3, 4))
