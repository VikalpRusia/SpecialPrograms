#!/bin/python3

# Complete the countTriplets function below.
def countTriplets(arr, r):
    count = 0

    f = {}
    for i in arr:
        val1 = i / r
        if val1 % 1 == 0:
            val2 = val1 / r
            if val2 % 1 == 0:
                if f.__contains__((val2, val1, i)):
                    f[(val2, val1, i)] += f[(val2,val1)]
                else:
                    if f.__contains__((val2, val1)):
                        f[(val2, val1, i)] = f[(val2, val1)]
            if f.__contains__((val1, i)):
                f[(val1, i)] += f[val1]
            else:
                if f.__contains__(val1):
                    f[(val1, i)] = f[val1]

        if f.__contains__(i):
            f[i] += 1
        else:
            f[i] = 1

    for j in f.keys():
        if type(j)== tuple and j.__len__() == 3:
            count += f[j]
    return count


if __name__ == '__main__':

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))
    ans = countTriplets(arr, r)
    print(ans)
