print("P\tQ\tP => Q")

for p in [True, False]:
    for q in [True, False]:
        print(p, q, not p or q)

print("P\tQ\tP <-> Q")
for p in [True, False]:
    for q in [True, False]:
        print(p , q, (not p or q) and (not q or p))

