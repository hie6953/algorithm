N,S=map(int,input().split())
nums=list(map(int,input().split()))
T=[0]
for n in nums:
    A=T[:]
    for a in A:
        T.append(a+n)
c=T[1:].count(S)
print(c)