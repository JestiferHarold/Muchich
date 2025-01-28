
print("P\tQ\tR\t(Q v r)\tP ^ (Q v R)\tP ^ Q\tP ^ R\t(P ^ Q) v (P ^ R)")

for p in [True, False]:
    for q in [True, False]:
        for r in [True,False]:

print("P\tQ\t~P\t~Q\t(P ^ Q)\t~(P ^ Q)\t~P v ~Q")

for p in [True, False]:
    for q in [True, False]:
        print(p , q , not p, not q, p and q, not ( p and q), not p or not q)
