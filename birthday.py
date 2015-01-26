c = "82759090997683929482787599"
print("".join([chr(int(c[i:i+2])+22) for i in range(0, 26, 2)]))
