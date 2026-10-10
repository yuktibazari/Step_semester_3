
def mostPopular(orders):
    count = {}

    for item in orders:
        count[item] = count.get(item, 0) + 1

    bestItem = orders[0]
    bestCount = count[bestItem]

    for item in orders:
        if count[item] > bestCount:
            bestItem = item
            bestCount = count[item]

    return (bestItem, bestCount)


print(mostPopular(["dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"]))
print(mostPopular(["tea", "coffee", "coffee", "tea"]))