import java.util.Arrays;

public class NBody {
    public static double readRadius(String a) {
        In in = new In(a);
        int planetNumber = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Body[] readBodies(String a) {
        In in = new In(a);
        int planetNumber = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[planetNumber];
        for (int i = 0; i < planetNumber; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        }
        return bodies;
    }

    public static String background = "./images/starfield.jpg";

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.setScale(-radius, radius);

        StdDraw.picture(0, 0, background);
        for (Body a : bodies) {
            a.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);

            }
            if(time<=3*dt){
                System.out.println(Arrays.toString(yForces));
            }
            for(int i=0;i<bodies.length;i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Body a : bodies) {
                a.draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
            time+=dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Body body : bodies) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    body.xxPos, body.yyPos, body.xxVel,
                    body.yyVel, body.mass, body.imgFileName);
        }


    }


}
