#!/usr/bin/env python
import sys

if len(sys.argv) <= 1:
    print "Need input file"
    exit()
print sys.argv
g = open(sys.argv[1]).read().split("\n")
g = list(map(lambda x: x.split("\t"), g))
print g[0]
exit();

g = [(int(g[3*i]), int(g[3*i+1]), float(g[3*i+2])) for i in range(len(g)/3)]
print "graph graph123{"
for src, to, c in g:
    print "\t%d -- %d;"%(src, to)
print "}"
