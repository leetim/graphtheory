#!/usr/bin/env python
import sys


g = open(sys.argv[2]).read().split("\n")
g = [i.split() for i in g]
nds = open(sys.argv[3]).read().split("\n")
nds = [n.split() for n in nds]
res = open(sys.argv[4]).read().split()
v_flag = sys.argv[1] == "-e"

if v_flag:
    res = (res[2], res[4])
else:
    res = res[2]

print "digraph graph123{"
for n, x, y in nds[: len(nds)-1]:
    if not v_flag and n == res:
        print "\t%s[pos=\"%d,%d!\", color=\"blue\"];"%(n, int(y), int(x))
    else:
        print "\t%s[pos=\"%d,%d!\"];"%(n, int(y), int(x))
for src, to, c in g[: len(g)-1]:
    if v_flag and src == res[2] and to == res[4]:
        print "\t%s->%s[label=\"%s\", color=\"red\"];"%(src, to, c)
    else:
        print "\t%s->%s[label=\"%s\"];"%(src, to, c)

print "}"
