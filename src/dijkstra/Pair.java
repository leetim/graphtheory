/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author kiniry
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> implements Comparable{

    public final A fst;
    public final B snd;

    public Pair(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public String toString() {
        return "Pair[" + fst + "," + snd + "]";
    }

    private static boolean equals(Object x, Object y) {
        return (x == null && y == null) || (x != null && x.equals(y));
    }

    public boolean equals(Object other) {
        return
            other instanceof Pair<?,?> &&
            equals(fst, ((Pair<?,?>)other).fst) &&
            equals(snd, ((Pair<?,?>)other).snd);
    }

    public int hashCode() {
        if (fst == null) return (snd == null) ? 0 : snd.hashCode() + 1;
        else if (snd == null) return fst.hashCode() + 2;
        else return fst.hashCode() * 17 + snd.hashCode();
    }

    public static <A,B> Pair<A,B> of(A a, B b) {
        return new Pair<A,B>(a,b);
    }

    @Override
    public int compareTo(Object o) {
        if (this.equals(o)){
            return 0;
        }
        Pair<A, B> other = (Pair<A, B>) o;
        Comparable lf = (Comparable) this.fst;
        Comparable rf = (Comparable) other.fst;
        Comparable ls = (Comparable) this.snd;
        Comparable rs = (Comparable) other.snd;
        if (lf.compareTo(rf) == 0){
            return ls.compareTo(rs);
        }
        if (lf.compareTo(rf) == 1){
            return 1;
        }
        else{
            return -1;
        }
    }
}