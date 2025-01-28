def implies(p, q): return not p or q

def biconditional(p, q): return implies(p, q) ^ implies(q, p)

print("P\tQ\tR\tP <=> Q\tQ <=> R\t(P <=> Q) v (Q <=> R)")

for p in [True, False]:
    for q in [True, False]:
        for q in [True, False]:
            print(p, q, r, implies(p,q), implies(q,r), implies(p,q) or implies(q,r))

print("P\tQ\tR\tP <=> Q\tQ <=> R\t(P <=> Q) ^ (Q <=> R)")

for p in [True, False]:
    for q in [True, False]:
        for q in [True, False]:
            print(p, q, r, implies(p,q), implies(q,r), implies(p,q) and implies(q,r))

print("P\tQ\tR\tP <=> Q\tQ <=> R\tP <=> R\t(P <=> Q) ^ (Q <=> R)\t((P <=> Q) ^ (Q <=> R)) <=> P ")

for p in [True, False]:
    for q in [True, False]:
        for q in [True, False]:
            print(p, q, r, implies(p,q), implies(q,r),implies(p, r), implies(p,q) and implies(q,r), implies(implies(p,r),(implies(p,q) and implies(q,r)))


