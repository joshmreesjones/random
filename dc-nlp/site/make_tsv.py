import os, os.path

for root, _unused, files in os.walk("raw-data"):
	for filename in files:
		raw_data = open("raw-data/" + filename, "r")
		for line in raw_data:
			line.replace(r"\r", "")
			print(repr(line))

#for every file in raw-data:
#	for every line in each file:
#		replace the space with a tab
#		append to tsv file
