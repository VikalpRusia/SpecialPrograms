#!/bin/python3
# https://www.hackerrank.com/challenges/special-palindrome-again/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=strings


# Complete the substrCount function below.
def substrCount(n, s):
    count = 0
    for i in range(0, n):
        value: int = 1
        character: str = s[max(i-1,0)]
        for j in range(1, min(i, n - 1 - i) + 1):
            if s[i - j] == character and s[i + j] == character:
                count += 1
            else:
                break
        for j in range(1, n-i):
            if s[i] == s[i + j]:
                value += 1
            else:
                break
        count += value // 2
    return count+s.__len__()


if __name__ == '__main__':
    n = int(input())

    s = input()

    result = substrCount(n, s)
    print(result)
