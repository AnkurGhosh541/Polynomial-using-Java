/**
 * This class will create any arbitrary polynomial to work with. It uses linked link to 
 * store the terms of the polynomial.
 *  
 * @author Sunirmal Khatua
 *
 */
public class Polynomial {
    // ********** attributes ********************
    private Node head;
    private int degree;

    /*
     * Default constructor
     */
    public Polynomial() {
        head = null;
        degree = 0;
    }

    /*
     * Parameterized constructor
     */
    public Polynomial(double[] coeff) {
        for (int i = 0; i < coeff.length; i++) {
            if (coeff[i] != 0.0) {
                insertSorted(coeff[i], i);
                degree = Math.max(degree, i);
            }
        }
    }

    /*
     * This is a utility method that can be used to insert a term in sorted order in
     * the current polynomial
     */
    private void insertSorted(double coeff, int power) {
        if (head == null) {
            head = new Node(new Terms(coeff, power), null);
        } else {
            Node curr = head.next;
            Node prev = head;
            while (curr != null && curr.term.power < power) {
                prev = curr;
                curr = curr.next;
            }
            if (curr != null && curr.term.power == power)
                curr.term.coeff += coeff;
            else
                prev.next = new Node(new Terms(coeff, power), curr);
        }
    }

    /*
     * subtract a polynomial q from the given polynomial p i.e. p = p-q
     */
    public void subtract(Polynomial p) {
        degree = Math.max(degree, p.degree);
        head = diff(this, p).head;
    }

    /*
     * returns value of p(x)
     */
    public double evaluate(double x) {
        double result = 0.0;
        Node curr = head;
        while (curr != null) {
            result += curr.term.coeff * Math.pow(x, curr.term.power);
            curr = curr.next;
        }
        return result;
    }

    /*
     * add a polynomial q to the given polynomial p i.e. p = p+q
     */
    public void add(Polynomial p) {
        degree = Math.max(degree, p.degree);
        head = sum(this, p).head;
    }

    /*
     * multiply the given polynomial by a polynomial q i.e. p = p*q
     */
    public void multiply(Polynomial q) {
        degree = this.degree + q.degree;
        head = product(this, q).head;
    }

    /*
     * multiply the given polynomial by a constant a i.e p = a.p
     */
    public void scale(double a) {
        Node curr = head;
        while (curr != null) {
            curr.term.coeff *= a;
            curr = curr.next;
        }
    }

    /*
     * prints a polynomial object
     */
    public String toString() {
        Node curr = head;
        StringBuilder result = new StringBuilder();
        if (curr != null) {
            if (curr.term.power == 1)
                result.append(curr.term.coeff).append("x");
            else if (curr.term.power == 0)
                result.append(curr.term.coeff);
            else
                result.append(curr.term.coeff).append("x^").append(curr.term.power);
            curr = curr.next;
        }
        while (curr != null) {
            if (curr.term.power == 1)
                result.insert(0, curr.term.coeff + "x" + "+");
            else
                result.insert(0, curr.term.coeff + "x^" + curr.term.power + "+");
            curr = curr.next;
        }
        return result.toString();
    }

    /*
     * multiplies 2 polynomials and creates a new polynomial without destroying the
     * original i.e. r = p*q
     */
    public static Polynomial product(Polynomial p, Polynomial q) {
        Polynomial temp = new Polynomial();
        Node currP = p.head;
        while (currP != null) {
            Node currQ = q.head;
            while (currQ != null) {
                temp.insertSorted(currP.term.coeff * currQ.term.coeff,
                        currP.term.power + currQ.term.power);
                currQ = currQ.next;
            }
            currP = currP.next;
        }
        return temp;
    }

    /*
     * adds 2 polynomials and creates a new polynomial without destroying the
     * original i.e. r = p+q
     */
    public static Polynomial sum(Polynomial p, Polynomial q) {
        Polynomial temp = new Polynomial();
        Node currP = p.head;
        Node currQ = q.head;

        while (currP != null || currQ != null) {
            if (currP == null) {
                temp.insertSorted(currQ.term.coeff, currQ.term.power);
                currQ = currQ.next;
            } else if (currQ == null) {
                temp.insertSorted(currP.term.coeff, currP.term.power);
                currP = currP.next;
            } else {
                if (currP.term.power > currQ.term.power) {
                    temp.insertSorted(currP.term.coeff, currP.term.power);
                    currP = currP.next;
                } else if (currP.term.power < currQ.term.power) {
                    temp.insertSorted(currQ.term.coeff, currQ.term.power);
                    currQ = currQ.next;
                } else {
                    temp.insertSorted(currP.term.coeff + currQ.term.coeff, currP.term.power);
                    currP = currP.next;
                    currQ = currQ.next;
                }
            }
        }
        return temp;
    }

    /*
     * subtracts 2 polynomials and creates a new polynomial without destroying the
     * originals i.e. r = p-q
     */
    public static Polynomial diff(Polynomial p, Polynomial q) {
        Polynomial temp = new Polynomial();
        Node currP = p.head;
        Node currQ = q.head;

        while (currP != null || currQ != null) {
            if (currP == null) {
                temp.insertSorted(-currQ.term.coeff, currQ.term.power);
                currQ = currQ.next;
            } else if (currQ == null) {
                temp.insertSorted(currP.term.coeff, currP.term.power);
                currP = currP.next;
            } else {
                if (currP.term.power > currQ.term.power) {
                    temp.insertSorted(currP.term.coeff, currP.term.power);
                    currP = currP.next;
                } else if (currP.term.power < currQ.term.power) {
                    temp.insertSorted(-currQ.term.coeff, currQ.term.power);
                    currQ = currQ.next;
                } else {
                    temp.insertSorted(currP.term.coeff - currQ.term.coeff, currP.term.power);
                    currP = currP.next;
                    currQ = currQ.next;
                }
            }
        }
        return temp;
    }
}
