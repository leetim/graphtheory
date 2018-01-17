#!/usr/bin/env python
import sys


g = open(sys.argv[2]).read().split("\n")
g = list(filter(lambda x: len(x) != 0, [i.split() for i in g]))
nds = open(sys.argv[3]).read().split("\n")
nds = list(filter(lambda x: len(x) != 0, [n.split() for n in nds]))
res = open(sys.argv[4]).read().split()
v_flag = (sys.argv[1] == "-e")

if v_flag:
    res = (res[1], res[3].split(":")[0])
else:
    res = res[1]

print "digraph graph123{"
for n, x, y in nds:
    if not v_flag and n == res:
        print "\t%s[pos=\"%d,%d!\", color=\"blue\"];"%(n, int(y), int(x))
    else:
        print "\t%s[pos=\"%d,%d!\"];"%(n, int(y), int(x))
for src, to, c in g:
    if v_flag and src == res[0] and to == res[1]:
        print "\t%s->%s[label=\"%s\", color=\"red\"];"%(src, to, c)
    else:
        print "\t%s->%s[label=\"%s\"];"%(src, to, c)

print "}"
