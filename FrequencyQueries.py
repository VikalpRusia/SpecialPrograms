#!/bin/python3
# link to Que : https://www.hackerrank.com/challenges/frequency-queries/problem?h_r=profile

# Complete the freqQuery function below.
def freqQuery(queries):
    array = {}
    number = {}
    for operation, value in queries:
        if operation == "1":
            if array.__contains__(value):
                number[array[value]] -= 1
                array[value] += 1
                if number.__contains__(array[value]):
                    number[array[value]] += 1
                else:
                    number[array[value]] = 1
            else:
                array[value] = 1
                if number.__contains__(array[value]):
                    number[array[value]] += 1
                else:
                    number[array[value]] = 1
        elif operation == "2":
            if array.__contains__(value):
                if array[value] != 0:
                    number[array[value]] -= 1
                    array[value] -= 1
                    if number.__contains__(array[value]):
                        number[array[value]] += 1
                    else:
                        number[array[value]] = 1
        else:
            # result: bool = False
            # for i in array.values():
            #     if i == int(value):
            #         result = True
            #         break
            if number.__contains__(int(value)) and number[int(value)] > 0:
                print(1)
            else:
                print(0)
    return


if __name__ == '__main__':

    q = int(input().strip())

    queries = []

    for _ in range(q):
        queries.append(list(input().rstrip().split()))

    freqQuery(queries)

