from collections import defaultdict
import csv

team_translate = {
    "University of Maryla": "University of Maryland",
    "University of North ": "University of North Carolina Charlotte",
    "University of South ": "University of South Carolina",
    "University of Georgi": "University of Georgia",
    "North Carolina State": "North Carolina State University",
    "East Carolina Univer": "East Carolina University",
    "Duke University     ": "Duke University"
}

f = open("results.txt")
data = defaultdict(dict)

for line in f.readlines():
    line = line.replace("\n", "")

    place  = line[0:3].strip()
    points = line[63:66].strip()
    time   = line[51:59].strip()
    name   = line[4:30].strip()
    team   = team_translate[line[30:50]]

    data[team][name] = {"place": place, "points": points, "time": time}

reader = csv.reader(open("nirca-missing.csv"))
output = open("nirca-complete.csv", "w")
output.write(",".join(next(reader, None)) + "\n") # Write header

for row in reader:
    PLACE = 0
    POINTS = 1
    LAST_NAME = 3
    FIRST_NAME = 4
    TEAM = 6
    TIME = 8

    name = row[FIRST_NAME] + " " + row[LAST_NAME]
    team = row[TEAM]
    try:
        result = data[team][name]
        row[PLACE] = result["place"]
        row[POINTS] = result["points"]
        row[TIME] = result["time"]

        output.write(",".join(row) + "\n")
    except KeyError: pass
