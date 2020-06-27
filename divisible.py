#!/bin/python3

import math


# Complete the checkMagazine function below.
def equalise(threshold, d, ls):
    ls.sort()
    zeroPresent = ls.count(0)
    if ls[0] != 0:
        zeroPresent = 0
        ls.insert(0, 0)
    minimum = 9999
    p = 0
    if ls[1] == 1:

        possible = 0
        steps = 0
        if zeroPresent>=threshold:
            minimum=0
        else:
            for i in ls[zeroPresent+1:]:
                steps += int(math.log(i, d) + 1)
                possible += 1
                if possible == threshold - zeroPresent:
                    minimum = steps
                    break

        one_present = ls.count(1)
        p=one_present+zeroPresent+1
        if one_present>=threshold:
            minimum=0
        else:
            possible = 0
            steps = 0
            for y in range(p, ls.__len__()):
                try:
                    value = math.log(ls[y] // d, d)
                except ValueError:
                    continue
                if value % 1 == 0:
                    possible += 1
                    steps += value
                    if possible == threshold - one_present:
                        break
            if possible == threshold - one_present:
                steps+=possible
                if minimum>steps:
                    minimum = steps
    else:  # ls[0] == 0:
        p=zeroPresent+1
        possible = 0
        steps = 0
        if zeroPresent >= threshold:
            minimum = 0
        else:
            for i in ls[zeroPresent + 1:]:
                steps += int(math.log(i, d) + 1)
                possible += 1
                if possible == threshold - zeroPresent:
                    minimum = steps
                    break

    # elif ls[0] == 1:
    #     p = 1
    #     possible = 0
    #     steps = 0
    #     for i in ls:
    #         if possible == threshold - 1:
    #             minimum = steps
    #             break
    #         value = math.log(i, d)
    #         if value % 1 < 0.5:
    #             steps += int(value)
    for i in range(p, ls.__len__()):
        possible = 0
        steps = 0
        for y in range(i + 1, ls.__len__()):
            value = math.log(ls[y] // ls[i], d)
            if 0 < value and value % 1 == 0:
                possible += 1
                steps += value
            elif ls[y]==ls[i]:
                possible+=1
            if possible == threshold - 1:
                break
        if possible == threshold - 1 and minimum > steps:
            minimum = possible
            if minimum==0:
                break
    return minimum


if __name__ == '__main__':
    threshold, d = map(int, input().split())

    ls = list(map(int, input().split()))

    result = equalise(threshold, d, ls)

    print(result)
