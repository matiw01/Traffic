import csv


def count_time(path):
    print("counting time")
    with open(path) as csv_file:
        reader = csv.reader(csv_file, delimiter=",")
        for row in reader:
            print(row[3])
