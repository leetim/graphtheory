#!/usr/bin/env python
import sys


g = open(sys.argv[1]).read().split("\n")
g = [i.split() for i in g]
nds = open(sys.argv[2]).read().split("\n")
nds = [n.split() for n in nds]

# print g
# print nds
print "digraph graph123{"
for n, x, y in nds[: len(nds)-1]:
    if n == "4":
        print "\t%s[pos=\"%d,%d!\", color=\"blue\"];"%(n, int(y), int(x))
    else:
        print "\t%s[pos=\"%d,%d!\"];"%(n, int(y), int(x))
for src, to, c in g[: len(g)-1]:
    if src == "2" and to == "3":
        print "\t%s->%s[label=\"%s\", color=\"red\"];"%(src, to, c)
    else:
        print "\t%s->%s[label=\"%s\"];"%(src, to, c)

print "}"
