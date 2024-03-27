import javax.swing.JOptionPane;

public class PolynomialTest {

	public static void main(String[] args) {
/*
		double[] coef1 = {2,3,0,5};
		double[] coef2 = {0,2,2,4};

		Polynomial p1 = new Polynomial(coef1);
		Polynomial p2 = new Polynomial(coef2);

		System.out.println("p1="+p1);
		System.out.println("p2="+p2);

		System.out.println("p1(1)="+p1.evaluate(1));
		System.out.println("p2(2)="+p2.evaluate(2));

		Polynomial p3 = Polynomial.sum(p1, p2);
		System.out.println("p1+p2="+p3);

		Polynomial p4 = Polynomial.diff(p1, p2);
		System.out.println("p1-p2="+p4);

		Polynomial p5 = Polynomial.product(p1, p2);
		System.out.println("p1 x p2="+p5);

		p1.scale(2);
		System.out.println("p1 after scaled by 2="+p1);
*/

        String coeff1Str = JOptionPane.showInputDialog("Enter the coefficients for polynomial 1\n separate values with commas(,)");
        String coeff2Str = JOptionPane.showInputDialog("Enter the coefficients for polynomial 2\n separate values with commas(,)");

        String[] coeff1 = coeff1Str.split(",");
        String[] coeff2 = coeff2Str.split(",");

        double[] c1 = new double[coeff1.length];
        double[] c2 = new double[coeff2.length];

        for (int i = 0; i < coeff1.length; i++) {
            c1[i] = Double.parseDouble(coeff1[i]);
        }
        for (int i = 0; i < coeff2.length; i++) {
            c2[i] = Double.parseDouble(coeff2[i]);
        }

        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);

        String[] options = {
                "Display",
                "Evaluate",
                "Add",
                "Subtract",
                "Multiply",
                "Scale"
        };

        Object option;
        do {
            option = JOptionPane.showInputDialog(null,
                    "Select the operation you want to perform\n",
                    "Polynomial Operations",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    "Display");

            switch (String.valueOf(option)) {
                case "Display":
                    JOptionPane.showMessageDialog(null, "p1 : " + p1 + "\n" + "p2 : " + p2 + "\n\n");
                    break;
                case "Evaluate":
                    double x = Double.parseDouble(JOptionPane.showInputDialog("Enter the value of x in p1(x):"));
                    double y = Double.parseDouble(JOptionPane.showInputDialog("Enter the value of y in p2(y):"));
                    JOptionPane.showMessageDialog(null, "p1(" + x + ") = " + p1.evaluate(x) + "\n" + "p2(" + y + ") = " + p2.evaluate(y) + "\n\n");
                    break;
                case "Add":
                    JOptionPane.showMessageDialog(null, "p1 + p2 = " + Polynomial.sum(p1, p2) + "\n\n");
                    break;
                case "Subtract":
                    JOptionPane.showMessageDialog(null, "p1 - p2 = " + Polynomial.diff(p1, p2) + "\n\n");
                    break;
                case "Multiply":
                    JOptionPane.showMessageDialog(null, "p1 x p2 = " + Polynomial.product(p1, p2) + "\n\n");
                    break;
                case "Scale":
                    x = Double.parseDouble(JOptionPane.showInputDialog("Scale p1 by :"));
                    p1.scale(x);
                    y = Double.parseDouble(JOptionPane.showInputDialog("Scale p2 by :"));
                    p2.scale(y);
                    JOptionPane.showMessageDialog(null, "p1 after scaled by " + x + " = " + p1 + "\n" + "p2 after scaled by " + y + " = " + p2 + "\n\n");
                    break;
            }
        } while (option != null);
	}

}

