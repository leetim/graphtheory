#!/usr/bin/env python

g = open("vlad-2009.dat").read().split()
g = [(int(g[3*i]), int(g[3*i+1]), float(g[3*i+2])) for i in range(len(g)/3)]
print "graph graph123{"
for src, to, c in g:
    print "\t%d -- %d;"%(src, to)
print "}"
