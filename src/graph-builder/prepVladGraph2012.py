#!/usr/bin/env python
import sys

if len(sys.argv) <= 1:
    print "Need input file"
    exit()
g = open(sys.argv[1]).read().split("\n")
g = list(map(lambda x: x.split("\t"), g))
g = [(int(i[2]), int(i[3]), float(i[4])) for i in g[1:len(g)-1]]
# print "graph graph123{"
# for i in g:
#     print i
for src, to, c in g:
    print "%d %d %f"%(src, to, c)
# print "}"
