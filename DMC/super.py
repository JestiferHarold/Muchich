print("P\t~P")
for p in [True, False]:
      print(p,not p)

def negation(p):
    return not p

print("P\t~P")
for p in [True, False]:
    print(p, negation(p))

def conjuntion(a,b):
    return a and b

print("P\tQ\tP\P & Q")
for i in [True, False]:
    for j in [True, False]:
        print(i, j, conjuntion(i,j))
