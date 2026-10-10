
def findSlot(prices, newPrice):
    low = 0
    high = len(prices) - 1

    while low <= high:
        mid = (low + high) // 2

        if prices[mid] == newPrice:
            return mid
        elif prices[mid] < newPrice:
            low = mid + 1
        else:
            high = mid - 1

    return low


print(findSlot([120, 150, 200, 260], 150))
print(findSlot([120, 150, 200, 260], 210))
print(findSlot([120, 150, 200, 260], 300))
