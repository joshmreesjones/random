VALS_PER_LINE = 48

num_count = 0
line_count = 0

table1 = open('table1.txt', 'w')
table2 = open('table2.txt', 'w')

def add_number(num, output):
    global num_count, line_count

    if num_count == 0:
        output.write('vals%d dw %d' % (line_count, num))
    else:
        output.write(',%d' % num)

    num_count += 1

    if (num_count == VALS_PER_LINE):
        output.write(' ; Values for line %d\n' % line_count)
        num_count = 0
        line_count += 1

# Table 1
for i in range(128):
    for j in range(256):
        add_number(i * j, table1)

# Table 2
num_count = 0
line_count += 1
for i in range(128, 256):
    for j in range(256):
        add_number(i * j, table2)
